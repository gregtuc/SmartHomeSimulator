package utility;

import Main.LayoutParser;
import controllers.HomeController;
import controllers.OutsideTemperatureController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import models.ActiveUser;
import models.Month;
import models.OutsideTemperature;
import models.Room;
import models.Zone;
import security.TemperatureObserver;

import java.io.IOException;
import java.util.ArrayList;


public class TemperatureManager implements TemperatureObserver {
    private static volatile TemperatureManager instance = null;

    public static TemperatureManager getInstance() {
        if (instance == null) {
            synchronized (TemperatureManager.class) {
                if (instance == null) {
                    instance = new TemperatureManager();
                }
            }
        }
        return instance;
    }

    private TemperatureManager() {
    }

    //Creating a timeline object to loop in intervals.
    public static Timeline temperatureTimeline;

    public static void initialize() {
        HomeController.temperatureWatcher.subscribe(TemperatureManager.getInstance());
    }

    public static void checkFreezingTemperature(Room room) {
        if (room.getInitialTemp() <= 0.0) {
            AlertManager.freezingTemperatureAlert(room.getRoomName());
        } else {
            AlertManager.numberOfAlertSent = 0;
        }
    }

  //checks if room temperature is hotter than outside during summer, if it is, open window to cool it. Away mode has to be off 
    public static void checkRoomSummerTemp(Room room) {
    	if ( ActiveUser.getActiveUserAwayMode()==false ) {
    		if(Month.getCurrentSeason().equals("Summer")){
    			if(room.getInitialTemp()> OutsideTemperature.getTemperature()
                        && room.numberOfAutoWindowOpenAlertSent == 0 ) {
   		 			room.setWindowStatus(true);
   		 			AlertManager.AutoWindowOpen(room.getRoomName());
   		 			room.numberOfAutoWindowOpenAlertSent++;
   		 		}
   		 	}
    	}
    }
    // Method for changing the temperature of a single room in the house.
    // Variable targetTemperature will be the outside temperature of the house, usually.
    public static void changeTemperature(String status, String roomName, double targetTemperature, String simulator) throws IOException {
        if (simulator.equals("Stop Simulator") && status.equals("open")) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    Room room = LayoutParser.grid.get(row).get(col);
                    room.setManualOverrideActivated(true);
                    if (roomName.equals(room.roomName)) {
                        temperatureTimeline = new Timeline(
                                // Every second, check if the current room temp is smaller/greater than the target temp.
                                // Adjust upwards or downwards by 0.1 degrees Celsius every second until they're both equal.
                                new KeyFrame(Duration.seconds(1 / (UniversalElements.getClock().getSpeed())), e -> {

                                    //Condition to break the animation once the target temperature has been reached.
                                    if (room.getInitialTemp() == targetTemperature) {
                                        temperatureTimeline.stop();
                                    }
                                    if (targetTemperature > room.getInitialTemp()) {
                                        //room.setInitialTemp(room.getInitialTemp() + 0.1);
                                        String formattedIncrementedTemperature = String.format("%.2f", (room.getInitialTemp() + 0.1));
                                        room.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
                                    } else if (targetTemperature < room.getInitialTemp()) {
                                        String formattedIncrementedTemperature = String.format("%.2f", (room.getInitialTemp() - 0.1));
                                        room.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
                                    }
                                    // This Check if roomTemperture drop below 0 degree celcius and sents a warning to the user about it.
                                    if(room.getInitialTemp() <= 0) {
                                        checkFreezingTemperature(room);
                                    }
                                    //Checks if room temperature is hotter than outside during summer, if it is, open window automatically to cool it.
                                    checkRoomSummerTemp(room);
                                })
                        );
                        //Run the timeline indefinitely or until paused/stopped manually.
                        temperatureTimeline.setCycleCount(Animation.INDEFINITE);
                        //Play the timeline.
                        temperatureTimeline.play();
                        break;
                    }
                }
            }
        } else if (simulator.equals("Paused") || status.contentEquals("close")) {
            temperatureTimeline.pause();
        } else if (simulator.equals("Resume")) {
            temperatureTimeline.play();
        } else if (simulator.equals("Start Simulator")) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    Room room = LayoutParser.grid.get(row).get(col);
                    if (roomName.equals(room.roomName)) {
                        room.setInitialTemp(23.5);
                    }
                }
            }
            temperatureTimeline.stop();
        }
    }

    //Creating a timeline object to loop in intervals for zones.
    public static Timeline zoneTemperatureTimeline;

    // Method for changing the temperature of a zone in the house.
    public static void changeZoneTemperature(String zoneName, int period, String simulator) throws IOException {

        //Stop existing zone temperature timelines.
        if (zoneTemperatureTimeline != null) {
            zoneTemperatureTimeline.stop();
        }

        //Creating an instance of the zone and initializing the target temperature double.
        Zone zone = ZoneManager.getZone(zoneName);
        double targetTemperature;

        //Setting the target temperature to the relevant period based on the current clock time.
        switch (period) {
            case 1 -> {
                assert zone != null;
                targetTemperature = zone.getFirstPeriodTemp();
            }
            case 2 -> {
                assert zone != null;
                targetTemperature = zone.getSecondPeriodTemp();
            }
            case 3 -> {
                assert zone != null;
                targetTemperature = zone.getThirdPeriodTemp();
            }
            default -> targetTemperature = OutsideTemperature.getTemperature();
        }

        //ArrayList roomsInZone for all rooms in the zone, and ArrayList matchingRooms for temporary storage.
        ArrayList<String> roomsInZone = ZoneManager.getRoomsInZone(zoneName);
        ArrayList<Room> matchingRooms = new ArrayList<Room>();

        //Iterate through rooms in the house and add matching zone rooms to matchingRooms.
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                //Declare the current room in the iteration.
                Room room = LayoutParser.grid.get(row).get(col);

                //Find matching rooms and store them in a local ArrayList.
                if (roomsInZone.contains(room.roomName)) {
                    matchingRooms.add(room);
                }
            }
        }
        //Begin a new Timeline that will modify the temperature of the room every second according to the model given in the instructions.
        //Run the timeline indefinitely or until paused/stopped manually.
        //Play the timeline.
        //Delete all non-default zones.
        switch (simulator) {
            case "Stop Simulator" -> {
                zoneTemperatureTimeline = new Timeline(
                        //Check if the current room temp is smaller/greater than the target temp every second.
                        //Adjust upwards or downwards by 0.1 degrees celsius every second until they're both equal.
                        new KeyFrame(Duration.seconds(1 / (UniversalElements.getClock().getSpeed())), e -> {
                            for (Room matchingRoom : matchingRooms) {
                                // If the target temperature hasn't been reached, change temp by 0.1 every second until reached.
                                if (!matchingRoom.targetTempReached) {
                                    if(!matchingRoom.getManualOverrideActivated()){
                                        if (targetTemperature > matchingRoom.getInitialTemp()) {
                                            String formattedIncrementedTemperature = String.format("%.2f", (matchingRoom.getInitialTemp() + 0.1));
                                            matchingRoom.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
                                        } else if (targetTemperature < matchingRoom.getInitialTemp()) {
                                            String formattedIncrementedTemperature = String.format("%.2f", (matchingRoom.getInitialTemp() - 0.1));
                                            matchingRoom.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
                                        } else if (targetTemperature == matchingRoom.getInitialTemp()) {
                                            matchingRoom.targetTempReached = true;
                                        }
                                        // This Check if roomTemperture drop below 0 degree celcius and sents a warning to the user about it.
                                        if(matchingRoom.getInitialTemp() <= 0) {                            
                                        	checkFreezingTemperature(matchingRoom);
                                        }
                                    }
                                }
                                // If the target temp has been reached, allow for gradual decay of current temp by 0.05 until threshold, then toggle targetTempReached.
                                else {
                                    // If the outside of the house is hotter than the inside of the room:
                                    if (((OutsideTemperature.getTemperature() - matchingRoom.getInitialTemp())) > 0) {
                                        String formattedIncrementedTemperature = String.format("%.2f", (matchingRoom.getInitialTemp() + 0.05));
                                        matchingRoom.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
                                    }
                                    // If the outside of the house is colder than the inside of the room:
                                    else if (((OutsideTemperature.getTemperature() - matchingRoom.getInitialTemp())) < 0) {
                                        String formattedIncrementedTemperature = String.format("%.2f", (matchingRoom.getInitialTemp() - 0.05));
                                        matchingRoom.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
                                    }
                                    if ((Math.abs(targetTemperature - matchingRoom.getInitialTemp()) >= 0.3)) {
                                        matchingRoom.targetTempReached = false;
                                    }
                                }
                            }
                        })
                );
                zoneTemperatureTimeline.setCycleCount(Animation.INDEFINITE);
                zoneTemperatureTimeline.play();
            }
            case "Paused" -> zoneTemperatureTimeline.pause();
            case "Resume" -> zoneTemperatureTimeline.play();
            case "Start Simulator" -> {
                for (int i = 0; i < ZoneManager.getZones().size(); i++) {
                    if (!ZoneManager.getZones().get(i).getZoneName().equals("Default")) {
                        //Remove all zones.
                        //ZoneManager.deleteZone(ZoneManager.getZones().get(i).getZoneName());
                        //Set all rooms to outside temperature.
                        setRoomsToOutsideTemperature();
                    }
                }
                //Stop existing zone temperature timelines.
                if (zoneTemperatureTimeline != null) {
                    zoneTemperatureTimeline.stop();
                }
            }
        }
    }

    public static void setRoomsToOutsideTemperature() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (!LayoutParser.grid.get(row).get(col).roomName.equals("Unnamed") && LayoutParser.grid.get(row).get(col) != null) {
                    LayoutParser.grid.get(row).get(col).setInitialTemp(OutsideTemperature.getTemperature());
                }
            }
        }
    }

    @Override
    // The alarm should take 3 parameters.
    // TYPE: This is the equivalent of an ENUM by which we are separating the type of alarm to send.
    // This can be either "ROOM" or "ZONE", as the behaviour should change depending on whether multiple rooms are involved.
    // NAME: The String value of the room/zone we are passing.
    // SIMULATOR: The status (running, stopped, or paused) of the simulator.
    public void alarm(String type, String name, int period, String simulator) throws IOException {
        // TODO: When a zone is modified (rooms changed, zone deleted), trigger the alarm.
        if (type.equals("ROOM")) {
            TemperatureManager.changeTemperature(type, name, OutsideTemperatureController.outsideTemperature.getTemperature(), simulator);
        } else if (type.equals("ZONE")) {
            TemperatureManager.changeZoneTemperature(name, period, simulator);
        }
    }
}
