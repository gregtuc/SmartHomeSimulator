package utility;

import Main.LayoutParser;
import controllers.HomeController;
import controllers.OutsideTemperatureController;
import controllers.ProfileController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import models.ActiveUser;
import models.Room;
import security.Observer;
import security.WindowObserver;
import javafx.animation.Timeline;

import java.io.IOException;


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
                                    if (targetTemperature > room.getInitialTemp()) {
                                        //room.setInitialTemp(room.getInitialTemp() + 0.1);
                                        String formattedIncrementedTemperature = String.format("%.1f", (room.getInitialTemp()+0.1));
                                        room.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
                                    } else if (targetTemperature < room.getInitialTemp()) {
                                        String formattedIncrementedTemperature = String.format("%.1f", (room.getInitialTemp()-0.1));
                                        room.setInitialTemp((Double.parseDouble(formattedIncrementedTemperature)));
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
        }
        else if (simulator.equals("Paused")){
            // This doesn't work as intended...
            temperatureTimeline.pause();
        }
        else if (simulator.equals("Resume")){
            // This doesn't work as intended...
            temperatureTimeline.play();
        }
        else if (simulator.equals("Start Simulator")){
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

    @Override
    public void alarm(String status, String roomName, String simulator) throws IOException {
        //TODO: WHEN THE TEMPERATURE DROPS TO 0 DEGREES CELSIUS, TRIGGER ALARM
        TemperatureManager.changeTemperature(roomName, OutsideTemperatureController.outsideTemperature.getTemperature(), simulator);
    }
}
