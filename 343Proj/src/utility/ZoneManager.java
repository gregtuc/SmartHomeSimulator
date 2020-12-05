package utility;

import models.Zone;

import java.util.ArrayList;

public class ZoneManager {

    //Singleton design pattern.
    private static volatile ZoneManager instance = null;

    private ZoneManager() {
    }

    public static ZoneManager getInstance() {
        if (instance == null) {
            synchronized (ZoneManager.class) {
                if (instance == null) {
                    instance = new ZoneManager();
                }
            }
        }
        return instance;
    }

    //ArrayList containing all zones.
    private static ArrayList<Zone> zones = new ArrayList<Zone>();

    //Create a new zone and add it to the static list.
    public static void createZone(String zoneName, String zoneType) {
        zones.add(new Zone(zoneName, zoneType));
    }

    //Delete a zone from the zones ArrayList.
    public static void deleteZone(String zoneName) {
        for (int i = 0; i < zones.size(); i++) {
            if (zones.get(i).getZoneName().equals(zoneName)) {
                zones.remove(i);
                break;
            }
        }
    }

    public static void setZoneTemperatures(String zoneName, double periodOneTemp, double periodTwoTemp, double periodThreeTemp){
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                zone.setFirstPeriodTemp(periodOneTemp);
                zone.setSecondPeriodTemp(periodTwoTemp);
                zone.setThirdPeriodTemp(periodThreeTemp);
                break;
            }
        }
    }

    //Add a room to a zone inside of the zones ArrayList.
    public static void addRoomToZone(String zoneName, String roomName) {
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                zone.addRoomToZone(roomName);
                break;
            }
        }
    }

    //Remove a room from a Zone
    public static void removeRoomFromZone(String zoneName, String roomName) {
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                zone.removeRoomFromZone(roomName);
                break;
            }
        }
    }

    //Get an Arraylist containing the names of all rooms in a specified zone.
    public static ArrayList<String> getRoomsInZone(String zoneName){
        ArrayList<String> rooms = new ArrayList<String>();
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                return zone.getRooms();
            }
        }
        return rooms;
    }

    //Get the temperatures for the periods.
    public static ArrayList<Double> zoneTemperatures(String zoneName){
        ArrayList<Double> temperatures = new ArrayList<Double>();
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                temperatures.add(zone.getFirstPeriodTemp());
                temperatures.add(zone.getSecondPeriodTemp());
                temperatures.add(zone.getThirdPeriodTemp());
                return temperatures;
            }
        }
        return temperatures;
    }

    //Extra Boolean method to check if a zone contains a room. Just in case it is needed for some
    //unique situation.
    public static Boolean checkRoomInZone(String zoneName, String roomName){
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                if(zone.getRooms().contains(roomName)){
                    return true;
                }
            }
        }
        return false;
    }

    public static String getZoneOfRoom(String roomName){
        for (Zone zone : zones) {
            if (zone.getRooms().contains(roomName)) {
                return zone.getZoneName();
            }
        }
        return "";
    }

    //Get a list of all zones
    public static ArrayList<Zone> getZones() {
        return zones;
    }
}
