package models;

import java.util.AbstractMap;
import java.util.Map;

/**
 * The type OutsideTemperature.
 */
public class Month {
    /**
     * Declaring private month attributes.
     */
    private static String month;
    /**
     * A map of the 12 seasons to the corresponding season, fall and spring are not included
     */
    static Map<String,String> season = Map.ofEntries(new AbstractMap.SimpleEntry<String,String>("November","Winter"),
    		new AbstractMap.SimpleEntry<String,String>("January","Winter"),
    		new AbstractMap.SimpleEntry<String,String>("February","Winter"),
    		new AbstractMap.SimpleEntry<String,String>("March","Winter"),
    		new AbstractMap.SimpleEntry<String,String>("April","Winter"),
    		new AbstractMap.SimpleEntry<String,String>("May","Summer"),
    		new AbstractMap.SimpleEntry<String,String>("June","Summer"),
    		new AbstractMap.SimpleEntry<String,String>("July","Summer"),
    		new AbstractMap.SimpleEntry<String,String>("August","Summer"),
    		new AbstractMap.SimpleEntry<String,String>("September","Summer"),
    		new AbstractMap.SimpleEntry<String,String>("October","Summer"),
    		new AbstractMap.SimpleEntry<String,String>("December","Winter"));
    /**
     * Instantiates a new month.
     * Sets the month to September as a test to see if the functionality to change months in the UI exists
     *
     */
    public Month() {
    	this.month = "September";
    }
    /**
     * Gets the month
     *
     * @return the month
     */
    public static String getMonth() {
		return month;
	}
    /**
     * Sets month
     *
     * @param month the month
     */
	public void setMonth(String month) {
		this.month = month;
	}
    /**
     * Gets the season map object
     *
     * @return the season map object
     */
	public Map<String, String> getSeasonMap() {
		return season;
	}
    /**
     * Gets the season a month is associated with
     *
     * @param a month
     *
     * @return the season
     */
	public static String getSeason(String month) {
		return season.get(month);
	}

    /**
     * Gets the current season
     *
     * @return the current season
     */
	public static String getCurrentSeason() {
		return Month.getSeason(Month.getMonth());
	}
}
