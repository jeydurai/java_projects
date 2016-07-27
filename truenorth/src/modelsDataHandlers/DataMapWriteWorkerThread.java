package modelsDataHandlers;

import helperUtilities.Comforter;
import helperUtilities.DefaultSQLQueries;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import modelsDataStructures.DSDumpFromFinance;
import modelsDataStructures.DSGTMuMaster;
import modelsDataStructures.DSIOTPortfolios;
import modelsDataStructures.DSTechGrandMaster;
import modelsDataStructures.DSUniqueCities;
import modelsDataStructures.DSUniqueSalesAgents;
import modelsDataStructures.DSUniversalUniqueNames;
import modelsDataStructures.DSWeekMaster;
import modelsSQL.SQLDumpFromFinance;
import modelsSQL.SQLGTMuMaster;
import modelsSQL.SQLIOTPortfolios;
import modelsSQL.SQLQueryEngine;
import modelsSQL.SQLTechGrandMaster;
import modelsSQL.SQLUniqueCities;
import modelsSQL.SQLUniqueSalesAgents;
import modelsSQL.SQLUniversalUniqueNames;
import modelsSQL.SQLWeekMaster;
import queryEngines.Queryable;
import configurations.SQLQueryAssembly;
import dataStructures.DSCustomizable;
import dataStructures.DSParameterDouble;
import dataStructures.DSParameterFloat;
import dataStructures.DSParameterInteger;
import dataStructures.DSParameterString;
import sql.MySQLConnection;

public class DataMapWriteWorkerThread extends SwingWorker<Void, DataPair> implements PropertyChangeListener{
	
	private String threadName;
	private String atAttach, accountName, customerName, erpDealID, salesOrderNumberDetail, fpQuarter,
	partner, partnerName, tbm, region, salesLevel6, scms, subSCMS, tmsLevel1SalesAllocated, techName,
	techCode, technologyGroup, partnerTierCode, shipToCity, tmsBookingsQuantity, beName, subBEName, 
	tamMatch, agMatch, agParMatch, agMatch2, arch1, arch2, productID, mappedID5, mappedName5, mappedType5, 
	vsTeamNode, billToSiteCity, vertical, fy15TAM, iotPortfolio, gtmu, partnerCertification, partnerType, 
	productFamily, bookingAdjustment, mappedSalesLevel6, mappedSubSCMS, mappedRegion, mappedGTMu, mappedID4, 
	mappedName4, mappedType4, mappedID3, mappedName3, mappedType3, mappedID2, mappedName2, mappedType2, 
	mappedID1, mappedName1, mappedType1, mappedID0, mappedName0, mappedType0, uniqueCity, uniqueState, 
	uniqueCountry, prodSer;
	private int fpYear, fpMonth, fpWeek, tempFPWeek, recordsWritten;
	private double bookingNet, baseList;
	private Integer year;
	private Connection sqlConn;
	private boolean guiEnabled;
	private JTextArea jtaDB, jtaProcess, jtaTimer, jtaError;
	private Queryable sqlEngine;
	private JButton runButton;
	private double startTime;
	private SQLQueryAssembly queryAssembly;
	private Map<Integer, DSCustomizable> params;
	private DSCustomizable paramValue;
	private List<DSDumpFromFinance> array;
	private String tempSL5, tempSubSCMS, tmsLevel1;
	
	public DataMapWriteWorkerThread(String threadName, Integer year, MySQLConnection connObject, 
			boolean guiEnabled, JTextArea jtaDB, JTextArea jtaProcess, JTextArea jtaTimer, 
			JTextArea jtaError, JButton runButton, int progress, double startTime) {
		this.threadName = threadName; this.year = year; 
		this.sqlConn = connObject.acquirePooledSQLConnection(); this.guiEnabled = guiEnabled;
		this.jtaDB = jtaDB; this.jtaProcess = jtaProcess; this.jtaTimer = jtaTimer; this.jtaError = jtaError;
		this.runButton = runButton;
		this.startTime = startTime;
		this.addPropertyChangeListener(this);
	}
	
