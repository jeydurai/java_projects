package main;

import helper.ProcessHelper;
import helper.ProcessTime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import main.Periodical;
import sql.SQLConnection;
import sql.SQLParamInteger;
import sql.SQLParamString;
import sql.SQLQueryEngine;
import sql.TypeCustomizable;

public class Master implements Periodical {

	private Integer[] fp_year, fp_month;
	private String[] fp_quarter, fp_week;
	private String field, where_clause, group_by_clause;
	private Map<String, String> map = new HashMap<String, String>();
	private TypeCustomizable data, data2, data3, data4;
	private Map<Integer, TypeCustomizable> sqlParams;
	private SQLQueryEngine queryEngine;
	private SQLConnection connObject;
	private ProcessTime elapsedTime;
	
	public Master () {
		System.out.println("Master Initiated!");
		this.elapsedTime = new ProcessTime("Conversion");
		this.field = "fp_year";
		this.where_clause = "";
		this.group_by_clause = "";
		this.map.put("table_name", ProcessHelper.BASE_TABLE_NAME);
		this.connObject = new SQLConnection();
	}
	
	public void masterThread () {
		this.fp_year = this.getYear();
		List<Worker> arrayThread = new ArrayList<Worker>();
		String period = "";
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		for (Integer year : this.fp_year) {
			//System.out.print(year);
			this.fp_quarter = this.getQuarter(year);
			period = Integer.toString(year);
			arrayThread.add(new Worker("by Year", period, where_clause, group_by_clause, sqlParams, new SQLConnection())); 
			for (String quarter : this.fp_quarter) {
				//System.out.print("|" + quarter);
				this.fp_month = this.getMonth(quarter);
				period = Integer.toString(year) + quarter;
				arrayThread.add(new Worker("by Quarter", period, where_clause, group_by_clause, sqlParams, new SQLConnection())); 				
				for (Integer month : this.fp_month) {
					//System.out.print("|" + month);
					this.fp_week = this.getWeek(month);
					period = Integer.toString(year) + quarter + Integer.toString(month);
					arrayThread.add(new Worker("by Month", period, where_clause, group_by_clause, sqlParams, new SQLConnection())); 				
					for (String week : this.fp_week) {
						//System.out.println("|" + week);
						setParameterMapAfterWeek(week);
						period = Integer.toString(year) + quarter + Integer.toString(month) + week;
						arrayThread.add(new Worker("by Week", period, where_clause, group_by_clause, sqlParams, new SQLConnection())); 				
					}	
				}	
			}	
		}
		for (Worker thread : arrayThread) {
			threadExecutor.execute(thread);
		}
		//threadExecutor.execute(poolPrinter);
		threadExecutor.shutdown();
		try {
			boolean taskEnded = threadExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
			if (taskEnded) {
				System.out.println("Thread Ended!");
				this.elapsedTime.elapsedTime();
			} else {
				System.out.println("Thread timed out!");
				this.elapsedTime.elapsedTime();
			}
		} catch (InterruptedException e) {
			System.out.println("Multi Threading got Interrupted/Completed!");
			this.elapsedTime.elapsedTime();
		}
		threadExecutor = null;
	}
	
	@Override
	public Integer[] getYear() {
		Integer[] returnInteger = null;
		this.addParametersMap();
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnInteger = queryEngine.fetchIntegerList(null);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnInteger;
	}

	@Override
	public String[] getQuarter(Integer year) {
		String[] returnArray = null;
		sqlParams = null;
		sqlParams = new HashMap<Integer, TypeCustomizable>();
		this.field = "fp_quarter";
		this.where_clause = " WHERE fp_year = ?";
		this.group_by_clause = "";
		this.addParametersMap();
		data = null;
		data = new SQLParamInteger();
		data.setData(year);
		sqlParams.put(1, data);
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnArray = queryEngine.fetchStringArray(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnArray;
	}

	@Override
	public Integer[] getMonth(String quarter) {
		Integer[] returnInteger = null;
		sqlParams = null;
		sqlParams = new HashMap<Integer, TypeCustomizable>();
		this.field = "fp_month";
		this.where_clause = " WHERE fp_year = ? AND fp_quarter = ?";
		this.group_by_clause = "";
		this.addParametersMap();
		data2 = null;
		data2 = new SQLParamString();
		data2.setData(quarter);
		sqlParams.put(1, data);
		sqlParams.put(2, data2);
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnInteger = queryEngine.fetchIntegerList(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnInteger;
	}

	@Override
	public String[] getWeek(Integer month) {
		String[] returnArray = null;
		sqlParams = null;
		sqlParams = new HashMap<Integer, TypeCustomizable>();
		this.field = "fp_week";
		this.where_clause = " WHERE fp_year = ? AND fp_quarter = ? AND fp_month = ?";
		this.group_by_clause = "";
		this.addParametersMap();
		data3 = null;
		data3 = new SQLParamInteger();
		data3.setData(month);
		sqlParams.put(1, data);
		sqlParams.put(2, data2);
		sqlParams.put(3, data3);
		try {
			queryEngine = new SQLQueryEngine(this.map, this.connObject.getSQLConnection());
			returnArray = queryEngine.fetchStringArray(this.sqlParams);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnArray;
	}

	private void setParameterMapAfterWeek(String week) {
		sqlParams = null;
		sqlParams = new HashMap<Integer, TypeCustomizable>();
		this.where_clause = " WHERE fp_year = ? AND fp_quarter = ? AND fp_month = ? AND fp_week = ?";
		this.group_by_clause = "";
		this.addParametersMap();
		data4 = null;
		data4 = new SQLParamString();
		data4.setData(week);
		sqlParams.put(1, data);
		sqlParams.put(2, data2);
		sqlParams.put(3, data3);
		sqlParams.put(4, data4);
	}

	
	public void addParametersMap() {
		this.setField(this.field);
		this.setWhereClause(this.where_clause);
		this.setGroupByClause(this.group_by_clause);
	}


	/*
	 * FOLLOWING ARE THE PRIVATE METHODS
	 * */
	
	public void setField(String field) {
		this.map.put("field", field);
	}
	
	public void setWhereClause(String where_clause) {
		this.map.put("where_clause", where_clause);
	}
	
	public void setGroupByClause(String group_by_clause) {
		this.map.put("group_by_clause", group_by_clause);
	}

}
