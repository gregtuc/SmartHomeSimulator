package models;
/**
 * The type AwayModeTemperature
 */
public class AwayModeTemperature {
    /**
     * Declaring private temperature attributes.
     * Values are preset to avoid any calls when the values are null
     */
	private static double summerTemperature = 10.0;
	private static double winterTemperature = 22.0;
    /**
     * Instantiates a new temperature.
     */
	public AwayModeTemperature() {
	}
    /**
     * Gets the summer away temperature.
     *
     * @return the summerTemperature
     */
	public static double getSummerTemperature() {
		return summerTemperature;
	}
    /**
     * Sets summer away temperature.
     *
     * @param summerTemperature the summer away temperature
     */
	public void setSummerTemperature(double summerTemperature) {
		AwayModeTemperature.summerTemperature = summerTemperature;
	}
    /**
     * Gets the winter away temperature.
     *
     * @return the winterTemperature
     */
	public static double getWinterTemperature() {
		return winterTemperature;
	}
    /**
     * Sets winter away temperature.
     *
     * @param winterTemperature the winter away temperature
     */
	public void setWinterTemperature(double winterTemperature) {
		AwayModeTemperature.winterTemperature = winterTemperature;
	}
    /**
     * Gets the away mode temperature based on the season.
     *
     * @return the away mode temperature
     */
	public static double getAwayModeTemperature() {
		if(Month.getCurrentSeason().equals("Winter")) {
			return AwayModeTemperature.winterTemperature;
		}
		else {
			return AwayModeTemperature.summerTemperature;
		}
	}
}
