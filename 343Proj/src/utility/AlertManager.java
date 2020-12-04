package utility;

import java.util.Optional;
import Main.Main;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertManager {
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
    
    public static void freezingTemperatureAlert() {
    	 Alert alert = new Alert(Alert.AlertType.WARNING,
                 "Warning! The Room temperture has reached 0 Degrees Celsius. Please increase the Temperture.",
                 ButtonType.CLOSE);
         alert.setHeaderText(null);
         Optional<ButtonType> result = alert.showAndWait();
    }
    
    public static void ItemDoesNotExist (String roomName, String item) {
   	 Alert alert = new Alert(Alert.AlertType.WARNING,
                "ERROR: The " + roomName + " does Not have a " + item,
                ButtonType.CLOSE);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
   }

}
