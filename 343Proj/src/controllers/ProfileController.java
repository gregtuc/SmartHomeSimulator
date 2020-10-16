package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Main;
import models.Profile;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    //Create an activeProfile attribute to know which profile is currently logged in.
    static Profile activeProfile = new Profile();

    //Create a static profiles ArrayList to store all the profiles.
    static ArrayList<Profile> profiles = new ArrayList<Profile>();

    //Fetching fxml elements.  The name of the variable must correlate with the id assigned in the fxml document.
    public TextField createUsernameInput = new TextField();
    public ComboBox<String> createUserTypeInput = new ComboBox<String>();
    public ComboBox<Object> editUserInput = new ComboBox<>();
    public ComboBox<String> loginUserInput = new ComboBox<String>();

    //Initialize runs immediately when the page loads.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Populate createUserTypeInput ComboBox.
        createUserTypeInput.getItems().add("Adult");
        createUserTypeInput.getItems().add("Child");
        createUserTypeInput.getItems().add("Guest");
        createUserTypeInput.getItems().add("Stranger");

        //Populate editUserInput ComboBox.
        for (Profile value : profiles) {
            editUserInput.getItems().add(value.getProfileName());
        }

        //Populate loginUserInput ComboBox.
        for (Profile profile : profiles) {
            loginUserInput.getItems().add(profile.getProfileName());
        }
    }

    //Add a new profile to the profiles ArrayList.
    public void createProfile(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        //Create temporary newProfile variable.
        Profile newProfile = new Profile(createUsernameInput.getText(), createUserTypeInput.getValue());
        //Store newProfile to the profiles ArrayList.
        profiles.add(newProfile);
        //Sign the new user in.
        activeProfile = newProfile;
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    //Edit existing profiles inside of the profiles ArrayList.
    public void editProfile(MouseEvent mouseEvent){
        //Get the selected username value from the input form.
        String usernameToDelete = (String) editUserInput.getValue();
        //Remove the selected user from the profiles ArrayList if it exists.
        profiles.removeIf(obj -> obj.getProfileName().equals(usernameToDelete));
        //Reset the activeProfile if it matches the deleted user.
        if(activeProfile.getProfileName().equals(usernameToDelete)) activeProfile = new Profile();
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    //Set the activeProfile attribute to a specified user profile.
    public void loginProfile(MouseEvent mouseEvent){
        //Get the selected username value from the input form.
        String usernameToLogin = loginUserInput.getValue();
        //Set the activeProfile attribute to the matching user.
        activeProfile = profiles.stream()
                .filter(p -> p.getProfileName().equals(usernameToLogin)).findFirst().orElse(null);
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    //Accessor method to access activeProfile.
    public String getActiveProfileName(){
        return activeProfile.getProfileName();
    }

    //Accessor method to access profiles ArrayList.
    public ArrayList<Profile> getAllProfiles(){
        return profiles;
    }
}