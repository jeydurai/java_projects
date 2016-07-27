package writeEngines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import configurations.SQLQueryConfig;
import dataStructures.DSCustomizable;

public abstract class SQLWriteAdapter {

	protected SQLQueryConfig queryConfig;
	protected Connection conn;
	protected String fetchField;
	protected PreparedStatement prepStatementInsert;

	public SQLWriteAdapter(Connection conn, SQLQueryConfig queryConfig) {
		this.queryConfig = queryConfig; this.conn = conn; this.fetchField = this.queryConfig.getResultSetField(); 
	}

	public void createPreparedStatement(Object errDisplayObj) {};
	public PreparedStatement getPreparedStatementInsert() { return null; };
	public void writeData(Map<Integer, DSCustomizable> params, Object errDisplayObj) {};

}
