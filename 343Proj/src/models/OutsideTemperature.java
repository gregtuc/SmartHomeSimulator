package models;
/**
 * The type Clock.
 */
public class OutsideTemperature {
    /**
     * Declaring private Clock attributes.
     */
    private double temperature;

    /**
     * Instantiates a new Clock.
     */
    public OutsideTemperature(){
        this.temperature = 0.0;
    }
    /**
     * Instantiates a new Clock.
     *
     * @param customOutsideTemperature   the custom temperature
     */
    public OutsideTemperature(double customOutsideTemperature){
        this.temperature = customOutsideTemperature;
    }
    /**
     * Gets the outside temperature.
     *
     * @return the temperature
     */
	public double getTemperature() {
		return temperature;
	}
    /**
     * Sets temperature.
     *
     * @param second the outside temperature
     */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
