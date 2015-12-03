package webb.richard.model;

import javafx.beans.property.FloatProperty;
/**
 * 
 * @author Richard Webb
 *
 */
public class StatisticsDisplay implements Observer, DisplayElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum = 0.0f;
	private int numReadings;
	@SuppressWarnings("unused")
	private WeatherData weatherData;
	private String statistics;

	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(FloatProperty temp, FloatProperty humidity, FloatProperty pressure, FloatProperty windSpeed) {
		tempSum += temp.floatValue();
		numReadings++;

		if (temp.floatValue() > maxTemp) {
			maxTemp = temp.floatValue();
			maxTemp = (Math.round(maxTemp));
		}

		if (temp.floatValue() < minTemp) {
			minTemp = temp.floatValue();
			minTemp = (Math.round(minTemp));
		}

		display();
	}
	
	@Override
	public void display() {
		statistics = ("Avg/Max/Min temperature = " + (Math.round(tempSum / numReadings)) + "/" + maxTemp + "/" + minTemp);
	}
	
	/**
	 * Get the Statistics in String format
	 * @return
	 */
	public String getStatistics() {
		return statistics;
	}

}
