package utility;

import Main.LayoutParser;
import controllers.HomeController;
import controllers.OutsideTemperatureController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import models.Room;
import security.WindowObserver;

import java.io.IOException;
import java.util.ArrayList;


public class TemperatureManager implements WindowObserver {
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
        HomeController.windowWatcher.subscribe(TemperatureManager.getInstance());
    }

    // Method for changing the temperature of a single room in the house.
    // Variable targetTemperature will be the outside temperature of the house, usually.
    public static void changeTemperature(String roomName, double targetTemperature, String simulator) throws IOException {
        if (simulator.equals("Stop Simulator")) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    Room room = LayoutParser.grid.get(row).get(col);
                    if (roomName.equals(room.roomName)) {
                        // TODO: Math formula to adjust temperature of room towards target, gradually, every second.
                        // Timeline that runs in intervals of 1 second.
                        // It is used for a variety of things from updating fxml elements to checking for new values.
                        temperatureTimeline = new Timeline(
                                // Every second, check if the current room temp is smaller/greater than the target temp.
                                // Adjust upwards or downwards by 0.1 degrees Celsius every second until they're both equal.
                                new KeyFrame(Duration.seconds(1), e -> {
                                    System.out.println(room.getInitialTemp());
                                    if (targetTemperature > room.getInitialTemp()) {
                                        room.setInitialTemp(room.getInitialTemp() + 0.1);
                                    } else if (targetTemperature < room.getInitialTemp()) {
                                        room.setInitialTemp(room.getInitialTemp() - 0.1);
                                    }
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
        } else if (simulator.equals("Paused")) {
            // This doesn't work as intended...
            temperatureTimeline.pause();
        } else if (simulator.equals("Resume")) {
            // This doesn't work as intended...
            temperatureTimeline.play();
        } else if (simulator.equals("Start Simulator")) {
            // This doesn't work as intended...
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
    public static void changeZoneTemperature(String zoneName, double targetTemperature, String simulator) throws IOException {

        //Get an ArrayList containing all rooms in the specified zone.
        ArrayList<String> roomsInZone = ZoneManager.getRoomsInZone(zoneName);

        //Declare an ArrayList to temporarily store Room objects that are in the Zone.
        ArrayList<Room> matchingRooms = new ArrayList<Room>();

        //Iterate through rooms in the house.
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
        zoneTemperatureTimeline = new Timeline(
                //Check if the current room temp is smaller/greater than the target temp every second.
                //Adjust upwards or downwards by 0.1 degrees celsius every second until they're both equal.
                new KeyFrame(Duration.seconds(1), e -> {
                    for (Room matchingRoom : matchingRooms) {
                        if (targetTemperature > matchingRoom.getInitialTemp()) {
                            matchingRoom.setInitialTemp(matchingRoom.getInitialTemp() + 0.1);
                        } else if (targetTemperature < matchingRoom.getInitialTemp()) {
                            matchingRoom.setInitialTemp(matchingRoom.getInitialTemp() - 0.1);
                        }
                    }
                })
        );
    }

    @Override
    public void alarm(String status, String roomName, String simulator) throws IOException {
        //TODO: WHEN THE TEMPERATURE DROPS TO 0 DEGREES CELSIUS, TRIGGER ALARM
        TemperatureManager.changeTemperature(roomName, OutsideTemperatureController.outsideTemperature.getTemperature(), simulator);
    }
}
