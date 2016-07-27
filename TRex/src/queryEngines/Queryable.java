package queryEngines;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import dataStructures.DSCustomizable;
import dataStructures.DSSQLStatus;
import dataStructures.DSToppers;

public interface Queryable {

	public String createPreparedStatement();
	public String createExtendedPreparedStatement();
	public String createCreateLikePreparedStatement(String likeTableName);
	public PreparedStatement getPreparedStatementDistinct();
	public PreparedStatement getPreparedStatementAggregate();
	public PreparedStatement getPreparedStatementExtended();
	public <T> T getValue(Map<Integer, DSCustomizable> params);
	public <T> T[] getValues(Map<Integer, DSCustomizable> params);
	public <T> List<T> fetchToppersList (Map<Integer, DSCustomizable> params);
	public <T> List<T> fetchAllData (Map<Integer, DSCustomizable> params);
	public DSSQLStatus deleteTableRecords();
	public String dropTable();
	public String createTable();
	public DSSQLStatus insertTableRecords(Map<Integer, DSCustomizable> params);
	public DSSQLStatus extendedInsertTableRecords(Object errDisplayObj);
}
