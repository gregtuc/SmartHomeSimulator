package models;

public class Clock {
    //Declaring private Clock attributes.
    private int hour;
    private int minute;
    private int second;

    //Default Constructor
    public Clock(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }
    //Constructor that accepts values for attributes.
    public Clock(int customHour, int customMinute, int customSecond){
        this.hour = customHour;
        this.minute = customMinute;
        this.second = customSecond;
    }

    //Getters and Setters
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public int getSecond() {
        return second;
    }
    public void setSecond(int second) {
        this.second = second;
    }
}
