package controllers;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.WithNode;
import models.Room;
import Main.Main;
import Main.LayoutParser;
import models.ActiveUser;
import security.AlarmSystem;
import security.WindowWatcher;
import utility.*;

/**
 * The type Home controller.
 * The HomeController class contains general methods related to the broader application. It controls the Home.fxml page.
 * The HomeController class acts as a primary router and may call on other controller classes to complete actions when necessary.
 */
public class HomeController extends Label implements Initializable {
    //Instantiating the alarm system for the home.
    public static AlarmSystem alarmSystem = new AlarmSystem();
    public static WindowWatcher windowWatcher = new WindowWatcher();

    //Creating a timeline object to loop in intervals.
    Timeline timeline;

    //Instantiating controller classes.
    ClockController clockController = new ClockController();
    OutsideTemperatureController OutsideTemperatureController = new OutsideTemperatureController();
    LocationController locationController = new LocationController();

    //Labels found on home.fxml.
    public Label timeLabel = new Label();
    public Label userLabel = new Label();
    public Label outsideTemperatureLabel = new Label();
    public Label locationLabel = new Label();
    public Label awayModeLabel = new Label();
    public Label simulationSpeedLabel = new Label();

    //Buttons found on home.fxml.
    public Button startStopButton = new Button();
    public Button awayModeButton = new Button();

    //TextArea found on home.fxml.
    public TextArea outputConsoleText = new TextArea();

    //SHC Tab FXML elements and variables.
    public ListView<String> itemList = new ListView<>();
    public ListView<String> roomList = new ListView<>();
    public ListView<String> allRoomList = new ListView<>();
    public ListView<String> temperatureRoomList = new ListView<>();
    ArrayList<ArrayList<Room>> roomGrid = LayoutParser.getGridRooms();

    // Old Profiles.
    public static int oldNumberOfProfiles = ProfileController.profileList.size();
    // Old simulator start/stop button label:
    public String oldSimulatorButton = startStopButton.getText();

    //Panes matching up with the house layout file.
    TextArea[][] panes = new TextArea[4][4];
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
        panes[0][0] = sq22;
        panes[0][1] = sq23;
        panes[0][2] = sq24;
        panes[0][3] = sq25;
        panes[1][0] = sq32;
        panes[1][1] = sq33;
        panes[1][2] = sq34;
        panes[1][3] = sq35;
        panes[2][0] = sq42;
        panes[2][1] = sq43;
        panes[2][2] = sq44;
        panes[2][3] = sq45;
        panes[3][0] = sq52;
        panes[3][1] = sq53;
        panes[3][2] = sq54;
        panes[3][3] = sq55;

