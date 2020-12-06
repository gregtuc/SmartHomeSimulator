package controllers;

import Main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.ActiveUser;
import models.OutsideTemperature;
import models.Room;
import utility.AlertManager;
import utility.CommandLogger;
import utility.TemperatureManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RoomTemperatureController extends Label implements Initializable {
    static OutsideTemperature outsideTemperature = new OutsideTemperature();
    public TextField roomTemperatureInput = new TextField();
    public Room selectedRoom = new Room();
    Double oldTemperature = selectedRoom.getInitialTemp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedRoom = Main.roomSelectedFromSHH;
    }

    public void editRoomTemperature(MouseEvent mouseEvent) throws IOException {
        //Set the temperature of the room to the inputted value.
        TemperatureManager.changeTemperature("open", selectedRoom.getRoomName(), Double.parseDouble(roomTemperatureInput.getText()), "Stop Simulator");

        //Call closeEditTemperature from Main and return to the primary stage.
        AlertManager.successfulPermissionsAlert();

        //Logging.
        try {
            CommandLogger.logCommand("SHH", ActiveUser.getActiveUsername() + " has changed the temperature of the "
                    + selectedRoom.getRoomName() + " from " + oldTemperature + "°C to " + selectedRoom.getInitialTemp() + "°C.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.closeEditRoomTemperature();
    }
}
