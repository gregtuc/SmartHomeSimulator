package controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import Main.Main;
import models.Clock;
import utility.UniversalElements;

/**
 * The type Clock controller.
 * The ClockController class contains methods related to Clocks in the application. It controls the Clock.fxml page.
 * The HomeController class can communicate with the ClockController class by creating an instance of ClockController and using the available public methods.
 * The static Clock attribute creates a singular static clock for the whole system, as there can only be one active clock at a time.
 */
public class ClockController extends Label {
    /**
     * Creating an instance of the Clock class. There exists a single constant clock, so it must remain static.
     */
    static Clock clock = new Clock();
    /**
     * currentTime AtomicReference String to facilitate easier formatting.
     */
    AtomicReference<String> currentTime = new AtomicReference<>(clock.getHour() + " : " + clock.getMinute() + " : " + clock.getSecond());
    /**
     * Timeline object that can be paused/played in any method.
     */
    Timeline timeline;
    /**
     * The Hour TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public TextField hourInput = new TextField();
    /**
     * The Minute TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public TextField minuteInput = new TextField();
    /**
     * The Second TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public TextField secondInput = new TextField();
    /**
     * The speed TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public TextField simulationSpeedInput = new TextField();
    
    /**
     * Begins a new Timeline object that updates the clock once every second.
     * @param timeLabel the time label
     */
    public void beginTime(Label timeLabel){



        //If the user is resuming the existing clock session, redirect to resumeTime().
        //Otherwise, a new clock session will begin.
    	//This checks if the simulation speed is set to a very low number, zero or a negative number, it'll set it to 1.0
		if (clock.getSpeed() < 0.001) {
			clock.setSpeed(1.0);
		}
            timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1/clock.getSpeed()), e -> {
                        //Condition blocks to manage the time variables.
                        if (clock.getSecond() > 59) {
                            //At 60s, return seconds to 0.
                            clock.setSecond(0);
                            //If minutes is < 60, add a minute.
                            if (clock.getMinute() < 59) {
                                clock.setMinute(clock.getMinute()+1);
                                //Else at 60m, return minute to 0.
                            } else {
                                clock.setMinute(0);
                                //If hours is < 24, add an hour.
                                if (clock.getHour() < 24) {
                                    clock.setHour(clock.getHour()+1);
                                    //Else at 24h, return hour to 0.
                                } else {
                                    clock.setHour(0);
                                }
                            }
                        } else {
                            clock.setSecond(clock.getSecond()+1);
                        }
                        //Set the currentTime variable to a string with integers that have two digits (for the leading 0's format).
                        currentTime.set(String.format("%02d", clock.getHour()) + " : " + String.format("%02d", clock.getMinute()) + " : " + String.format("%02d", clock.getSecond()));
                        // TODO: Modify Lights if necessary
                        //Set the text of the Label (which was passed as a parameter to this method) to the variable currentTime.
                        timeLabel.setText(currentTime.get());
                    })
            );
            //Run the timeline indefinitely or until paused/stopped manually.
            timeline.setCycleCount(Animation.INDEFINITE);
            //Play the timeline.
            timeline.play();
    }

    /**
     * Stop the existing timeline.
     *
     * @param timeLabel the time label
     */
    public void stopTime(Label timeLabel){
        clock.setHour(0);
        clock.setMinute(0);
        clock.setSecond(0);
        currentTime.set(String.format("%02d", clock.getHour()) + " : " + String.format("%02d", clock.getMinute()) + " : " + String.format("%02d", clock.getSecond()));
        timeLabel.setText(currentTime.get());
        timeline.stop();
    }

    /**
     * Resume the existing timeline.
     */
    public void resumeTime(){
        timeline.play();
    }

    /**
     * Pause the existing timeline.
     */
    public void pauseTime(){
        timeline.pause();
    }

    /**
     * Change the static time attributes according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     */
    public void editTime(MouseEvent mouseEvent) throws IOException {
        //Set the time variables to the inputed values.
        clock.setHour(Integer.parseInt(String.valueOf(hourInput.getText())));
        clock.setMinute(Integer.parseInt(String.valueOf(minuteInput.getText())));
        clock.setSecond(Integer.parseInt(String.valueOf(secondInput.getText())));
        clock.setSpeed(Double.parseDouble(String.valueOf(simulationSpeedInput.getText())));

        //Passing the clock object to UniversalElements to make it accessible everywhere.
        UniversalElements.setClock(clock);

        //Call closeEditTime from Main and return to the primary stage.
        Main.closeEditTime();
    }
    public String getSimulationSpeed(){
        return String.valueOf(clock.getSpeed());
    }

    public static String getCurrentTime(){
        return String.format("%02d", clock.getHour()) + " : " + String.format("%02d", clock.getMinute()) + " : " + String.format("%02d", clock.getSecond());
    }
}
