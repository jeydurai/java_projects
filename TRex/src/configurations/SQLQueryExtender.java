package configurations;

public class SQLQueryExtender {

	private String extendedQueryString;
	
	public SQLQueryExtender(String query1, String query2) {
		this.extendedQueryString = query1 + " \n" + query2;
	}

	public String getExtendedQueryString() { return this.extendedQueryString; }
}
