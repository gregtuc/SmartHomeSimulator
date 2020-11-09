package utility;

import Main.LayoutParser;
import controllers.HomeController;
import models.ActiveUser;
import models.Location;
import models.Room;

import java.io.IOException;

public class PeopleLocationManager{
    //Instantiating the alarm system to monitor the location of individuals.


    //Method for modifying the visual representation of the user profile.
    public static void insertProfile(String location) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Revert the old room back to normal.
                if(ActiveUser.getOldProfileLocation().equals(room.roomName)){
                    room.setActiveProfileIsHere(false);
                    //Change the text on the house representation.
                    UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                            +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                }
                //Update the room with the active users presence.
                if(location.equals(room.roomName)){
                    room.activeProfileIsHere = true;
                    CommandLogger.logCommand("SHC","Active User has moved to "+room.roomName+".");
                    //Change the text on the house representation.
                    UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                            +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                }

            }
        }
    }

    //Method for modifying the visual representation of the location of person objects.
    public static void insertPerson(String location) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(location.equals(room.roomName)){
                    //Insert a person object.
                    CommandLogger.logCommand("SHC","Person added to "+room.roomName);
                    if(ActiveUser.getActiveUserAwayMode()){
                        HomeController.alarmSystem.triggerAlarm();
                    }
                    //Change the text on the house representation.
                    UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                            +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()+" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());
                    break;
                }
            }
        }
    }
}
