package Main.java;
import java.util.*;

public class Room {
    String roomName = "Unnamed";
    int graphNumber = 0;
    ArrayList<ArrayList<Integer>> windows = new ArrayList<ArrayList<Integer>>(); // windows[which cell?][closed or open?]
    // This ArrayList checks the current room (see graphNumber) against every other cell (free space or other room).
    // All [closed or open?] spots are set as null before the graph is drawn.
    // There are 32 total cells in the grid, of which one is this room.
    // When a window connects this room to another cell directly (cannot be another room), set [closed or open?] to 0 or 1.
    ArrayList<ArrayList<Integer>> doors = new ArrayList<ArrayList<Integer>>(); // doors[which cell?][closed or open?]
    // Same logic as windows is applied to doors.
    Boolean lights = false; // Lights off (false) or on (true) in the room.
    int numLights = 0; // Total number of lights in the room.
    double initialTemp = 23.5;

    public Room(String roomName, int graphNumber, ArrayList<ArrayList<Integer>> windows,
                ArrayList<ArrayList<Integer>> doors, Boolean lights, int numLights, double initialTemp) {
        this.roomName = roomName;
        this.graphNumber = graphNumber;
        this.windows = windows;
        this.doors = doors;
        this.lights = lights;
        this.numLights = numLights;
        this.initialTemp = initialTemp;
    }
}
