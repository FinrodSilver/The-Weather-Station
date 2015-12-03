package webb.richard.model;

/**
 * 
 * @author Richard Webb Subject Interface
 *
 */
public interface Subject {

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();

}
