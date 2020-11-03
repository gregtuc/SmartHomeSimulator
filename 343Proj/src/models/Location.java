package models;
/**
 * The type Location.
 */
public class Location {
	/**
     *  Declaring private Location attributes.
     */
    private String location;

    /**
     * Instantiates a new Location.
     */
    public Location(){
        this.location = "None";
    }
    /**
     * Instantiates a new Location.
     *
     * @param customLocation  the custom location
     */
    public Location (String customLocation){
        this.location = customLocation;
    }
    /**
     * Gets the location
     *
     * @return the location
     */
	public String getLocation() {
		return location;
	}
    /**
     * Sets location.
     *
     * @param location the location
     */
	public void setLocation(String location) {
		this.location = location;
	}
}
