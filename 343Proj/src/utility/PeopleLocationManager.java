package utility;

import Main.LayoutParser;
import controllers.HomeController;
import models.ActiveUser;
import models.Room;

import java.io.IOException;

public class PeopleLocationManager {
    //Instantiating the alarm system to monitor the location of individuals.


    //Method for modifying the visual representation of the user profile.
    public static void insertProfile(String location) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                //Revert the old room back to normal.
                if (ActiveUser.getOldProfileLocation().equals(room.roomName)) {
                    room.setActiveProfileIsHere(false);
                }
                //Update the room with the active users presence.
                if (location.equals(room.roomName)) {
                    room.activeProfileIsHere = true;
                    CommandLogger.logCommand("SHC", "Active User has moved to " + room.roomName + ".");
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
                if (location.equals(room.roomName)) {
                    //Insert a person object.
                    if (ActiveUser.getActiveUserAwayMode()) {
                        HomeController.alarmSystem.triggerAlarm();
                    }
                    room.setPersonIsHere(true);
                    CommandLogger.logCommand("SHC", "Person added to " + room.roomName);
                    break;
                }
            }
        }
    }
}
