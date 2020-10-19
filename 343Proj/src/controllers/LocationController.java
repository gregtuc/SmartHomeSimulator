package controllers;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import models.Location;

import Main.Main;

/**
 * The type Location controller.
 * The Location class contains methods related to Locations in the application. It controls the Location.fxml page.
 * The HomeController class can communicate with the LocationController class by creating an instance of LocationController and using the available public methods.
 * The static userLocation and peopleLocation attributes create static user and people location for the whole system, as there can only be one active User location and people location at a time.
 */

public class LocationController extends Label {
	 /**
     * Creating an instance of the Location class. There exists a single constant UserLocation, so it must remain static.
     */
	static Location userLocation = new Location();
	 /**
     * Creating an instance of the Location class. There exists a single constant PeopleLocation, so it must remain static.
     */
	static Location peopleLocation = new Location();
	
	AtomicReference<String> currentUserLocation = new AtomicReference<>(userLocation.getLocation());
	AtomicReference<String> currentPeopleLocation = new AtomicReference<>(peopleLocation.getLocation());
	
	/**
     * The UserLocation TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
	public TextField UserLocationInput = new TextField();
	/**
     * The PeopleLocation TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
	public TextField PeopleLocationInput = new TextField();
	/**
     * Change the static UserLocation attribute according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     **/
	public void editUserLocation(MouseEvent mouseEvent) throws IOException {
		//Set the temperature variable to the inputed value.
	    userLocation.setLocation(UserLocationInput.getText());
	    //Call closeEditTemperature from Main and return to the primary stage.
	    Main.closeEditLocation();
	 }
	/**
     * Change the static PeopleLocation attribute according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     **/
	public void editPeopleLocation(MouseEvent mouseEvent) throws IOException {
        //Set the temperature variable to the inputed value.
    	peopleLocation.setLocation(PeopleLocationInput.getText());
        //clock.setHour(Integer.parseInt(String.valueOf(hourInput.getText())));
        //Call closeEditTemperature from Main and return to the primary stage.
        Main.closeEditLocation();
	}
	/**
     * Get active User Location string.
     *
     * @return the string
     */
	public String getUserLocation(){
		return String.valueOf(userLocation.getLocation());
	}
	/**
     * Get active People Location string.
     *
     * @return the string
     */
	public String getPeopleLocation(){
		return String.valueOf(peopleLocation.getLocation());
	}
	
}
