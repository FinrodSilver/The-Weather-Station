package webb.richard.model;

/**
 * 
 * @author Richard Webb
 * @version 1
 * Connector Class allow connection to a MySQL Database
 * 
 */
import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class Connector {

	/**
	 * Default Constructor
	 * 
	 * @throws SQLException
	 */
	public Connector() throws SQLException {
	}

	// Variables
	private Connection conn;
	private String PASSWORD = ""; // Change this variable if there is a password
									// by using get and set

	/**
	 * Connection to the Database
	 * 
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Connection connectDatabase(String password) throws SQLException {

		if (conn == null) {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/weatherdata", "root",
					password);
		}
		return conn;
	}

	/**
	 * Database Statement
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Statement makeStatement() throws SQLException {
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		return st;
	}

	/**
	 * Close the Database Connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int close() throws SQLException {
		conn.close();
		return 1;
	}

	/**
	 * Pull Metadata for the Table names
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> tableMetaData() throws SQLException {
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String[] types = null;
		ArrayList<String> tableNames = new ArrayList<String>();
		DatabaseMetaData databaseMetaData = conn.getMetaData();
		ResultSet result = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);

		while (result.next()) {
			String table = result.getString(3);
			tableNames.add(table);
		}
		return tableNames;

	}

	/**
	 * Get the Database Password
	 * 
	 * @return
	 */
	public String getPASSWORD() {
		return PASSWORD;
	}

	/**
	 * Set the Database Password
	 * 
	 * @param PASSWORD
	 */
	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}
}
