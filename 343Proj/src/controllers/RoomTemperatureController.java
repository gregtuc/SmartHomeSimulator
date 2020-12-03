package controllers;

import Main.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.ActiveUser;
import models.OutsideTemperature;
import models.Room;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import utility.AlertManager;
import utility.CommandLogger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type OutsideTemperature controller.
 * The OutsideTemperature class contains methods related to OutsideTemperature in the application. It controls the OutsideTemperature.fxml page.
 * The HomeController class can communicate with the OutsideTemperatureController class by creating an instance of OutsideTemperatureController and using the available public methods.
 * The static OutsideTemperature attribute creates a singular static OutsideTemperature for the whole system, as there can only be one active OutsideTemperature at a time.
 */
public class RoomTemperatureController extends Label implements Initializable{
    /**
     * Creating an instance of the OutsideTemperature class. There exists a single constant OutsideTemperature, so it must remain static.
     */
    static OutsideTemperature outsideTemperature = new OutsideTemperature();
    /**
     * The OutsideTemperature TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public TextField roomTemperatureInput = new TextField();
    public Room selectedRoom = new Room();
    Double oldTemperature = selectedRoom.getInitialTemp();
    /**
     * Change the static outside temperature attribute according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     **/
    @Override
    public void initialize(URL location, ResourceBundle resources){
        selectedRoom = Main.roomSelectedFromSHH;
    }

    public void editRoomTemperature(MouseEvent mouseEvent) throws IOException {
        //Set the temperature of the room to the inputted value.
        selectedRoom.setInitialTemp(Double.parseDouble(roomTemperatureInput.getText()));
        //Call closeEditTemperature from Main and return to the primary stage.
        AlertManager.successfulPermissionsAlert();
        //Logging.
        try {
            CommandLogger.logCommand("SHH", ActiveUser.getActiveUsername()+" has changed the temperature of the "
                    +selectedRoom.getRoomName()+" from " + oldTemperature + "°C to " + selectedRoom.getInitialTemp() + "°C.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.closeEditRoomTemperature();
    }
}
