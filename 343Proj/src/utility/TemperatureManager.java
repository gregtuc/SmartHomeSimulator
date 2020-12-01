package utility;

import Main.LayoutParser;
import controllers.HomeController;
import models.Room;
import security.Observer;

import java.io.IOException;

public class TemperatureManager implements Observer {
    private static volatile TemperatureManager instance = null;
    public static TemperatureManager getInstance() {
        if (instance == null) {
            synchronized(TemperatureManager.class) {
                if (instance == null) {
                    instance = new TemperatureManager();
                }
            }
        }
        return instance;
    }
    private TemperatureManager() {}
    public static void initialize(){
        HomeController.alarmSystem.subscribe(TemperatureManager.getInstance());
    }

    //Method for changing the temperature of a single room in the house.
    public static void changeTemperature(String location, double newTemperature) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(location.equals(room.roomName)){
                    room.setInitialTemp(newTemperature);

                        room.setWindowStatus(true);
                        CommandLogger.logCommand("SHH","Temperature set to "+newTemperature+" degrees Celsius in "+room.roomName);
                        /*TODO: This section will be included in the refactoring of the code.
                        * This means that the data concerning the rooms (such as windows being open/closed, the temperature, etc.)
                        * can no longer be held as text inside the box on in the layout representation. Having to re-write a big
                        * block of code every time a single piece of data is changed is suicide, and needs to be refactored.
                        */
                        //Change the text on the house representation.
                        UniversalElements.getPanes()[col][row].setText("Room #: "+room.graphNumber+"\nRoom name: "+room.roomName
                                +"\nDoor: "+room.getDoorExists()+" Door Open: "+room.door.getDoorIsOpen()+"\nWindow: "+room.getWindowExists()
                                +" Window Open: "+room.window.getWindowIsOpen()+"\nActive User: "+room.getActiveProfileIsHere()+"\nPerson Object: "+room.getPersonIsHere());

                    break;
                }
            }
        }
    }

    @Override
    public void alarm() throws IOException {
        //TODO: WHEN THE TEMPERATURE DROPS TO 0 DEGREES CELSIUS, TRIGGER ALARM
    }
}
