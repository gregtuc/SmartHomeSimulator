package models;
/**
 * The type OutsideTemperature.
 */
public class OutsideTemperature {
    /**
     * Declaring private temperature attributes.
     */
    private static double temperature;

    /**
     * Instantiates a new temperature.
     */
    public OutsideTemperature(){
        OutsideTemperature.temperature = 0.0;
    }
    /**
     * Instantiates a new temperature.
     *
     * @param customOutsideTemperature   the custom temperature
     */
    public OutsideTemperature(double customOutsideTemperature){
        OutsideTemperature.temperature = customOutsideTemperature;
    }
    /**
     * Gets the outside temperature.
     *
     * @return the temperature
     */
	public static double getTemperature() {
		return temperature;
	}
    /**
     * Sets temperature.
     *
     * @param second the outside temperature
     */
	public void setTemperature(double temperature) {
		OutsideTemperature.temperature = temperature;
	}
}
