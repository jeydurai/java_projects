package modelsSQL;

import helperUtilities.Comforter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTextArea;

import configurations.SQLQueryConfig;
import configurations.SQLQueryExtender;
import dataStructures.DSCustomizable;
import dataStructures.DSSQLStatus;
import dataStructures.DSToppers;
import queryEngines.SQLQueryAdapter;

public class SQLQueryEngine extends SQLQueryAdapter {

	public SQLQueryEngine(Connection conn, SQLQueryConfig queryConfig) {
		super(conn, queryConfig);
	}

	public SQLQueryEngine(Connection conn, SQLQueryExtender queryExtender) {
		super(conn, queryExtender);
	}
	
	public String createPreparedStatement() {
		String errorString = "";
		try {
			this.prepStatementDistinct = this.conn.prepareStatement(this.queryConfig.generateDistinctQuery());
			this.prepStatementAggregate = this.conn.prepareStatement(this.queryConfig.generateAggregateQuery());
			this.prepStatementDelete = this.conn.prepareStatement(this.queryConfig.generatePlainDeleteQuery());
			this.prepStatementInsert = this.conn.prepareStatement(this.queryConfig.generateWriteQuery());
			this.prepStatementDrop = this.conn.prepareStatement(this.queryConfig.generateDropTableQuery());
		} catch (SQLException e) {
			errorString = Comforter.StackTraceToString(e);
		}
		return errorString;
	}

	public String createExtendedPreparedStatement() {
		String errorString = "";
		try {
			this.prepStatementExtended = this.conn.prepareStatement(this.queryExtender.getExtendedQueryString());
		} catch (SQLException e) {
			errorString = Comforter.StackTraceToString(e);
		}
		return errorString;
	}

	public String createCreateLikePreparedStatement(String likeTableName) {
		String errorString = "";
		try {
			this.prepStatementCreateLike = this.conn.prepareStatement(this.queryConfig.generateLikeCreateTableQuery(likeTableName));
		} catch (SQLException e) {
		}
		return errorString;
	}

	public DSSQLStatus deleteTableRecords(Object errDisplayObj) {
		int result = 0;
		String errorString = "";
		try {
			result = this.prepStatementDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (this.prepStatementDelete != null)
				try {
					this.prepStatementDelete.close();
				} catch (SQLException e) {
					errorString = Comforter.StackTraceToString(e);
				}
		}
		DSSQLStatus sqlStatus = new DSSQLStatus(result, errorString);
		return sqlStatus;
	}

	public String createTable() {
		String errorString = "";
		try {
			this.prepStatementCreateLike.execute();
		} catch (SQLException e) {
			errorString = Comforter.StackTraceToString(e);
		} finally {
			if (this.prepStatementCreateLike != null)
				try {
					this.prepStatementCreateLike.close();
				} catch (SQLException e) {
					errorString = Comforter.StackTraceToString(e);
				}
		}
		return errorString;
	}

	
	public String dropTable() {
		String errorString = "";
		try {
			this.prepStatementDrop.execute();
		} catch (SQLException e) {
			errorString = Comforter.StackTraceToString(e);
		} finally {
			if (this.prepStatementDrop != null)
				try {
					this.prepStatementDrop.close();
				} catch (SQLException e) {
					errorString = Comforter.StackTraceToString(e);
				}
		}
		return errorString;
	}

	
	public DSSQLStatus extendedInsertTableRecords(Object errDisplayObj) {
		int result = 0;
		String errorString = "";
		try {
			result = this.prepStatementExtended.executeUpdate();
		} catch (SQLException e) {
			errorString = Comforter.StackTraceToString(e);
		} finally {
			if (this.prepStatementExtended != null)
				try {
					this.prepStatementExtended.close();
				} catch (SQLException e) {
					errorString = Comforter.StackTraceToString(e);
				}
		}
		DSSQLStatus sqlStatus = new DSSQLStatus(result, errorString);
		return sqlStatus;
	}

