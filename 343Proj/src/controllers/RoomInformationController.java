package controllers;

import Main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import models.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import utility.UniversalElements;
import utility.ZoneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RoomInformationController extends Label implements Initializable {

    //private static final String currentSkinClassName = null; // variable to initialize the image attribute
	public Room selectedRoom = new Room();
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
    //public Image image = new Image(currentSkinClassName);

    public static Timeline roomInformationTimeline;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        selectedRoom = Main.roomSelectedFromLayout;
        roomNumber.setText(Integer.toString(selectedRoom.getGraphNumber()));
        roomName.setText(selectedRoom.getRoomName());
        String formattedTemperature = String.format("%.1f", selectedRoom.getInitialTemp());
        roomTemperature.setText(formattedTemperature);
        roomDoor.setText(Boolean.toString(selectedRoom.getDoorExists()));
        roomDoorOpen.setText(Boolean.toString(selectedRoom.getDoorStatus()));
        roomWindow.setText(Boolean.toString(selectedRoom.getWindowExists()));
        roomWindowOpen.setText(Boolean.toString(selectedRoom.getWindowStatus()));
        roomActiveUser.setText(Boolean.toString(selectedRoom.getActiveProfileIsHere()));
        roomPersonObject.setText(Boolean.toString(selectedRoom.getPersonIsHere()));
        roomZone.setText(ZoneManager.getZoneOfRoom(selectedRoom.getRoomName()));
        //image = new Image(selectedRoom.getIconFile());

        roomInformationTimeline = new Timeline(
                // Every second, check if the temperature of the room is different than the displayed temperature.
                // If it is different, update the displayed temperature.
                new KeyFrame(Duration.seconds(1/(UniversalElements.getClock().getSpeed())), e -> {
                    if (!roomTemperature.getText().equals(selectedRoom.getInitialTemp())) {
                        //roomTemperature.setText(String.format("%.1f", selectedRoom.getInitialTemp()));
                        roomTemperature.setText(Double.toString(selectedRoom.getInitialTemp()));
                    }
                })
        );
        //Run the timeline indefinitely or until paused/stopped manually.
        roomInformationTimeline.setCycleCount(Animation.INDEFINITE);
        //Play the timeline.
        roomInformationTimeline.play();
    }

    // TODO: Get the selected room object the layout to the room information controller.
    public void editOutsideTemperature(MouseEvent mouseEvent) throws IOException {
        //Set the temperature variable to the inputted value.
    	//outsideTemperature.setTemperature(Double.parseDouble(outsideTemperatureInput.getText()));
        //Call closeEditTemperature from Main and return to the primary stage.
        Main.closeEditOutsideTemperature();
    }
}
