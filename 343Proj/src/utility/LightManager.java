package utility;

import Main.LayoutParser;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.Room;

public class LightManager {
    //Method for turning on all lights.
    public static void turnOnAllLights(TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights on in the room.
                room.lights = true;
                //Change the color on the house representation.
                Pane test = (Pane)panes[col][row].getParent();
                test.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    //Method for turning on a specific light.
    public static void turnOnLight(String location,  TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights on in the room.
                if(location.equals(room.roomName)){
                    room.lights = true;
                    //Change the color on the house representation.
                    Pane test = (Pane)panes[col][row].getParent();
                    test.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    //Method for turning off all lights.
    public static void turnOffAllLights(TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights on in the room.
                room.lights = false;
                //Change the color on the house representation.
                Pane test = (Pane)panes[col][row].getParent();
                test.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    //Method for turning off a specific light.
    public static void turnOffLight(String location,  TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Turn the lights off in the room.
                if(location.equals(room.roomName)){
                    room.lights = false;
                    //Change the color on the house representation.
                    Pane test = (Pane)panes[col][row].getParent();
                    test.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    //Method for modifying when lights turn on or off for a specific room. Only activates during away mode.
    public static void configureLights(String location, int lightsOffTime, int lightsOnTime){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Change the light setting attributes for the room object.
                if(location.equals(room.roomName)){
                    //Change the color on the house representation.
                    room.lightsOffTime = lightsOffTime;
                    room.lightsOnTime = lightsOnTime;
                }
            }
        }
    }
}
