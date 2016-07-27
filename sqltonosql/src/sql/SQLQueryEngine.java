package sql;

import helper.ProcessHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import dataClasses.ReverseTopperData;
import dataClasses.TopperData;

public class SQLQueryEngine {

	Connection conn;
	PreparedStatement prepare, prepareMetrics;
	String field = "";
	String table_name = "";
	String where_clause = "";
	String group_by_clause = "";
	String queryArrayString = "";
	String queryMetrics = "";
	public SQLQueryEngine(Map<String, String> map, Connection conn) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.field = (map.get("field") != null) ? map.get("field") : "";
		this.table_name = (map.get("table_name") != null) ? map.get("table_name") : "";
		this.where_clause = (map.get("where_clause") != null) ? map.get("where_clause") : "";
		this.group_by_clause = (map.get("group_by_clause") != null) ? map.get("group_by_clause") : "";
		queryArrayString = "SELECT DISTINCT " + this.field + " FROM " + this.table_name + this.where_clause + this.group_by_clause;
		queryMetrics = "SELECT " + this.field + " FROM " + this.table_name + this.where_clause + this.group_by_clause;
		try {
			this.conn = conn;
			prepare = conn.prepareStatement(queryArrayString);
			prepareMetrics = conn.prepareStatement(queryMetrics);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<TopperData> fetchToppersList (Map<Integer, TypeCustomizable> params) throws SQLException{
		List<TopperData> results = null;
		ResultSet resultSet = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						if (params.get(key).getData() instanceof String) {
							prepare.setString(key, (String)params.get(key).getData());
						} else if (params.get(key).getData() instanceof Integer) {
							prepare.setInt(key, (Integer)params.get(key).getData());
						}
					}
				}
			}
		} catch (NullPointerException e) {
		} finally {
			try {
				resultSet = prepare.executeQuery();
				results = new ArrayList<TopperData>();
				while (resultSet.next()) {
					results.add(new TopperData(resultSet.getString(1), resultSet.getDouble(2)));
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				try {
					if(resultSet != null ) resultSet.close();
					if(prepare != null ) prepare.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		}
		Collections.sort(results, new TopperData());
		return results;
	}

	public List<ReverseTopperData> fetchReverseToppersList (Map<Integer, TypeCustomizable> params) throws SQLException{
		List<ReverseTopperData> results = null;
		ResultSet resultSet = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						if (params.get(key).getData() instanceof String) {
							prepare.setString(key, (String)params.get(key).getData());
						} else if (params.get(key).getData() instanceof Integer) {
							prepare.setInt(key, (Integer)params.get(key).getData());
						}
					}
				}
			}
		} catch (NullPointerException e) {
		} finally {
			try {
				resultSet = prepare.executeQuery();
				results = new ArrayList<ReverseTopperData>();
				while (resultSet.next()) {
					results.add(new ReverseTopperData(resultSet.getString(1), resultSet.getDouble(2)));
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				try {
					if(resultSet != null ) resultSet.close();
					if(prepare != null ) prepare.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		}
		Collections.sort(results, new ReverseTopperData());
		return results;
	}
	
	
	public String[] fetchStringArray (Map<Integer, TypeCustomizable> params) throws SQLException{
		List<String> results = null;
		ResultSet resultSet = null;
		String[] resultArray = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						if (params.get(key).getData() instanceof String) {
							//System.out.printf("Parameter: %s\n", (String)params.get(key).getData());
							prepare.setString(key, (String)params.get(key).getData());
						} else if (params.get(key).getData() instanceof Integer) {
							//System.out.printf("Parameter: %d\n", (Integer)params.get(key).getData());
							prepare.setInt(key, (Integer)params.get(key).getData());
						}
					}
				}
			}
		} catch (NullPointerException e) {
		} finally {
			try {
				resultSet = prepare.executeQuery();
				results = new ArrayList<String>();
				while (resultSet.next()) {
					results.add(resultSet.getString(this.field));
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				try {
					if(resultSet != null ) resultSet.close();
					if(prepare != null ) prepare.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		}
		
		if (!results.isEmpty()) {
			results = ProcessHelper.getCleanArrayList(results); 
			resultArray = results.toArray(new String[results.size()]);
		}
		return resultArray;
	}

	public Integer[] fetchIntegerList (Map<Integer, TypeCustomizable> params) throws SQLException{
		List<Integer> results = null;
		ResultSet resultSet = null;
		Integer[] resultArray = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						if (params.get(key).getData() instanceof String) {
							prepare.setString(key, (String)params.get(key).getData());
						} else if (params.get(key).getData() instanceof Integer) {
							prepare.setInt(key, (Integer)params.get(key).getData());
						}
					}
				}
			}
		} catch (NullPointerException e) {
		} finally {
			try {
				resultSet = prepare.executeQuery();
				results = new ArrayList<Integer>();
				while (resultSet.next()) {
					results.add(resultSet.getInt(this.field));
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				try {
					if(resultSet != null ) resultSet.close();
					if(prepare != null ) prepare.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		}
		if (!results.isEmpty()) {
			results = ProcessHelper.getCleanArrayList(results); 
			resultArray = results.toArray(new Integer[results.size()]);
		}
		return resultArray;
	}


	public Double fetchDoubleValue (Map<Integer, TypeCustomizable> params) throws SQLException{
		Double results = 0.0;
		ResultSet resultSet = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						if (params.get(key).getData() instanceof String) {
							prepare.setString(key, (String)params.get(key).getData());
						} else if (params.get(key).getData() instanceof Integer) {
							prepare.setInt(key, (Integer)params.get(key).getData());
						}
					}
				}
			}
		} catch (NullPointerException e) {
		} finally {
			try {
				resultSet = prepare.executeQuery();
				Double metric = 0.0;
				while (resultSet.next()) {
					metric = metric + resultSet.getDouble(1);
				}
				results = metric;
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				try {
					if(resultSet != null ) resultSet.close();
					if(prepare != null ) prepare.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		}
		return results;
	}

	public int fetchRowCount (Map<Integer, TypeCustomizable> params) throws SQLException{
		int results = 0;
		ResultSet resultSet = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						if (params.get(key).getData() instanceof String) {
							prepareMetrics.setString(key, (String)params.get(key).getData());
						} else if (params.get(key).getData() instanceof Integer) {
							prepareMetrics.setInt(key, (Integer)params.get(key).getData());
						}
					}
				}
			}
		} catch (NullPointerException e) {
		} finally {
			try {
				resultSet = prepareMetrics.executeQuery();
				resultSet.last();
				results = resultSet.getRow();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				try {
					if(resultSet != null ) resultSet.close();
					if(prepareMetrics != null ) prepareMetrics.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		}
		return results;
	}
	
	
	public void closeConnection() {
		try {
			conn.close();
		} catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
}
