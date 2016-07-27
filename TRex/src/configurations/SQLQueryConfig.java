package configurations;

import dataStructures.DSSQLField;

public class SQLQueryConfig {

	private SQLFieldConfig fieldConfig;
	private SQLConditionable conditionConfig;
	private	SQLGroupByConfig groupByConfig;
	private SQLOrderByConfig orderByConfig;
	private SQLLimitConfig limitConfig;
	private SQLTableConfig tableConfig;
	
	public SQLQueryConfig(SQLFieldConfig fieldConfig, SQLConditionable conditionConfig, 
			SQLGroupByConfig groupByConfig, SQLOrderByConfig orderByConfig, SQLLimitConfig limitConfig, 
			SQLTableConfig tableConfig) {
		this.fieldConfig = fieldConfig; this.conditionConfig = conditionConfig; this.groupByConfig = groupByConfig;
		this.orderByConfig = orderByConfig; this.tableConfig = tableConfig; this.limitConfig = limitConfig;
		
	}
	
	public String generateDistinctQuery() {
		String query = "SELECT DISTINCT " + this.fieldConfig.getFieldsAsString() + 
				this.tableConfig.getFromSource() + this.conditionConfig.getConditions() + 
				this.groupByConfig.getGroubyString() + this.orderByConfig.getOrderbyString() + 
				this.limitConfig.getLimitString();
		return query;
	}
	
	public String generateAggregateQuery() {
		String query = "SELECT " + this.fieldConfig.getFieldsAsString() + 
				this.tableConfig.getFromSource() + this.conditionConfig.getConditions() + 
				this.groupByConfig.getGroubyString() + this.orderByConfig.getOrderbyString() + 
				this.limitConfig.getLimitString();
		return query;
	}
	
	public String generateWriteQuery() {
		String query = "INSERT INTO" + this.tableConfig.getTableName() + " (" + this.fieldConfig.getFieldsAsString() + ")" + 
				" VALUES (" + this.fieldConfig.getParamValueString() + ")";
		//System.out.println(query);
		return query;
	}

	public String generatePlainWriteQuery() {
		String query = "INSERT INTO" + this.tableConfig.getTableName();
		return query;
	}

	public String generatePlainDeleteQuery() {
		String query = "DELETE FROM" + this.tableConfig.getTableName();
		return query;
	}

	public String generateDropTableQuery() {
		String query = "DROP TABLE" + this.tableConfig.getTableName();
		return query;
	}

	public String generateLikeCreateTableQuery(String likeTableName) {
		String query = "CREATE TABLE" + this.tableConfig.getTableName() + " LIKE " + likeTableName;
		return query;
	}
	
	public String getResultSetField() {
		String returnString = null;
		DSSQLField[] sqlFieldDS = this.fieldConfig.getSQLFieldDataStructure();
		for (DSSQLField fieldDS : sqlFieldDS) {
			returnString = fieldDS.getFieldAlias();
		}
		return returnString;
	}
	
	
}
