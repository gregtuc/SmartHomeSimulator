package controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import Main.Main;
import models.OutsideTemperature;
import utility.TemperatureManager;
import utility.ZoneManager;
import Main.LayoutParser;

/**
 * The type OutsideTemperature controller.
 * The OutsideTemperature class contains methods related to OutsideTemperature in the application. It controls the OutsideTemperature.fxml page.
 * The HomeController class can communicate with the OutsideTemperatureController class by creating an instance of OutsideTemperatureController and using the available public methods.
 * The static OutsideTemperature attribute creates a singular static OutsideTemperature for the whole system, as there can only be one active OutsideTemperature at a time.
 */
public class OutsideTemperatureController extends Label {
    public static OutsideTemperature outsideTemperature = new OutsideTemperature();
    public TextField outsideTemperatureInput = new TextField();

    public void editOutsideTemperature(MouseEvent mouseEvent) throws IOException {
        // Set the temperature variable to the inputted value.
        outsideTemperature.setTemperature(Double.parseDouble(outsideTemperatureInput.getText()));
        // Set the Default zone temperatures for all 3 periods to the outside temperature.
        ZoneManager.setZoneTemperatures("Default",
                Double.parseDouble(outsideTemperatureInput.getText()), // Period 1 temperature.
                Double.parseDouble(outsideTemperatureInput.getText()), // Period 2 temperature.
                Double.parseDouble(outsideTemperatureInput.getText())); // Period 3 temperature.

        TemperatureManager.setRoomsToOutsideTemperature();
        //Call closeEditTemperature from Main and return to the primary stage.
        Main.closeEditOutsideTemperature();
    }

    public String getOutsideTemperature() {
        return String.valueOf(outsideTemperature.getTemperature());
    }
}
