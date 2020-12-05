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

    public static void addPeriodsToZone(String zoneName, double periodOneTemp, double periodTwoTemp, double periodThreeTemp){
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

    public static void removeRoomFromZone(String zoneName, String roomName) {
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                zone.removeRoomFromZone(roomName);
                break;
            }
        }
    }

    public static ArrayList<String> getRoomsInZone(String zoneName){
        ArrayList<String> rooms = new ArrayList<String>();
        for (Zone zone : zones) {
            if (zone.getZoneName().equals(zoneName)) {
                return zone.getRooms();
            }
        }
        return rooms;
    }

    public static ArrayList<Zone> getZones() {
        return zones;
    }
}
