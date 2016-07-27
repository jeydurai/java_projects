package queryEngines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import configurations.SQLQueryConfig;
import configurations.SQLQueryExtender;
import dataStructures.DSCustomizable;
import dataStructures.DSSQLStatus;
import dataStructures.DSToppers;

public abstract class SQLQueryAdapter implements Queryable {
	
	protected SQLQueryConfig queryConfig;
	protected SQLQueryExtender queryExtender;
	protected Connection conn;
	protected String fetchField;
	protected PreparedStatement prepStatementDistinct, prepStatementAggregate, prepStatementExtended, 
	prepStatementInsert, prepStatementDelete, prepStatementDrop, prepStatementCreateLike;
	
	public SQLQueryAdapter(Connection conn, SQLQueryConfig queryConfig) {
		this.queryConfig = queryConfig; this.conn = conn; this.fetchField = this.queryConfig.getResultSetField(); 
	}
	
	public SQLQueryAdapter(Connection conn, SQLQueryExtender queryExtender) {
		this.queryExtender = queryExtender; this.conn = conn; 
	}

	public String createPreparedStatement() { return null; }
	public String createExtendedPreparedStatement() { return null; }
	public String createCreateLikePreparedStatement(String likeTableName) { return null; };
	public PreparedStatement getPreparedStatementDistinct() { return null; }
	public PreparedStatement getPreparedStatementAggregate() { return null; }
	public PreparedStatement getPreparedStatementExtended() { return null; }
	public <T> T getValue(Map<Integer, DSCustomizable> params) { return null; };
	public <T> T[] getValues(Map<Integer, DSCustomizable> params) { return null; }
	public <T> List<T> fetchToppersList (Map<Integer, DSCustomizable> params) { return null; }
	public <T> List<T> fetchAllData (Map<Integer, DSCustomizable> params) { return null; }
	public DSSQLStatus deleteTableRecords() { return null; }
	public String dropTable() { return null; }
	public String createTable() { return null; }
	public DSSQLStatus insertTableRecords(Map<Integer, DSCustomizable> params) { return null; }
	public DSSQLStatus extendedInsertTableRecords() { return null; }
}
