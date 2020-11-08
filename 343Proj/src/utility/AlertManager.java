package utility;

import java.util.Optional;

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

}
