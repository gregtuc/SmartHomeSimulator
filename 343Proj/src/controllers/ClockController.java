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

import main.Main;
import models.Clock;

public class ClockController extends Label {
    //Creating an instance of the Clock class. There exists a single constant clock, so it must remain static.
    static Clock clock = new Clock();

    //Declaring a currentTime string to facilitate easier formatting.
    AtomicReference<String> currentTime = new AtomicReference<>(clock.getHour() + " : " + clock.getMinute() + " : " + clock.getSecond());

    //Declaring a Timeline object as an attribute so that it can be paused/played in any method.
    Timeline timeline;

    //Fetching fxml elements.  The name of the variable must correlate with the id assigned in the fxml document.
    public TextField hourInput = new TextField();
    public TextField minuteInput = new TextField();
    public TextField secondInput = new TextField();

    //Begins a new Timeline object that updates the clock once every second.
    public void beginTime(Label timeLabel){
        //If the user is resuming the existing clock session, redirect to resumeTime().
        //Otherwise, a new clock session will begin.
            timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), e -> {
                        //Condition blocks to manage the time variables.
                        if (clock.getSecond() >= 60) {
                            //At 60s, return seconds to 0.
                            clock.setSecond(0);
                            //If minutes is < 60, add a minute.
                            if (clock.getMinute() < 60) {
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
                        //Set the text of the Label (which was passed as a parameter to this method) to the variable currentTime.
                        timeLabel.setText(currentTime.get());
                    })
            );
            //Run the timeline indefinitely or until paused/stopped manually.
            timeline.setCycleCount(Animation.INDEFINITE);
            //Play the timeline.
            timeline.play();
    }

    //Stop the existing timeline.
    public void stopTime(Label timeLabel){
        clock.setHour(0);
        clock.setMinute(0);
        clock.setSecond(0);
        currentTime.set(String.format("%02d", clock.getHour()) + " : " + String.format("%02d", clock.getMinute()) + " : " + String.format("%02d", clock.getSecond()));
        timeLabel.setText(currentTime.get());
        timeline.stop();
    }

    //Resume the existing timeline.
    public void resumeTime(){
        timeline.play();
    }

    //Pause the existing timeline.
    public void pauseTime(){
        timeline.pause();
    }

    //Change the static time attributes according to user input.
    public void editTime(MouseEvent mouseEvent) throws IOException {
        //Set the time variables to the inputted values.
        clock.setHour(Integer.parseInt(String.valueOf(hourInput.getText())));
        clock.setMinute(Integer.parseInt(String.valueOf(minuteInput.getText())));
        clock.setSecond(Integer.parseInt(String.valueOf(secondInput.getText())));
        //Call closeEditTime from Main and return to the primary stage.
        Main.closeEditTime();
    }
}
