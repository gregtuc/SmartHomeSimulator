package controllers;

import Main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.*;
import java.io.*;
import java.net.URL;
import java.util.*;
/**
 * The type Month controller.
 * The month class contains methods related to month in the application. It controls the Month.fxml page.
 * The HomeController class can communicate with the MonthController class by creating an instance of MonthController and using the available public methods.
 * The static Month attribute creates a singular static Month for the whole system, as there can only be one active Month at a time.
 */
public class MonthController extends Label implements Initializable{
    /**
     * Creating an instance of the Month class. There exists a single constant Month, so it must remain static.
     */
    public static Month month = new Month();
    /**
     * The editMonthInput ComboBox.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public ComboBox<String> editMonthInput = new ComboBox<String>();
    
    /**
     * Initializes the editMonthInput ComboBox to contain all the months
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	editMonthInput.getItems().addAll("January","February","March","April","May","June","July","August",
    			"September","October","November","December");
    	editMonthInput.getSelectionModel().selectFirst();
    }
    /**
     * Change the static month attribute according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     **/
    public void editMonth(MouseEvent mouseEvent) throws IOException {
        //Set the month variable to the chosen value.
    	month.setMonth(editMonthInput.getValue());
    	//month.setMonth("March");
        //Call closeEditMonth from Main and return to the primary stage.
        Main.closeEditMonth();
    }
    /**
     * Gets the current month.
     * 
     * @return the current month
     */
    public String getMonth(){
    	return month.getMonth();
    }
    /**
     * Gets the current season
     * 
     * @return the current season
     */
    public String getSeason(){
    	return month.getSeason(month.getMonth());
    }
}
