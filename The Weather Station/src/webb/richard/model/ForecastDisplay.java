package webb.richard.model;

import javafx.beans.property.FloatProperty;

/**
 * The Forecast Display Observer
 * 
 * @author Richard Webb
 *
 */
public class ForecastDisplay implements Observer, DisplayElement {
	private float currentPressure = 29.92f;
	private float lastPressure;
	@SuppressWarnings("unused")
	private WeatherData weatherData;
	private String forecast;

	public ForecastDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(FloatProperty temp, FloatProperty humidity, FloatProperty pressure, FloatProperty windSpeed) {
		lastPressure = currentPressure;
		currentPressure = pressure.floatValue();
		display();
	}

	/**
	 * Get the Forecast, in String format
	 * 
	 * @return
	 */
	public String getForecast() {
		return forecast;
	}

	/**
	 * Assign the String data to the forecast variable
	 */
	@Override
	public void display() {
		forecast = ("Forecast: ");
		if (currentPressure > lastPressure) {
			forecast += ("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			forecast += ("More of the same");
		} else if (currentPressure < lastPressure) {
			forecast += ("Watch out for cooler, rainy weather");
		}
	}
}