	public DSSQLStatus insertTableRecords(Map<Integer, DSCustomizable> params) {
		int result = 0;
		String errorString = "";
		try {
			if (!params.isEmpty() && params != null ) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer eachKey : sortedKeys) {
						if (params.get(eachKey).getParameter() instanceof String) {
							try {
								this.prepStatementInsert.setString(eachKey, (String)params.get(eachKey).getParameter());
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						} else if (params.get(eachKey).getParameter() instanceof Integer) {
							try {
								this.prepStatementInsert.setInt(eachKey, (Integer)params.get(eachKey).getParameter());
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						} else if (params.get(eachKey).getParameter() instanceof Double) {
							try {
								this.prepStatementInsert.setDouble(eachKey, (Double)params.get(eachKey).getParameter());
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						} else if (params.get(eachKey).getParameter() instanceof Float) {
							try {
								this.prepStatementInsert.setFloat(eachKey, (Float)params.get(eachKey).getParameter());
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						} else {
							try {
								this.prepStatementInsert.setNull(eachKey, 0);
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {
			// Purposefully left this space uncoded
		} finally {
			try {
				result = this.prepStatementInsert.executeUpdate();
			} catch (SQLException e) {
				errorString = Comforter.StackTraceToString(e);
			} finally {
				if (this.prepStatementInsert != null)
					try {
						this.prepStatementInsert.close();
					} catch (SQLException e) {
						errorString = Comforter.StackTraceToString(e);
					}
			}
		}
		DSSQLStatus sqlStatus = new DSSQLStatus(result, errorString);
		return sqlStatus;
	}

	
	public <T> T[] getValues(Map<Integer, DSCustomizable> params) {
		T[] returnArray = null;
		List<String> values = null;
		ResultSet resultSet = null;
		String errorString = "";
		try {
			if (!params.isEmpty() && params != null ) {
				Set<Integer> keys = params.keySet();
				TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
				if (sortedKeys.size() > 0) {
					for (Integer eachKey : sortedKeys) {
						if (params.get(eachKey).getParameter() instanceof String) {
							try {
								this.prepStatementDistinct.setString(eachKey, (String)params.get(eachKey).getParameter());
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						} else {
							try {
								this.prepStatementDistinct.setInt(eachKey, (Integer)params.get(eachKey).getParameter());
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {
			// Purposefully left this space uncoded
		} finally {
			try {
				resultSet = this.prepStatementDistinct.executeQuery();
				values = new ArrayList<String>();
				while (resultSet.next()) values.add(resultSet.getString(this.fetchField));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (resultSet != null)
					try {
						resultSet.close();
					} catch (SQLException e) {
						errorString = Comforter.StackTraceToString(e);
					}
				if (this.prepStatementDistinct != null)
					try {
						this.prepStatementDistinct.close();
					} catch (SQLException e) {
						errorString = Comforter.StackTraceToString(e);
					}
			}
		}
		if (!values.isEmpty()) {
			values = companion.Comrade.getDeDupList(values);
			returnArray = (T[]) values.toArray(new String[values.size()]);
		}
		System.out.println(errorString);
		return returnArray;
	}

	
	public <T> List<T> fetchToppersList (Map<Integer, DSCustomizable> params) {
		List<DSToppers> results = null;
		ResultSet resultSet = null;
		String errorString = "";
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
								errorString = Comforter.StackTraceToString(e);
							}
						} else if (params.get(key).getParameter() instanceof Integer) {
							try {
								this.prepStatementDistinct.setInt(key, (Integer)params.get(key).getParameter());
							} catch (SQLException e) {
								errorString = Comforter.StackTraceToString(e);
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {
			
		} finally {
			try {
				resultSet = this.prepStatementDistinct.executeQuery();
				results = new ArrayList<DSToppers>();
				while (resultSet.next()) results.add(new DSToppers(resultSet.getString(1), resultSet.getDouble(2)));
			} catch (SQLException sqlException) {
				errorString = Comforter.StackTraceToString(sqlException);
			} finally {
				try {
					if (resultSet != null) resultSet.close();
					if (this.prepStatementDistinct != null) this.prepStatementDistinct.close();
				} catch (SQLException sqlException) {
					errorString = Comforter.StackTraceToString(sqlException);
				}
			}
		}
		
		System.out.println(errorString);
		return (List<T>) results;

	}

}
