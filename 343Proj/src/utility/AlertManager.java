package utility;

import java.util.Optional;
import Main.Main;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertManager {
	public static int numberOfAlertSent = 0; 
	 /**
     * Opens a window to show if a command is successful
     */
    public static void successfulPermissionsAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Success!",
                ButtonType.CLOSE);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
    }

    /**
     * Opens a window to show if a command is unsuccessful
     */
    public static void badPermissionsAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "You do not have permission to execute this command.",
                ButtonType.CLOSE);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    /**
     * This Alert is sent when a room reached zero degrees or below
     */
    public static void freezingTemperatureAlert(String roomName) {
    	if(numberOfAlertSent < 1) {
    		Alert alert = new Alert(Alert.AlertType.WARNING, "Warning! The " + roomName + " temperture has reached below 0 Degrees Celsius. Please increase the Temperture.",
    			ButtonType.CLOSE);
    		alert.setTitle(null);
    		alert.setHeaderText(null);
    		alert.show();
    		numberOfAlertSent++;
    	}   
    }
    /**
     * This Alert is sent when a Room does not have a door or window and the user tries to open or close them.
     * @param roomName
     * @param item
     */
    public static void ItemDoesNotExist (String roomName, String item) {
   	 Alert alert = new Alert(Alert.AlertType.WARNING,
                "ERROR: The " + roomName + " does Not have a " + item,
                ButtonType.CLOSE);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
   }

}