	@Override
	protected Void doInBackground() {
		setProgress(0);
		queryAssembly = DefaultSQLQueries.getAssemblyDumpFromFinance();
		if (guiEnabled) {
			params = new HashMap<Integer, DSCustomizable>();
			paramValue = new DSParameterInteger();
			paramValue.setParameter(year);
			params.put(1, paramValue);
			sqlEngine = new SQLDumpFromFinance(sqlConn, queryAssembly.getQueryConfiguration());
			sqlEngine.createPreparedStatement(jtaError);
			array = sqlEngine.fetchAllData(params, jtaError);
			
			int counter = 0;
			int sizeofArray = array.size();
			for (DSDumpFromFinance element: array) { 
				if (!this.isCancelled()) {
					
					// ================================================================================
						// Match and Fetch Customer Names and Vertical info from universal_unique_names
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getCustomerName());
					params.put(1, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyUniversalUniqueNames();
					sqlEngine = new SQLUniversalUniqueNames(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSUniversalUniqueNames> customerNameArray = sqlEngine.fetchAllData(params, jtaError);
					for (DSUniversalUniqueNames dict: customerNameArray) {
						this.customerName = dict.getUniqueName(); this.vertical = dict.getIndustryVertical();
					}
					
					tamMatch = ""; agMatch = ""; agParMatch = ""; agMatch2 = ""; fy15TAM = "";
					// ====================================================================================
					
					// ================================================================================
					// Substring operation to extract periods
					// ================================================================================
					fpYear = Integer.parseInt(element.getFiscalPeriodID().substring(0, 4)); 
					fpQuarter = element.getFiscalQuarterID().substring(element.getFiscalQuarterID().length()-2); 
					fpMonth = Integer.parseInt(element.getFiscalPeriodID().substring(element.getFiscalPeriodID().length()-2));
					tempFPWeek = Integer.parseInt(element.getFiscalWeekID().substring(element.getFiscalWeekID().length()-2));
					// ================================================================================
					// ================================================================================
					// Match and Fetch Fiscal week from week_master
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(fpQuarter);
					params.put(1, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(Integer.toString(tempFPWeek));
					params.put(2, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyWeekMaster();
					sqlEngine = new SQLWeekMaster(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSWeekMaster> weeks = sqlEngine.fetchAllData(params, jtaError);
					for (DSWeekMaster week: weeks) {
						this.fpWeek = Integer.parseInt(week.getFiscalWeek());
					}
					// ================================================================================
					// ================================================================================
					// Match and Fetch Partner Names from universal_unique_names
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getPartnerName());
					params.put(1, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyUniversalUniqueNames();
					sqlEngine = new SQLUniversalUniqueNames(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSUniversalUniqueNames> partnerNameArray = sqlEngine.fetchAllData(params, jtaError);
					for (DSUniversalUniqueNames dict: partnerNameArray) {
						this.partnerName = dict.getUniqueName();
					}
					// ================================================================================
					// Substring operation to extract Region from Sales Level 5
					// ================================================================================
					try {
						tempSL5 = element.getSalesLevel5();
						region = tempSL5.substring((tempSL5.indexOf("_")+1), (tempSL5.indexOf("_", (tempSL5.indexOf("_")+1))));
						//System.out.println(region);
					} catch(Exception e) {
						e.printStackTrace();
						jtaError.append("\n" + Comforter.StackTraceToString(e));
					}
					// ================================================================================
					// ================================================================================
					// Match and Fetch GTMu from gtmu_master
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(region);
					params.put(1, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyGTMu();
					sqlEngine = new SQLGTMuMaster(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSGTMuMaster> gtmuArray = sqlEngine.fetchAllData(params, jtaError);
					for (DSGTMuMaster dict: gtmuArray) {
						gtmu = dict.getGTMu();
					}
					// ================================================================================
					// ================================================================================
					// Substring operation to extract Sub SCMS from from sub_scms
					// ================================================================================
					try {
						tempSubSCMS = element.getSubSCMS();
						subSCMS = tempSubSCMS.substring((tempSubSCMS.indexOf("_")+1), tempSubSCMS.length());
						//System.out.println(subSCMS);
					} catch(Exception e) {
						e.printStackTrace();
						jtaError.append("\n" + Comforter.StackTraceToString(e));
					}
					// ===============================================================================
					// ===============================================================================
					// Substring operation to extract Technology code from TMS_Level_1_Sales_Allocated
					// and Fetch Technology, Arch1, Arch2 from tech_grand_master
					// ===============================================================================
					if (year <= 2012) {
						try {
							tmsLevel1SalesAllocated = element.getTMSLevel1SalesAllocated();
							techCode = tmsLevel1SalesAllocated.substring((tmsLevel1SalesAllocated.indexOf("-")+1), tmsLevel1SalesAllocated.length());
							//System.out.println(techCode);
						} catch(Exception e) {
							e.printStackTrace();
							jtaError.append("\n" + Comforter.StackTraceToString(e));
						}
					} else {
						techCode = element.getSubBEName();
					}
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(techCode);
					params.put(1, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyTechGrandMaster();
					sqlEngine = new SQLTechGrandMaster(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSTechGrandMaster> techArray = sqlEngine.fetchAllData(params, jtaError);
					for (DSTechGrandMaster dict: techArray) {
						techName = dict.getTechnology();
						arch1 = dict.getArchitecture1();
						arch2 = dict.getArchitecture2();
					}
					// ================================================================================
					// ================================================================================
					// Match and Fetch mapped_sales_levels from unique_sales_agents
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSalesAgent());
					params.put(1, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSalesLevel6());
					params.put(2, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyUniqueSalesAgents();
					sqlEngine = new SQLUniqueSalesAgents(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSUniqueSalesAgents> salesAgents = sqlEngine.fetchAllData(params, jtaError);
					for (DSUniqueSalesAgents agent: salesAgents) {
						mappedID5 = agent.getMappedID5(); mappedName5 = agent.getMappedName5(); mappedType5 = agent.getMappedType5(); vsTeamNode = agent.getVSTeamnode();
						mappedSalesLevel6 = agent.getMappedSalesLevel6(); mappedSubSCMS = agent.getMappedSubSCMS(); mappedRegion = agent.getMappedRegion(); mappedGTMu = agent.getMappedGTMu();
						mappedID4 = agent.getMappedID4(); mappedName4 = agent.getMappedName4(); mappedType4 = agent.getMappedType4();
						mappedID3 = agent.getMappedID3(); mappedName3 = agent.getMappedName3(); mappedType3 = agent.getMappedType3();
						mappedID2 = agent.getMappedID2(); mappedName2 = agent.getMappedName2(); mappedType2 = agent.getMappedType2();
						mappedID1 = agent.getMappedID1(); mappedName1 = agent.getMappedName1(); mappedType1 = agent.getMappedType1();
						mappedID0 = agent.getMappedID0(); mappedName0 = agent.getMappedName0(); mappedType0 = agent.getMappedType0();
					}
					System.out.println(mappedID5 + "|" + mappedName5 + "|" + mappedType5 + "|" + mappedSalesLevel6 + "|" + mappedSubSCMS + "|" + mappedRegion + "|" + mappedGTMu);
					// ================================================================================
					// ================================================================================
					// Match and Fetch IOT Portfolios from iot_portfolios
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getProductFamily());
					params.put(1, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getProductID());
					params.put(2, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyIOTPortfolios();
					sqlEngine = new SQLIOTPortfolios(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSIOTPortfolios> iotPortfolios = sqlEngine.fetchAllData(params, jtaError);
					for (DSIOTPortfolios iot: iotPortfolios) {
						iotPortfolio = iot.getIOTPortfolio(); 
					}
					System.out.println(iotPortfolio + "|" + mappedID5 + "|" + mappedName5 + "|" + mappedType5 + "|" + mappedSalesLevel6 + "|" + mappedSubSCMS + "|" + mappedRegion + "|" + mappedGTMu);
					// ================================================================================
					// ================================================================================
					// Match and Fetch Unique Cities from unique_cities
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSalesLevel6());
					params.put(1, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSalesAgent());
					params.put(2, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getBillToSiteCity());
					params.put(3, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getShipToCity());
					params.put(4, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyUniqueCities();
					sqlEngine = new SQLUniqueCities(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					List<DSUniqueCities> cities = sqlEngine.fetchAllData(params, jtaError);
					for (DSUniqueCities city: cities) {
						uniqueCity = city.getUniqueCity(); uniqueState = city.getUniqueState(); uniqueCountry = city.getUniqueCountry();
					}
					System.out.println(uniqueCity + "|" + uniqueState + "|" + uniqueCountry + "|" + iotPortfolio + "|" + mappedID5 + "|" + mappedName5 + "|" + mappedType5 + "|" + mappedSalesLevel6 + "|" + mappedSubSCMS + "|" + mappedRegion + "|" + mappedGTMu);
					// ================================================================================
					// ================================================================================
					// Write in SQL booking_dump table
					// ================================================================================
					params = null;
					params = new HashMap<Integer, DSCustomizable>();
					paramValue = null;
					paramValue = new DSParameterFloat();
					System.out.print("\nID No. from Dump from Finance is ");
					System.out.println(element.getID());
					paramValue.setParameter(element.getID());
					params.put(1, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getATAttach());
					params.put(2, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getCustomerName());
					params.put(3, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(customerName);
					params.put(4, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getERPDealID());
					params.put(5, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSalesOrderNumberDetail());
					params.put(6, paramValue);
					paramValue = null;
					paramValue = new DSParameterInteger();
					paramValue.setParameter(fpYear);
					params.put(7, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(fpQuarter);
					params.put(8, paramValue);
					paramValue = null;
					paramValue = new DSParameterInteger();
					paramValue.setParameter(fpMonth);
					params.put(9, paramValue);
					paramValue = null;
					paramValue = new DSParameterInteger();
					paramValue.setParameter(fpWeek);
					params.put(10, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getPartnerName());
					params.put(11, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(partnerName);
					params.put(12, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSalesAgent());
					params.put(13, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(region);
					params.put(14, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSalesLevel6());
					params.put(15, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSCMS());
					params.put(16, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSubSCMS());
					params.put(17, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getTMSLevel1SalesAllocated());
					params.put(18, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(techName);
					params.put(19, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(techCode);
					params.put(20, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getTechnologyGroup());
					params.put(21, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getPartnerTierCode());
					params.put(22, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getShipToCity());
					params.put(23, paramValue);
					paramValue = null;
					paramValue = new DSParameterDouble();
					paramValue.setParameter(element.getBookingNet());
					params.put(24, paramValue);
					paramValue = null;
					paramValue = new DSParameterDouble();
					paramValue.setParameter(element.getBaseList());
					params.put(25, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getBookingsQuantity());
					params.put(26, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getBEName());
					params.put(27, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getSubBEName());
					params.put(28, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(tamMatch);
					params.put(29, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(agMatch);
					params.put(30, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(agParMatch);
					params.put(31, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(agMatch2);
					params.put(32, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(arch1);
					params.put(33, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(arch2);
					params.put(34, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getProductID());
					params.put(35, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedID5);
					params.put(36, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedName5);
					params.put(37, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedType5);
					params.put(38, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(vsTeamNode);
					params.put(39, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getBillToSiteCity());
					params.put(40, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(vertical);
					params.put(41, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(fy15TAM);
					params.put(42, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(iotPortfolio);
					params.put(43, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(gtmu);
					params.put(44, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getPartnerCertification());
					params.put(45, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getPartnerType());
					params.put(46, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getProductFamily());
					params.put(47, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getBookingsAdjustmentsType());
					params.put(48, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedSalesLevel6);
					params.put(49, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedSubSCMS);
					params.put(50, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedRegion);
					params.put(51, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedGTMu);
					params.put(52, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedID4);
					params.put(53, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedName4);
					params.put(54, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedID4);
					params.put(55, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedID3);
					params.put(56, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedName3);
					params.put(57, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedType3);
					params.put(58, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedID2);
					params.put(59, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedName2);
					params.put(60, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedType2);
					params.put(61, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedID1);
					params.put(62, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedName1);
					params.put(63, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedType1);
					params.put(64, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedID0);
					params.put(65, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedName0);
					params.put(66, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(mappedType0);
					params.put(67, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(uniqueCity);
					params.put(68, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(uniqueState);
					params.put(69, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(uniqueCountry);
					params.put(70, paramValue);
					paramValue = null;
					paramValue = new DSParameterString();
					paramValue.setParameter(element.getProdSer());
					params.put(71, paramValue);
					queryAssembly = DefaultSQLQueries.getAssemblyInsertTableRecords();
					sqlEngine = new SQLQueryEngine(sqlConn, queryAssembly.getQueryConfiguration());
					sqlEngine.createPreparedStatement(jtaError);
					int recordsWritten = sqlEngine.insertTableRecords(params, jtaError);
					// ================================================================================
					++counter;
					int progress = (int)(((float)counter/(float)sizeofArray)*100.0);
					if (progress == 100) setProgress(progress);
					publish(new DataPair(progress, this.fpYear + "|" + this.fpQuarter + "|" + this.fpMonth + "|" + this.fpWeek + " | " + this.customerName + " |" + String.valueOf(element.getBookingNet())));
				}
			}
		} else {
			System.out.println(queryAssembly.getQueryConfiguration().generateDistinctQuery());
		}
        return null;
	}
	
	public void process(List<DataPair> pairs) {
		this.jtaError.append("\n" + threadName + " - " + queryAssembly.getQueryConfiguration().generateAggregateQuery());
		DataPair pair = pairs.get(pairs.size()-1);
		jtaProcess.append("\n" + threadName + " (" + pair.progress + "%)" + " | " + pair.element);
		
		/*for (DataPair pair : pairs) {
			jtaProcess.append("\n" + threadName + " - " + "\t" + pair.element + " " + pair.progress + "%");
		}*/
	}

	
	@Override
	public void done() {
		Toolkit.getDefaultToolkit().beep();
		runButton.setEnabled(true);
		double endTime = System.currentTimeMillis();
		double timeElapsed = (endTime - this.startTime)/1000;
		this.jtaTimer.append(String.format("\nElapsed Time: %.3f (secs)", timeElapsed));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress"  == evt.getPropertyName()) {
			this.jtaTimer.append(String.format("\nCompleted %s Task!", this.threadName));
		}
	}

}
