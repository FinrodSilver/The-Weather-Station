package webb.richard.model;

import org.junit.Test;

public class FreezeTest extends WeatherData{

	@Test
	public void testFreeze() {
		
		forecast = new Cold();
		String test = this.forecastPrediction();
		System.out.println(test);
		
	}

}
