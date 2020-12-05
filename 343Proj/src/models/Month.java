package models;

import java.util.AbstractMap;
import java.util.Map;

public class Month {
    //The month
    private String month;
    //A map of the month to the season
    Map<String,String> season = Map.ofEntries(new AbstractMap.SimpleEntry<String,String>("November","Winter"),
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
    
    public Month() {
    	this.month = "September";
    }
    public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Map<String, String> getSeasonMap() {
		return season;
	}
	public String getSeason(String month) {
		return season.get(month);
	}

}
