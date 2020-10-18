package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Main.Main;
import models.Profile;
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
     * Create an activeProfile attribute to know which profile is currently logged in.
     */
    static Profile activeProfile = new Profile();

    /**
     * Static ArrayList to store objects of type Profile.
     * This attribute allows the storage of all Profiles created in runtime. The static property will
     * force a single instance to exist and ensure all references to "profiles" will reference the same instance.
     */
    static ArrayList<Profile> profiles = new ArrayList<Profile>();

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

    /**
     * Add a new profile to the profiles ArrayList.
     *
     * @param mouseEvent the mouse event
     * @throws IOException            the io exception
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
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

    /**
     * Edit existing profiles inside of the profiles ArrayList.
     *
     * @param mouseEvent the mouse event
     */
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


    /**
     * Set the activeProfile attribute to a specified user profile.
     *
     * @param mouseEvent the mouse event
     */
    public void loginProfile(MouseEvent mouseEvent){
        //Get the selected username value from the input form.
        String usernameToLogin = loginUserInput.getValue();
        //Set the activeProfile attribute to the matching user.
        activeProfile = profiles.stream()
                .filter(p -> p.getProfileName().equals(usernameToLogin)).findFirst().orElse(null);
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    /**
     * Get active profile name string.
     *
     * @return the string
     */
    public String getActiveProfileName(){
        return activeProfile.getProfileName();
    }

    /**
     * Get all profiles array list.
     *
     * @return the array list
     */
    public ArrayList<Profile> getAllProfiles(){
        return profiles;
    }
}