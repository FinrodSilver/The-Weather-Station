package webb.richard.model;
/**
 * 
 * @author Richard Webb
 * WeatherObserver Class
 *
 */
public class WeatherObserver {

	//Create variables to hold the observers
	private WeatherData weather;
	private CurrentConditionsDisplay current;
	private ForecastDisplay forecast;
	private HeatIndexDisplay heatIndex;
	private WindChillDisplay windChillIndex;
	private StatisticsDisplay stats;
	
	/**
	 * Default Constructor
	 */
	public WeatherObserver() {
	}
	/**
	 * Set Weather Object
	 * @param weather
	 */
	public void setWeatherData(WeatherData weather) {
		this.weather = weather;
	}
	/**
	 * Get Weather Object
	 * @return
	 */
	public WeatherData getWeatherData() {
		return this.weather;
	}
	/**
	 * Initialize all the observers
	 */
	public void initializeObservables() {
		CurrentConditionsDisplay current = new CurrentConditionsDisplay(weather);
		ForecastDisplay forecast = new ForecastDisplay(weather) ;
		HeatIndexDisplay heat = new HeatIndexDisplay(weather);
		WindChillDisplay windChillIndex = new WindChillDisplay(weather);
		StatisticsDisplay stats = new StatisticsDisplay(weather);
		this.current = current;
		this.forecast = forecast;
		this.heatIndex = heat;
		this.windChillIndex = windChillIndex;
		this.stats = stats;
	}
	/**
	 * Get Current Conditions
	 * @return
	 */
	public CurrentConditionsDisplay getCurrent() {
		return current;
	}
	/**
	 * Get Forecast
	 * @return
	 */
	public ForecastDisplay getForecast() {
		return forecast;
	}
	/**
	 * Get Heat Index
	 * @return
	 */
	public HeatIndexDisplay getHeatIndex() {
		return heatIndex;
	}
	/**
	 * Get Wind Chill Index
	 * @return
	 */
	public WindChillDisplay getWindChillIndex() {
		return windChillIndex;
	}
	/**
	 * Get the temperature statistics
	 * @return
	 */
	public StatisticsDisplay getStats() {
		return stats;
	}
	
}

