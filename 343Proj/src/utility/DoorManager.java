package utility;

import Main.LayoutParser;
import javafx.scene.control.TextArea;
import models.Room;

public class DoorManager {
    //Method for unlocking all doors in the house.
    public static void unlockAllDoors(TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(room.door){
                    room.doorLocked = false;
                }
            }
        }
    }

    //Method for locking all doors in the house.
    public static void lockAllDoors(TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(room.door){
                    room.doorLocked = true;
                }
            }
        }
    }

    //Method for unlocking a single door in the house.
    public static void unlockWindow(String location, TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(location.equals(room.roomName)){
                    if(room.door){
                        room.doorLocked = false;
                    }
                }
            }
        }
    }

    //Method for locking a single window in the house.
    public static void lockWindow(String location, TextArea[][] panes){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(room.window){
                    room.windowLocked = true;
                }
            }
        }
    }
}
