package sql;

import javax.sql.DataSource;

import helper.ConnectionStrings;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionPooling {

	private GenericObjectPool connectionPool = null;
	
	public DataSource setUp() {
		//
		// Load JDBC Driver class.
		//
		try {
			Class.forName(ConnectionStrings.REGISTER_STRING).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// Creates an instance of GenericObjectPool that holds our
		// pool of connections object.
		//
		connectionPool = new GenericObjectPool();
		connectionPool.setMaxActive(500);
		//connectionPool.setMaxWait(300000);
		//
		// Creates a connection factory object which will be used by
		// the pool to create the connection object. We pass the
		// JDBC url info, username and password.
		//
		ConnectionFactory cf = new DriverManagerConnectionFactory(
				ConnectionStrings.URL,
				ConnectionStrings.USR,
				ConnectionStrings.PWD);
		//
		// Creates a PoolableConnectionFactory that will wraps the 
		// connection object created by the ConnectionFactory to add
		// object pooling functionality
		//
		
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, connectionPool, 
				null, null, false, true);
		return new PoolingDataSource(this.connectionPool);
		
	}
	
	public GenericObjectPool getConnectionPool() {
		return this.connectionPool;
	}

}
