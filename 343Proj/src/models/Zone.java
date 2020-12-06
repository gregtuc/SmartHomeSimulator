package models;

import java.util.ArrayList;

public class Zone {

    private String zoneName;
    private String zoneType;
    private double firstPeriodTemp;
    private double secondPeriodTemp;
    private double thirdPeriodTemp;
    private Boolean temperatureRegulationActive;
    private ArrayList<String> rooms = new ArrayList<String>();

    public Zone(String zoneName, String zoneType) {
        this.zoneName = zoneName;
        this.zoneType = zoneType;
    }

    public void addRoomToZone(String room) {
        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }

    public void setFirstPeriodTemp(double firstPeriodTemp) {
        this.firstPeriodTemp = firstPeriodTemp;
    }

    public void setSecondPeriodTemp(double secondPeriodTemp) {
        this.secondPeriodTemp = secondPeriodTemp;
    }

    public void setThirdPeriodTemp(double thirdPeriodTemp) {
        this.thirdPeriodTemp = thirdPeriodTemp;
    }

    public double getFirstPeriodTemp() {
        return firstPeriodTemp;
    }

    public double getSecondPeriodTemp() {
        return secondPeriodTemp;
    }

    public double getThirdPeriodTemp() {
        return thirdPeriodTemp;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void removeRoomFromZone(String room) {
        rooms.remove(room);
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public ArrayList<String> getRooms() {
        return rooms;
    }

    public Boolean getTemperatureRegulationActive() {
        return temperatureRegulationActive;
    }

    public void setTemperatureRegulationActive(Boolean temperatureRegulationActive) {
        this.temperatureRegulationActive = temperatureRegulationActive;
    }
}
