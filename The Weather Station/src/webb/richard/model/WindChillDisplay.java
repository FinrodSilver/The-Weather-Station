package webb.richard.model;

import javafx.beans.property.FloatProperty;

/**
 * 
 * @author Richard Webb Wind Chill Display and Observer
 */
public class WindChillDisplay implements Observer, DisplayElement {

	// Variable
	private float windChill = 0.0f;
	@SuppressWarnings("unused")
	private WeatherData weatherData;
	private String windText;

	public WindChillDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	/**
	 * Compute the Wind chill
	 * 
	 * @param temp
	 * @param wind
	 * @return
	 */
	private float computeWindChill(float temp, float wind) {
		if (temp > 40) {
			float chill = 0;
			return chill;
		}
		float chill = (float) (35.74 + 0.6215 * temp - 35.75 * Math.pow(wind, .16)
				+ 0.4275 * temp * Math.pow(wind, 0.16));
		chill = (Math.round(chill)); // Round the long float number
		return chill;
	}

	/**
	 * Get the Wind Chill in String Format
	 * 
	 * @return
	 */
	public String getWindChill() {
		return windText;
	}

	/**
	 * Assign the display to a variable
	 */
	@Override
	public void display() {
		if (windChill == 0) {
			windText = ("The Wind Chill is negligible");
		} else {
			windText = ("The Wind Chill is " + windChill);
		}
	}

	@Override
	public void update(FloatProperty temp, FloatProperty humidity, FloatProperty pressure, FloatProperty wind) {
		windChill = computeWindChill(temp.floatValue(), wind.floatValue());
		display();

	}

}
