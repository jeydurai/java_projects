package modelsSQL;

import helperUtilities.Comforter;
import helperUtilities.ConstantsUniqueSalesAgents;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTextArea;

import modelsDataStructures.DSUniqueSalesAgents;
import configurations.SQLQueryConfig;
import dataStructures.DSCustomizable;

public class SQLUniqueSalesAgents extends SQLQueryEngine{

	public SQLUniqueSalesAgents(Connection conn, SQLQueryConfig queryConfig) {
		super(conn, queryConfig);
	}

	public <T> List<T> fetchAllData (Map<Integer, DSCustomizable> params, Object errDisplayObj) {
		List<DSUniqueSalesAgents> results = null;
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
				results = new ArrayList<DSUniqueSalesAgents>();
				while (resultSet.next()) results.add(new DSUniqueSalesAgents(
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_SL6),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_SUB_SCMS),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_REGION),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_GTMU),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_ID5),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_NAME5),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_TYPE5),
						resultSet.getString(ConstantsUniqueSalesAgents._VS_TEAM_NODE),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_ID4),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_NAME4),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_TYPE4),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_ID3),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_NAME3),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_TYPE3),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_ID2),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_NAME2),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_TYPE2),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_ID1),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_NAME1),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_TYPE1),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_ID0),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_NAME0),
						resultSet.getString(ConstantsUniqueSalesAgents._MAPPED_TYPE0)));
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
