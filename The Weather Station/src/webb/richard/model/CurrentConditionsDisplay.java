package webb.richard.model;

import javafx.beans.property.FloatProperty;

/**
 * Current Conditions Observer, Display class for the Weather Station
 * 
 * @author Richard Webb
 *
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

	private FloatProperty temperature;
	private FloatProperty humidity;
	@SuppressWarnings("unused")
	private Subject weatherData;
	private String c;

	/**
	 * Default Constructor
	 * 
	 * @param weatherData
	 */
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	/**
	 * Update for the Observer
	 */
	@Override
	public void update(FloatProperty temperature, FloatProperty humidity, FloatProperty pressure,
			FloatProperty windSpeed) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}

	/**
	 * Get the String statement for the Conditions
	 * 
	 * @return
	 */
	public String getCondition() {
		return c;
	}

	/**
	 * Assign String statement to a variable String
	 */
	@Override
	public void display() {

		this.c = ("Current conditions: " + temperature.getValue().toString() + "F degrees and "
				+ humidity.getValue().toString() + "% humidity");

	}
}
