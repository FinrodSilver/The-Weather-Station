package webb.richard.model;

import javafx.beans.property.FloatProperty;

/**
 * 
 * @author Richard Webb Heat Index Display and Observer
 *
 */
public class HeatIndexDisplay implements Observer, DisplayElement {
	private float heatIndex = 0.0f;
	@SuppressWarnings("unused")
	private WeatherData weatherData;
	private String heatText;

	public HeatIndexDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(FloatProperty t, FloatProperty rh, FloatProperty pressure, FloatProperty wind) {
		heatIndex = computeHeatIndex(t.floatValue(), rh.floatValue());
		display();
	}

	/**
	 * Compute the Heat Index if Heat is greater then 80F and 40% Humidity
	 * 
	 * @param t
	 * @param rh
	 * @return
	 */
	private float computeHeatIndex(float t, float rh) {
		if (t < 80 && rh < 40) {
			float index = 0;
			return index;
		} else {
			float index = (float) ((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh)
					+ (0.00941695 * (t * t)) + (0.00728898 * (rh * rh)) + (0.000345372 * (t * t * rh))
					- (0.000814971 * (t * rh * rh)) + (0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t))
					+ (0.0000291583 * (rh * rh * rh)) + (0.00000142721 * (t * t * t * rh))
					+ (0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh))
					+ 0.000000000843296 * (t * t * rh * rh * rh)) - (0.0000000000481975 * (t * t * t * rh * rh * rh)));
			index = (Math.round(index));
			return index;
		}
	}

	/**
	 * Get the HeatIndex in String Format
	 * 
	 * @return
	 */
	public String getHeatIndex() {
		return heatText;
	}

	/**
	 * Assign the display to the Heat String
	 */
	@Override
	public void display() {
		if (heatIndex == 0) {
			heatText = ("Heat index is negligible");
		} else {
			heatText = ("Heat index is " + heatIndex);
		}
	}
}
