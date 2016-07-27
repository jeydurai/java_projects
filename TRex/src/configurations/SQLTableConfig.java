package configurations;

public class SQLTableConfig {

	private String fromTableName, tableName;
	
	public SQLTableConfig(String tableName) { 
		this.fromTableName = " FROM " + tableName; 
		this.tableName = " " + tableName; 
	} //Constructor
	
	public String getFromSource() { return this.fromTableName; }
	public String getTableName() { return this.tableName; }
}
