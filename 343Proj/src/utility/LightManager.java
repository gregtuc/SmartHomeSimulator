package utility;

import Main.LayoutParser;
import controllers.HomeController;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import models.Room;
import security.Observer;

import java.io.IOException;

public class LightManager implements Observer {
    private static volatile LightManager instance = null;

    //Only a singular instantiation of this class will be allowed.
    //This method will return that instance.
    public static LightManager getInstance() {
        if (instance == null) {
            synchronized (LightManager.class) {
                if (instance == null) {
                    instance = new LightManager();
                }
            }
        }
        return instance;
    }

    private LightManager() {
    }

    public static void initialize() {
        HomeController.alarmSystem.subscribe(LightManager.getInstance());
    }

    //Method for turning on all lights.
    public static void turnOnAllLights() throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights on in the room.
                room.lights = true;
                //Change the color on the house representation.
                Region content = (Region) UniversalElements.getPanes()[col][row].lookup(".content");
                content.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        CommandLogger.logCommand("SHC", "All lights turned on in the house");
    }

    //Method for turning on a specific light.
    public static void turnOnLight(String location) throws IOException {
        AlertManager.successfulPermissionsAlert();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights on in the room.
                if (location.equals(room.roomName)) {
                    CommandLogger.logCommand("SHC", "Light turned on in " + room.roomName);
                    room.lights = true;
                    //Change the color on the house representation.
                    Region content = (Region) UniversalElements.getPanes()[col][row].lookup(".content");
                    content.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    //Method for turning off all lights.
    public static void turnOffAllLights() throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights on in the room.
                room.lights = false;
                //Change the color on the house representation.
                Region content = (Region) UniversalElements.getPanes()[col][row].lookup(".content");
                content.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        CommandLogger.logCommand("SHC", "All lights turned off in the house");
    }

    //Method for turning off a specific light.
    public static void turnOffLight(String location) throws IOException {
        AlertManager.successfulPermissionsAlert();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights off in the room.
                if (location.equals(room.roomName)) {
                    CommandLogger.logCommand("SHC", "Light turned off in " + room.roomName);
                    room.lights = false;
                    //Change the color on the house representation.
                    Region content = (Region) UniversalElements.getPanes()[col][row].lookup(".content");
                    content.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    //Method for modifying when lights turn on or off for a specific room. Only activates during away mode.
    public static void configureLights(String location, String lightsOnTime, String lightsOffTime) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Change the light setting attributes for the room object.
                if (location.equals(room.roomName)) {
                    CommandLogger.logCommand("SHC", "Lights configured. Lights on Time for " + room.roomName + " is " + lightsOnTime + " and lights off time is " + lightsOffTime + ".");
                    //Change the color on the house representation.
                    room.setLightsOffTime(lightsOffTime);
                    room.setLightsOnTime(lightsOnTime);
                }
            }
        }
    }

    @Override
    public void alarm() throws IOException {
        LightManager.turnOnAllLights();
    }
}
