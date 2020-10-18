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
import models.OutsideTemperature;

/**
 * The type OutsideTemperature controller.
 * The OutsideTemperature class contains methods related to OutsideTemperature in the application. It controls the OutsideTemperature.fxml page.
 * The HomeController class can communicate with the OutsideTemperatureController class by creating an instance of OutsideTemperatureController and using the available public methods.
 * The static OutsideTemperature attribute creates a singular static OutsideTemperature for the whole system, as there can only be one active OutsideTemperature at a time.
 */
public class OutsideTemperatureController extends Label{
    /**
     * Creating an instance of the OutsideTemperature class. There exists a single constant OutsideTemperature, so it must remain static.
     */
    static OutsideTemperature outsideTemperature = new OutsideTemperature();
    /**
     * currentOutsideTemperature AtomicReference String to facilitate easier formatting. Only showing two decimal places for now.
     */
    AtomicReference<String> currentOutsideTemperature = new AtomicReference<>(outsideTemperature.getTemperature() + " ");
    /**
     * The OutsideTemperature TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public TextField outsideTemperatureInput = new TextField();
    
    /**
     * Change the static outside temperature attribute according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     **/
    public void editOutsideTemperature(MouseEvent mouseEvent) throws IOException {
        //Set the temperature variable to the inputed value.
    	outsideTemperature.setTemperature(Double.parseDouble(outsideTemperatureInput.getText()));
        //clock.setHour(Integer.parseInt(String.valueOf(hourInput.getText())));
        //Call closeEditTemperature from Main and return to the primary stage.
        Main.closeEditOutsideTemperature();
    }
    /**
     * Get active outside temperature string.
     *
     * @return the string
     */
    public String getOutsideTemperature(){
        return String.valueOf(outsideTemperature.getTemperature());
    }
}
