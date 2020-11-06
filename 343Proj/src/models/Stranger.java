package models;

public class Stranger extends Profile{
    public Stranger(String profileName){
        this.profileName = profileName;
        ActiveUser.setActiveUser(profileName, "Stranger");
    }
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
