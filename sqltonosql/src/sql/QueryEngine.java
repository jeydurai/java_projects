package sql;

import helper.ConnectionStrings;
import helper.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class QueryEngine implements Queryable {

	Connection conn;
	PreparedStatement prepare;
	String field = "";
	String table_name = "";
	String where_clause = "";
	String group_by_clause = "";
	String query_string = "";
	public QueryEngine(Map<String, String> map) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.field = (map.get("field") != null) ? map.get("field") : "";
		this.table_name = (map.get("table_name") != null) ? map.get("table_name") : "";
		this.where_clause = (map.get("where_clause") != null) ? map.get("where_clause") : "";
		this.group_by_clause = (map.get("group_by_clause") != null) ? map.get("group_by_clause") : "";
		query_string = "SELECT DISTINCT " + this.field + " FROM " + this.table_name + this.where_clause + this.group_by_clause;
		//System.out.println(query_string);
		try {
			Class.forName(ConnectionStrings.REGISTER_STRING).newInstance();
			conn = 	DriverManager.getConnection(ConnectionStrings.URL, ConnectionStrings.USR, ConnectionStrings.PWD);
			prepare = conn.prepareStatement(query_string);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<String> fetchStringList (Map<Integer, TypeCustomizable> params) throws SQLException{
		List<String> results = null;
		ResultSet resultSet = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						prepare.setInt(key, (Integer)params.get(key).getData());
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
					resultSet.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
					closeConnection();
				}
			}
		}
		return Helper.getCleanArrayList(results);
	}

	public List<Integer> fetchIntegerList (Map<Integer, TypeCustomizable> params) throws SQLException{
		List<Integer> results = null;
		ResultSet resultSet = null;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						prepare.setString(key, (String)params.get(key).getData());
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
					resultSet.close();
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
					closeConnection();
				}
			}
		}
		return Helper.getCleanArrayList(results);
	}

	
	private void closeConnection() {
		try {
			conn.close();
		} catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
