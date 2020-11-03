package controllers;

import Main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Profile controller.
 * The ProfileController class contains methods related to Profiles in the application. It controls the Profile.fxml page.
 * The HomeController class can communicate with the ProfileController class by fetching values via the getActiveProfileName() and getAllProfiles() methods.
 */
public class ProfileController implements Initializable {

    /**
     * The createUsernameInput TextField.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public TextField createUsernameInput = new TextField();
    /**
     * The createUserTypeInput TextField.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public ComboBox<String> createUserTypeInput = new ComboBox<String>();
    /**
     * The editUserInput TextField.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public ComboBox<Object> editUserInput = new ComboBox<>();
    /**
     * The loginUserInput TextField.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public ComboBox<String> loginUserInput = new ComboBox<String>();

    //Initialize runs immediately when the page loads.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Populate createUserTypeInput ComboBox.
        createUserTypeInput.getItems().add("Parent");
        createUserTypeInput.getItems().add("Child");
        createUserTypeInput.getItems().add("Guest");
        createUserTypeInput.getItems().add("Stranger");
    }

    /**
     * Add a new profile to the profiles ArrayList.
     *
     * @param mouseEvent the mouse event
     * @throws IOException            the io exception
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void createProfile(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        switch (createUserTypeInput.getValue()) {
            case "Parent":
                Parent parent = new Parent(createUsernameInput.getText());
                //Assign the active user's name to the singleton instance.
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                //TODO: Save the new object to the text file.
                break;
            case "Child":
                Child child = new Child(createUsernameInput.getText());
                //Assign the active user's name to the singleton instance.
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                //TODO: Save the new object to the text file.
                break;
            case "Guest":
                Guest guest = new Guest(createUsernameInput.getText());
                //Assign the active user's name to the singleton instance.
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                //TODO: Save the new object to the text file.
                break;
            case "Stranger":
                Stranger stranger = new Stranger(createUsernameInput.getText());
                //Assign the active user's name to the singleton instance.
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                //TODO: Save the new object to the text file.
                break;
        }
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    /**
     * Edit existing profiles inside of the profiles ArrayList.
     *
     * @param mouseEvent the mouse event
     */
    public void editProfile(MouseEvent mouseEvent){
        //Get the selected username value from the input form.
        String usernameToDelete = (String) editUserInput.getValue();

        //TODO: Check if the username to be deleted actually exists in the text file.

        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    /**
     * Set the activeProfile attribute to a specified user profile.
     *
     * @param mouseEvent the mouse event
     */
    public void loginProfile(MouseEvent mouseEvent){
        //Get the selected username value from the input form.
        String usernameToLogin = loginUserInput.getValue();

        //TODO: Check if the login username actually exists in the text file.

        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }
}