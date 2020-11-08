package models;

import java.util.ArrayList;
import java.util.List;

public class Room{
    //Room property variables
    public String roomName = "Unnamed";
    public int graphNumber = 0;
    public int gridCol = -1; // The column coordinate of the room in the grid.
    public int gridRow = -1; // The row coordinate of the room in the grid.

    //Room door variables
    public Door door;

    //Room window object
    public Window window;

    //Room light Variables
    public boolean lights = false; // Lights off (false) or on (true) in the room.
    public int lightsOnTime = 0;
    public int lightsOffTime = 0;

    //Temperature variables
    public double initialTemp = 23.5; // The starting temperature of the room in Celsius.

    //Room User Details
    public Boolean activeProfileIsHere; //Boolean saying whether the active user is in this room.

    //ArrayList<ArrayList<Integer>> windows = new ArrayList<ArrayList<Integer>>(); // windows[which cell?][closed or open?]
    // This ArrayList checks the current room (see graphNumber) against every other cell (free space or other room).
    // All [closed or open?] spots are set as null before the graph is drawn.
    // There are 32 total cells in the grid, of which one is this room.
    // When a window connects this room to another cell directly (cannot be another room), set [closed or open?] to 0 or 1.
    //ArrayList<ArrayList<Integer>> doors = new ArrayList<ArrayList<Integer>>(); // doors[which cell?][closed or open?]
    // Same logic as windows is applied to doors.

    // Complete constructor for Room class.
    public Room(String roomName, int graphNumber, int gridCol, int gridRow, Boolean door, Boolean windowExists, Boolean lights, double initialTemp) {
        this.roomName = roomName;
        this.graphNumber = graphNumber;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        this.window = new Window(windowExists);
        this.door = new Door(true);
        this.lights = lights;
        this.initialTemp = initialTemp;
    }

    // For first pass algorithm, only attributes required are graph number and grid coordinates.
    public Room(int graphNumber, int gridCol, int gridRow) {
        this.graphNumber = graphNumber;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        this.window = new Window(false);
        this.door = new Door(false);
    }
    /**
     * return the roomName attribute
     * @return
     */
    public String getRoomName() {
        return roomName;
    }
    /**
     * setter for roomName attribute
     * @param roomName
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * getter for graphNumber attribute
     * 
     * @return
     */
    public int getGraphNumber() {
        return graphNumber;
    }
    /**
     * setter for graphNumber attribute
     * @param graphNumber
     */
    public void setGraphNumber(int graphNumber) {
        this.graphNumber = graphNumber;
    }

    public void setDoorExists(boolean exists){
        door.setDoorExists(exists);
    }

    public Boolean getDoorExists(){
        return door.getDoorExists();
    }

    public void setDoorStatus(Boolean isOpen) {
        door.setDoorIsOpen(isOpen);
    }
    public Boolean getDoorStatus(){
        return door.getDoorIsOpen();
    }

    public void setWindowExists(boolean exists) {
    	window.setWindowExists(exists);
    }

    public Boolean getWindowExists() {
        return window.getWindowExists();
    }

    public void setWindowStatus(boolean isOpen) {
    	window.setWindowIsOpen(isOpen);
    }

    public Boolean getWindowStatus() {
        return window.getWindowIsOpen();
    }

    public Boolean getLights() {
        return lights;
    }

    public void setLights(Boolean lights) {
        this.lights = lights;
    }

    public int getLightsOffTime(){ return lightsOffTime; }

    public void setLightsOffTime(int lightsOffTime){ this.lightsOffTime = lightsOffTime; }

    public int getLightsOnTime(){ return lightsOnTime; }

    public void setLightsOnTime(int lightsOnTime){ this.lightsOnTime = lightsOnTime; }

    /**
     * getter for intialTemp
     * @return
     */
    public double getInitialTemp() {
        return initialTemp;
    }
    /**
     * setter for intialTemp
     * @param initialTemp
     */
    public void setInitialTemp(double initialTemp) {
        this.initialTemp = initialTemp;
    }
    
    // toString() method for printing purposes.
    public String toString() {
        return ("[" + this.graphNumber + "]");
    }
}
