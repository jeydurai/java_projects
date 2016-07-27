package sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


public class SQLConnection {

	ConnectionPooling pool = new ConnectionPooling();
	DataSource dataSource = pool.setUp();
	Connection conn;

	public SQLConnection () {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	public Connection getSQLConnection() {
		return this.conn;
	}
	
	public void closeSQLConnection() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
}
