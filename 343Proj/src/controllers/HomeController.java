package controllers;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.Duration;
import Main.Main;

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
    

    //Initialize runs immediately when the page loads.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Check for new activeUsers in intervals of 1 second.
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    userLabel.setText(profileController.getActiveProfileName());
                    outsideTemperatureLabel.setText(OutsideTemperatureController.getOutsideTemperature());
                    locationLabel.setText(locationController.getUserLocation());
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
        System.out.println("startSimulator input registered.");
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
        System.out.println("pauseSimulation input registered.");
        clockController.pauseTime();
    }

    /**
     * Handles the button for resuming the simulator. (currently only resumes the clock).
     *
     * @param mouseEvent the mouse event
     */
    public void resumeSimulation(MouseEvent mouseEvent){
        System.out.println("resumeSimulation input registered.");
        clockController.resumeTime();
    }

    /**
     * Opens the edit time window. Calls method showEditTime from class Main.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     */
    @FXML
    public void editTimeClicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("editTimeClicked input registered.");
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
        System.out.println("editOutsideTemperatureClicked input registered.");
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
        System.out.println("editLocationClicked input registered.");
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
}