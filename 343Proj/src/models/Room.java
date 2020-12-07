package models;

import security.TemperatureObserver;
import utility.CommandLogger;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Room {
    //Room property variables
    public String roomName = "Unnamed";
    public int graphNumber = 0;
    public int gridCol = -1; // The column coordinate of the room in the grid.
    public int gridRow = -1; // The row coordinate of the room in the grid.
    public Boolean targetTempReached = false;
    public Boolean manualOverrideActivated = false;
    //public String iconFile ;

    //Room door variables
    public Door door;

    //Room window object
    public Window window;

    //Room light Variables
    public boolean lights = false; // Lights off (false) or on (true) in the room.
    public String lightsOnTime = "";
    public String lightsOffTime = "";

    public int numberOfAutoWindowOpenAlertSent = 0;
    //Temperature variables
    public double initialTemp = 23.5; // The starting temperature of the room in Celsius.

    //Room User Details
    public Boolean activeProfileIsHere = false; //Boolean saying whether the active user is in this room.
    public Boolean personIsHere = false;

    // For first pass algorithm, only attributes required are graph number and grid coordinates.
    public Room(int graphNumber, int gridCol, int gridRow) {
        this.graphNumber = graphNumber;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        this.window = new Window(false);
        this.door = new Door(false);
    }

    public Room(){};

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

    //Room methods.
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    //Graph methods.
    public int getGraphNumber() {
        return graphNumber;
    }
    public void setGraphNumber(int graphNumber) {
        this.graphNumber = graphNumber;
    }

    //Door methods.
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

    //Window methods.
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

    //Light methods.
    public Boolean getLights() {
        return lights;
    }
    public void setLights(Boolean lights) {
        this.lights = lights;
    }
    public String getLightsOffTime(){ return lightsOffTime; }
    public void setLightsOffTime(String lightsOffTime){ this.lightsOffTime = lightsOffTime; }
    public String getLightsOnTime(){ return lightsOnTime; }
    public void setLightsOnTime(String lightsOnTime){ this.lightsOnTime = lightsOnTime; }

    //Temperature methods.
    public double getInitialTemp() {
        return initialTemp;
    }
    public void setInitialTemp(double initialTemp) {
        this.initialTemp = initialTemp;
    }

    //toString method.
    public String toString() {
        return ("[" + this.graphNumber + "]");
    }

    public Boolean getActiveProfileIsHere() {
        return activeProfileIsHere;
    }

    public void setActiveProfileIsHere(Boolean activeProfileIsHere) {
        this.activeProfileIsHere = activeProfileIsHere;
    }

    public Boolean getPersonIsHere() {
        return personIsHere;
    }

    public void setPersonIsHere(Boolean personIsHere) throws IOException {
        this.personIsHere = personIsHere;
    }

    public Boolean getManualOverrideActivated() {
        return manualOverrideActivated;
    }

    public void setManualOverrideActivated(Boolean manualOverrideActivated) {
        this.manualOverrideActivated = manualOverrideActivated;
    }
}
