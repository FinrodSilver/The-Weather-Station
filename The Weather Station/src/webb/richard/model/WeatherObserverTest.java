package webb.richard.model;

import org.junit.Test;

@SuppressWarnings("unused")
public class WeatherObserverTest {

	private WeatherData weather;
	private CurrentConditionsDisplay current;
	private ForecastDisplay forecast;
	private HeatIndexDisplay heatIndex;
	private WindChillDisplay windChillIndex;
	private StatisticsDisplay stats;

	@Test
	public void testWeatherObserver() {
		// Default Constructor
	}

	@Test
	public void testSetWeatherData() {
		// Simple setter, no need to test
	}

	@Test
	public void testGetWeatherData() {
		// Simple Getter
	}

	@Test
	public void testInitializeObservables() {
		//Dummy Data to simulate the weatherdata object
			WeatherData weather = new WeatherData(1, 10.0f, 10.0f, 10.0f, 10.0f);
			CurrentConditionsDisplay current = new CurrentConditionsDisplay(weather);
			ForecastDisplay forecast = new ForecastDisplay(weather);
			HeatIndexDisplay heat = new HeatIndexDisplay(weather);
			WindChillDisplay windChillIndex = new WindChillDisplay(weather);
			StatisticsDisplay stats = new StatisticsDisplay(weather);
			this.current = current;
			this.forecast = forecast;
			this.heatIndex = heat;
			this.windChillIndex = windChillIndex;
			this.stats = stats;
	}

}
