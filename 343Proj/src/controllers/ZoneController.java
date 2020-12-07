package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import models.Zone;
import security.TemperatureWatcher;
import utility.UniversalElements;
import utility.ZoneManager;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ZoneController implements Initializable {

    public TextField periodOneTemp = new TextField();
    public TextField periodTwoTemp = new TextField();
    public TextField periodThreeTemp = new TextField();
    public Label zoneNameText = new Label();
    public Label periodOneTempCurrent = new Label();
    public Label periodTwoTempCurrent = new Label();
    public Label periodThreeTempCurrent = new Label();
    public ListView<String> roomsInZoneList = new ListView<String>();
    public ListView<String> zonesInHomeList = new ListView<String>();
    public static TemperatureWatcher temperatureWatcher = new TemperatureWatcher();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zoneNameText.setText(UniversalElements.getSelectedZone());
        updateZonePeriodTemperatures();
        populateZoneControllerLists();
    }

    public void populateZoneControllerLists() {
        // Populating the list of rooms in the selected zone:
        roomsInZoneList.getItems().clear();
        roomsInZoneList.getItems().addAll(ZoneManager.getRoomsInZone(UniversalElements.getSelectedZone()));
        // Populating the list of other zones in the home:
        ArrayList<Zone> zones = ZoneManager.getZones();
        zonesInHomeList.getItems().clear();
        for (Zone zone : zones) { // Make sure the selected zone is not included in the list of other zones.
            if (!zone.getZoneName().equals(UniversalElements.getSelectedZone())) {
                zonesInHomeList.getItems().add(zone.getZoneName());
            }
        }
    }

    private void updateZonePeriodTemperatures() {
        periodOneTempCurrent.setText(String.format("%.2f", Objects.requireNonNull(ZoneManager.getZone(UniversalElements.getSelectedZone())).getFirstPeriodTemp()));
        periodTwoTempCurrent.setText(String.format("%.2f", Objects.requireNonNull(ZoneManager.getZone(UniversalElements.getSelectedZone())).getSecondPeriodTemp()));
        periodThreeTempCurrent.setText(String.format("%.2f", Objects.requireNonNull(ZoneManager.getZone(UniversalElements.getSelectedZone())).getThirdPeriodTemp()));
    }

    @FXML
    public void setPeriods(MouseEvent mouseEvent) throws IOException {
        ZoneManager.setZoneTemperatures(
                UniversalElements.getSelectedZone(),
                Double.parseDouble(periodOneTemp.getText()),
                Double.parseDouble(periodTwoTemp.getText()),
                Double.parseDouble(periodThreeTemp.getText()));
        updateZonePeriodTemperatures();
        // TODO: Trigger the temperature change alarm (which should check if the period temp is different than the current room temp)
    }

    @FXML
    public void transferRoom(MouseEvent mouseEvent) throws IOException {
        if (zonesInHomeList.getSelectionModel().getSelectedItem() != null && roomsInZoneList.getSelectionModel().getSelectedItem() != null) {
            ZoneManager.transferRoomBetweenZones(
                    UniversalElements.getSelectedZone(), // The zone which is being modified.
                    zonesInHomeList.getSelectionModel().getSelectedItem(), // The zone which the room will be transferred to.
                    roomsInZoneList.getSelectionModel().getSelectedItem()); // The room which will be transferred.
            if (!HomeController.timeline.currentRateProperty().toString().equals("0")) {
                temperatureWatcher.triggerAlarm("ZONE",
                        zonesInHomeList.getSelectionModel().getSelectedItem(), // The zone which the room will be transferred to.
                        Objects.requireNonNull(ZoneManager.getZone(zonesInHomeList.getSelectionModel().getSelectedItem())).getCurrentPeriod(),
                        "Stop Simulator");
            }
            roomsInZoneList.getItems().clear();
            roomsInZoneList.getItems().addAll(ZoneManager.getRoomsInZone(UniversalElements.getSelectedZone()));
        }
    }

    @FXML
    public void deleteZone(MouseEvent mouseEvent) throws IOException {
        ZoneManager.deleteZone(UniversalElements.getSelectedZone());
        Main.closeEditZoneTemp();
    }

    @FXML
    public void returnToHome(MouseEvent mouseEvent) throws IOException {
        Main.closeEditZoneTemp();
    }

}
