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
import main.Main;

public class HomeController extends Label implements Initializable {
    Timeline timeline;

    //Creating an instance of the ClockController class.
    ClockController clockController = new ClockController();
    ProfileController profileController = new ProfileController();

    //Declaring elements that must be changed or read. The name of the object must correlate with the id assigned in the fxml document.
    //This correlation creates an association between the variable and the fxml element.
    public Label timeLabel = new Label();
    public Label userLabel = new Label();
    public Button startStopButton = new Button();

    //Initialize runs immediately when the page loads.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Check for new activeUsers in intervals of 1 second.
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    userLabel.setText(profileController.getActiveProfileName());
                })
        );
        //Run the timeline indefinitely or until paused/stopped manually.
        timeline.setCycleCount(Animation.INDEFINITE);
        //Play the timeline.
        timeline.play();
    }

    //Handles the button for starting and stopping the simulator.
    //TODO: Make this more concrete by not using button text for conditions.
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

    //Handles the button for pausing the simulator. (currently only pauses the clock).
    public void pauseSimulation(MouseEvent mouseEvent){
        System.out.println("pauseSimulation input registered.");
        clockController.pauseTime();
    }

    //Handles the button for resuming the simulator. (currently only resumes the clock).
    public void resumeSimulation(MouseEvent mouseEvent){
        System.out.println("resumeSimulation input registered.");
        clockController.resumeTime();
    }

    //Opens the edit time window. Calls method showEditTime from class Main.
    @FXML
    public void editTimeClicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("editTimeClicked input registered.");
        Main.showEditTime();
    }

    //Opens the edit profile window. Calls method showEditProfile from class Main.
    @FXML
    public void editProfileClicked(MouseEvent mouseEvent) {
        Main.showEditProfile();
    }
}