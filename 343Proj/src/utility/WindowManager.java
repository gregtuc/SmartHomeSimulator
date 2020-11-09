package utility;

import Main.LayoutParser;
import controllers.HomeController;
import models.Room;
import security.Observer;

import java.io.IOException;

public class WindowManager implements Observer {
    private static volatile WindowManager instance = null;
    public static WindowManager getInstance() {
        if (instance == null) {
            synchronized(WindowManager.class) {
                if (instance == null) {
                    instance = new WindowManager();
                }
            }
        }
        return instance;
    }
    private WindowManager() {}
    public static void initialize(){
        HomeController.alarmSystem.subscribe(WindowManager.getInstance());
    }
    //Method for unlocking all windows in the house.
    public static void unlockAllWindows() throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(room.getWindowExists()){
                    room.setWindowStatus(true);
                    //Change the text on the house representation.
                    UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                            +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                }
            }
        }
        CommandLogger.logCommand("SHC","All windows in the house unlocked.");
    }

    //Method for locking all windows in the house.
    public static void lockAllWindows() throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(room.getWindowExists()){
                    room.setWindowStatus(false);
                    //Change the text on the house representation.
                    UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                            +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                }
            }
        }
        CommandLogger.logCommand("SHC","All windows in the house locked.");
    }

    //Method for unlocking a single window in the house.
    public static void unlockWindow(String location) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(location.equals(room.roomName)){
                    if(room.getWindowExists()){
                        room.setWindowStatus(true);
                        CommandLogger.logCommand("SHC","Window unlocked in "+room.roomName);
                        //Change the text on the house representation.
                        UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                                +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                    }
                    break;
                }
            }
        }
    }

    //Method for locking a single window in the house.
    public static void lockWindow(String location) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(location.equals(room.roomName)){
                    if(room.getWindowExists()){
                        room.setWindowStatus(false);
                        CommandLogger.logCommand("SHC","Window unlocked in "+room.roomName);
                        //Change the text on the house representation.
                        UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                                +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void alarm() throws IOException {
        WindowManager.lockAllWindows();
    }
}
