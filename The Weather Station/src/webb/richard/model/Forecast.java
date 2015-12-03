package webb.richard.model;

/**
 * Interface to generating the forecast Using Strategy Pattern
 * 
 * @author Richard Webb
 *
 */
public interface Forecast {

	String forecast();

}

class Sunny implements Forecast {

	@Override
	public String forecast() {

		return "It is Sunny today!";
	}
}

class Rainy implements Forecast {

	@Override
	public String forecast() {

		return "It is raining/snowing like cats and dogs!";
	}
}

class Hot implements Forecast {

	@Override
	public String forecast() {

		return "It is Hot today!";
	}
}

class Cold implements Forecast {

	@Override
	public String forecast() {
		
		return "It is Freezing today!";
	}
}

class Mild implements Forecast {
	
	@Override
	public String forecast() {
		
		return "Mild day today!";
	}
}
