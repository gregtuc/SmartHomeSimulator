package main;

import controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    static Stage primaryStage;
    static Stage editTimeStage;
    static Stage editProfileStage;

    //Create and display the primary application window (Home.fxml).
    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../views/Home.fxml"));
        primaryStage.setTitle("Smart Home Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //Display the window that lets users edit the time parameter (Clock.fxml).
    //This method is called from HomeController.
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

    //Close the window that lets users edit the time parameter (Clock.fxml).
    //This method is called from ClockController.
    public static void closeEditTime(){
        try {
            Main.editTimeStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Display the window that lets users edit the profile parameter (Profile.fxml).
    //This method is called from HomeController.
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

    //Close the window that lets users edit the profile parameter (Profile.fxml).
    //This method is called from ProfileController.
    public static void closeEditProfile(){
        try {
            Main.editProfileStage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Begins the execution.
    public static void main(String[] args) {
        launch(args);
    }
}