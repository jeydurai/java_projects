package modelsSQL;

import helperUtilities.Comforter;
import helperUtilities.ConstantsDumpFromFinance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTextArea;

import modelsDataStructures.DSDumpFromFinance;
import configurations.SQLQueryConfig;
import dataStructures.DSCustomizable;
import dataStructures.DSToppers;

public class SQLDumpFromFinance extends SQLQueryEngine {

	public SQLDumpFromFinance(Connection conn, SQLQueryConfig queryConfig) {
		super(conn, queryConfig);
	}
	
	public <T> List<T> fetchAllData (Map<Integer, DSCustomizable> params, Object errDisplayObj) {
		List<DSDumpFromFinance> results = null;
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
				results = new ArrayList<DSDumpFromFinance>();
				while (resultSet.next()) results.add(new DSDumpFromFinance(
						resultSet.getFloat(ConstantsDumpFromFinance._ID), 
						resultSet.getString(ConstantsDumpFromFinance._BOOKINGS_ADJUSTMENTS_CODE), 
						resultSet.getString(ConstantsDumpFromFinance._BOOKINGS_ADJUSTMENTS_DESCRIPTION), 
						resultSet.getString(ConstantsDumpFromFinance._BOOKINGS_ADJUSTMENTS_TYPE), 
						resultSet.getString(ConstantsDumpFromFinance._AT_ATTACH), 
						resultSet.getString(ConstantsDumpFromFinance._BUSINESS_UNIT), 
						resultSet.getString(ConstantsDumpFromFinance._CUSTOMER_NAME), 
						resultSet.getString(ConstantsDumpFromFinance._ERP_DEAL_ID), 
						resultSet.getString(ConstantsDumpFromFinance._SALES_ORDER_NO), 
						resultSet.getString(ConstantsDumpFromFinance._FISCAL_PERIOD_ID), 
						resultSet.getString(ConstantsDumpFromFinance._FISCAL_QUARTER_ID), 
						resultSet.getString(ConstantsDumpFromFinance._FISCAL_WEEK_ID), 
						resultSet.getString(ConstantsDumpFromFinance._PARTNER_NAME), 
						resultSet.getString(ConstantsDumpFromFinance._PRODUCT_FAMILY), 
						resultSet.getString(ConstantsDumpFromFinance._PRODUCT_ID), 
						resultSet.getString(ConstantsDumpFromFinance._SALES_AGENT), 
						resultSet.getString(ConstantsDumpFromFinance._SL2), 
						resultSet.getString(ConstantsDumpFromFinance._SL3), 
						resultSet.getString(ConstantsDumpFromFinance._SL4), 
						resultSet.getString(ConstantsDumpFromFinance._SL5), 
						resultSet.getString(ConstantsDumpFromFinance._SL6), 
						resultSet.getString(ConstantsDumpFromFinance._SCMS), 
						resultSet.getString(ConstantsDumpFromFinance._SUB_SCMS), 
						resultSet.getString(ConstantsDumpFromFinance._TMS1), 
						resultSet.getString(ConstantsDumpFromFinance._TMS2), 
						resultSet.getString(ConstantsDumpFromFinance._TMS3), 
						resultSet.getString(ConstantsDumpFromFinance._TMS4), 
						resultSet.getString(ConstantsDumpFromFinance._TECHNOLOGY_GROUP), 
						resultSet.getString(ConstantsDumpFromFinance._PARTNER_TIER_CODE), 
						resultSet.getString(ConstantsDumpFromFinance._PARTNER_CERTIFICATION), 
						resultSet.getString(ConstantsDumpFromFinance._PARTNER_TYPE), 
						resultSet.getString(ConstantsDumpFromFinance._BILL_TO_CITY), 
						resultSet.getString(ConstantsDumpFromFinance._SHIP_TO_CITY), 
						resultSet.getString(ConstantsDumpFromFinance._CBN_FLAG), 
						resultSet.getString(ConstantsDumpFromFinance._BOOKINGS_QTY), 
						resultSet.getString(ConstantsDumpFromFinance._BE_NAME), 
						resultSet.getString(ConstantsDumpFromFinance._SUB_BE_NAME), 
						resultSet.getString(ConstantsDumpFromFinance._PROD_SER), 
						resultSet.getDouble(ConstantsDumpFromFinance._BOOKING_NET), 
						resultSet.getDouble(ConstantsDumpFromFinance._BASE_LIST), 
						resultSet.getDouble(ConstantsDumpFromFinance._BOOKINGS_STD_COST)));
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
