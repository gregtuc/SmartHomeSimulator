package controllers;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.*;
import utility.CommandLogger;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class MonthController extends Label implements Initializable{
    /**
     * Creating an instance of the OutsideTemperature class. There exists a single constant OutsideTemperature, so it must remain static.
     */
    public static Month month = new Month();
    /**
     * The editMonthInput ComboBox.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public ComboBox<String> editMonthInput = new ComboBox<String>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	editMonthInput.getItems().addAll("January","February","March","April","May","June","July","August",
    			"September","October","November","December");
    	editMonthInput.getSelectionModel().selectFirst();
    }
    public void editMonth(MouseEvent mouseEvent) throws IOException {
        //Set the month variable to the chosen value.
    	month.setMonth(editMonthInput.getValue());
    	//month.setMonth("March");
        //Call closeEditMonth from Main and return to the primary stage.
        Main.closeEditMonth();
    }
    public String getMonth(){
    	return month.getMonth();
    }
    public String getSeason(){
    	return month.getSeason(month.getMonth());
    }
}
