package configurations;

import dataStructures.DSSQLField;

public class SQLUnionConfig {

	private String unionQuery, unionAllQuery;
	private String[] queries;
	
	public SQLUnionConfig(String[] queries) {
		this.queries = queries;
		int sizeOfArray = queries.length;
		String unionQuery = "";
		String unionAllQuery = "";
		
		for (int i = 0; i < sizeOfArray; i++) {
			String query = queries[i];
			if (i == sizeOfArray-1) {
				unionQuery = unionQuery + query;
				unionAllQuery = unionAllQuery + query;
			} else {
				unionQuery = unionQuery + query + " UNION ";
				unionAllQuery = unionAllQuery + query + " UNION ALL ";
			}
		}
		
		this.unionQuery = unionQuery;
		this.unionAllQuery = unionAllQuery;
	}
	
	public String getUnionQuery() { return this.unionQuery; }
	public String getUnionAllQuery() { return this.unionAllQuery; }
}
