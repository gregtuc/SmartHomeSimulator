package models;

public class Guest extends Profile{
    public Guest(String profileName){
        this.profileName = profileName;
        ActiveUser.setActiveUser(profileName, "Guest");
    }
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
