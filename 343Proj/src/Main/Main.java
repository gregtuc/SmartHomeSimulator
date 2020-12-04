

package Main;
import controllers.RoomInformationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import security.AlarmSystem;
import utility.CommandLogger;
import utility.DoorManager;
import utility.LightManager;
import utility.WindowManager;
import models.Room;

import java.util.ArrayList;

/**
 * The type Main.
 */
public class Main extends Application{

    /**
     * The Primary stage.
     */
    static Stage primaryStage;
    /**
     * The Edit time stage.
     */
    static Stage editTimeStage;
    /**
     * The Edit profile stage.
     */
    static Stage editProfileStage;
    /**
     * The Edit outside temperature stage.
     */
    static Stage editOutsideTemperatureStage;
    /**
     * The Edit Location stage.
     */
    static Stage editLocationStage;

    static Stage editLightStage;

    static Stage editZoneTemp;

    /**
     * The Room Information stage.
     */
    static Stage roomInformationStage;

    /**
     * The Room Temperature stage.
     */
    static Stage editRoomTemperatureStage;

    // REFACTORING
    public static Room roomSelectedFromLayout = new Room();
    public static Room roomSelectedFromSHH = new Room();
    public static Room getRoomSelectedFromLayout() {
        return roomSelectedFromLayout;
    }
    
    //Create and display the primary application window (Home.fxml).
    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        primaryStage.setTitle("Smart Home Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Display the window that lets users edit the time parameter (Clock.fxml).
     * This method is called from HomeController.
     */
    public static void showEditTime(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/Clock.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage editTimeStage = new Stage();
            Main.editTimeStage = editTimeStage;
            editTimeStage.setScene(new Scene(root));
            editTimeStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the window that lets users edit the time parameter (Clock.fxml).
     * This method is called from ClockController.
     */
    public static void closeEditTime(){
        try {
            Main.editTimeStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Display the window that lets users edit the profile parameter (Profile.fxml).
     * This method is called from HomeController.
     */
    public static void showEditProfile(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/Profile.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage editProfileStage = new Stage();
            Main.editProfileStage = editProfileStage;
            editProfileStage.setScene(new Scene(root));
            editProfileStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the window that lets users edit the profile parameter (Profile.fxml).
     * This method is called from ProfileController.
     */
    public static void closeEditProfile(){
        try {
            Main.editProfileStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Display the window that lets users edit the outside temperature parameter (OutsideTemperature.fxml).
     * This method is called from HomeController.
     */
    public static void showEditOutsideTemperature(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/OutsideTemperature.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage editOutsideTemperatureStage = new Stage();
            Main.editOutsideTemperatureStage = editOutsideTemperatureStage;
            editOutsideTemperatureStage.setScene(new Scene(root));
            editOutsideTemperatureStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the window that lets users edit the temperature parameter (OutsideTemperature.fxml).
     * This method is called from TemperatureController.
     */
    public static void closeEditOutsideTemperature(){
        try {
            Main.editOutsideTemperatureStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Display the window that lets users edit the currently logged-in User location and people's location(Location.fxml).
     * This method is called from HomeController.
     */
    public static void showEditLocation(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/Location.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage editLocationStage = new Stage();
            Main.editLocationStage = editLocationStage;
            editLocationStage.setScene(new Scene(root));
            editLocationStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Close the window that lets users edit the currently logged-in User location and people's location(Location.fxml).
     * This method is called from LocationController.
     */
    public static void closeEditLocation(){
        try {
            Main.editLocationStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void showConfigureTime(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/LightConfigurer.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage editLightStage = new Stage();
            Main.editLightStage = editLightStage;
            editLightStage.setScene(new Scene(root));
            editLightStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void closeEditZoneTemp(){
        try {
            Main.editZoneTemp.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void showEditZoneTemp(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/Zone.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage editZoneTemp = new Stage();
            Main.editZoneTemp = editZoneTemp;
            editZoneTemp.setScene(new Scene(root));
            editZoneTemp.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Close the window that lets users edit the currently logged-in User location and people's location(Location.fxml).
     * This method is called from LocationController.
     */
    public static void closeConfigureTime(){
        try {
            Main.editLightStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // REFACTORING
    /**
     * Display the window that lets users see the clicked room's information (RoomInformation.fxml).
     * This method is called from HomeController.
     */
    public static void showRoomInformation(Room clickedRoom){
        try {
            roomSelectedFromLayout = clickedRoom;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/RoomInformation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage roomInformationStage = new Stage();
            Main.roomInformationStage = roomInformationStage;
            roomInformationStage.setScene(new Scene(root));
            // TODO: Get the selected room object the layout to the room information controller.
            roomInformationStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Close the window that lets users see the clicked room's information (RoomInformation.fxml).
     * This method is called from LocationController.
     */
    public static void closeRoomInformation(){
        try {
            Main.roomInformationStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Display the window that lets users edit the outside temperature parameter (OutsideTemperature.fxml).
     * This method is called from HomeController.
     */
    public static void showEditRoomTemperature(String roomName){
        try {
            ArrayList<ArrayList<Room>> roomGrid = LayoutParser.getGridRooms();
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (roomGrid.get(row).get(col).roomName.equals(roomName)) {
                        roomSelectedFromSHH = roomGrid.get(row).get(col);
                    }
                }
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/RoomTemperature.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage editRoomTemperatureStage = new Stage();
            Main.editRoomTemperatureStage = editRoomTemperatureStage;
            editRoomTemperatureStage.setScene(new Scene(root));
            editRoomTemperatureStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the window that lets users edit the temperature parameter (OutsideTemperature.fxml).
     * This method is called from TemperatureController.
     */
    public static void closeEditRoomTemperature(){
        try {
            Main.editRoomTemperatureStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Display the window that lets users edit the profile parameter (Profile.fxml).
     * This method is called from HomeController.
     */
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}