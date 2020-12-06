package controllers;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.*;
import utility.CommandLogger;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * The type Profile controller.
 * The ProfileController class contains methods related to Profiles in the application. It controls the Profile.fxml page.
 * The HomeController class can communicate with the ProfileController class by fetching values via the getActiveProfileName() and getAllProfiles() methods.
 */
public class ProfileController implements Initializable {
    public static ObservableList<String> profileList = FXCollections.observableArrayList();

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
     * The editUserInput ComboBox.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public ComboBox<String> editUserInput = new ComboBox<String>();
    /**
     * The loginUserInput TextField.
     * FXML element. The variable name matches the id of the fxml element and creates an association.
     */
    public ComboBox<String> loginUserInput = new ComboBox<String>();
    public ComboBox<String> permissionInput = new ComboBox<String>();

    //Initialize runs immediately when the page loads.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Populate createUserTypeInput ComboBox.
        createUserTypeInput.getItems().add("Parent");
        createUserTypeInput.getItems().add("Child");
        createUserTypeInput.getItems().add("Guest");
        createUserTypeInput.getItems().add("Stranger");
        //Populate Permissions ComboBox.
        permissionInput.getItems().add("Core");
        permissionInput.getItems().add("Security");
        // Populating the ComboBoxes for existing profiles.
        try {
            refreshProfileList();
            editUserInput.setItems(ProfileController.profileList);
            loginUserInput.setItems(ProfileController.profileList);
        } catch (Exception e) {
            System.out.println("An error occurred during refreshProfileList.");
            e.printStackTrace();
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
    public void createProfile(MouseEvent mouseEvent) throws Exception, IOException, SQLException, ClassNotFoundException {
        //Assign the active user's name to the singleton instance.
        // Create profile text file and write its type.
        //Assign the active user's name to the singleton instance.
        // Create profile text file and write its type.
        //Assign the active user's name to the singleton instance.
        // Create profile text file and write its type.
        //Assign the active user's name to the singleton instance.
        // Create profile text file and write its type.
        switch (createUserTypeInput.getValue()) {
            case "Parent" -> {
                Parent parent = new Parent(createUsernameInput.getText());
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                createProfileFile(createUsernameInput.getText(), "Parent");
            }
            case "Child" -> {
                Child child = new Child(createUsernameInput.getText());
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                createProfileFile(createUsernameInput.getText(), "Child");
            }
            case "Guest" -> {
                Guest guest = new Guest(createUsernameInput.getText());
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                createProfileFile(createUsernameInput.getText(), "Guest");
            }
            case "Stranger" -> {
                Stranger stranger = new Stranger(createUsernameInput.getText());
                ActiveUser.setActiveUser(createUsernameInput.getText(), createUserTypeInput.getValue());
                createProfileFile(createUsernameInput.getText(), "Stranger");
            }
        }
        CommandLogger.logCommand("SHC", "New profile created called " + ActiveUser.getActiveUsername() + " and it has been signed in.");
        // Update the ComboBoxes with the new profiles.
        refreshProfileList();
        editUserInput.setItems(ProfileController.profileList);
        loginUserInput.setItems(ProfileController.profileList);
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    /**
     * Edit existing profiles inside of the profiles ArrayList.
     *
     * @param mouseEvent the mouse event
     */
    public void deleteProfile(MouseEvent mouseEvent) throws Exception {
        //Get the selected username value from the input form.
        String usernameToDelete = (String) editUserInput.getValue();

        try {
            // Iterate through the profiles in the "profiles" directory. Once found, delete matching file.
            File profilesFolder = new File("343Proj/src/profiles/");
            File[] listOfFiles = profilesFolder.listFiles();
            assert listOfFiles != null;
            for (File file : listOfFiles) {
                String filename = file.getName();
                if (filename.equals(usernameToDelete + ".txt")) {
                    file.delete();
                    System.out.println("File " + usernameToDelete + ".txt has been successfully deleted!");
                    break;
                }
            }
            // If the user was logged in and deleted their own profile, set active user to empty.
            if (usernameToDelete.equals(ActiveUser.getActiveUsername())) {
                ActiveUser.setActiveUser("", "");
                System.out.println("Since this user is the active user, they have been logged out due to deletion.");
            }
            // Update the ComboBoxes with the new profiles.
            refreshProfileList();
            editUserInput.setItems(ProfileController.profileList);
            loginUserInput.setItems(ProfileController.profileList);
            CommandLogger.logCommand("SHC", "Profile deleted called " + usernameToDelete + ".");
        } catch (Exception e) {
            System.out.println("An error occurred during when attempting to delete a profile.");
            e.printStackTrace();
        }
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    public static Boolean userHasCorePermissions(String name) {
        try {
            // Create file variable and scanner.
            File f = new File("343Proj/src/profiles/" + name + ".txt");
            Scanner sc = new Scanner(f);
            StringBuilder buffer = new StringBuilder();

            //Assign existing text file to fileContents and close the scanner.
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("Core")) {
                    return true;
                }
            }
            sc.close();
            return false;
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    public static Boolean userHasSecurityPermissions() {
        String name = ActiveUser.getActiveUsername();
        try {
            // Create file variable and scanner.
            File f = new File("343Proj/src/profiles/" + name + ".txt");
            Scanner sc = new Scanner(f);
            StringBuilder buffer = new StringBuilder();

            //Assign existing text file to fileContents and close the scanner.
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("Security")) {
                    return true;
                }
            }
            sc.close();
            return false;
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    public void removePermission() {
        //Get the selected username value from the input form.
        String name = editUserInput.getValue();
        String permission = permissionInput.getValue();
        try {
            // Create file variable and scanner.
            File f = new File("343Proj/src/profiles/" + name + ".txt");
            Scanner sc = new Scanner(f);
            StringBuilder buffer = new StringBuilder();

            //Assign existing text file to fileContents and close the scanner.
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()).append(System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();

            //Remove the specified permission from the text file and assign the new file to newFile.
            String newFile = fileContents.replaceAll(permission, "");

            //Write the new contents to the file and close the writer.
            FileWriter writer = new FileWriter("343Proj/src/profiles/" + name + ".txt", false);
            writer.write(newFile);
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    public void addPermission() {
        //Get the selected username value from the input form.
        String name = editUserInput.getValue();
        String permission = permissionInput.getValue();
        try {
            // Create file variable and scanner.
            File f = new File("343Proj/src/profiles/" + name + ".txt");
            Scanner sc = new Scanner(f);
            StringBuilder buffer = new StringBuilder();

            //Assign existing text file to fileContents and close the scanner.
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()).append(System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();

            //If it doesn't exist, add the specified permission from the text file and assign the new file to newFile.
            String newFile = "";
            if (!fileContents.contains(permission)) {
                newFile = fileContents + "\n" + permission;
                //Write the new contents to the file and close the writer.
                FileWriter writer = new FileWriter("343Proj/src/profiles/" + name + ".txt", false);
                writer.write(newFile);
                writer.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    /**
     * Set the activeProfile attribute to a specified user profile.
     *
     * @param mouseEvent the mouse event
     */
    public void loginProfile(MouseEvent mouseEvent) throws Exception {
        //Get the selected username value from the input form.
        String usernameToLogin = loginUserInput.getValue();

        //TODO: Check if the login username actually exists in the text file.

        if (!usernameToLogin.equals(ActiveUser.getActiveUsername())) {
            try {
                // Iterate through the profiles in the "profiles" directory. Once found, login.
                File profilesFolder = new File("343Proj/src/profiles/");
                File[] listOfFiles = profilesFolder.listFiles();
                assert listOfFiles != null;
                for (File file : listOfFiles) {
                    String filename = file.getName();
                    if (filename.equals(usernameToLogin + ".txt")) {
                        Scanner sc = new Scanner(file);
                        String currentLine = null;
                        String[] words = new String[0];
                        currentLine = sc.nextLine();
                        words = currentLine.split(" ");
                        ActiveUser.setActiveUser(usernameToLogin, words[1]);
                        System.out.println(usernameToLogin + "'s file (" + words[1] + ") has been found and is now the active user!");
                        break;
                    }
                }
                CommandLogger.logCommand("SHC", "Profile logged in. Username is " + ActiveUser.getActiveUsername() + ".");
                // Update the ComboBoxes with the new profiles.
                refreshProfileList();
                editUserInput.setItems(ProfileController.profileList);
                loginUserInput.setItems(ProfileController.profileList);
            } catch (Exception e) {
                System.out.println("An error occurred while attempting to login with " + usernameToLogin + "'s profile.");
                e.printStackTrace();
            }
        }
        // If the user is trying to log in with the same user twice, cancel the operation.
        else {
            System.out.println("You are already logged in as" + usernameToLogin + ", login attempt cancelled.");
        }

        //Call closeEditProfile from Main and return to the primary stage.
        Main.closeEditProfile();
    }

    /**
     * A function to create a file of name "[name].txt".
     *
     * @param name the file's name
     */
    public void createProfileFile(String name, String userType) throws IOException {
        try {
            // Create file variable.
            File f = new File("343Proj/src/profiles/" + name + ".txt");
            // Create new file.
            // If the file does not exist yet...
            if (f.createNewFile()) // Create the file.
                System.out.println("File " + name + ".txt has been successfully created!");
            else // The file already exists, no action is taken.
                System.out.println("File " + name + ".txt already exists in this directory.");
            // Write to newly created text file.
            try {
                // Set append to "false" in order to overwrite permissions later.
                FileWriter myWriter = new FileWriter("343Proj/src/profiles/" + createUsernameInput.getText() + ".txt", false);
                myWriter.write("Type " + createUserTypeInput.getValue() + "\n");
                if (userType.equals("Parent") || userType.equals("Child") || userType.equals("Guest")) {
                    myWriter.write("Core" + "\n");
                }
                if (userType.equals("Parent") || userType.equals("Child")) {
                    myWriter.write("Security" + "\n");
                }
                myWriter.close();
                CommandLogger.logCommand("SHC", "New profile file has been created called " + name + ".txt.");
            } catch (IOException e) {
                System.out.println("An error occurred during the output process.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void refreshProfileList() throws Exception {
        try {
            File profilesFolder = new File("343Proj/src/profiles/");
            File[] listOfFiles = profilesFolder.listFiles();
            profileList.clear();
            assert listOfFiles != null;
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String[] words1 = (file.getName().split("/"));
                    String[] words2 = words1[0].split("\\.");
                    profileList.add(words2[0]);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during refreshProfileList.");
            e.printStackTrace();
        }
    }
}