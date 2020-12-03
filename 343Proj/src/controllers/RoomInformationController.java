package controllers;

import Main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import models.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type OutsideTemperature controller.
 * The OutsideTemperature class contains methods related to OutsideTemperature in the application. It controls the OutsideTemperature.fxml page.
 * The HomeController class can communicate with the OutsideTemperatureController class by creating an instance of OutsideTemperatureController and using the available public methods.
 * The static OutsideTemperature attribute creates a singular static OutsideTemperature for the whole system, as there can only be one active OutsideTemperature at a time.
 */
public class RoomInformationController extends Label implements Initializable {
    /**
     * Creating an instance of the OutsideTemperature class. There exists a single constant OutsideTemperature, so it must remain static.
     */
    /**
     * The OutsideTemperature TextField input.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */

    public Room selectedRoom = new Room();

    //Initialize runs immediately when the page loads.
    public Text roomNumber = new Text();
    public Text roomName = new Text();
    public Text roomTemperature = new Text();
    public Text roomZone = new Text();
    public Text roomDoor = new Text();
    public Text roomDoorOpen = new Text();
    public Text roomWindow = new Text();
    public Text roomWindowOpen = new Text();
    public Text roomActiveUser = new Text();
    public Text roomPersonObject = new Text();

    public static Timeline roomInformationTimeline;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        selectedRoom = Main.roomSelectedFromLayout;
        roomNumber.setText(Integer.toString(selectedRoom.getGraphNumber()));
        roomName.setText(selectedRoom.getRoomName());
        String formattedTemperature = String.format("%.1f", selectedRoom.getInitialTemp());
        roomTemperature.setText(formattedTemperature);
        // TODO: Create "Zone" attribute for Room objects, set it here.
        roomDoor.setText(Boolean.toString(selectedRoom.getDoorExists()));
        roomDoorOpen.setText(Boolean.toString(selectedRoom.getDoorStatus()));
        roomWindow.setText(Boolean.toString(selectedRoom.getWindowExists()));
        roomWindowOpen.setText(Boolean.toString(selectedRoom.getWindowStatus()));
        roomActiveUser.setText(Boolean.toString(selectedRoom.getActiveProfileIsHere()));
        roomPersonObject.setText(Boolean.toString(selectedRoom.getPersonIsHere()));

        roomInformationTimeline = new Timeline(
                // Every second, check if the temperature of the room is different than the displayed temperature.
                // If it is different, update the displayed temperature.
                new KeyFrame(Duration.seconds(1), e -> {
                    if (!roomTemperature.equals(selectedRoom.getInitialTemp())) {
                        roomTemperature.setText(String.format("%.1f", selectedRoom.getInitialTemp()));
                    }
                })
        );
        //Run the timeline indefinitely or until paused/stopped manually.
        roomInformationTimeline.setCycleCount(Animation.INDEFINITE);
        //Play the timeline.
        roomInformationTimeline.play();
    }



    // TODO: Get the selected room object the layout to the room information controller.

    /**
     * Change the static outside temperature attribute according to user input.
     *
     * @param mouseEvent the mouse event
     * @throws IOException the io exception
     **/
    public void editOutsideTemperature(MouseEvent mouseEvent) throws IOException {
        //Set the temperature variable to the inputted value.
    	//outsideTemperature.setTemperature(Double.parseDouble(outsideTemperatureInput.getText()));
        //Call closeEditTemperature from Main and return to the primary stage.
        Main.closeEditOutsideTemperature();
    }
    /**
     * Get active outside temperature string.
     *
     * @return the string
     */
}
