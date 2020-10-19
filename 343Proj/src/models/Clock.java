package models;

/**
 * The type Clock.
 */
public class Clock {
    /**
     * Declaring private Clock attributes.
     */
    private int hour;
    private int minute;
    private int second;

    /**
     * Instantiates a new Clock.
     */
    public Clock(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    /**
     * Instantiates a new Clock.
     *
     * @param customHour   the custom hour
     * @param customMinute the custom minute
     * @param customSecond the custom second
     */
    public Clock(int customHour, int customMinute, int customSecond){
        this.hour = customHour;
        this.minute = customMinute;
        this.second = customSecond;
    }

    /**
     * Gets hour.
     *
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets hour.
     *
     * @param hour the hour
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Gets minute.
     *
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets minute.
     *
     * @param minute the minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Gets second.
     *
     * @return the second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Sets second.
     *
     * @param second the second
     */
    public void setSecond(int second) {
        this.second = second;
    }
}