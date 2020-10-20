package models;
/**
 * The type OutsideTemperature.
 */
public class OutsideTemperature {
    /**
     * Declaring private temperature attributes.
     */
    private double temperature;

    /**
     * Instantiates a new temperature.
     */
    public OutsideTemperature(){
        this.temperature = 0.0;
    }
    /**
     * Instantiates a new temperature.
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