        //Parse the Layout.
        try {
            LayoutParser.parseLayout(panes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initializing user elements to make them accessible from anywhere.
        UniversalElements.setUserLabel(userLabel);
        UniversalElements.setLocationLabel(locationLabel);
        UniversalElements.setOutsideTemperatureLabel(outsideTemperatureLabel);
        UniversalElements.setTimeLabel(timeLabel);
        UniversalElements.setAwayModeLabel(awayModeLabel);
        UniversalElements.setSimulationSpeedLabel(simulationSpeedLabel);
        UniversalElements.setOutputConsoleText(outputConsoleText);
        UniversalElements.setPanes(panes);

        //Subscribing necessary elements to alarm system.
        CommandLogger.initialize();
        WindowManager.initialize();
        DoorManager.initialize();
        LightManager.initialize();

        // Subscribing the necessary elements to the temperature regulation system.
        TemperatureManager.initialize();

        //Populating rooms container in SHC.
        allRooms();

        //Timeline that runs in intervals of 1 second. It is used for a variety of things from updating fxml elements to checking for new values.
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    //Helper for configuring lights.
                    try {
                        manageAutomaticLights();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Updating username label text.
                    if(!ActiveUser.getActiveUsername().equals(userLabel.getText())){
                        if(!ActiveUser.getActiveUsername().equals("")){
                            userLabel.setText(ActiveUser.getActiveUsername());
                        } else {
                            userLabel.setText(ActiveUser.getActiveUsername());
                        }
                    }
                    //Log if a user profile has been deleted.
                    if(ProfileController.profileList.size() < oldNumberOfProfiles){
                        oldNumberOfProfiles = ProfileController.profileList.size();
                    }
                    //Change outside temperature if the name has been changed.
                    if(!OutsideTemperatureController.getOutsideTemperature().equals(outsideTemperatureLabel.getText())){
                        outsideTemperatureLabel.setText(OutsideTemperatureController.getOutsideTemperature());
                    }
                    //Change the speed if the name has been changed.
                    if(!clockController.getSimulationSpeed().equals(simulationSpeedLabel.getText())){
                    	simulationSpeedLabel.setText(clockController.getSimulationSpeed());
                    }
                    //Change active user location if the name has been changed.
                    if(!ActiveUser.getActiveUserLocation().equals(locationLabel.getText())){
                        locationLabel.setText(ActiveUser.getActiveUserLocation());
                    }

                    if(!startStopButton.getText().equals(oldSimulatorButton)){
                        oldSimulatorButton = startStopButton.getText();
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
    public void startStopSimulator(MouseEvent mouseEvent) throws IOException{
        //If the simulator is stopped, button click will start the simulator.
        if(startStopButton.getText().equals("Start Simulator")){

            startStopButton.setText("Stop Simulator");
            clockController.beginTime(timeLabel);
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has started the simulator.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //If the simulator is started, button click will stop the simulator.
        } else {
            startStopButton.setText("Start Simulator");
            windowWatcher.triggerAlarm("close", roomList.getSelectionModel().getSelectedItem(), startStopButton.getText());
            clockController.stopTime(timeLabel);
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has stopped the simulator.");
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
    public void pauseSimulation(MouseEvent mouseEvent) throws IOException {
        //Pause Time only if the simulation has started.
        if(!timeLabel.getText().equals("HH:MM:SS")) {
            clockController.pauseTime();
            windowWatcher.triggerAlarm("open", roomList.getSelectionModel().getSelectedItem(), "Paused");
            // TODO: Freeze the change in temperature if a window is open.
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has paused the simulator.");
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
    public void resumeSimulation(MouseEvent mouseEvent) throws IOException{
        //Resume Time only if the simulation has started.
        if(!timeLabel.getText().equals("HH:MM:SS")){
            clockController.resumeTime();
            windowWatcher.triggerAlarm("open", roomList.getSelectionModel().getSelectedItem(), "Resume");
            //Logging.
            try {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has resumed the simulator.");
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
            CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has pressed the edit time button.");
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
            CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has pressed the edit outside temperature button.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditOutsideTemperature();
    }

    @FXML
    public void configureTimeClicked(MouseEvent mouseEvent) throws IOException {
        Main.showConfigureTime();
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
            CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has pressed the edit location button.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditLocation();
    }

    @FXML
    public void openItemButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms(roomList.getSelectionModel().getSelectedItem())){
            switch (itemList.getSelectionModel().getSelectedItem()) {
                case "Windows":
                    WindowManager.unlockWindow(roomList.getSelectionModel().getSelectedItem());
                    // TODO: Something with the TemperatureManager class
                    windowWatcher.triggerAlarm("open", roomList.getSelectionModel().getSelectedItem(),startStopButton.getText());
                    break;
                case "Doors":
                    DoorManager.unlockDoor(roomList.getSelectionModel().getSelectedItem());
                    break;
                case "Lights":
                    LightManager.turnOnLight(roomList.getSelectionModel().getSelectedItem());
                    break;
            }
        } else {
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" tried to set "+itemList.getSelectionModel().getSelectedItem()+" in "+roomList.getSelectionModel().getSelectedItem()+" to closed/off but was denied!");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @FXML
    public void closeItemButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms(roomList.getSelectionModel().getSelectedItem())){
            switch (itemList.getSelectionModel().getSelectedItem()) {
                case "Windows":
                    WindowManager.lockWindow(roomList.getSelectionModel().getSelectedItem());
                    // TODO: Something with the TemperatureManager class
                    windowWatcher.triggerAlarm("close", roomList.getSelectionModel().getSelectedItem(),startStopButton.getText());
                    break;
                case "Doors":
                    DoorManager.lockDoor(roomList.getSelectionModel().getSelectedItem());
                    break;
                case "Lights":
                    LightManager.turnOffLight(roomList.getSelectionModel().getSelectedItem());
                    break;
            }
        } else {
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" tried to set "+itemList.getSelectionModel().getSelectedItem()+" in "+roomList.getSelectionModel().getSelectedItem()+" to closed/off but was denied!");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // This opens the "Edit Room Temperature" window, for the room selected in the SHH tab.
    @FXML
    public void editRoomTempButtonClicked(MouseEvent mouseEvent) throws IOException {
        // Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms(temperatureRoomList.getSelectionModel().getSelectedItem())){
            // Open the editRoomTemperature window:
            Main.showEditRoomTemperature(temperatureRoomList.getSelectionModel().getSelectedItem());
            //Logging.
            try {
                CommandLogger.logCommand("SHH", ActiveUser.getActiveUsername()+" is editing the "+temperatureRoomList.getSelectionModel().getSelectedItem()+"'s temperature.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            AlertManager.badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("SHH", ActiveUser.getActiveUsername()+" tried to edit the "+temperatureRoomList.getSelectionModel().getSelectedItem()+"'s temperature but was denied!");
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
                //Turn on away mode.
                ActiveUser.turnOnAwayMode();

                //Lock all windows and doors and turn off all lights.
                LightManager.turnOffAllLights();
                WindowManager.lockAllWindows();
                DoorManager.lockAllDoors();

                //Prevent further commands to windows and doors!
                WindowManager.turnOnLockdownMode();
                DoorManager.turnOnLockdownMode();

                //Change the label text.
                awayModeButton.setText("Deactivate");
                awayModeLabel.setText("Active");
                //Logging.
                try {
                    CommandLogger.logCommand("SHP", ActiveUser.getActiveUsername()+" activated away mode.");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                //If away mode is currently activated turn off away mode.
                ActiveUser.turnOffAwayMode();
                awayModeButton.setText("Activate");
                awayModeLabel.setText("Not active");

                //Deactivate lockdown mode for the Window and Door managers
                WindowManager.turnOffLockdownMode();
                DoorManager.turnOffLockdownMode();
                //Logging.
                try {
                    CommandLogger.logCommand("SHP", ActiveUser.getActiveUsername()+" deactivated away mode.");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            //Alert the user of the success.
            AlertManager.successfulPermissionsAlert();
        } else {
            //Alert the user of the failure.
            AlertManager.badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("SHP", ActiveUser.getActiveUsername()+" failed to edit away mode.");
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
                CommandLogger.logCommand("Dashboard", "A non-logged-in user pressed the edit profile button.");
            }
            else {
                CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername() + " pressed the edit profile button.");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditProfile();
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
    public void displayRooms (MouseEvent mouseEvent) throws IOException {
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

    //Method to fill the rooms container in the SHC tab.
    public void allRooms() {
        ArrayList<String> rooms = new ArrayList<String>();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (!roomGrid.get(row).get(col).roomName.equals("Unnamed") && roomGrid.get(row).get(col) != null) {
                    rooms.add(roomGrid.get(row).get(col).roomName);
                }
            }
        }
        allRoomList.getItems().addAll(rooms);
        roomList.getItems().addAll(rooms);
        temperatureRoomList.getItems().addAll(rooms);
    }

    public void manageAutomaticLights() throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Room room = LayoutParser.grid.get(row).get(col);
                if (room.graphNumber == 0)
                    continue;
                if(ClockController.getCurrentTime().equals("00 : 00 : 00")){
                    break;
                }
                if(ClockController.getCurrentTime().equals(room.lightsOnTime)){
                    LightManager.turnOnLight(room.roomName);
                }
                if(ClockController.getCurrentTime().equals(room.lightsOffTime)){
                    LightManager.turnOffLight(room.roomName);
                }
            }
        }
    }


    public TextArea getHomeTextArea() {
        return outputConsoleText;
    }

}
