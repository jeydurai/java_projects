package modelsSQL;

import helperUtilities.Comforter;
import helperUtilities.ConstantsBookingDump;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTextArea;

import modelsDataStructures.DSBookingDump;
import configurations.SQLQueryConfig;
import dataStructures.DSCustomizable;

public class SQLBookingDump extends SQLQueryEngine {

	public SQLBookingDump(Connection conn, SQLQueryConfig queryConfig) {
		super(conn, queryConfig);
	}

	public <T> List<T> fetchAllData (Map<Integer, DSCustomizable> params, Object errDisplayObj) {
		List<DSBookingDump> results = null;
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
				results = new ArrayList<DSBookingDump>();
				while (resultSet.next()) results.add(new DSBookingDump(
						resultSet.getInt(ConstantsBookingDump._FP_YEAR), 
						resultSet.getInt(ConstantsBookingDump._FP_MONTH), 
						resultSet.getInt(ConstantsBookingDump._FP_WEEK), 
						resultSet.getDouble(ConstantsBookingDump._BOOKING_NET), 
						resultSet.getDouble(ConstantsBookingDump._BASE_LIST), 
						resultSet.getString(ConstantsBookingDump._AT_ATTACH), 
						resultSet.getString(ConstantsBookingDump._ACCOUNT_NAME), 
						resultSet.getString(ConstantsBookingDump._CUSTOMER_NAME), 
						resultSet.getString(ConstantsBookingDump._ERP_DEAL_ID), 
						resultSet.getString(ConstantsBookingDump._SALES_ORDER_NUMBER), 
						resultSet.getString(ConstantsBookingDump._FP_QUARTER), 
						resultSet.getString(ConstantsBookingDump._PARTNER), 
						resultSet.getString(ConstantsBookingDump._PARTNER_NAME), 
						resultSet.getString(ConstantsBookingDump._SALES_AGENTS), 
						resultSet.getString(ConstantsBookingDump._REGION), 
						resultSet.getString(ConstantsBookingDump._SL6), 
						resultSet.getString(ConstantsBookingDump._SCMS), 
						resultSet.getString(ConstantsBookingDump._SUB_SCMS), 
						resultSet.getString(ConstantsBookingDump._TMS1), 
						resultSet.getString(ConstantsBookingDump._TECH_NAME), 
						resultSet.getString(ConstantsBookingDump._TECH_CODE), 
						resultSet.getString(ConstantsBookingDump._TECHNOLOGY_GROUP), 
						resultSet.getString(ConstantsBookingDump._PARTNER_TIER_CODE), 
						resultSet.getString(ConstantsBookingDump._SHIP_TO_CITY), 
						resultSet.getString(ConstantsBookingDump._BOOKINGS_QTY), 
						resultSet.getString(ConstantsBookingDump._BE_NAME), 
						resultSet.getString(ConstantsBookingDump._SUB_BE_NAME), 
						resultSet.getString(ConstantsBookingDump._TAM_MATCH), 
						resultSet.getString(ConstantsBookingDump._AG_MATCH), 
						resultSet.getString(ConstantsBookingDump._AG_PAR_MATCH), 
						resultSet.getString(ConstantsBookingDump._AG_MATCH_2), 
						resultSet.getString(ConstantsBookingDump._ARCH1), 
						resultSet.getString(ConstantsBookingDump._ARCH2), 
						resultSet.getString(ConstantsBookingDump._PRODUCT_ID), 
						resultSet.getString(ConstantsBookingDump._MAPPED_ID5), 
						resultSet.getString(ConstantsBookingDump._MAPPED_NAME5), 
						resultSet.getString(ConstantsBookingDump._MAPPED_TYPE5), 
						resultSet.getString(ConstantsBookingDump._VS_TEAM_NODE), 
						resultSet.getString(ConstantsBookingDump._BILL_TO_SITE_CITY), 
						resultSet.getString(ConstantsBookingDump._VERTICAL), 
						resultSet.getString(ConstantsBookingDump._FY15_TAM), 
						resultSet.getString(ConstantsBookingDump._IOT_PORTFOLIO), 
						resultSet.getString(ConstantsBookingDump._GTMU), 
						resultSet.getString(ConstantsBookingDump._PARTNER_CER), 
						resultSet.getString(ConstantsBookingDump._PARTNER_TYPE), 
						resultSet.getString(ConstantsBookingDump._PRODUCT_FAMILY), 
						resultSet.getString(ConstantsBookingDump._BOOKING_ADJUSTMENT), 
						resultSet.getString(ConstantsBookingDump._MAPPED_SL6), 
						resultSet.getString(ConstantsBookingDump._MAPPED_SUB_SCMS), 
						resultSet.getString(ConstantsBookingDump._MAPPED_REGION), 
						resultSet.getString(ConstantsBookingDump._MAPPED_GTMU), 
						resultSet.getString(ConstantsBookingDump._MAPPED_ID4), 
						resultSet.getString(ConstantsBookingDump._MAPPED_NAME4), 
						resultSet.getString(ConstantsBookingDump._MAPPED_TYPE4), 
						resultSet.getString(ConstantsBookingDump._MAPPED_ID3), 
						resultSet.getString(ConstantsBookingDump._MAPPED_NAME3), 
						resultSet.getString(ConstantsBookingDump._MAPPED_TYPE3), 
						resultSet.getString(ConstantsBookingDump._MAPPED_ID2), 
						resultSet.getString(ConstantsBookingDump._MAPPED_NAME2), 
						resultSet.getString(ConstantsBookingDump._MAPPED_TYPE2), 
						resultSet.getString(ConstantsBookingDump._MAPPED_ID1), 
						resultSet.getString(ConstantsBookingDump._MAPPED_NAME1), 
						resultSet.getString(ConstantsBookingDump._MAPPED_TYPE1), 
						resultSet.getString(ConstantsBookingDump._MAPPED_ID0), 
						resultSet.getString(ConstantsBookingDump._MAPPED_NAME0), 
						resultSet.getString(ConstantsBookingDump._MAPPED_TYPE0), 
						resultSet.getString(ConstantsBookingDump._UNIQUE_CITY), 
						resultSet.getString(ConstantsBookingDump._UNIQUE_STATE), 
						resultSet.getString(ConstantsBookingDump._UNIQUE_COUNTRY), 
						resultSet.getString(ConstantsBookingDump._PROD_SER)));
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
