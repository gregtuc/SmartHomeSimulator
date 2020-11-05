package controllers;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.util.Duration;
import models.Profile;
import models.Room;
import Main.Main;
import Main.LayoutParser;

/**
 * The type Home controller.
 * The HomeController class contains general methods related to the broader application. It controls the Home.fxml page.
 * The HomeController class acts as a primary router and may call on other controller classes to complete actions when necessary.
 */
public class HomeController extends Label implements Initializable {
    /**
     * Declaring a Timeline object as an attribute so that it can be paused/played in any method.
     */
    Timeline timeline;
    /**
     * Instance of the ClockController class.
     * This will let us access public methods and attributes from that controller.
     */
    ClockController clockController = new ClockController();
  
    /**
     * Instance of the OutsideTemperatureController class.
     * This will let us access public methods and attributes from that controller.
     */
    OutsideTemperatureController OutsideTemperatureController = new OutsideTemperatureController();
    
    /**
     * Instance of the LocationController class.
     * This will let us access public methods and attributes from that controller.
     */
    LocationController locationController = new LocationController();
    
    /**
     * Instance of the ProfileController class.
     * This will let us access public methods and attributes from that controller.
     */
    ProfileController profileController = new ProfileController();
    /**
     * The timeLabel Label Input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */

    public Label timeLabel = new Label();
    /**
     * The userLabel Label Input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public Label userLabel = new Label();
    /**
     * The outsideTemperatureLabel Label Input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public Label outsideTemperatureLabel = new Label();
    /**
     * The startStopButton Button Input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public Button startStopButton = new Button();
    
    /**
     * The locationLabel Label Input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public Label locationLabel = new Label();

    /**
     * The oldLocationLabel.
     * Keep a value of the old location. This is essentially a helper-variable for when the active user changes locations
     * and we want to clean up the old location that they were at.
     */
    public static String oldLocationLabel = "";

    /**
     * The panes 2D array.
     * Hold references to all the House-Layout FXML elements.
     */
    TextArea[][] panes = new TextArea[4][4];
    /**
     * ListView to hold all items such as windows, doors and lights
     */
    public ListView<String> itemList = new ListView<>();
    /**
     * ListView to hold all Rooms with either windows, doors or lights
     */
    public ListView<String> RoomsList = new ListView<>();
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

        //Check for new activeUsers in intervals of 1 second.
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    //Change current profile name if the name has been changed.
                    if(!profileController.getActiveProfileName().equals(userLabel.getText())){
                        userLabel.setText(profileController.getActiveProfileName());
                    }
                    //Change outside temperature if the name has been changed.
                    if(!OutsideTemperatureController.getOutsideTemperature().equals(outsideTemperatureLabel.getText())){
                        outsideTemperatureLabel.setText(OutsideTemperatureController.getOutsideTemperature());
                    }
                    //Change active user location if the name has been changed.
                    if(!locationController.getUserLocation().equals(locationLabel.getText())){
                        locationLabel.setText(locationController.getUserLocation());
                        LayoutParser.insertProfile(locationController.getUserLocation(), oldLocationLabel, panes);
                    }
                    //Change person location if the name has been changed.
                    if(!locationController.getPeopleLocation().equals("None")){

                        LayoutParser.insertPerson(locationController.getPeopleLocation(), panes);
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
            //If the simulator is started, button click will stop the simulator.
        } else {
            startStopButton.setText("Start Simulator");
            clockController.stopTime(timeLabel);
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
        Main.showEditLocation();
    }
    
    /**
     * Opens the edit profile window. Calls method showEditProfile from class Main.
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    public void editProfileClicked(MouseEvent mouseEvent) {
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
    public void DisplayRooms (MouseEvent mouseEvent) throws IOException {
    	RoomsList.getItems().clear();
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
        RoomsList.getItems().addAll(rooms);    
    }
    
}
