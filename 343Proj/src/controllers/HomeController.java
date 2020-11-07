package controllers;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.util.Duration;
import models.Room;
import Main.Main;
import Main.LayoutParser;
import models.ActiveUser;
import utility.*;

/**
 * The type Home controller.
 * The HomeController class contains general methods related to the broader application. It controls the Home.fxml page.
 * The HomeController class acts as a primary router and may call on other controller classes to complete actions when necessary.
 */
public class HomeController extends Label implements Initializable {
    Timeline timeline;

    ClockController clockController = new ClockController();
    OutsideTemperatureController OutsideTemperatureController = new OutsideTemperatureController();
    LocationController locationController = new LocationController();

    public Label timeLabel = new Label();
    public Label userLabel = new Label();
    public Label outsideTemperatureLabel = new Label();
    public Label locationLabel = new Label();
    public Label awayModeLabel = new Label();
    public Label simulationSpeedLabel = new Label();

    public Button startStopButton = new Button();
    public Button awayModeButton = new Button();

    public TextArea outputConsoleText = new TextArea();

    //Panes matching up with the house layout file.
    TextArea[][] panes = new TextArea[4][4];

    //TextArea FXML elements.
    /**
     * ListView to hold all items such as windows, doors and lights
     */
    public ListView<String> itemList = new ListView<>();
    public static String oldLocationLabel = "";
    public static int oldNumberOfProfiles = ProfileController.profileList.size();

    /**
     * ListView to hold all Rooms with either windows, doors or lights
     */
    public ListView<String> roomList = new ListView<>();
    /**
     * ArrayList to hold the grid from LayoutParser class
     */
    ArrayList<ArrayList<Room>> roomGrid = LayoutParser.getGridRooms();
    /**
     * TextArea FXML elements.
     */
    public TextArea sq22 = new TextArea();
    public TextArea sq23 = new TextArea();
    public TextArea sq24 = new TextArea();
    public TextArea sq25 = new TextArea();
    public TextArea sq26 = new TextArea();
    public TextArea sq32 = new TextArea();
    public TextArea sq33 = new TextArea();
    public TextArea sq34 = new TextArea();
    public TextArea sq35 = new TextArea();
    public TextArea sq36 = new TextArea();
    public TextArea sq42 = new TextArea();
    public TextArea sq43 = new TextArea();
    public TextArea sq44 = new TextArea();
    public TextArea sq45 = new TextArea();
    public TextArea sq46 = new TextArea();
    public TextArea sq52 = new TextArea();
    public TextArea sq53 = new TextArea();
    public TextArea sq54 = new TextArea();
    public TextArea sq55 = new TextArea();
    public TextArea sq56 = new TextArea();

    //Initialize runs immediately when the page loads.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Assign all the FXML Pane Elements to a 2D Array.
        //Col 0
        panes[0][0] = sq22;
        panes[0][1] = sq23;
        panes[0][2] = sq24;
        panes[0][3] = sq25;
        //Col 1
        panes[1][0] = sq32;
        panes[1][1] = sq33;
        panes[1][2] = sq34;
        panes[1][3] = sq35;
        //Col 2
        panes[2][0] = sq42;
        panes[2][1] = sq43;
        panes[2][2] = sq44;
        panes[2][3] = sq45;
        //Col 3
        panes[3][0] = sq52;
        panes[3][1] = sq53;
        panes[3][2] = sq54;
        panes[3][3] = sq55;

