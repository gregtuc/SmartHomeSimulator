package models;

public class Child extends Profile{
    public Child(String profileName){
        this.profileName = profileName;
        ActiveUser.setActiveUser(profileName, "Child");
    }
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
