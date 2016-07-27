package modelsDataHandlers;

import GeneralConstants.Generics;
import helperUtilities.DefaultSQLQueries;
import helperUtilities.GeneralConstants;
import modelsSQL.SQLQueryEngine;
import queryEngines.Queryable;
import sql.MySQLConnection;
import configurations.SQLQueryAssembly;

public class CleanBookingDataAndWriteSQL {

	public void executeProcess() {
		MySQLConnection sqlConnection = new MySQLConnection(Generics.LOW_MAX_ACTIVE_POOLING);
		double startTime = 0.0;
		// ===================================================================
		// Preliminary clean up
		// ===================================================================
		
		//Deleting tech_master Table for clean up
		startTime = System.currentTimeMillis();
		System.out.println("\nDeleting 'tech_master' Table for clean up...\n");
		SQLQueryAssembly queryAssembly0 = DefaultSQLQueries.deleteTableRecords(GeneralConstants.TBLNAME_TECH_MASTER);
		Queryable sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryAssembly0.getQueryConfiguration());
		sqlEngine0.createPreparedStatement(jtaFooter);
		int affectedRows = sqlEngine0.deleteTableRecords(jtaFooter);
		jtaRightSQLProcess.append(affectedRows + " row(s) got affected in 'tech_master'!\n");
		jtaRightSQLDB.append("'tech_master' Table has been deleted!\n");
		double endTime = System.currentTimeMillis();
		double timeElapsed = (endTime - startTime)/1000;
		jtaRightSQLTrack.append(String.format("\nDeleting 'tech_master'\nElapsed Time: %.3f (secs)", timeElapsed));

	}
}
