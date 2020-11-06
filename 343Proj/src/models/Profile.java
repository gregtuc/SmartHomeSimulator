package models;

/**
 * The type Profile.
 */
public abstract class Profile {
    /**
     * Declaring private Clock attributes.
     */
    protected String profileName;

    /**
     * Gets profile name.
     *
     * @return the profile name
     */
    public abstract String getProfileName();

    /**
     * Sets profile name.
     *
     * @param profileName the profile name
     */
    public abstract void setProfileName(String profileName);
}
