package sql;

public class PoolStatusPrinter implements Runnable {

	ConnectionPooling pool = null;
	
	public PoolStatusPrinter() {
		pool = new ConnectionPooling();
	}

	@Override
	public void run() {
		//if (pool.getConnectionPool() != null) {
			System.out.println("Max : " + pool.getConnectionPool().getMaxActive() + "; " +
						"Active: " + pool.getConnectionPool().getNumActive() + "; " +
						"Idle  : " + pool.getConnectionPool().getNumIdle());
		//}
	}
	
}
