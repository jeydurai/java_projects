package helperUtilities;

import java.util.HashMap;
import java.util.Map;

import modelsSQL.SQLQueryEngine;
import queryEngines.Queryable;
import sql.MySQLConnection;
import GeneralConstants.Generics;
import GeneralConstants.Generics.FieldMode;
import GeneralConstants.Generics.SQLLogicalOperatorCode;
import GeneralConstants.Generics.SQLOperatorCode;
import helperUtilities.GeneralConstants;
import configurations.SQLQueryAssembly;
import configurations.SQLQueryExtender;
import configurations.SQLUnionConfig;
import dataStructures.DSLimit2D;
import dataStructures.DSSQLCondition;
import dataStructures.DSSQLField;

public class DefaultSQLQueries {

	
	public static SQLQueryAssembly getAssemblyDumpFromFinance() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		//orderByMap.put("region", null);
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		//fieldDSArray[0] = new DSSQLField(FieldMode.SUM, "Booking", "booking_net");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition("LEFT(fiscal_period_id, 4)", SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_DUMP_FROM_FINANCE, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));

		return queryAssembly;
	}

	public static SQLQueryAssembly getAssemblyUniversalUniqueNames() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		//orderByMap.put("region", null);
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		//fieldDSArray[0] = new DSSQLField(FieldMode.SUM, "Booking", "booking_net");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition(ConstantsUniversalUniqueNames._NAMES, SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_UNIVERSAL_UNIQUE_NAMES, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		return queryAssembly;
	}

	public static SQLQueryAssembly getAssemblyGTMu() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition(ConstantsGTMuMaster._REGION, SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_GTMu_MASTER, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		return queryAssembly;
	}

	public static SQLQueryAssembly getAssemblyTechGrandMaster() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition(ConstantsTechGrandMaster._TECH_CODE, SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_TECH_GRAND_MASTER, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		return queryAssembly;
	}

	public static SQLQueryAssembly getAssemblyWeekMaster() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition[] sqlCondition = new DSSQLCondition[2];
		sqlCondition[0] = new DSSQLCondition(ConstantsWeekMaster._FISCAL_QUARTER, SQLOperatorCode.EQ, SQLLogicalOperatorCode.AND);
		sqlCondition[1] = new DSSQLCondition(ConstantsWeekMaster._WEEK_IN_DATABASE, SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_WEEK_MASTER, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));

		return queryAssembly;
	}

	public static SQLQueryAssembly getAssemblyUniqueSalesAgents() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition[] sqlCondition = new DSSQLCondition[2];
		sqlCondition[0] = new DSSQLCondition(ConstantsUniqueSalesAgents._SALES_AGENT, SQLOperatorCode.EQ, SQLLogicalOperatorCode.AND);
		sqlCondition[1] = new DSSQLCondition(ConstantsUniqueSalesAgents._SL6, SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_UNIQUE_SALES_AGENTS, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));

		return queryAssembly;
	}

	public static SQLQueryAssembly getAssemblyIOTPortfolios() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition[] sqlCondition = new DSSQLCondition[2];
		sqlCondition[0] = new DSSQLCondition(ConstantsIOTPortfolios._PRODUCT_FAM_ID, SQLOperatorCode.EQ, SQLLogicalOperatorCode.AND);
		sqlCondition[1] = new DSSQLCondition(ConstantsIOTPortfolios._PRODUCT_FAM_ID, SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_IOT_PORTFOLIOS, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));

		return queryAssembly;
	}

	public static SQLQueryAssembly getAssemblyUniqueCities() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition[] sqlCondition = new DSSQLCondition[4];
		sqlCondition[0] = new DSSQLCondition(ConstantsUniqueCities._SL6, SQLOperatorCode.EQ, SQLLogicalOperatorCode.AND);
		sqlCondition[1] = new DSSQLCondition(ConstantsUniqueCities._SALES_AGENT, SQLOperatorCode.EQ, SQLLogicalOperatorCode.AND);
		sqlCondition[2] = new DSSQLCondition(ConstantsUniqueCities._BILL_TO_CITY, SQLOperatorCode.EQ, SQLLogicalOperatorCode.AND);
		sqlCondition[3] = new DSSQLCondition(ConstantsUniqueCities._SHIP_TO_CITY, SQLOperatorCode.EQ, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_UNIQUE_CITIES, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));

		return queryAssembly;
	}
	
	public static SQLQueryAssembly getAssemblyDumpFromFinanceYear() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, "year", "LEFT(fiscal_period_id, 4)");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_DUMP_FROM_FINANCE, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));

		return queryAssembly;
	}


	public static SQLQueryExtender getExtenderTechMaster(String tableName) {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray = new DSSQLField[4];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, "Tech_Code", "RIGHT(TMS_Level_1_Sales_Allocated,(LENGTH(TMS_Level_1_Sales_Allocated)-LOCATE('-',TMS_Level_1_Sales_Allocated,1)))");
		fieldDSArray[1] = new DSSQLField(FieldMode.FIELD, "Tech_Name_1", "IFNULL((SELECT ts.Tech_Name_1 FROM tech_spec AS ts WHERE RIGHT(TMS_Level_1_Sales_Allocated,(LENGTH(TMS_Level_1_Sales_Allocated)-LOCATE('-',TMS_Level_1_Sales_Allocated,1))) = ts.tech_code),'Others')");
		fieldDSArray[2] = new DSSQLField(FieldMode.FIELD, "arch1", "IFNULL((SELECT ts2.arch1 FROM tech_spec AS ts2 WHERE RIGHT(TMS_Level_1_Sales_Allocated,(LENGTH(TMS_Level_1_Sales_Allocated)-LOCATE('-',TMS_Level_1_Sales_Allocated,1))) = ts2.tech_code),'Other')");
		fieldDSArray[3] = new DSSQLField(FieldMode.FIELD, "arch2", "IFNULL((SELECT ts3.arch2 FROM tech_spec AS ts3 WHERE RIGHT(TMS_Level_1_Sales_Allocated,(LENGTH(TMS_Level_1_Sales_Allocated)-LOCATE('-',TMS_Level_1_Sales_Allocated,1))) = ts3.tech_code),'Others')");
		orderByMap = new HashMap<String, String>();
		orderByMap.put("tech_code", null);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_DUMP_FROM_FINANCE, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		String query2 = queryAssembly.getQueryConfiguration().generateDistinctQuery();
		
		fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		orderByMap = null;
		queryAssembly = new SQLQueryAssembly(fieldDSArray, tableName, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		String query1 = queryAssembly.getQueryConfiguration().generatePlainWriteQuery();
		SQLQueryExtender queryExtender = new SQLQueryExtender(query1, query2);

		return queryExtender;
	}

	public static SQLQueryExtender getExtenderTechMaster1(String tableName) {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray = new DSSQLField[4];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, "Tech_Code", "Internal_Sub_Business_Entity_Name");
		fieldDSArray[1] = new DSSQLField(FieldMode.FIELD, "Tech_Name_1", "IFNULL((SELECT ts.Tech_Name_1 FROM tech_spec1 AS ts WHERE Internal_Sub_Business_Entity_Name = ts.tech_code),'Others')");
		fieldDSArray[2] = new DSSQLField(FieldMode.FIELD, "arch1", "IFNULL((SELECT ts2.arch1 FROM tech_spec1 AS ts2 WHERE Internal_Sub_Business_Entity_Name = ts2.tech_code),'Others')");
		fieldDSArray[3] = new DSSQLField(FieldMode.FIELD, "arch2", "IFNULL((SELECT ts3.arch2 FROM tech_spec1 AS ts3 WHERE Internal_Sub_Business_Entity_Name = ts3.tech_code),'Others')");
		orderByMap = new HashMap<String, String>();
		orderByMap.put("tech_code", null);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_DUMP_FROM_FINANCE, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		String query2 = queryAssembly.getQueryConfiguration().generateDistinctQuery();
		
		fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		orderByMap = null;
		queryAssembly = new SQLQueryAssembly(fieldDSArray, tableName, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		String query1 = queryAssembly.getQueryConfiguration().generatePlainWriteQuery();
		SQLQueryExtender queryExtender = new SQLQueryExtender(query1, query2);

		return queryExtender;
	}

	
	public static SQLQueryExtender getExtenderTechGrandMaster(String tableName) {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_TECH_MASTER, sqlCondition, groupByFieldArray, null, new DSLimit2D(0, 0));
		String query1 = queryAssembly.getQueryConfiguration().generateDistinctQuery();
		
		fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_TECH_MASTER1, sqlCondition, groupByFieldArray, null, new DSLimit2D(0, 0));
		String query2 = queryAssembly.getQueryConfiguration().generateDistinctQuery();

		String[] queries = {query1, query2};
		SQLUnionConfig unionConfig = new SQLUnionConfig(queries);
		
		
		fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		orderByMap = null;
		queryAssembly = new SQLQueryAssembly(fieldDSArray, tableName, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		String query3 = queryAssembly.getQueryConfiguration().generatePlainWriteQuery();
		SQLQueryExtender queryExtender = new SQLQueryExtender(query3, unionConfig.getUnionAllQuery());

		return queryExtender;
	}


	public static SQLQueryAssembly deleteTableRecords(String tableName) {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, tableName, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		return queryAssembly;
	}

	public static SQLQueryAssembly dropTableRecords(String tableName) {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, tableName, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		return queryAssembly;
	}

	public static SQLQueryAssembly createTableLikeTable(String tableName) {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		orderByMap = null;
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, "*");
		
		// Preparing Field Configuration for the Field Data Structure
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, tableName, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		return queryAssembly;
	}

	
	public static SQLQueryAssembly getAssemblyInsertTableRecords() {
		String[] groupByFieldArray = {};
		Map<String, String> orderByMap = new HashMap<String, String>();
		DSSQLCondition sqlCondition = new DSSQLCondition("", SQLOperatorCode.DO_NOTHING, SQLLogicalOperatorCode.DO_NOTHING);
		
		// Preparing Fields in an array as Data Structure
		DSSQLField[] fieldDSArray = new DSSQLField[1];
		fieldDSArray = new DSSQLField[71];
		fieldDSArray[0] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._ID);
		fieldDSArray[1] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._AT_ATTACH);
		fieldDSArray[2] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._ACCOUNT_NAME);
		fieldDSArray[3] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._CUSTOMER_NAME);
		fieldDSArray[4] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._ERP_DEAL_ID);
		fieldDSArray[5] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._SALES_ORDER_NUMBER);
		fieldDSArray[6] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._FP_YEAR);
		fieldDSArray[7] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._FP_QUARTER);
		fieldDSArray[8] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._FP_MONTH);
		fieldDSArray[9] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._FP_WEEK);
		fieldDSArray[10] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PARTNER);
		fieldDSArray[11] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PARTNER_NAME);
		fieldDSArray[12] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._SALES_AGENTS);
		fieldDSArray[13] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._REGION);
		fieldDSArray[14] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._SL6);
		fieldDSArray[15] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._SCMS);
		fieldDSArray[16] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._SUB_SCMS);
		fieldDSArray[17] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._TMS1);
		fieldDSArray[18] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._TECH_NAME);
		fieldDSArray[19] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._TECH_CODE);
		fieldDSArray[20] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._TECHNOLOGY_GROUP);
		fieldDSArray[21] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PARTNER_TIER_CODE);
		fieldDSArray[22] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._SHIP_TO_CITY);
		fieldDSArray[23] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._BOOKING_NET);
		fieldDSArray[24] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._BASE_LIST);
		fieldDSArray[25] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._BOOKINGS_QTY);
		fieldDSArray[26] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._BE_NAME);
		fieldDSArray[27] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._SUB_BE_NAME);
		fieldDSArray[28] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._TAM_MATCH);
		fieldDSArray[29] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._AG_MATCH);
		fieldDSArray[30] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._AG_PAR_MATCH);
		fieldDSArray[31] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._AG_MATCH_2);
		fieldDSArray[32] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._ARCH1);
		fieldDSArray[33] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._ARCH2);
		fieldDSArray[34] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PRODUCT_ID);
		fieldDSArray[35] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_ID5);
		fieldDSArray[36] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_NAME5);
		fieldDSArray[37] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_TYPE5);
		fieldDSArray[38] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._VS_TEAM_NODE);
		fieldDSArray[39] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._BILL_TO_SITE_CITY);
		fieldDSArray[40] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._VERTICAL);
		fieldDSArray[41] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._FY15_TAM);
		fieldDSArray[42] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._IOT_PORTFOLIO);
		fieldDSArray[43] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._GTMU);
		fieldDSArray[44] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PARTNER_CER);
		fieldDSArray[45] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PARTNER_TYPE);
		fieldDSArray[46] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PRODUCT_FAMILY);
		fieldDSArray[47] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._BOOKING_ADJUSTMENT);
		fieldDSArray[48] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_SL6);
		fieldDSArray[49] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_SUB_SCMS);
		fieldDSArray[50] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_REGION);
		fieldDSArray[51] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_GTMU);
		fieldDSArray[52] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_ID4);
		fieldDSArray[53] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_NAME4);
		fieldDSArray[54] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_TYPE4);
		fieldDSArray[55] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_ID3);
		fieldDSArray[56] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_NAME3);
		fieldDSArray[57] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_TYPE3);
		fieldDSArray[58] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_ID2);
		fieldDSArray[59] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_NAME2);
		fieldDSArray[60] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_TYPE2);
		fieldDSArray[61] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_ID1);
		fieldDSArray[62] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_NAME1);
		fieldDSArray[63] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_TYPE1);
		fieldDSArray[64] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_ID0);
		fieldDSArray[65] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_NAME0);
		fieldDSArray[66] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._MAPPED_TYPE0);
		fieldDSArray[67] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._UNIQUE_CITY);
		fieldDSArray[68] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._UNIQUE_STATE);
		fieldDSArray[69] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._UNIQUE_COUNTRY);
		fieldDSArray[70] = new DSSQLField(FieldMode.FIELD, null, ConstantsBookingDump._PROD_SER);
		orderByMap = new HashMap<String, String>();
		orderByMap = null;
		System.out.println("Printing SQL Field String and Query String");
		SQLQueryAssembly queryAssembly = new SQLQueryAssembly(fieldDSArray, GeneralConstants.TBLNAME_BOOKING_DUMP, sqlCondition, groupByFieldArray, orderByMap, new DSLimit2D(0, 0));
		System.out.println("SQL Field String: " + queryAssembly.getSQLFieldConfiguration().getFieldsAsString());
		System.out.println("SQL Write Query String: " + queryAssembly.getQueryConfiguration().generateWriteQuery());
		return queryAssembly;
	}

	
}


