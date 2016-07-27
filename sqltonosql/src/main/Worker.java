package main;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import nosql.MongoEngine;
import sql.SQLConnection;
import sql.SQLParamString;
import sql.SQLQueryEngine;
import sql.TypeCustomizable;
import helper.Config;
import helper.ProcessHelper;
import helper.ProcessTime;

import org.json.simple.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;

import dataClasses.MetricParamShare;
import dataClasses.ReverseTopperData;
import dataClasses.TopperData;

public class Worker implements Runnable{

	private SQLConnection connObject;
	private SQLQueryEngine queryEngine;
	private Map<String, String> map = new HashMap<String, String>();
	private String period;
	private String field, whereClause, groupByClause, threadName;
	private String stateWhereClause;
	private Map<Integer, TypeCustomizable> sqlParams;
	private Map<String, String[]> regionByGtmu, sl6sByGtmu, sl6sByRegion, sl6sBySubSCMS, salesAgentsByGtmu, 
	salesAgentsByRegion, salesAgentsBySubSCMS, salesAgentsBySL6, partnersByGtmu, partnersByRegion, partnersBySubSCMS, 
	partnersBySL6, partnersBySalesAgent, customersByGtmu, customersByRegion, customersBySubSCMS, customersBySL6, 
	customersBySalesAgent, customersByPartner;
	private Map<String, String[]> allSubSCMSs, allGTMUs, allRegions, allSL6s, allSalesAgents, allPartners, 
	allCustomers;
	private Map<String, String> condi1, condi2, condi3;
	private String[] subSCMSsNoCondition, gtmusNoCondition, regionsNoCondition, sl6sNoCondition, salesAgentsNoCondition, 
	partnersNoCondition, customersNoCondition, archs2NoCondition, techsNoCondition, 
	atAttachNoCondition, partnerTierCodeNoCondition, verticalNoCondition, iotPortfolioNoCondition, salesAgentTypeNoCondition;
	private TypeCustomizable data, data2, data3;
	private final String fileName = "printStream.txt";
	private File file;
	private FileWriter fw;
	private MongoEngine me;
	private BasicDBObjectBuilder document, document2, sl6Object, saObject, partnerObject, customerObject;
	private BasicDBObject subSCMSMetrics, gtmuMetrics, regionMetrics, sl6Metrics, saMetrics, partnerMetrics, customerMetrics;
	private ProcessTime timer;
//	private int sleepTime;
		
	public Worker (String threadName, String period, 
			String whereClause, String groupByClause, Map<Integer, TypeCustomizable> sqlParams, SQLConnection connObject) {
		this.connObject = connObject;
		this.map.put("table_name", ProcessHelper.BASE_TABLE_NAME);
		this.threadName = threadName;
		this.period = period;
		this.timer = new ProcessTime(this.threadName + "-" + this.period);
		this.stateWhereClause = whereClause;
		this.groupByClause = groupByClause;
		this.sqlParams = sqlParams;
		this.regionByGtmu = new HashMap<String, String[]>();
		this.sl6sByGtmu = new HashMap<String, String[]>();
		this.sl6sByRegion = new HashMap<String, String[]>();
		this.sl6sBySubSCMS = new HashMap<String, String[]>();
		this.salesAgentsByGtmu = new HashMap<String, String[]>();
		this.salesAgentsByRegion = new HashMap<String, String[]>();
		this.salesAgentsBySubSCMS = new HashMap<String, String[]>();
		this.salesAgentsBySL6 = new HashMap<String, String[]>();
		this.partnersByGtmu = new HashMap<String, String[]>();
		this.partnersByRegion = new HashMap<String, String[]>();
		this.partnersBySubSCMS = new HashMap<String, String[]>();
		this.partnersBySL6 = new HashMap<String, String[]>();
		this.partnersBySalesAgent = new HashMap<String, String[]>();
		this.customersByGtmu = new HashMap<String, String[]>();
		this.customersByRegion = new HashMap<String, String[]>();
		this.customersBySubSCMS = new HashMap<String, String[]>();
		this.customersBySL6 = new HashMap<String, String[]>();
		this.customersBySalesAgent = new HashMap<String, String[]>();
		this.customersByPartner = new HashMap<String, String[]>();
		this.allSubSCMSs = new HashMap<String, String[]>();
		this.allGTMUs = new HashMap<String, String[]>();
		this.allRegions = new HashMap<String, String[]>();
		this.allSL6s = new HashMap<String, String[]>();
		this.allSalesAgents = new HashMap<String, String[]>();
		this.allPartners = new HashMap<String, String[]>();
		this.allCustomers = new HashMap<String, String[]>();
		this.subSCMSMetrics = new BasicDBObject();
		this.gtmuMetrics = new BasicDBObject();
		this.regionMetrics = new BasicDBObject();
		this.sl6Metrics = new BasicDBObject();
		this.saMetrics = new BasicDBObject();
		this.partnerMetrics = new BasicDBObject();
		this.customerMetrics = new BasicDBObject();
		condi1 = new HashMap<String, String>();
		condi2 = new HashMap<String, String>();
		condi3 = new HashMap<String, String>();
		file = new File(Config.STATIC + fileName);
		System.out.printf("Thread %s %s has initiated!\n", this.threadName, this.period);
	}

