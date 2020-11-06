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
import java.util.ResourceBundle;

import javafx.util.Duration;
import Main.Main;
import Main.LayoutParser;
import models.ActiveUser;
import utility.CommandLogger;
import utility.PermissionChecker;

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

    public Button startStopButton = new Button();
    public Button openBackyardButton = new Button();
    public Button openGarageButton = new Button();
    public Button openMainButton = new Button();
    public Button awayModeButton = new Button();

    public TextArea outputConsoleText = new TextArea();

    /**
     * The oldLocationLabel.
     * Keep a value of the old location. This is essentially a helper-variable for when the active user changes locations
     * and we want to clean up the old location that they were at.
     */
    public static String oldLocationLabel = "";
    public static int oldNumberOfProfiles = ProfileController.profileList.size();

    /**
     * The panes 2D array.
     * Hold references to all the House-Layout FXML elements.
     */
    TextArea[][] panes = new TextArea[4][4];

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

        //Check for new activeUsers in intervals of 1 second.
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
                    //Change outside temperature if the name has been changed.
                    if(!OutsideTemperatureController.getOutsideTemperature().equals(outsideTemperatureLabel.getText())){
                        outsideTemperatureLabel.setText(OutsideTemperatureController.getOutsideTemperature());
                        //Logging.
                        try {
                            CommandLogger.logCommand("Parameter", ActiveUser.getActiveUsername()+" has changed the temperature to OutsideTemperatureController.getOutsideTemperature().", outputConsoleText);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    //Change active user location if the name has been changed.
                    if(!ActiveUser.getActiveUserLocation().equals(locationLabel.getText())){
                        locationLabel.setText(ActiveUser.getActiveUserLocation());
                        LayoutParser.insertProfile(ActiveUser.getActiveUserLocation(), oldLocationLabel, panes);
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
        oldLocationLabel = locationLabel.getText();
        //Logging.
        try {
            CommandLogger.logCommand("Dashboard", ActiveUser.getActiveUsername()+" has pressed the edit location button.", outputConsoleText);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Main.showEditLocation();
    }

    @FXML
    public void backyardButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms()){
            //Change Button text
            if(openBackyardButton.getText().equals("Open")){
                openBackyardButton.setText("Close");
                //Logging.
                try {
                    CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" has opened the backyard door.", outputConsoleText);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                openBackyardButton.setText("Open");
                //Logging.
                try {
                    CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" has closed the backyard door.", outputConsoleText);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            successfulPermissionsAlert();
            //TODO - Add functionality for backyard.
        } else {
            badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" tried to open the backyard door but was not allowed.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @FXML
    public void garageButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms()){
            //Change Button text
            if(openGarageButton.getText().equals("Open")){
                openGarageButton.setText("Close");
                //Logging.
                try {
                    CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" has closed the garage.", outputConsoleText);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                openGarageButton.setText("Open");
                //Logging.
                try {
                    CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" has opened the garage.", outputConsoleText);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            successfulPermissionsAlert();
            //TODO - Add functionality for garage.
        } else {
            badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" tried to open the garage but was not allowed.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @FXML
    public void mainButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkCorePerms()){
            //User has permission.
            successfulPermissionsAlert();
            //TODO - Add functionality for main.
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" has opened the main door.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            badPermissionsAlert();
            //Logging.
            try {
                CommandLogger.logCommand("Core", ActiveUser.getActiveUsername()+" tried to open the main door but was not allowed.", outputConsoleText);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    @FXML
    public void awayModeButtonClicked(MouseEvent mouseEvent) throws IOException {
        //Permission Validation. If active user does not have permission, an alert box will appear.
        if(PermissionChecker.checkSecurityPerms()){

            //If away mode is currently deactivated
            if(ActiveUser.getActiveUserAwayMode()){
                ActiveUser.setActiveUserAwayMode();
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

    public TextArea getHomeTextArea() {
        return outputConsoleText;
    }
}