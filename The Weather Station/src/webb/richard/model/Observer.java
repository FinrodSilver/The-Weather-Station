package webb.richard.model;

import javafx.beans.property.FloatProperty;

/**
 * 
 * @author Richard Webb Observer Interface
 */
public interface Observer {

	public void update(FloatProperty temperature, FloatProperty humidity, FloatProperty pressure, FloatProperty speed);

}
