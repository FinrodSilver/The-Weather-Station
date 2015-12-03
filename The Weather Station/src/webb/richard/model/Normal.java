package webb.richard.model;
/**
 * Class  that describes normal conditions
 * @author Richard Webb
 *
 */
public class Normal extends WeatherData {

	public Normal() {
		
		super();
		forecast = new Mild();
	}
}
