package webb.richard.model;

import java.util.ArrayList;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *  Model class for weather data
 * @author Richard Webb
 *
 */
public class WeatherData implements Subject {

	// variables
	private ArrayList<Observer> observers;
	private IntegerProperty key;
	private FloatProperty temperature;
	private FloatProperty humidity;
	private FloatProperty pressure;
	private FloatProperty speed;
	
	// Instance variable for the interface Forecast
	public Forecast forecast;

	/**
	 * Default Constructor
	 * @param key
	 * @param temp
	 * @param humidity
	 * @param pressure
	 * @param speed
	 */
	public WeatherData() {
		observers = new ArrayList<Observer>();
	}
	
	/**
	 * Constructor for a complete weather data Object
	 * @param key
	 * @param temp
	 * @param humidity
	 * @param pressure
	 * @param speed
	 */
	public WeatherData(Integer key, Float temp, Float humidity, Float pressure, Float speed) {
		this.key = new SimpleIntegerProperty(key);
		this.temperature = new SimpleFloatProperty(temp);
		this.humidity = new SimpleFloatProperty(humidity);
		this.pressure = new SimpleFloatProperty(pressure);
		this.speed = new SimpleFloatProperty(speed);
		observers = new ArrayList<Observer>();
	}
	
	/**
	 *  Register an Observer that updates the forcast
	 */
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	/**
	 * Remove an Observer that holds the forecast data
	 */
	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}

	}
	
	/**
	 * Notify the Observers of weather data Change
	 */
	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(temperature, humidity, pressure, speed);
		}

	}	
	
	/**
	 * Pull the data from the database and send it to the Observers
	 * @param temp
	 * @param humidity
	 * @param pressure
	 * @param speed
	 */
	public void pullMeasurements(Integer key, Float temp, Float humidity, Float pressure, Float speed) {
		this.temperature = new SimpleFloatProperty(temp);
		this.humidity = new SimpleFloatProperty(humidity);
		this.pressure = new SimpleFloatProperty(pressure);
		this.speed = new SimpleFloatProperty(speed);
		measurementsChanged();
	}
	
	/**
	 * Call the notifyObservers() for changes in data
	 */
	public void measurementsChanged() {
		notifyObservers();
	}
	

	//Getters and Setters
	/**
	 * Get the Primary Key of the Database
	 * @return
	 */
	public Integer getKey() {
		return key.get();
	}
	/**
	 * Set the Primary key of the Database
	 * @param key
	 */
	public void setKey(Integer key) {
		this.key.set(key);
		measurementsChanged();
	}
	/**
	 * Get the Primary key of the Database
	 * @return
	 */
	public IntegerProperty keyProperty() {
		return key;
	}
	/**
	 * Get the Temperature
	 * @return
	 */
	public Float getTemperature() {
		return temperature.get();
	}
	/**
	 * Set the Temperature
	 * @param temperature
	 */
	public void setTemperature(Float temperature) {
		this.temperature.set(temperature);
		measurementsChanged();
	}
	/**
	 * Get the Temperature
	 * @return
	 */
	public FloatProperty tempFloatProperty() {
		return temperature;
	}
	/**
	 * Get the Humidity
	 * @return
	 */
	public Float getHumidity() {
		return humidity.get();
	}
	/**
	 * Set the Humidity
	 * @param humidity
	 */
	public void setHumidity(Float humidity) {
		this.humidity.set(humidity);
		measurementsChanged();
	}
	/**
	 * Get the Humidity
	 * @return
	 */
	public FloatProperty humidityFloatProperty() {
		return humidity;
	}
	/**
	 * Get the Pressure
	 * @return
	 */
	public Float getPressure() {
		return pressure.get();
	}
	/**
	 * Set the Pressure
	 * @param pressure
	 */
	public void setPressure(Float pressure) {
		this.pressure.set(pressure);
		measurementsChanged();
	}
	/**
	 * Get the Pressure
	 * @return
	 */
	public FloatProperty pressureFloatProperty() {
		return pressure;
	}
	/**
	 * Get the Wind Speed
	 * @return
	 */
	public Float getSpeed() {
		return speed.get();
	}
	/**
	 * Set the Wind Speed 
	 * @param speed
	 */
	public void setSpeed(Float speed) {
		this.speed.set(speed);
		measurementsChanged();
	}
	/**
	 * Get the Wind Speed
	 * @return
	 */
	public FloatProperty speedFloatProperty() {
		return speed;
	}
	/**
	 * Get the Forecast Prediction
	 * @return
	 */
	public String forecastPrediction() {
		return forecast.forecast();
	}
	/**
	 * Set the Forecast Prediction
	 * @param newForecast
	 */
	public void setForecastPrediction(Forecast newForecast) {
		forecast = newForecast;
	}
	


}
