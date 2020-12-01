package utility;

import Main.LayoutParser;
import controllers.HomeController;
import models.ActiveUser;
import models.Room;
import security.Observer;

import java.io.IOException;

public class DoorManager implements Observer {
    private static volatile DoorManager instance = null;
    private DoorManager() {}

    static Boolean lockdownMode = false;

    public static void turnOnLockdownMode(){
        lockdownMode = true;
    }
    public static void turnOffLockdownMode(){
        lockdownMode = false;
    }
    public static Boolean checkLockdownMode() throws IOException {
        if(lockdownMode){
            CommandLogger.logCommand("SHP", "Door manager was accessed but access is not permitted during lockdown mode!");
            return true;
        } else {
            return false;
        }
    }

    //Only a singular instantiation of this class will be allowed.
    //This method will return that instance.
    public static DoorManager getInstance() {
        if (instance == null) {
            synchronized(DoorManager.class) {
                if (instance == null) {
                    instance = new DoorManager();
                }
            }
        }
        return instance;
    }

    public static void initialize(){
        HomeController.alarmSystem.subscribe(DoorManager.getInstance());
    }
    //Method for unlocking all doors in the house.
    public static void unlockAllDoors() throws IOException {
        if(!checkLockdownMode()){
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    Room room = LayoutParser.grid.get(row).get(col);
                    if (room.graphNumber == 0)
                        continue;
                    room.setDoorStatus(true);
                    //Change the text on the house representation.
                    UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                            +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                }
            }
            CommandLogger.logCommand("SHC","All doors in the house unlocked.");
        }
    }

    //Method for locking all doors in the house.
    public static void lockAllDoors() throws IOException {
        if(!checkLockdownMode()){
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    Room room = LayoutParser.grid.get(row).get(col);
                    if (room.graphNumber == 0)
                        continue;
                    room.setDoorStatus(false);
                    //Change the text on the house representation.
                    UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                            +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                }
            }
            CommandLogger.logCommand("SHC","All doors locked in the house");
        }
    }

    //Method for unlocking a single door in the house.
    public static void unlockDoor(String location) throws IOException {
        if(!checkLockdownMode()){
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    Room room = LayoutParser.grid.get(row).get(col);
                    if (room.graphNumber == 0)
                        continue;
                    if(location.equals(room.roomName)){
                        room.setDoorStatus(true);
                        CommandLogger.logCommand("SHC","Door unlocked in "+room.roomName);
                        //Change the text on the house representation.
                        UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                                +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                        AlertManager.successfulPermissionsAlert();
                        break;
                    }
                }
            }
        } else {
            AlertManager.badPermissionsAlert();
        }
    }

    //Method for locking a single door in the house.
    public static void lockDoor(String location) throws IOException {
        if(!checkLockdownMode()){
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    Room room = LayoutParser.grid.get(row).get(col);
                    if (room.graphNumber == 0)
                        continue;
                    if(location.equals(room.roomName)){
                        room.setDoorStatus(false);
                        //Change the text on the house representation.
                        CommandLogger.logCommand("SHC","Door locked in "+room.roomName);
                        UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                                +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                        AlertManager.successfulPermissionsAlert();
                        break;
                    }
                }
            }
        } else {
            AlertManager.badPermissionsAlert();
        }
    }

    @Override
    public void alarm() throws IOException {
        DoorManager.lockAllDoors();
    }
}
