package controllers;

import Main.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.*;
import java.io.*;
/**
 * The type AwayModeTemperature controller.
 * The AwayModeTemperature class contains methods related to AwayModeTemperature in the application. It controls the AwayTemperature.fxml page.
 * The HomeController class can communicate with the AwayModeTemperature class by creating an instance of AwayModeTemperature and using the available public methods.
 * The static AwayModeTemperature attribute creates a singular static Month for the whole system, as there can only be one active AwayModeTemperature at a time.
 */
public class AwayModeTemperatureController extends Label{
    /**
     * Creating an instance of the AwayModeTemperature class. There exists a single constant AwayModeTemperature, so it must remain static.
     */
	public static AwayModeTemperature awayModeTemperature = new AwayModeTemperature();
    /**
     * The summerTemperature TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
	public TextField summerTemperatureInput = new TextField();
    /**
     * The winterTemperature TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
	public TextField winterTemperatureInput = new TextField();
    /**
     * Change the static month attribute according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     **/
    public void editAwayModeTemperature(MouseEvent mouseEvent) throws IOException {
        //Set the month variable to the chosen value.
    	awayModeTemperature.setSummerTemperature(Double.parseDouble(summerTemperatureInput.getText()));
    	awayModeTemperature.setWinterTemperature(Double.parseDouble(winterTemperatureInput.getText()));
    	//month.setMonth("March");
        //Call closeEditMonth from Main and return to the primary stage.
        Main.closeEditAwayModeTemperature();
    }

    public static double getAwayModeTemperature() {
        return AwayModeTemperature.getAwayModeTemperature();
    }

    public static void setAwayModeTemperature(AwayModeTemperature awayModeTemperature) {
        AwayModeTemperatureController.awayModeTemperature = awayModeTemperature;
    }
}
