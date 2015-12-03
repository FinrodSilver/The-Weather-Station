package webb.richard.model;

import java.sql.SQLException;

/**
 * Database Command Interface
 * 
 * @author Richard Webb
 *
 */
public interface DatabaseCommands {

	public void update();

	public void add();

	public void delete();

	public void query();

	public void close() throws SQLException;

}
