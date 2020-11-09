package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Room;
import utility.LightManager;
import Main.LayoutParser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LightConfigurerController implements Initializable {
    public TextField hourInput2 = new TextField();
    public TextField minuteInput2 = new TextField();
    public TextField secondInput2 = new TextField();
    public TextField hourInput1 = new TextField();
    public TextField minuteInput1 = new TextField();
    public TextField secondInput1 = new TextField();
    public ListView<String> locationList = new ListView<>();

    ArrayList<ArrayList<Room>> roomGrid = LayoutParser.getGridRooms();

    //Populate the locationList ListView element when the window loads.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allRooms();
    }

    @FXML
    public void configureTime(MouseEvent mouseEvent) throws IOException {
        LightManager.configureLights(locationList.getSelectionModel().getSelectedItem(),
                String.format("%02d", Integer.parseInt(String.valueOf(hourInput2.getText()))) + " : " + String.format("%02d", Integer.parseInt(minuteInput2.getText())) + " : " + String.format("%02d", Integer.parseInt(String.valueOf(secondInput2.getText()))),
                String.format("%02d", Integer.parseInt(String.valueOf(hourInput1.getText()))) + " : " + String.format("%02d", Integer.parseInt(minuteInput1.getText())) + " : " + String.format("%02d", Integer.parseInt(String.valueOf(secondInput1.getText()))));
        //Call closeEditTime from Main and return to the primary stage.
        Main.closeConfigureTime();
    }

    //Method to populate the room selection box.
    public void allRooms() {
        ArrayList<String> rooms = new ArrayList<String>();
        rooms.removeAll(rooms);
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (!roomGrid.get(row).get(col).roomName.equals("Unnamed") && roomGrid.get(row).get(col) != null) {
                    rooms.add(roomGrid.get(row).get(col).roomName);
                }
            }
        }
        locationList.getItems().addAll(rooms);
    }
}
