package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import utility.UniversalElements;
import utility.ZoneManager;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ZoneController implements Initializable {

    public TextField periodOneTemp = new TextField();
    public TextField periodTwoTemp = new TextField();
    public TextField periodThreeTemp = new TextField();
    public Label zoneNameText = new Label();
    public ListView<String> roomsInZoneList = new ListView<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zoneNameText.setText(UniversalElements.getSelectedZone());
        roomsInZoneList.getItems().addAll(ZoneManager.getRoomsInZone(UniversalElements.getSelectedZone()));
    }
    @FXML
    public void setPeriods(MouseEvent mouseEvent) throws IOException {
        ZoneManager.addPeriodsToZone(UniversalElements.getSelectedZone(), Double.parseDouble(periodOneTemp.getText()), Double.parseDouble(periodTwoTemp.getText()), Double.parseDouble(periodThreeTemp.getText()));
    }
    @FXML
    public void removeRoom(MouseEvent mouseEvent) throws IOException {
        ZoneManager.removeRoomFromZone(UniversalElements.getSelectedZone(), roomsInZoneList.getSelectionModel().getSelectedItem());
        roomsInZoneList.getItems().clear();
        roomsInZoneList.getItems().addAll(ZoneManager.getRoomsInZone(UniversalElements.getSelectedZone()));
    }

    @FXML
    public void returnToHome(MouseEvent mouseEvent) throws IOException {
        Main.closeEditZoneTemp();
    }

}
