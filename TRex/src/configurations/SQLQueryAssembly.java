package configurations;

import java.util.Map;

import GeneralConstants.Generics.SQLLogicalOperatorCode;
import GeneralConstants.Generics.SQLOperatorCode;
import dataStructures.DSLimit2D;
import dataStructures.DSSQLCondition;
import dataStructures.DSSQLField;

public class SQLQueryAssembly {
	private SQLFieldConfig fieldConfig;
	private SQLTableConfig tableConfig;
	private SQLConditionable conditionConfig;
	private SQLGroupByConfig groupByConfig;
	private SQLOrderByConfig orderByConfig;
	private SQLLimitConfig limitConfig;
	private SQLQueryConfig queryConfig;
	private DSSQLField[] fieldDSArray;
	
	public SQLQueryAssembly(DSSQLField[] fieldDSArray, String tableName, DSSQLCondition condition, 
			String[] groupByArray, Map<String, String> orderByMap, DSLimit2D limit2d) {
		fieldConfig = new SQLFieldConfig(fieldDSArray);
		tableConfig = new SQLTableConfig(tableName);
		conditionConfig = new SQLConditionConfig(condition);
		groupByConfig = new SQLGroupByConfig(groupByArray);
		orderByConfig = new SQLOrderByConfig(orderByMap);
		limitConfig = new SQLLimitConfig(limit2d);
		System.out.println(fieldConfig.getFieldsAsString());
		queryConfig = new SQLQueryConfig(fieldConfig, conditionConfig, 
				groupByConfig, orderByConfig, limitConfig, tableConfig);

	}
	
	public SQLQueryAssembly(DSSQLField[] fieldDSArray, String tableName, DSSQLCondition[] conditions, 
			String[] groupByArray, Map<String, String> orderByMap, DSLimit2D limit2d) {
		fieldConfig = new SQLFieldConfig(fieldDSArray);
		tableConfig = new SQLTableConfig(tableName);
		conditionConfig = new SQLConditionMultiConfig(conditions);
		groupByConfig = new SQLGroupByConfig(groupByArray);
		orderByConfig = new SQLOrderByConfig(orderByMap);
		limitConfig = new SQLLimitConfig(limit2d);
		
		queryConfig = new SQLQueryConfig(fieldConfig, conditionConfig, 
				groupByConfig, orderByConfig, limitConfig, tableConfig);

	}

	public SQLQueryConfig getQueryConfiguration() { return this.queryConfig; }
	public SQLFieldConfig getSQLFieldConfiguration() { return this.fieldConfig; }
	
}
