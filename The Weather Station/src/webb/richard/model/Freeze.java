package webb.richard.model;
/**
 * Class that describes freezing conditions
 * @author Richard Webb
 *
 */
public class Freeze extends WeatherData {

	public Freeze() {
		
		super();
		forecast = new Cold();
	}
}
