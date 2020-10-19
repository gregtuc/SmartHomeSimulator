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
    int gridCol = -1; // The column coordinate of the room in the grid.
    int gridRow = -1; // The row coordinate of the room in the grid.

    //ArrayList<ArrayList<Integer>> windows = new ArrayList<ArrayList<Integer>>(); // windows[which cell?][closed or open?]
    // This ArrayList checks the current room (see graphNumber) against every other cell (free space or other room).
    // All [closed or open?] spots are set as null before the graph is drawn.
    // There are 32 total cells in the grid, of which one is this room.
    // When a window connects this room to another cell directly (cannot be another room), set [closed or open?] to 0 or 1.
    //ArrayList<ArrayList<Integer>> doors = new ArrayList<ArrayList<Integer>>(); // doors[which cell?][closed or open?]
    // Same logic as windows is applied to doors.

    // Complete constructor for Room class.
    public Room(String roomName, int graphNumber, int gridCol, int gridRow, Boolean door, Boolean window, Boolean lights, double initialTemp) {
        this.roomName = roomName;
        this.graphNumber = graphNumber;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        this.door = door;
        this.window = window;
        this.lights = lights;
        // this.numLights = numLights;
        this.initialTemp = initialTemp;
    }

    // For first pass algorithm, only attributes required are graph number and grid coordinates.
    public Room(int graphNumber, int gridCol, int GridRow) {
        this.graphNumber = graphNumber;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getGraphNumber() {
        return graphNumber;
    }

    public void setGraphNumber(int graphNumber) {
        this.graphNumber = graphNumber;
    }

    public Boolean getDoor() {
        return door;
    }

    public void setDoor(Boolean door) {
        this.door = door;
    }

    public Boolean getWindow() {
        return window;
    }

    public void setWindow(Boolean window) {
        this.window = window;
    }

    public Boolean getLights() {
        return lights;
    }

    public void setLights(Boolean lights) {
        this.lights = lights;
    }

    public double getInitialTemp() {
        return initialTemp;
    }

    public void setInitialTemp(double initialTemp) {
        this.initialTemp = initialTemp;
    }

    // toString() method for printing purposes.
    public String toString() {
        return ("[" + this.graphNumber + "]");
    }
}
