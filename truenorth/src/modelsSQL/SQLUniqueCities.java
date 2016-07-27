package modelsSQL;

import helperUtilities.Comforter;
import helperUtilities.ConstantsUniqueCities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTextArea;

import modelsDataStructures.DSUniqueCities;
import configurations.SQLQueryConfig;
import dataStructures.DSCustomizable;

public class SQLUniqueCities extends SQLQueryEngine {

	public SQLUniqueCities(Connection conn, SQLQueryConfig queryConfig) {
		super(conn, queryConfig);
	}

	public <T> List<T> fetchAllData (Map<Integer, DSCustomizable> params, Object errDisplayObj) {
		List<DSUniqueCities> results = null;
		ResultSet resultSet = null;
		JTextArea textArea = (JTextArea)errDisplayObj;
		try {
			if (!params.isEmpty() && params != null) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer key : sortedKeys) {
						if (params.get(key).getParameter() instanceof String) {
							try {
								this.prepStatementDistinct.setString(key, (String)params.get(key).getParameter());
							} catch (SQLException e) {
								textArea.append("\n" + Comforter.StackTraceToString(e));
							}
						} else if (params.get(key).getParameter() instanceof Integer) {
							try {
								this.prepStatementDistinct.setInt(key, (Integer)params.get(key).getParameter());
							} catch (SQLException e) {
								textArea.append("\n" + Comforter.StackTraceToString(e));
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {
			
		} finally {
			try {
				resultSet = this.prepStatementDistinct.executeQuery();
				results = new ArrayList<DSUniqueCities>();
				while (resultSet.next()) results.add(new DSUniqueCities(
						resultSet.getString(ConstantsUniqueCities._SALES_AGENT),
						resultSet.getString(ConstantsUniqueCities._BILL_TO_CITY),
						resultSet.getString(ConstantsUniqueCities._SHIP_TO_CITY),
						resultSet.getString(ConstantsUniqueCities._UNIQUE_CITY),
						resultSet.getString(ConstantsUniqueCities._UNIQUE_STATE),
						resultSet.getString(ConstantsUniqueCities._UNIQUE_COUNTRY)));
			} catch (SQLException sqlException) {
				textArea.append("\n" + Comforter.StackTraceToString(sqlException));
			} finally {
				try {
					if (resultSet != null) resultSet.close();
					if (this.prepStatementDistinct != null) this.prepStatementDistinct.close();
				} catch (SQLException sqlException) {
					textArea.append("\n" + Comforter.StackTraceToString(sqlException));
				}
			}
		}
		return (List<T>) results;

	}

}
