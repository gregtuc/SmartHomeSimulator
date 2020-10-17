package models;

/**
 * The type Profile.
 */
public class Profile {
    /**
     * Declaring private Clock attributes.
     */
    private String profileName;
    private String profileType;

    /**
     * Instantiates a new Profile.
     */
    public Profile(){
        profileName = "";
        profileType = "";
    }

    /**
     * Instantiates a new Profile.
     *
     * @param profileName the profile name
     * @param profileType the profile type
     */
    public Profile(String profileName, String profileType){
        this.profileName = profileName;
        this.profileType = profileType;
    }

    /**
     * Gets profile name.
     *
     * @return the profile name
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Sets profile name.
     *
     * @param profileName the profile name
     */
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    /**
     * Gets profile type.
     *
     * @return the profile type
     */
    public String getProfileType() {
        return profileType;
    }

    /**
     * Sets profile type.
     *
     * @param profileType the profile type
     */
    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }
}
