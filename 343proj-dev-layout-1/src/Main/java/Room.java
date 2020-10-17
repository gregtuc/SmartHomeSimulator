package Main.java;
import java.util.*;

public class Room {
    String roomName = "Unnamed";
    int graphNumber = 0;
    Boolean door = false; // Whether this room's door to the outside is closed or open.
    Boolean window = false; // Whether this room's window to the outside is closed or open.
    Boolean lights = false; // Lights off (false) or on (true) in the room.
    // int numLights = 0; // Total number of lights in the room.
    double initialTemp = 23.5; // The starting temperature of the room in Celsius.

    //ArrayList<ArrayList<Integer>> windows = new ArrayList<ArrayList<Integer>>(); // windows[which cell?][closed or open?]
    // This ArrayList checks the current room (see graphNumber) against every other cell (free space or other room).
    // All [closed or open?] spots are set as null before the graph is drawn.
    // There are 32 total cells in the grid, of which one is this room.
    // When a window connects this room to another cell directly (cannot be another room), set [closed or open?] to 0 or 1.
    //ArrayList<ArrayList<Integer>> doors = new ArrayList<ArrayList<Integer>>(); // doors[which cell?][closed or open?]
    // Same logic as windows is applied to doors.

    // Complete constructor for Room class.
    public Room(String roomName, int graphNumber, Boolean door, Boolean window, Boolean lights, double initialTemp) {
        this.roomName = roomName;
        this.graphNumber = graphNumber;
        this.door = door;
        this.window = window;
        this.lights = lights;
        // this.numLights = numLights;
        this.initialTemp = initialTemp;
    }
    // For first pass algorithm, only attribute required is graph (grid) number.
    public Room(int graphNumber) {
        this.graphNumber = graphNumber;
    }

    public String toString() {
        return ("[" + this.graphNumber + "]");
    }
}