	public void run () {
		
		//System.out.printf("Thread - %s is processing...\n", this.period);
		Integer keyBefore = getParamMapKeySize();
		
		setQueryCriteria("nothing", "", "nothing", "");
		subSCMSsNoCondition = getNodesStringArray(condi1, condi2, "sub_scms", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		gtmusNoCondition = getNodesStringArray(condi1, condi2, "gtmu", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		regionsNoCondition = getNodesStringArray(condi1, condi2, "region", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		sl6sNoCondition = getNodesStringArray(condi1, condi2, "sales_level_6", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		salesAgentsNoCondition = getNodesStringArray(condi1, condi2, "tbm", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		partnersNoCondition = getNodesStringArray(condi1, condi2, "partner_name", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		customersNoCondition = getNodesStringArray(condi1, condi2, "customer_name", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		archs2NoCondition = getNodesStringArray(condi1, condi2, "arch2", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		techsNoCondition = getNodesStringArray(condi1, condi2, "tech_name", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		atAttachNoCondition = getNodesStringArray(condi1, condi2, "at_attach", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		partnerTierCodeNoCondition = getNodesStringArray(condi1, condi2, "Partner_Tier_Code", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		verticalNoCondition = getNodesStringArray(condi1, condi2, "Vertical", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		iotPortfolioNoCondition = getNodesStringArray(condi1, condi2, "iot_portfolio", 0); removeParamMapKeys(keyBefore);
		setQueryCriteria("nothing", "", "nothing", "");
		salesAgentTypeNoCondition = getNodesStringArray(condi1, condi2, "Mapped_Type", 0); removeParamMapKeys(keyBefore);

		//System.out.printf("%s|Customers: %d\n", this.period, customersNoCondition.length);

		subSCMSNoConditionLoop(keyBefore);
		gtmuNoConditionLoop(keyBefore);		
		regionNoConditionLoop(keyBefore);
		sl6NoConditionLoop(keyBefore);
		salesAgentNoConditionLoop(keyBefore);		
		partnerNoConditionLoop(keyBefore);
		customerNoConditionLoop(keyBefore);
		

		System.out.printf("Object Preparation Starts for thread '%s'-%s...\n", this.threadName, this.period);
		System.out.printf("SL6 Object Preparation Starts for thread '%s'-%s...\n", this.threadName, this.period);
		sl6Object = BasicDBObjectBuilder.start()
					.add(ProcessHelper.Key.ALL.asKey(), sl6sNoCondition)
					.add(ProcessHelper.Key.GTMU.asKey(), sl6sByGtmu)
					.add(ProcessHelper.Key.SUB_SCMS.asKey(), sl6sBySubSCMS)
					.add(ProcessHelper.Key.REGION.asKey(), sl6sByRegion)
					.add(ProcessHelper.Key.METRICS.asKey(), sl6Metrics);

		
		System.out.printf("SL6 Object Prepared for thread '%s'-%s!\n", this.threadName, this.period);
		System.out.printf("Sales Agents Object Preparation Starts for thread '%s'-%s...\n", this.threadName, this.period);
		saObject = BasicDBObjectBuilder.start()
					.add(ProcessHelper.Key.ALL.asKey(), salesAgentsNoCondition)
					.add(ProcessHelper.Key.GTMU.asKey(), salesAgentsByGtmu)
					.add(ProcessHelper.Key.SUB_SCMS.asKey(), salesAgentsBySubSCMS)
					.add(ProcessHelper.Key.REGION.asKey(), salesAgentsByRegion)
					.add(ProcessHelper.Key.SL6.asKey(), salesAgentsBySL6)
					.add(ProcessHelper.Key.METRICS.asKey(), saMetrics);

		System.out.printf("Sales Agents Object Prepared for thread '%s'-%s!\n", this.threadName, this.period);
		System.out.printf("Partners Object Preparation Starts for thread '%s'-%s...\n", this.threadName, this.period);
		partnerObject = BasicDBObjectBuilder.start()
						.add(ProcessHelper.Key.ALL.asKey(), partnersNoCondition)
						.add(ProcessHelper.Key.GTMU.asKey(), partnersByGtmu)
						.add(ProcessHelper.Key.SUB_SCMS.asKey(), partnersBySubSCMS)
						.add(ProcessHelper.Key.REGION.asKey(), partnersByRegion)
						.add(ProcessHelper.Key.SL6.asKey(), partnersBySL6)
						.add(ProcessHelper.Key.SA.asKey(), partnersBySalesAgent)
						.add(ProcessHelper.Key.METRICS.asKey(), partnerMetrics);

		System.out.printf("Partners Object Prepared for thread '%s'-%s!\n", this.threadName, this.period);
		System.out.printf("Customers Object Preparation Starts for thread '%s'-%s...\n", this.threadName, this.period);
		customerObject = BasicDBObjectBuilder.start()
						.add(ProcessHelper.Key.ALL.asKey(), customersNoCondition)
						.add(ProcessHelper.Key.GTMU.asKey(), customersByGtmu)
						.add(ProcessHelper.Key.SUB_SCMS.asKey(), customersBySubSCMS)
						.add(ProcessHelper.Key.REGION.asKey(), customersByRegion)
						.add(ProcessHelper.Key.SL6.asKey(), customersBySL6)
						.add(ProcessHelper.Key.SA.asKey(), customersBySalesAgent)
						.add(ProcessHelper.Key.PAR.asKey(), customersByPartner)
						.add(ProcessHelper.Key.METRICS.asKey(), customerMetrics);
		
		System.out.printf("Customers Object Prepared for thread '%s'-%s!\n", this.threadName, this.period);
		System.out.printf("Main Object Preparation Starts for thread '%s'-%s...\n", this.threadName, this.period);
		
		document = BasicDBObjectBuilder.start()
				.add(ProcessHelper.Key.PERIOD.asKey(), this.period)
				.add(ProcessHelper.Key.SUB_SCMS.asKey(), BasicDBObjectBuilder.start()
						.add(ProcessHelper.Key.NODES.asKey(), subSCMSsNoCondition)
						.add(ProcessHelper.Key.METRICS.asKey(), subSCMSMetrics).get())
				.add(ProcessHelper.Key.GTMU.asKey(), BasicDBObjectBuilder.start()
						.add(ProcessHelper.Key.NODES.asKey(), gtmusNoCondition)
						.add(ProcessHelper.Key.METRICS.asKey(), gtmuMetrics).get())
				.add(ProcessHelper.Key.REGION.asKey(), BasicDBObjectBuilder.start()
						.add(ProcessHelper.Key.NODES.asKey(),regionsNoCondition)
						.add(ProcessHelper.Key.METRICS.asKey(), regionMetrics).get())
				.add(ProcessHelper.Key.SL6.asKey(), BasicDBObjectBuilder.start()
						.add(ProcessHelper.Key.NODES.asKey(),sl6Object.get())
						.add(ProcessHelper.Key.METRICS.asKey(), sl6Metrics).get());

		document2 = BasicDBObjectBuilder.start()
				.add(ProcessHelper.Key.PERIOD.asKey(), this.period)
				.add(ProcessHelper.Key.SA.asKey(), saObject.get())
				.add(ProcessHelper.Key.PAR.asKey(), partnerObject.get())
				.add(ProcessHelper.Key.CUS.asKey(), customerObject.get());

		
		me = new MongoEngine(this.timer);
		String periodString = "Thread "+ this.threadName + "-" + this.period;
		System.out.printf("Documents insertion in Nodes Coll. for thread '%s' initializing ...\n", periodString);
		me.insertNodes(periodString, document);
		
		System.out.printf("Documents insertion in Business Partners Coll. for thread '%s' initializing ...\n", periodString);
		me.insertBusinessPartners(periodString, document2);

		//this.printObjects();
	}

	/*
	 * THE FOLLOWING ARE THE PRIVATE HELPER METHODS
	 * ############################################
	 */
	private void subSCMSNoConditionLoop(Integer keyBefore) {
		System.out.printf("Period-%s ... Sub SCMS No Condition LOOP in on...\n", this.period);
		Double booking = 0.0;
		Double totalBooking = 0.0;
		Double baseList = 0.0;
		Map<String, Double> bookingMap = new HashMap<String, Double>();
		Map<String, Double> baseListMap = new HashMap<String, Double>();
		for (String subSCMS : subSCMSsNoCondition) {
			//System.out.printf("\t\t%s\n", subSCMS);
			subSCMS = ProcessHelper.escDot(subSCMS);
			if (subSCMS != null && !subSCMS.equals("")) {
				setQueryCriteria("sub_scms", subSCMS, "nothing", "");
				sl6sBySubSCMS.put(JSONObject.escape(subSCMS) , getNodesStringArray(condi1, condi2, "sales_level_6", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("sub_scms", subSCMS, "nothing", "");
				salesAgentsBySubSCMS.put(JSONObject.escape(subSCMS), getNodesStringArray(condi1, condi2, "tbm", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("sub_scms", subSCMS, "nothing", "");
				partnersBySubSCMS.put(JSONObject.escape(subSCMS), getNodesStringArray(condi1, condi2, "partner_name", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("sub_scms", subSCMS, "nothing", "");
				customersBySubSCMS.put(JSONObject.escape(subSCMS), getNodesStringArray(condi1, condi2, "customer_name", keyBefore)); removeParamMapKeys(keyBefore);

				setQueryCriteria("sub_scms", subSCMS, "nothing", "");
				booking = getMetric(condi1, condi2, "SUM(booking_net) as booking", "", keyBefore); removeParamMapKeys(keyBefore);
				totalBooking = totalBooking + booking;
				bookingMap.put(JSONObject.escape(subSCMS), booking); 
				setQueryCriteria("sub_scms", subSCMS, "nothing", "");
				baseList = getMetric(condi1, condi2, "SUM(base_list) as base_list", "", keyBefore); removeParamMapKeys(keyBefore);
				baseListMap.put(JSONObject.escape(subSCMS), baseList); 
			}
		}
		subSCMSMetrics = getMetricsObject(bookingMap, baseListMap, totalBooking, "sub_scms", keyBefore, true, "Sub SCMS");
		System.out.printf("Year-%s ... Sub SCMS No Condition LOOP Completed!\n", this.period);
	}

	private void gtmuNoConditionLoop (Integer keyBefore) {
		Double booking = 0.0;
		Double totalBooking = 0.0;
		Double baseList = 0.0;
		Map<String, Double> bookingMap = new HashMap<String, Double>();
		Map<String, Double> baseListMap = new HashMap<String, Double>();
		System.out.printf("%s thread-%s ... GTMu No Condition LOOP in on...\n", this.threadName, this.period);
		for (String gtmu : gtmusNoCondition) {
			//System.out.printf("\t\t%s\n", gtmu);
			gtmu = ProcessHelper.escDot(gtmu);
			if (gtmu != null && !gtmu.equals("")) {
				setQueryCriteria("gtmu", gtmu, "nothing", "");
				regionByGtmu.put(JSONObject.escape(gtmu), getNodesStringArray(condi1, condi2, "region", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("gtmu", gtmu, "nothing", "");
				sl6sByGtmu.put(JSONObject.escape(gtmu), getNodesStringArray(condi1, condi2, "sales_level_6", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("gtmu", gtmu, "nothing", "");
				salesAgentsByGtmu.put(JSONObject.escape(gtmu), getNodesStringArray(condi1, condi2, "tbm", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("gtmu", gtmu, "nothing", "");
				partnersByGtmu.put(JSONObject.escape(gtmu), getNodesStringArray(condi1, condi2, "partner_name", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("gtmu", gtmu, "nothing", "");
				customersByGtmu.put(JSONObject.escape(gtmu), getNodesStringArray(condi1, condi2, "customer_name", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("gtmu", gtmu, "nothing", "");
				booking = getMetric(condi1, condi2, "SUM(booking_net) as booking", "", keyBefore); removeParamMapKeys(keyBefore);
				totalBooking = totalBooking + booking;
				bookingMap.put(JSONObject.escape(gtmu), booking); 
				setQueryCriteria("gtmu", gtmu, "nothing", "");
				baseList = getMetric(condi1, condi2, "SUM(base_list) as base_list", "", keyBefore); removeParamMapKeys(keyBefore);
				baseListMap.put(JSONObject.escape(gtmu), baseList); 
			}
		}
		gtmuMetrics = getMetricsObject(bookingMap, baseListMap, totalBooking, "gtmu", keyBefore, true, "GTMus");
		System.out.printf("Year-%s ... GTMu No Condition LOOP Completed!\n", this.period);
	}

	private void regionNoConditionLoop (Integer keyBefore) {
		Double booking = 0.0;
		Double totalBooking = 0.0;
		Double baseList = 0.0;
		Map<String, Double> bookingMap = new HashMap<String, Double>();
		Map<String, Double> baseListMap = new HashMap<String, Double>();
		System.out.printf("%s thread-%s ... Region No Condition LOOP in on...\n", this.threadName, this.period);
		for (String region : regionsNoCondition) {
			//System.out.printf("\t\t%s\n", region);
			region = ProcessHelper.escDot(region);
			if (region != null && !region.equals("")) {
				setQueryCriteria("region", region, "nothing", "");
				sl6sByRegion.put(JSONObject.escape(region), getNodesStringArray(condi1, condi2, "sales_level_6", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("region", region, "nothing", "");
				salesAgentsByRegion.put(JSONObject.escape(region), getNodesStringArray(condi1, condi2, "tbm", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("region", region, "nothing", "");
				partnersByRegion.put(JSONObject.escape(region), getNodesStringArray(condi1, condi2, "partner_name", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("region", region, "nothing", "");
				customersByRegion.put(JSONObject.escape(region), getNodesStringArray(condi1, condi2, "customer_name", keyBefore)); removeParamMapKeys(keyBefore);

				setQueryCriteria("region", region, "nothing", "");
				booking = getMetric(condi1, condi2, "SUM(booking_net) as booking", "", keyBefore); removeParamMapKeys(keyBefore);
				totalBooking = totalBooking + booking;
				bookingMap.put(JSONObject.escape(region), booking); 
				setQueryCriteria("region", region, "nothing", "");
				baseList = getMetric(condi1, condi2, "SUM(base_list) as base_list", "", keyBefore); removeParamMapKeys(keyBefore);
				baseListMap.put(JSONObject.escape(region), baseList); 
			}
		}
		regionMetrics = getMetricsObject(bookingMap, baseListMap, totalBooking, "region", keyBefore, true, "Region");
		System.out.printf("Year-%s ... Region No Condition LOOP Completed!\n", this.period);
	}

	private void sl6NoConditionLoop (Integer keyBefore) {
		Double booking = 0.0;
		Double totalBooking = 0.0;
		Double baseList = 0.0;
		Map<String, Double> bookingMap = new HashMap<String, Double>();
		Map<String, Double> baseListMap = new HashMap<String, Double>();
		System.out.printf("%s thread-%s ... Sales_Level_6 No Condition LOOP in on...\n", this.threadName, this.period);
		for (String sl6 : sl6sNoCondition) {
			//System.out.printf("\t\t%s\n", sl6);
			sl6 = ProcessHelper.escDot(sl6);
			if (sl6 != null && !sl6.equals("")) {
				setQueryCriteria("sales_level_6", sl6, "nothing", "");
				salesAgentsBySL6.put(JSONObject.escape(sl6), getNodesStringArray(condi1, condi2, "tbm", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("sales_level_6", sl6, "nothing", "");
				partnersBySL6	.put(JSONObject.escape(sl6), getNodesStringArray(condi1, condi2, "partner_name", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("sales_level_6", sl6, "nothing", "");
				customersBySL6.put(JSONObject.escape(sl6), getNodesStringArray(condi1, condi2, "customer_name", keyBefore)); removeParamMapKeys(keyBefore);

				setQueryCriteria("sales_level_6", sl6, "nothing", "");
				booking = getMetric(condi1, condi2, "SUM(booking_net) as booking", "", keyBefore); removeParamMapKeys(keyBefore);
				totalBooking = totalBooking + booking;
				bookingMap.put(JSONObject.escape(sl6), booking); 
				setQueryCriteria("sales_level_6", sl6, "nothing", "");
				baseList = getMetric(condi1, condi2, "SUM(base_list) as base_list", "", keyBefore); removeParamMapKeys(keyBefore);
				baseListMap.put(JSONObject.escape(sl6), baseList); 
			}
		}
		sl6Metrics = getMetricsObject(bookingMap, baseListMap, totalBooking, "sales_level_6", keyBefore, true, "Sales_Level_6");
		System.out.printf("Year-%s ... Sales_Level_6 No Condition LOOP Completed!\n", this.period);
	}
	
	private void salesAgentNoConditionLoop (Integer keyBefore) {
		Double booking = 0.0;
		Double totalBooking = 0.0;
		Double baseList = 0.0;
		Map<String, Double> bookingMap = new HashMap<String, Double>();
		Map<String, Double> baseListMap = new HashMap<String, Double>();
		System.out.printf("%s thread-%s ... Sales Agents No Condition LOOP in on...\n", this.threadName, this.period);
		for (String sa : salesAgentsNoCondition) {
			//System.out.printf("\t\t%s\n", sa);
			sa = ProcessHelper.escDot(sa);
			if (sa != null && !sa.equals("")) {
				setQueryCriteria("tbm", sa, "nothing", "");
				partnersBySalesAgent.put(JSONObject.escape(sa), getNodesStringArray(condi1, condi2, "partner_name", keyBefore)); removeParamMapKeys(keyBefore);
				setQueryCriteria("tbm", sa, "nothing", "");
				customersBySalesAgent.put(JSONObject.escape(sa), getNodesStringArray(condi1, condi2, "customer_name", keyBefore)); removeParamMapKeys(keyBefore);

				setQueryCriteria("tbm", sa, "nothing", "");
				booking = getMetric(condi1, condi2, "SUM(booking_net) as booking", "", keyBefore); removeParamMapKeys(keyBefore);
				totalBooking = totalBooking + booking;
				bookingMap.put(JSONObject.escape(sa), booking); 
				setQueryCriteria("tbm", sa, "nothing", "");
				baseList = getMetric(condi1, condi2, "SUM(base_list) as base_list", "", keyBefore); removeParamMapKeys(keyBefore);
				baseListMap.put(JSONObject.escape(sa), baseList); 
			}
		}
		saMetrics = getMetricsObject(bookingMap, baseListMap, totalBooking, "tbm", keyBefore, false, "Sales Agents");
		System.out.printf("Year-%s ... Sales Agent No Condition LOOP Completed!\n", this.period);
	}
	
	private void partnerNoConditionLoop (Integer keyBefore) {
		Double booking = 0.0;
		Double totalBooking = 0.0;
		Double baseList = 0.0;
		Map<String, Double> bookingMap = new HashMap<String, Double>();
		Map<String, Double> baseListMap = new HashMap<String, Double>();
		System.out.printf("%s thread-%s ... Partners No Condition LOOP in on...\n", this.threadName, this.period);
		for (String partner : partnersNoCondition) {
			//System.out.printf("\t\t%s\n", partner);
			partner = ProcessHelper.escDot(partner);
			if (partner != null && !partner.equals("")) {
				setQueryCriteria("partner_name", partner, "nothing", "");
				customersByPartner.put(JSONObject.escape(partner), getNodesStringArray(condi1, condi2, "customer_name", keyBefore)); removeParamMapKeys(keyBefore);

				setQueryCriteria("partner_name", partner, "nothing", "");
				booking = getMetric(condi1, condi2, "SUM(booking_net) as booking", "", keyBefore); removeParamMapKeys(keyBefore);
				totalBooking = totalBooking + booking;
				bookingMap.put(JSONObject.escape(partner), booking); 
				setQueryCriteria("partner_name", partner, "nothing", "");
				baseList = getMetric(condi1, condi2, "SUM(base_list) as base_list", "", keyBefore); removeParamMapKeys(keyBefore);
				baseListMap.put(JSONObject.escape(partner), baseList); 
			}
		}
		partnerMetrics = getMetricsObject(bookingMap, baseListMap, totalBooking, "partner_name", keyBefore, false, "Partners");
		System.out.printf("Year-%s ... Partner No Condition LOOP Completed!\n", this.period);
	}


	private void customerNoConditionLoop (Integer keyBefore) {
		Double booking = 0.0;
		Double totalBooking = 0.0;
		Double baseList = 0.0;
		Map<String, Double> bookingMap = new HashMap<String, Double>();
		Map<String, Double> baseListMap = new HashMap<String, Double>();
		System.out.printf("%s thread-%s ... Customers No Condition LOOP in on...\n", this.threadName, this.period);
		for (String customer : customersNoCondition) {
			//System.out.printf("\t\t%s\n", partner);
			customer = ProcessHelper.escDot(customer);
			if (customer != null && !customer.equals("")) {
				setQueryCriteria("customer_name", customer, "nothing", "");
				booking = getMetric(condi1, condi2, "SUM(booking_net) as booking", "", keyBefore); removeParamMapKeys(keyBefore);
				totalBooking = totalBooking + booking;
				bookingMap.put(JSONObject.escape(customer), booking); 
				setQueryCriteria("customer_name", customer, "nothing", "");
				baseList = getMetric(condi1, condi2, "SUM(base_list) as base_list", "", keyBefore); removeParamMapKeys(keyBefore);
				baseListMap.put(JSONObject.escape(customer), baseList); 
			}
		}
		customerMetrics = getMetricsObject(bookingMap, baseListMap, totalBooking, "customer_name", keyBefore, false, "Customers");
		System.out.printf("Year-%s ... Customers No Condition LOOP Completed!\n", this.period);
	}

	
	private BasicDBObject getMetricsObject(Map<String, Double> bookingEach, 
			Map<String, Double> baseListEach, Double total, String basedOn, Integer keyBefore, boolean isToppers, 
			String nodeName) {
		BasicDBObject metrics = new BasicDBObject();
		Set<String> keys = bookingEach.keySet();
		int counter = 0;
		System.out.printf("%s|Customers: %d\n", this.period, keys.size());
		for (String objectKey : keys) {
			BasicDBObject metricDetail = new BasicDBObject();
			BasicDBObject bookingDetail = new BasicDBObject();
			BasicDBObject baseListDetail = new BasicDBObject();
			BasicDBObject bookingTopperDetail = new BasicDBObject();
			BasicDBObject discountTopperDetail = new BasicDBObject();
			BasicDBObject salesAgentDBObject = new BasicDBObject();
			BasicDBObject partnerDBObject = new BasicDBObject();
			BasicDBObject customerDBObject = new BasicDBObject();
			BasicDBObject techDBObject = new BasicDBObject();
			List<ReverseTopperData> reverseTopDataSalesAgents = new ArrayList<ReverseTopperData>();
			List<TopperData> topDataSalesAgents = new ArrayList<TopperData>();
			List<TopperData> topDataPartners = new ArrayList<TopperData>();
			List<TopperData> topDataCustomers = new ArrayList<TopperData>();
			Map<String, String> dataFieldMap = new HashMap<String, String>();
			String queryFieldBooking = "SUM(booking_net) as booking" ;
			String queryFieldBookingSpecial = "SUM(CASE WHEN (ERP_Deal_ID = '') THEN booking_net ELSE 0 END) AS booking" ;
			String queryFieldBookingSpecial2 = "SUM(CASE WHEN (ERP_Deal_ID <> '') THEN booking_net ELSE 0 END) AS booking" ;
			String queryFieldList = "SUM(base_list) as booking" ;
			String queryFieldListSpecial = "SUM(CASE WHEN (ERP_Deal_ID = '') THEN base_list ELSE 0 END) AS list" ;
			String queryFieldListSpecial2 = "SUM(CASE WHEN (ERP_Deal_ID <> '') THEN base_list ELSE 0 END) AS list" ;
			String queryFieldBookingTopper = "";
			String topperGroupByOrderBy = "";

			/*
			 * BOOKING OBJECT
			 * ==============*/
			bookingDetail.put(ProcessHelper.Key.TOTAL.asKey(), bookingEach.get(objectKey));
			baseListDetail.put(ProcessHelper.Key.TOTAL.asKey(), baseListEach.get(objectKey));
			objectKey = ProcessHelper.reviveDotDollar(objectKey);
			
			dataFieldMap.put("query_field", queryFieldBooking);
			MetricParamShare paramShare = new MetricParamShare("NON SPECIAL", dataFieldMap);
			bookingDetail.put("Archs", getSubMetricsObject(archs2NoCondition, "arch2", basedOn, objectKey, keyBefore, paramShare));
			bookingDetail.put("Techs", getSubMetricsObject(techsNoCondition, "tech_name", basedOn, objectKey, keyBefore, paramShare));
			bookingDetail.put("attach", getSubMetricsObject(atAttachNoCondition, "at_attach", basedOn, objectKey, keyBefore, paramShare));
			bookingDetail.put("partner_tier", getSubMetricsObject(partnerTierCodeNoCondition, "partner_tier_code", basedOn, objectKey, keyBefore, paramShare));
			bookingDetail.put("industry", getSubMetricsObject(verticalNoCondition, "vertical", basedOn, objectKey, keyBefore, paramShare));
			bookingDetail.put("iot_portfolio", getSubMetricsObject(iotPortfolioNoCondition, "iot_portfolio", basedOn, objectKey, keyBefore, paramShare));
			bookingDetail.put("sales_agent_type", getSubMetricsObject(salesAgentTypeNoCondition, "mapped_type", basedOn, objectKey, keyBefore, paramShare));

			dataFieldMap.clear();
			dataFieldMap.put("WITH DEAL", queryFieldBookingSpecial);
			dataFieldMap.put("NO DEAL", queryFieldBookingSpecial2);
			paramShare = new MetricParamShare("SPECIAL", dataFieldMap);
			bookingDetail.put("DEAL", getSubMetricsObject(null, "", basedOn, objectKey, keyBefore, paramShare));
			//System.out.printf("%s|%d/%d|%s - Booking Detail finished!\n", this.period, ++counter, keys.size(), objectKey);

			dataFieldMap.clear();
			dataFieldMap.put("query_field", queryFieldList);
			paramShare = new MetricParamShare("NON SPECIAL", dataFieldMap);
			baseListDetail.put("Archs", getSubMetricsObject(archs2NoCondition, "arch2", basedOn, objectKey, keyBefore, paramShare));
			baseListDetail.put("Techs", getSubMetricsObject(techsNoCondition, "tech_name", basedOn, objectKey, keyBefore, paramShare));
			baseListDetail.put("attach", getSubMetricsObject(atAttachNoCondition, "at_attach", basedOn, objectKey, keyBefore, paramShare));
			baseListDetail.put("partner_tier", getSubMetricsObject(partnerTierCodeNoCondition, "partner_tier_code", basedOn, objectKey, keyBefore, paramShare));
			baseListDetail.put("industry", getSubMetricsObject(verticalNoCondition, "vertical", basedOn, objectKey, keyBefore, paramShare));
			baseListDetail.put("iot_portfolio", getSubMetricsObject(iotPortfolioNoCondition, "iot_portfolio", basedOn, objectKey, keyBefore, paramShare));
			baseListDetail.put("sales_agent_type", getSubMetricsObject(salesAgentTypeNoCondition, "mapped_type", basedOn, objectKey, keyBefore, paramShare));

			dataFieldMap.clear();
			dataFieldMap.put("WITH DEAL", queryFieldListSpecial);
			dataFieldMap.put("NO DEAL", queryFieldListSpecial2);
			paramShare = new MetricParamShare("SPECIAL", dataFieldMap);
			baseListDetail.put("DEAL", getSubMetricsObject(null, "", basedOn, objectKey, keyBefore, paramShare));

			if (isToppers) {
				/*
				 * BOOKING TOPPERS OBJECT
				 * ==============*/
				/* ==============
				 * ==============
				 * ==============================
				 * BOOKING TOPPERS BY SALES AGENT
				 * ==============================*/
				topperGroupByOrderBy = " GROUP BY tbm ORDER BY booking DESC LIMIT 0, 10";
				queryFieldBookingTopper = "tbm, SUM(booking_net) as booking";
				setQueryCriteria2("nothing", "", "nothing", "", basedOn, objectKey);
				topDataSalesAgents = getTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
				
				
				for (TopperData data : topDataSalesAgents) {
					Set<String> allSalesAgents = data.getTopperData().keySet();
					String salesAgent = allSalesAgents.iterator().next();
						queryFieldBookingTopper = "partner_name, SUM(booking_net) as booking";
						topperGroupByOrderBy = " GROUP BY partner_name ORDER BY booking DESC LIMIT 0, 10";
						setQueryCriteria2("tbm", salesAgent, "nothing", "", basedOn, objectKey);
						topDataPartners = getTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
						partnerDBObject.put(ProcessHelper.Key.BOOKING.asKey(), data.getTopperData().get(salesAgent));
						for (TopperData data2 : topDataPartners) {
							Set<String> allPartners = data2.getTopperData().keySet();
							String partner = allPartners.iterator().next();
								queryFieldBookingTopper = "customer_name, SUM(booking_net) as booking";
								topperGroupByOrderBy = " GROUP BY customer_name ORDER BY booking DESC LIMIT 0, 10";
								setQueryCriteria2("tbm", salesAgent, "partner_name", partner, basedOn, objectKey);
								topDataCustomers = getTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
								customerDBObject.put(ProcessHelper.Key.BOOKING.asKey(), data2.getTopperData().get(partner));
								for (TopperData data3 : topDataCustomers) {
									Set<String> allCustomers = data3.getTopperData().keySet();
									String customer = allCustomers.iterator().next();
									customerDBObject.put(customer, new BasicDBObject(ProcessHelper.Key.BOOKING.asKey(), data3.getTopperData().get(customer)));
								}	
								partnerDBObject.put(partner, customerDBObject);
								customerDBObject = null;
								customerDBObject = new BasicDBObject();
						}
						salesAgentDBObject.put(salesAgent, partnerDBObject);
						partnerDBObject = null;
						partnerDBObject = new BasicDBObject();
				}
				bookingTopperDetail.put(ProcessHelper.Key.SALES_AGENTS.asKey(), salesAgentDBObject);
				salesAgentDBObject = null;
				salesAgentDBObject = new BasicDBObject();

				 /* ==============================
				 * BOOKING TOPPERS BY PARTNER
				 * ==============================*/
				topperGroupByOrderBy = " GROUP BY tbm ORDER BY booking DESC LIMIT 0, 10";
				queryFieldBookingTopper = "partner_name, SUM(booking_net) as booking";
				setQueryCriteria2("nothing", "", "nothing", "", basedOn, objectKey);
				topDataPartners = getTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
				
				for (TopperData data : topDataPartners) {
					Set<String> allPartners = data.getTopperData().keySet();
					String partner = allPartners.iterator().next();
						queryFieldBookingTopper = "customer_name, SUM(booking_net) as booking";
						topperGroupByOrderBy = " GROUP BY customer_name ORDER BY booking DESC LIMIT 0, 10";
						setQueryCriteria2("partner_name", partner, "", "", basedOn, objectKey);
						topDataCustomers = getTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
						customerDBObject.put(ProcessHelper.Key.BOOKING.asKey(), data.getTopperData().get(partner));
						for (TopperData data2 : topDataCustomers) {
							Set<String> allCustomers = data2.getTopperData().keySet();
							String customer = allCustomers.iterator().next();
							customerDBObject.put(customer, new BasicDBObject(ProcessHelper.Key.BOOKING.asKey(), data2.getTopperData().get(customer)));
						}	
						partnerDBObject.put(partner, customerDBObject);
						customerDBObject = null;
						customerDBObject = new BasicDBObject();
				}
				bookingTopperDetail.put(ProcessHelper.Key.PARTNERS.asKey(), partnerDBObject);
				partnerDBObject = null;
				partnerDBObject = new BasicDBObject();
				

				 /* ==============================
				 * BOOKING TOPPERS BY CUSTOMER
				 * ==============================*/
				topperGroupByOrderBy = " GROUP BY tbm ORDER BY booking DESC LIMIT 0, 10";
				queryFieldBookingTopper = "customer_name, SUM(booking_net) as booking";
				setQueryCriteria2("nothing", "", "nothing", "", basedOn, objectKey);
				topDataCustomers = getTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
				for (TopperData data : topDataCustomers) {
					Set<String> allCustomers = data.getTopperData().keySet();
					String customer = allCustomers.iterator().next();
					customerDBObject.put(customer, new BasicDBObject(ProcessHelper.Key.BOOKING.asKey(), data.getTopperData().get(customer)));
				}	
				bookingTopperDetail.put(ProcessHelper.Key.CUSTOMERS.asKey(), customerDBObject);
				customerDBObject = null;
				customerDBObject = new BasicDBObject();
				
				 /* ==============================
				 * BOOKING TOPPERS BY TECHNOLOGIES
				 * ==============================*/
				for (String tech : techsNoCondition) {
					queryFieldBookingTopper = "customer_name, SUM(booking_net) as booking";
					topperGroupByOrderBy = " GROUP BY customer_name ORDER BY booking DESC LIMIT 0, 10";
					setQueryCriteria2("tech_name", tech, "", "", basedOn, objectKey);
					topDataCustomers = getTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
					for (TopperData data : topDataCustomers) {
						Set<String> allCustomers = data.getTopperData().keySet();
						String customer = allCustomers.iterator().next();
						customerDBObject.put(customer, new BasicDBObject(ProcessHelper.Key.BOOKING.asKey(), data.getTopperData().get(customer)));
					}	
					techDBObject.put(tech, customerDBObject);
					customerDBObject = null;
					customerDBObject = new BasicDBObject();
				}
				bookingTopperDetail.put(ProcessHelper.Key.TECHNOLOGY_TOPPERS.asKey(), techDBObject);
				partnerDBObject = null;
				partnerDBObject = new BasicDBObject();


				 /* ==============================
				 * DISCOUNT TOPPERS BY SALES AGENT
				 * ==============================*/
				topperGroupByOrderBy = " GROUP BY tbm HAVING ((1 - SUM(booking_net) / SUM(Base_List)) IS NOT NULL) AND ((1 - SUM(booking_net) / SUM(Base_List)) > 0) ORDER BY discount LIMIT 0 , 10;";
				queryFieldBookingTopper = "tbm, (1 - SUM(booking_net) / SUM(Base_List)) AS discount";
				setQueryCriteria2("nothing", "", "nothing", "", basedOn, objectKey);
				
				reverseTopDataSalesAgents = getReverseTopperMetric(condi1, condi2, condi3, queryFieldBookingTopper, topperGroupByOrderBy, keyBefore); removeParamMapKeys(keyBefore);
				
				
				for (ReverseTopperData data : reverseTopDataSalesAgents) {
					Set<String> allSalesAgents = data.getTopperData().keySet();
					String salesAgent = allSalesAgents.iterator().next();
					salesAgentDBObject.put(salesAgent, new BasicDBObject(ProcessHelper.Key.DISCOUNT.asKey(), data.getTopperData().get(salesAgent)));
				}	
			
				discountTopperDetail.put(ProcessHelper.Key.SALES_AGENTS.asKey(), salesAgentDBObject);
				salesAgentDBObject = null;
				salesAgentDBObject = new BasicDBObject();
			}
			
			
			objectKey = ProcessHelper.escDot(objectKey);
			metricDetail.put(ProcessHelper.Key.BOOKING.asKey(), bookingDetail);
			metricDetail.put(ProcessHelper.Key.BASE_LIST.asKey(), baseListDetail);
			 /* ==============================
			 * OTHER METRICS
			 * ==============================*/
			setQueryCriteria("nothing", "", basedOn, objectKey);
			Double dummyDouble = getMetric(condi1, condi2, "COUNT(DISTINCT customer_name) AS customers", "", keyBefore); removeParamMapKeys(keyBefore);
			metricDetail.put(ProcessHelper.Key.CUSTOMERS.asKey(), dummyDouble.intValue());
			setQueryCriteria("nothing", "", basedOn, objectKey);
			dummyDouble = getMetric(condi1, condi2, "COUNT(DISTINCT partner_name) AS partners", "", keyBefore); removeParamMapKeys(keyBefore);
			metricDetail.put(ProcessHelper.Key.PARTNERS.asKey(), dummyDouble.intValue());
			setQueryCriteria("nothing", "", basedOn, objectKey);
			Integer dummyInteger = getMetricAsRowCount(condi1, condi2, "COUNT(DISTINCT customer_name) AS techs", 
					" GROUP BY customer_name, tech_name", keyBefore); removeParamMapKeys(keyBefore);
			metricDetail.put(ProcessHelper.Key.TECHNOLOGIES.asKey(), dummyInteger);
			if (isToppers) {
				metricDetail.put(ProcessHelper.Key.BOOKING_TOPPERS.asKey(), bookingTopperDetail);
				metricDetail.put(ProcessHelper.Key.DISCOUNT_TOPPERS.asKey(), discountTopperDetail);
			}
			metrics.put(objectKey, metricDetail);
			
			/*
			 * VARIABLE DESTRUCTION
			 */
			metricDetail = null; bookingDetail = null; baseListDetail = null; bookingTopperDetail = null;
			discountTopperDetail = null; salesAgentDBObject = null; partnerDBObject = null; customerDBObject = null;
			techDBObject = null; reverseTopDataSalesAgents = null; topDataSalesAgents = null; topDataPartners = null;
			topDataCustomers = null; dataFieldMap = null; queryFieldBooking = null; queryFieldBookingSpecial = null;
			queryFieldBookingSpecial2 = null; queryFieldList = null; queryFieldListSpecial = null; queryFieldListSpecial2 = null;
			queryFieldBookingTopper = null; topperGroupByOrderBy = null;
//			System.out.printf("%s|%d/%d (%6.2f\\%)|%s - Metric Detail finished!\n", this.period, counter, keys.size(), procPercentage, objectKey);
			System.out.printf("%s|%d/%d|%s - Metric Detail finished!\n", this.period, ++counter, keys.size(), nodeName);
		}
		return metrics;
	}
	
	private BasicDBObject getSubMetricsObject (String[] noConditionNodes, 
			String field1, String field2, String param2, Integer keyBefore, MetricParamShare paramShare) {

		String fieldType = paramShare.getFieldType();
		BasicDBObject subDetailObject = new BasicDBObject();
		Double dummyDouble = 0D;
			if (fieldType.equals("SPECIAL")) {
				Set<String> dataFieldKey = paramShare.getDataFieldMap().keySet();
				for (String key : dataFieldKey) {
					dummyDouble = 0D;
					setQueryCriteria("nothing", "", field2, param2);
					dummyDouble = getMetric(condi1, condi2, paramShare.getDataFieldMap().get(key), "", keyBefore); removeParamMapKeys(keyBefore);
					subDetailObject.put(key, dummyDouble);
				}
			} else {
					for (String param1 : noConditionNodes) {
						setQueryCriteria(field1, param1, field2, param2);
						dummyDouble = getMetric(condi1, condi2, paramShare.getDataFieldMap().get("query_field"), "", keyBefore); removeParamMapKeys(keyBefore);
						if (param1 == null || param1.equals("")) {
							subDetailObject.put("no_"+field1, dummyDouble);
						} else {
							subDetailObject.put(JSONObject.escape(param1), dummyDouble);
						}
					}
			}
		
		return subDetailObject;
	}


	public List<TopperData> getTopperMetric(Map<String, String> condi1, 
			Map<String, String> condi2, Map<String, String> condi3, 
			String field, String groupBy, Integer key) {
		List<TopperData> returnList = null;
		this.field = field;
		this.groupByClause = groupBy;
		String condiCriterion1 = null, condiCriterion2 = null, condiCriterion3 = null;
		String condiField1 = null, condiField2 = null, condiField3 = null;
		Set<String> condiSetField1 = condi1.keySet();
		Set<String> condiSetField2 = condi2.keySet();
		Set<String> condiSetField3 = condi3.keySet();
		for (String fieldElement : condiSetField1) {
			condiField1 = fieldElement; 
			condiCriterion1 = condi1.get(fieldElement);
		}
		for (String fieldElement : condiSetField2) {
			condiField2 = fieldElement; 
			condiCriterion2 = condi2.get(fieldElement);
		}
		for (String fieldElement : condiSetField3) {
			condiField3 = fieldElement; 
			condiCriterion3 = condi3.get(fieldElement);
		}
		
	
		if (!condiCriterion1.equals("") && !condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ? AND " + condiField2 + " = ? AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion2);
			sqlParams.put(++key, this.data2);
			this.data3 = null;
			this.data3 = new SQLParamString();
			this.data3.setData(condiCriterion3);
			sqlParams.put(++key, this.data3);
		} else if (!condiCriterion1.equals("") && !condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ? AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion2);
			sqlParams.put(++key, this.data2);
		} else if (!condiCriterion1.equals("") && condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ? AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion3);
			sqlParams.put(++key, this.data2);
		} else if (!condiCriterion1.equals("") && condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
		} else if (condiCriterion1.equals("") && !condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField2 + " = ? AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion2);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion3);
			sqlParams.put(++key, this.data2);
		} else if (condiCriterion1.equals("") && !condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion2);
			sqlParams.put(key, this.data);
		} else if (condiCriterion1.equals("") && condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion3);
			sqlParams.put(key, this.data);
		} else if (condiCriterion1.equals("") && condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			this.whereClause = this.stateWhereClause + whereClause; 
		}
		this.addParametersMap();
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnList = queryEngine.fetchToppersList(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}


	public List<ReverseTopperData> getReverseTopperMetric(Map<String, String> condi1, 
			Map<String, String> condi2, Map<String, String> condi3, 
			String field, String groupBy, Integer key) {
		List<ReverseTopperData> returnList = null;
		this.field = field;
		this.groupByClause = groupBy;
		String condiCriterion1 = null, condiCriterion2 = null, condiCriterion3 = null;
		String condiField1 = null, condiField2 = null, condiField3 = null;
		Set<String> condiSetField1 = condi1.keySet();
		Set<String> condiSetField2 = condi2.keySet();
		Set<String> condiSetField3 = condi3.keySet();
		for (String fieldElement : condiSetField1) {
			condiField1 = fieldElement; 
			condiCriterion1 = condi1.get(fieldElement);
		}
		for (String fieldElement : condiSetField2) {
			condiField2 = fieldElement; 
			condiCriterion2 = condi2.get(fieldElement);
		}
		for (String fieldElement : condiSetField3) {
			condiField3 = fieldElement; 
			condiCriterion3 = condi3.get(fieldElement);
		}
		
	
		if (!condiCriterion1.equals("") && !condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ? AND " + condiField2 + " = ? AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion2);
			sqlParams.put(++key, this.data2);
			this.data3 = null;
			this.data3 = new SQLParamString();
			this.data3.setData(condiCriterion3);
			sqlParams.put(++key, this.data3);
		} else if (!condiCriterion1.equals("") && !condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ? AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion2);
			sqlParams.put(++key, this.data2);
		} else if (!condiCriterion1.equals("") && condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ? AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion3);
			sqlParams.put(++key, this.data2);
		} else if (!condiCriterion1.equals("") && condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
		} else if (condiCriterion1.equals("") && !condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField2 + " = ? AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion2);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion3);
			sqlParams.put(++key, this.data2);
		} else if (condiCriterion1.equals("") && !condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion2);
			sqlParams.put(key, this.data);
		} else if (condiCriterion1.equals("") && condiCriterion2.equals("") && !condiCriterion3.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField3 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion3);
			sqlParams.put(key, this.data);
		} else if (condiCriterion1.equals("") && condiCriterion2.equals("") && condiCriterion3.equals("")) {
			String whereClause = "";
			this.whereClause = this.stateWhereClause + whereClause; 
		}
		this.addParametersMap();
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnList = queryEngine.fetchReverseToppersList(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}

	
	public Double getMetric(Map<String, String> condi1, Map<String, String> condi2, String field, String groupBy, Integer key) {
		Double returnDouble = null;
		this.field = field;
		this.groupByClause = groupBy;
		String condiCriterion1 = null, condiCriterion2 = null;
		String condiField1 = null, condiField2 = null;
		Set<String> condiSetField1 = condi1.keySet();
		Set<String> condiSetField2 = condi2.keySet();
		for (String fieldElement : condiSetField1) {
			condiField1 = fieldElement; 
			condiCriterion1 = condi1.get(fieldElement);
		}
		for (String fieldElement : condiSetField2) {
			condiField2 = fieldElement; 
			condiCriterion2 = condi2.get(fieldElement);
		}	
		
		if (condiCriterion1.equals("") && !condiCriterion2.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion2);
			sqlParams.put(key, this.data);
		} else if (!condiCriterion1.equals("") && condiCriterion2.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
		} else if (!condiCriterion1.equals("") && !condiCriterion2.equals("")) {
			whereClause = " AND " + condiField1 + " = ? AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion2);
			sqlParams.put(++key, this.data2);
		} else {
			String whereClause = "";
			this.whereClause = this.stateWhereClause + whereClause; 
		}
		this.addParametersMap();
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnDouble = queryEngine.fetchDoubleValue(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnDouble;
	}
	

	public Integer getMetricAsRowCount(Map<String, String> condi1, Map<String, String> condi2, String field, String groupBy, Integer key) {
		Integer returnInteger = null;
		this.field = field;
		this.groupByClause = groupBy;
		String condiCriterion1 = null, condiCriterion2 = null;
		String condiField1 = null, condiField2 = null;
		Set<String> condiSetField1 = condi1.keySet();
		Set<String> condiSetField2 = condi2.keySet();
		for (String fieldElement : condiSetField1) {
			condiField1 = fieldElement; 
			condiCriterion1 = condi1.get(fieldElement);
		}
		for (String fieldElement : condiSetField2) {
			condiField2 = fieldElement; 
			condiCriterion2 = condi2.get(fieldElement);
		}
		
		if (condiCriterion1.equals("") && !condiCriterion2.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion2);
			sqlParams.put(key, this.data);
		} else if (!condiCriterion1.equals("") && condiCriterion2.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
		} else if (!condiCriterion1.equals("") && !condiCriterion2.equals("")) {
			whereClause = " AND " + condiField1 + " = ? AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion2);
			sqlParams.put(++key, this.data2);
		} else {
			String whereClause = "";
			this.whereClause = this.stateWhereClause + whereClause; 
		}
		this.addParametersMap();
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnInteger = queryEngine.fetchRowCount(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnInteger;
	}
	
	
	public String[] getNodesStringArray(Map<String, String> condi1, Map<String, String> condi2, String field, Integer key) {
		String[] returnArray = null;
		this.field = field;
		this.groupByClause = "";
		String condiCriterion1 = null, condiCriterion2 = null;
		String condiField1 = null, condiField2 = null;
		Set<String> condiSetField1 = condi1.keySet();
		Set<String> condiSetField2 = condi2.keySet();
		for (String fieldElement : condiSetField1) {
			condiField1 = fieldElement; 
			condiCriterion1 = condi1.get(fieldElement);
		}
		for (String fieldElement : condiSetField2) {
			condiField2 = fieldElement; 
			condiCriterion2 = condi2.get(fieldElement);
		}
		
		if (condiCriterion1.equals("") && !condiCriterion2.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion2);
			sqlParams.put(key, this.data);
		} else if (!condiCriterion1.equals("") && condiCriterion2.equals("")) {
			String whereClause = "";
			whereClause = " AND " + condiField1 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
		} else if (!condiCriterion1.equals("") && !condiCriterion2.equals("")) {
			whereClause = " AND " + condiField1 + " = ? AND " + condiField2 + " = ?";
			this.whereClause = this.stateWhereClause + whereClause; 
			this.data = null;
			this.data = new SQLParamString();
			this.data.setData(condiCriterion1);
			sqlParams.put(key, this.data);
			this.data2 = null;
			this.data2 = new SQLParamString();
			this.data2.setData(condiCriterion2);
			sqlParams.put(++key, this.data2);
		} else {
			String whereClause = "";
			this.whereClause = this.stateWhereClause + whereClause; 
		}
		this.addParametersMap();
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnArray = queryEngine.fetchStringArray(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnArray;
	}
	
	private void addParametersMap() {
		this.setField(this.field);
		this.setWhereClause(this.whereClause);
		this.setGroupByClause(this.groupByClause);
	}
	
	/*
	 * FOLLOWING ARE THE PRIVATE METHODS
	 * */
	private void setField(String field) {
		this.map.put("field", field);
	}
	
	private void setWhereClause(String where_clause) {
		this.map.put("where_clause", where_clause);
	}
	
	private void setGroupByClause(String group_by_clause) {
		this.map.put("group_by_clause", group_by_clause);
	}

	private Integer getParamMapKeySize() {
		Integer key = 1;
		try {
			Set<Integer> keys = this.sqlParams.keySet();
			TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
			key = sortedKeys.size() + 1;
		} catch (NullPointerException e) {
			key = 1;
		}
		return key;
	}

	private void removeParamMapKeys(Integer keyBefore) {
		Integer keyAfter = getParamMapKeySize();
		if (keyAfter > keyBefore) {
			Integer keyInit = ++keyBefore;
			for (Integer key = keyInit; key < keyAfter; key++) {
				this.sqlParams.remove(key);
			}
		}
	}

	private <T> void setQueryCriteria (String condiKey1, String condiValue1, 
			String condiKey2, String condiValue2) {
		this.condi1.clear(); condi1.put(condiKey1, condiValue1);
		this.condi2.clear(); condi2.put(condiKey2, condiValue2);
	}

	private <T> void setQueryCriteria2 (String condiKey1, String condiValue1, 
			String condiKey2, String condiValue2, 
			String condiKey3, String condiValue3) {
		this.condi1.clear(); condi1.put(condiKey1, condiValue1);
		this.condi2.clear(); condi2.put(condiKey2, condiValue2);
		this.condi3.clear(); condi3.put(condiKey3, condiValue3);
	}
	
	
	@SuppressWarnings("unused")
	private void printObjects() {
		BufferedWriter myBW = getBufferedWriter() ;
		if (!(myBW == null)) {
			ProcessHelper.printMappedArray(allSubSCMSs, myBW);
			ProcessHelper.printMappedArray(allGTMUs, myBW);
			ProcessHelper.printMappedArray(allRegions, myBW);
			ProcessHelper.printMappedArray(regionByGtmu, myBW);
			ProcessHelper.printMappedArray(allSL6s, myBW);
			ProcessHelper.printMappedArray(sl6sBySubSCMS, myBW);
			ProcessHelper.printMappedArray(sl6sByGtmu, myBW);
			ProcessHelper.printMappedArray(sl6sByRegion, myBW);
			ProcessHelper.printMappedArray(allSalesAgents, myBW);
			ProcessHelper.printMappedArray(salesAgentsBySubSCMS, myBW);
			ProcessHelper.printMappedArray(salesAgentsByGtmu, myBW);
			ProcessHelper.printMappedArray(salesAgentsByRegion, myBW);
			ProcessHelper.printMappedArray(salesAgentsBySL6, myBW);
			ProcessHelper.printMappedArray(allPartners, myBW);
			ProcessHelper.printMappedArray(partnersBySubSCMS, myBW);
			ProcessHelper.printMappedArray(partnersByGtmu, myBW);
			ProcessHelper.printMappedArray(partnersByRegion, myBW);
			ProcessHelper.printMappedArray(partnersBySL6, myBW);
			ProcessHelper.printMappedArray(partnersBySalesAgent, myBW);
			ProcessHelper.printMappedArray(allPartners, myBW);
			ProcessHelper.printMappedArray(partnersBySubSCMS, myBW);
			ProcessHelper.printMappedArray(partnersByGtmu, myBW);
			ProcessHelper.printMappedArray(partnersByRegion, myBW);
			ProcessHelper.printMappedArray(partnersBySL6, myBW);
			ProcessHelper.printMappedArray(partnersBySalesAgent, myBW);
			ProcessHelper.printMappedArray(allCustomers, myBW);
			ProcessHelper.printMappedArray(customersBySubSCMS, myBW);
			ProcessHelper.printMappedArray(customersByGtmu, myBW);
			ProcessHelper.printMappedArray(customersByRegion, myBW);
			ProcessHelper.printMappedArray(customersBySL6, myBW);
			ProcessHelper.printMappedArray(customersBySalesAgent, myBW);
			ProcessHelper.printMappedArray(customersByPartner, myBW);
			try {
				myBW.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Null BufferedWriter");
		}
	}
	
	private BufferedWriter getBufferedWriter () {
		BufferedWriter bw = null;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bw;
	}

	
}
