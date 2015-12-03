package webb.richard.model;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class DBTest {

	private Statement statement = null;
	private ResultSet rs = null;

	@Test
	public void test() {

		try {
			Connector conn = new Connector();
			conn.connectDatabase(conn.getPASSWORD());
			statement = conn.makeStatement();
			String sqlst = ("SELECT * FROM data ");
			rs = statement.executeQuery(sqlst);
			while(rs.next()) {
				System.out.println(rs.getRow() + "\n");
			}
		} catch (Exception e) {
			System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
		}
	}
}
