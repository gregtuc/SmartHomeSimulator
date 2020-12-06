package controllers;

import Main.LayoutParser;
import Main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.ActiveUser;
import models.Location;
import models.Room;
import utility.AlertManager;
import utility.PeopleLocationManager;
import utility.PermissionChecker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Location controller.
 * The Location class contains methods related to Locations in the application. It controls the Location.fxml page.
 * The HomeController class can communicate with the LocationController class by creating an instance of LocationController and using the available public methods.
 * The static userLocation and peopleLocation attributes create static user and people location for the whole system, as there can only be one active User location and people location at a time.
 */

public class LocationController extends Label implements Initializable {

    public ComboBox<String> userLocationInput = new ComboBox<String>();
    public ComboBox<String> peopleLocationInput = new ComboBox<String>();

    static Location peopleLocation = new Location();
    ArrayList<ArrayList<Room>> roomGrid = LayoutParser.getGridRooms();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Populate userLocationInput ComboBox
        ArrayList<String> rooms = new ArrayList<String>();
        injectRooms(rooms, roomGrid);
        userLocationInput.getItems().addAll(rooms);
        userLocationInput.getItems().add("None");

        //Populate peopleLocationInput ComboBox
        peopleLocationInput.getItems().addAll(rooms);

    }

    static void injectRooms(ArrayList<String> rooms, ArrayList<ArrayList<Room>> roomGrid) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (!roomGrid.get(row).get(col).roomName.equals("Unnamed")) {
                    rooms.add(roomGrid.get(row).get(col).roomName);
                }
            }
        }
    }

    public void editUserLocation(MouseEvent mouseEvent) throws IOException {
        if (PermissionChecker.checkActiveUserIsLoggedIn()) {
            ActiveUser.setActiveUserLocation((String) userLocationInput.getValue());
            AlertManager.successfulPermissionsAlert();
        } else {
            AlertManager.badPermissionsAlert();
        }
        ActiveUser.setActiveUserLocation((String) userLocationInput.getValue());
        PeopleLocationManager.insertProfile(ActiveUser.getActiveUserLocation());
        Main.closeEditLocation();
    }

    public void editPeopleLocation(MouseEvent mouseEvent) throws IOException {
        if (PermissionChecker.checkActiveUserIsLoggedIn()) {
            peopleLocation.setLocation((String) peopleLocationInput.getValue());
            AlertManager.successfulPermissionsAlert();
        } else {
            AlertManager.badPermissionsAlert();
        }
        PeopleLocationManager.insertPerson(peopleLocation.getLocation());
        Main.closeEditLocation();
    }
}
