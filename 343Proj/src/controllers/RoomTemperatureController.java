package controllers;

import Main.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.OutsideTemperature;

import java.io.IOException;

/**
 * The type OutsideTemperature controller.
 * The OutsideTemperature class contains methods related to OutsideTemperature in the application. It controls the OutsideTemperature.fxml page.
 * The HomeController class can communicate with the OutsideTemperatureController class by creating an instance of OutsideTemperatureController and using the available public methods.
 * The static OutsideTemperature attribute creates a singular static OutsideTemperature for the whole system, as there can only be one active OutsideTemperature at a time.
 */
public class RoomTemperatureController extends Label{
    /**
     * Creating an instance of the OutsideTemperature class. There exists a single constant OutsideTemperature, so it must remain static.
     */
    static OutsideTemperature outsideTemperature = new OutsideTemperature();
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
        //Set the temperature variable to the inputted value.
    	outsideTemperature.setTemperature(Double.parseDouble(outsideTemperatureInput.getText()));
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