        /*
        Parse the house-layout file by calling the LayoutParser class object.
        2D Array panes is passed to the method as a parameter to be able to dynamically inject the room details into the
        appropriate TextAreas on the grid.
        */
        try {
            LayoutParser.parseLayout(panes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Timeline that runs in intervals of 1 second. It is used for a variety of things from updating fxml elements to checking for new values.
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    //Change current profile name if the name has been changed.
                    if(!ActiveUser.getActiveUsername().equals(userLabel.getText())){
                        if (ActiveUser.getActiveUsername().equals("")) {
                            userLabel.setText(ActiveUser.getActiveUsername());
                            //Logging.
                            try {
                                CommandLogger.logCommand("Parameter", "The active user has been logged out due to account deletion.", outputConsoleText);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        else {
                            userLabel.setText(ActiveUser.getActiveUsername());
                            //Logging.
                            try {
                                CommandLogger.logCommand("Parameter", "Account " + ActiveUser.getActiveUsername() + " has logged in.", outputConsoleText);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                    // If a user has been deleted, log it.
                    if(ProfileController.profileList.size() < oldNumberOfProfiles){
                        //Logging.
                        try {
                            if (ActiveUser.getActiveUsername().equals("")){
                                CommandLogger.logCommand("Dashboard", "A non-logged-in user has deleted an account", outputConsoleText);
                            }
                            else {
                                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername() + "has deleted an account.", outputConsoleText);
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        oldNumberOfProfiles = ProfileController.profileList.size();
                    }
                    //Change simulation speed if the name has been changed.
                    if(!clockController.getSimulationSpeed().equals(simulationSpeedLabel.getText())){
                    	simulationSpeedLabel.setText(clockController.getSimulationSpeed());
                        //Logging.
                        try {
                            CommandLogger.logCommand("Parameter", ActiveUser.getActiveUsername()+" has changed the simulation speed to "+ clockController.getSimulationSpeed(), outputConsoleText);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    //Change outside temperature if the name has been changed.
                    if(!OutsideTemperatureController.getOutsideTemperature().equals(outsideTemperatureLabel.getText())){
                        outsideTemperatureLabel.setText(OutsideTemperatureController.getOutsideTemperature());
                        //Logging.
                        try {
                            CommandLogger.logCommand("Parameter", ActiveUser.getActiveUsername()+" has changed the temperature to "+ OutsideTemperatureController.getOutsideTemperature(), outputConsoleText);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    //Change active user location if the name has been changed.
                    if(!ActiveUser.getActiveUserLocation().equals(locationLabel.getText())){
                        locationLabel.setText(ActiveUser.getActiveUserLocation());
                        LayoutParser.insertProfile(ActiveUser.getActiveUserLocation(), panes);
                        //Logging.
                        try {
                            CommandLogger.logCommand("Context", ActiveUser.getActiveUsername()+" has changed locations to "+ActiveUser.getActiveUserLocation(), outputConsoleText);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    //Change person location if the name has been changed.
                    if(!locationController.getPeopleLocation().equals("None")){
                        LayoutParser.insertPerson(locationController.getPeopleLocation(), panes);
                        //Logging.
                        try {
                            CommandLogger.logCommand("Context", ActiveUser.getActiveUsername()+" has inserted a person at "+locationController.getPeopleLocation(), outputConsoleText);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                })
        );
        //Run the timeline indefinitely or until paused/stopped manually.
        timeline.setCycleCount(Animation.INDEFINITE);
        //Play the timeline.
        timeline.play();
        loadItems();
    }

    /**
     * Handles the button for starting and stopping the simulator.
     * @param mouseEvent the mouse event
     */
    public void startStopSimulator(MouseEvent mouseEvent){
        //If the simulator is stopped, button click will start the simulator.
        if(startStopButton.getText().equals("Start Simulator")){
            startStopButton.setText("Stop Simulator");
            clockController.beginTime(timeLabel);
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has started the simulator.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //If the simulator is started, button click will stop the simulator.
        } else {
            startStopButton.setText("Start Simulator");
            clockController.stopTime(timeLabel);
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has stopped the simulator.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Handles the button for pausing the simulator. (currently only pauses the clock).
     *
     * @param mouseEvent the mouse event
     */
    public void pauseSimulation(MouseEvent mouseEvent){
        //Pause Time only if the simulation has started.
        if(!timeLabel.getText().equals("HH:MM:SS")) {
            clockController.pauseTime();
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has paused the simulator.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Handles the button for resuming the simulator. (currently only resumes the clock).
     *
     * @param mouseEvent the mouse event
     */
    public void resumeSimulation(MouseEvent mouseEvent){
        //Resume Time only if the simulation has started.
        if(!timeLabel.getText().equals("HH:MM:SS")){
            clockController.resumeTime();
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has resumed the simulator.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Opens the edit time window. Calls method showEditTime from class Main.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     */
    @FXML
    public void editTimeClicked(MouseEvent mouseEvent) throws IOException {
        //Logging.
        try {
            CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has pressed the edit time button.", outputConsoleText);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditTime();
    }
    
    /**
     * Opens the edit time window. Calls method showEditTime from class Main.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     */
    @FXML
    public void editOutsideTemperatureClicked(MouseEvent mouseEvent) throws IOException {
        //Logging.
        try {
            CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has pressed the edit outside temperature button.", outputConsoleText);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditOutsideTemperature();
    }
    
    /**
     * Opens the edit Location. Calls method showEditLocation from class Main.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     */
    @FXML
    public void editLocationClicked(MouseEvent mouseEvent) throws IOException {
        //Logging.
        try {
            CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has pressed the edit location button.", outputConsoleText);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditLocation();
    }

    @FXML
    public void openItemButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms()){
            switch (itemList.getSelectionModel().getSelectedItem()) {
                case "Windows":
                    WindowManager.unlockWindow(roomList.getSelectionModel().getSelectedItem(), panes);
                    break;
                case "Doors":
                    DoorManager.unlockDoor(roomList.getSelectionModel().getSelectedItem(), panes);
                    break;
                case "Lights":
                    LightManager.turnOnLight(roomList.getSelectionModel().getSelectedItem(), panes);
                    break;
            }
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" has set "+itemList.getSelectionModel().getSelectedItem()+" in "+roomList.getSelectionModel().getSelectedItem()+" to open/on.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            successfulPermissionsAlert();
        } else {
            badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" tried to set "+itemList.getSelectionModel().getSelectedItem()+" in "+roomList.getSelectionModel().getSelectedItem()+" to closed/off but was denied!", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @FXML
    public void closeItemButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms()){
            switch (itemList.getSelectionModel().getSelectedItem()) {
                case "Windows":
                    WindowManager.lockWindow(roomList.getSelectionModel().getSelectedItem(), panes);
                    break;
                case "Doors":
                    DoorManager.lockDoor(roomList.getSelectionModel().getSelectedItem(), panes);
                    break;
                case "Lights":
                    LightManager.turnOffLight(roomList.getSelectionModel().getSelectedItem(), panes);
                    break;
            }
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" has set "+itemList.getSelectionModel().getSelectedItem()+"in "+roomList.getSelectionModel().getSelectedItem()+" to closed/off.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            successfulPermissionsAlert();
        } else {
            badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" tried to set "+itemList.getSelectionModel().getSelectedItem()+" in "+roomList.getSelectionModel().getSelectedItem()+" to closed/off but was denied!", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @FXML
    public void awayModeButtonClicked(MouseEvent mouseEvent) throws IOException {
        //TODO: Make it so that this button can only be clicked when users are not at home.
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkSecurityPerms()){

            //If away mode is currently deactivated
            if(!ActiveUser.getActiveUserAwayMode()){
                //Change the status of away mode.
                ActiveUser.setActiveUserAwayMode();

                //Lock all windows and doors.
                WindowManager.lockAllWindows(panes);
                LightManager.turnOffAllLights(panes);

                //Change the label text.
                awayModeButton.setText("Activate");
                awayModeLabel.setText("Not Active");
                //Logging.
                try {
                    CommandLogger.logCommand("SHP", ActiveUser.getActiveUsername()+" deactivated away mode.", outputConsoleText);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                //If away mode is currently activated.
                ActiveUser.setActiveUserAwayMode();
                awayModeButton.setText("Deactivate");
                awayModeLabel.setText("Active");
                //Logging.
                try {
                    CommandLogger.logCommand("SHP", ActiveUser.getActiveUsername()+" activated away mode.", outputConsoleText);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            //Alert the user of the success.
            successfulPermissionsAlert();
        } else {
            //Alert the user of the failure.
            badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("SHP", ActiveUser.getActiveUsername()+" failed to edit away mode.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Opens the edit profile window. Calls method showEditProfile from class Main.
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    public void editProfileClicked(MouseEvent mouseEvent) throws Exception{
        //Logging.
        try {
            if (ActiveUser.getActiveUsername().equals("")){
                CommandLogger.logCommand("Dashboard", "A non-logged-in user pressed the edit profile button.", outputConsoleText);
            }
            else {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername() + " pressed the edit profile button.", outputConsoleText);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditProfile();
    }

    public void successfulPermissionsAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Success!",
                ButtonType.CLOSE);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void badPermissionsAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "You do not have permission to execute this command.",
                ButtonType.CLOSE);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
    }

    /**
     * Loads the Items into the ItemList ListView
     */
    public void loadItems() {
    	itemList.getItems().add("Lights");
    	itemList.getItems().add("Doors");
    	itemList.getItems().add("Windows");
    }
    /**
     * The function Displays the Rooms with either lights, Doors or windows in them.
     * @param mouseEvent
     * @throws IOException
     */
    public void DisplayRooms (MouseEvent mouseEvent) throws IOException {
    	roomList.getItems().clear();
        String item = itemList.getSelectionModel().getSelectedItem();
        ArrayList<String> rooms = new ArrayList<String>();
        rooms.removeAll(rooms);
        //RoomsList.getItems().add(item);
        if(item.equals("Windows")) {
        	for (int row = 0; row < 4; row++) {
        		for (int col = 0; col < 4; col++) {
        			if (roomGrid.get(row).get(col).getWindowExists() == true && roomGrid.get(row).get(col) != null) {
        				rooms.add(roomGrid.get(row).get(col).roomName);
        			}
        		}
        	}
        }
        else if(item.equals("Doors")) {
        	for (int row = 0; row < 4; row++) {
        		for (int col = 0; col < 4; col++) {
        			if (!roomGrid.get(row).get(col).roomName.equals("Unnamed")) {
        				rooms.add(roomGrid.get(row).get(col).roomName);
        			}
        		}
        	}
        	
        }
        else if(item.equals("Lights")) {
        	for (int row = 0; row < 4; row++) {
        		for (int col = 0; col < 4; col++) {
        			if (!roomGrid.get(row).get(col).roomName.equals("Unnamed")) {
        				rooms.add(roomGrid.get(row).get(col).roomName);
        			}
        		}
        	}
        }
        roomList.getItems().addAll(rooms);
    }

    public TextArea getHomeTextArea() {
        return outputConsoleText;
    }
}
