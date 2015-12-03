package webb.richard.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Weather Data Controller to add, delete, query, and update to a MySQL database
 * for weather records
 * 
 * @author Richard Webb
 *
 */
public class WeatherDataController implements DatabaseCommands {

	private Statement statement = null;
	private ResultSet rs = null;

	/**
	 * Update a weather data record in the database
	 * 
	 * @param key
	 * @param temp
	 * @param pressure
	 * @param humidity
	 * @param wind
	 * @throws SQLException
	 */
	public void update(Integer key, Float temp, Float pressure, Float humidity, Float wind) {
		try {
			Connector conn = new Connector();
			conn.connectDatabase(conn.getPASSWORD());
			statement = conn.makeStatement();
			String sqlst = ("UPDATE data SET temperature=" + temp + ", pressure=" + pressure
					+ ", humidity=" + humidity + ", windspeed=" + wind + " WHERE id=" + key);

			statement.executeUpdate(sqlst);

			// Close the connection
			statement.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("ERROR: Either cannot connect to the DB or error with the SQL statement");
		}
	}

	/**
	 * Add a weather data record into the database
	 * 
	 * @param temp
	 * @param pressure
	 * @param humidity
	 * @param wind
	 * @throws SQLException
	 */
	public void add(Float temp, Float pressure, Float humidity, Float wind) {

		try {
			Connector conn = new Connector();
			conn.connectDatabase(conn.getPASSWORD());
			statement = conn.makeStatement();
			String sqlst = ("INSERT INTO data" + "(temperature, pressure, humidity, windspeed) " + "VALUES ('" + temp
					+ "','" + pressure + "','" + humidity + "','" + wind + "')");

			statement.execute(sqlst);

			// Close the Connection
			statement.close();
		} catch (Exception e) {
			System.err.println("ERROR: Either cannot connect to the BD or error with the SQL statement");
		}
	}

	/**
	 * Delete a record in the weather data database
	 * 
	 * @param key
	 */
	public void delete(Integer key) {

		try {
			Connector conn = new Connector();
			conn.connectDatabase(conn.getPASSWORD());
			statement = conn.makeStatement();
			String sqlst = ("DELETE FROM data WHERE ID = " + key + " ");
			statement.executeUpdate(sqlst);

			// close the connection
			statement.close();

		} catch (Exception e) {
			System.err.println("ERROR: Either cannot connect to the BD or error with the SQL statement");
		}

	}

	/**
	 * Query the weather Database and retrieve the info needed
	 * 
	 */
	@Override
	public void query() {
		try {
			Connector conn = new Connector();
			conn.connectDatabase(conn.getPASSWORD());
			statement = conn.makeStatement();
			String sqlst = ("SELECT * FROM data ");
			rs = statement.executeQuery(sqlst);
		} catch (Exception e) {
			System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
		}
	}

	/**
	 * Return Result Set for this Database
	 * 
	 * @return
	 */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * Receive a Result Set for this Database
	 * 
	 * @param rs
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	/**
	 * Close the Database Connections
	 */
	@Override
	public void close() throws SQLException {
		rs.close();
		statement.close();
	}

	/**
	 * Add an item to the Database Unimplemented
	 */
	@Override
	public void add() {
		// TODO

	}

	/**
	 * Delete an item from the Database Unimplemented
	 */
	@Override
	public void delete() {
		// TODO

	}

	/**
	 * Update an Item from the Database Unimplemented
	 */
	@Override
	public void update() {
		// TODO

	}

}
