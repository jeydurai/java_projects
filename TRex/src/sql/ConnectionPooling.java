package sql;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import GeneralConstants.ConnectionStrings;

public class ConnectionPooling {

	private GenericObjectPool connectionPool = null;

	public ConnectionPooling (String registerString) {
		//
		// Load JDBC Driver
		//
		try {
			Class.forName(registerString).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public DataSource setUp(String mySQLURL, String userID, String pwd, int maxActive) {
		//
		// Creates an instance of GenericObjectPool that holds our
		// pool of connection object
		//
		this.connectionPool = new GenericObjectPool();
		this.connectionPool.setMaxActive(maxActive);
		
		//
		// Creates connection factory object which will be used by
		// the pool to create the connection object. We pass the 
		// JDBC URL info, userName and password.
		//
		ConnectionFactory cf = new DriverManagerConnectionFactory(mySQLURL, userID, pwd);
		//
		// Creates a PoolableConnectionFactory that will wraps the
		// connection object created by the ConnectionFactory to add
		// object pooling functionality
		//
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, this.connectionPool, null, null, false, true);
		
		return new PoolingDataSource(this.connectionPool);
	}
	
	public GenericObjectPool getPooledConnection() {
		return this.connectionPool;
	}
}
