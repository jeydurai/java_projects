package main;

import helper.ProcessHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sql.QueryEngine;
import sql.Queryable;
import sql.SQLParamInteger;
import sql.TypeCustomizable;
import helper.ProcessHelper;

public class MasterConverter {

	private Integer[] fp_year, fp_month;
	private String[] fp_quarter, fp_week;
	private String field, where_clause, group_by_clause;
	private Master master;
	
	public MasterConverter () {
		System.out.println("MasterConverter Initiated!");
		this.field = "fp_year";
		this.where_clause = "";
		this.group_by_clause = "";
		master = new Master();
	}
	
	public void masterThread () {
		System.out.println("Inside Master Thread!");
		this.setProcessHelperParameters();
		this.fp_year = master.getYear();
		for (Integer year : this.fp_year) {
			System.out.println(year);
			Map<Integer, TypeCustomizable> sqlParams = new HashMap<Integer, TypeCustomizable>();
			this.field = "fp_quarter";
			this.where_clause = " WHERE fp_year = ?";
			this.group_by_clause = "";
			this.setProcessHelperParameters();
			
			TypeCustomizable data = new SQLParamInteger();
			data.setData(year);
			sqlParams.put(1, data);
			this.fp_quarter = master.getQuarter(year);
			for (String quarter : this.fp_quarter) {
				System.out.println(quarter);
			}
			
			
		}
	}
	
	private void setProcessHelperParameters() {
		master.setField(this.field);
		master.setWhereClause(this.where_clause);
		master.setGroupByClause(this.group_by_clause);
	}
}
