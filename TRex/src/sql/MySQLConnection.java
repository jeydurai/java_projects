package sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import GeneralConstants.ConnectionStrings;

public class MySQLConnection {

	private Connection conn;
	
	public MySQLConnection(int maxActive) {
		ConnectionPooling pool = new ConnectionPooling(ConnectionStrings.REGISTER_STRING);
		DataSource dataSource = pool.setUp(ConnectionStrings.URL, ConnectionStrings.USR, ConnectionStrings.PWD, maxActive);
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Connection acquirePooledSQLConnection() {
		return this.conn;
	}
	
	public void disconnectSQLConnection() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Can't disconnect SQL Connection!");
			}
		}
	}
}
