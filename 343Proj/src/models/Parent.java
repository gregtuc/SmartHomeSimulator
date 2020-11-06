package models;

public class Parent extends Profile{
    public Parent(String profileName){
        this.profileName = profileName;
        ActiveUser.setActiveUser(profileName, "Parent");
    }
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
