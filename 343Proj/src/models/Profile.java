package models;

public class Profile {
    private String profileName;
    private String profileType;

    public Profile(){
        profileName = "";
        profileType = "";
    }

    public Profile(String profileName, String profileType){
        this.profileName = profileName;
        this.profileType = profileType;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }
}
