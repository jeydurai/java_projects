package viewsGUI;

import helperUtilities.Comforter;
import helperUtilities.DefaultSQLQueries;
import helperUtilities.GeneralConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import queryEngines.Queryable;
import sql.MySQLConnection;
import GeneralConstants.Generics;
import GeneralConstants.Generics.FieldMode;
import GeneralConstants.Generics.SQLOperatorCode;
import GeneralConstants.Generics.SQLLogicalOperatorCode;
import configurations.SQLConditionConfig;
import configurations.SQLConditionable;
import configurations.SQLFieldConfig;
import configurations.SQLGroupByConfig;
import configurations.SQLLimitConfig;
import configurations.SQLOrderByConfig;
import configurations.SQLQueryAssembly;
import configurations.SQLQueryConfig;
import configurations.SQLQueryExtender;
import configurations.SQLTableConfig;
import dataStructures.DSCustomizable;
import dataStructures.DSLimit2D;
import dataStructures.DSParameterInteger;
import dataStructures.DSSQLCondition;
import dataStructures.DSSQLField;
import modelsDataHandlers.DataMapWriteWorkerThread;
import modelsSQL.SQLQueryEngine;
import viewsGUIComponents.CustomizedPanel;
import viewsGUIComponents.GroupContainer;
import viewsGUIComponents.InternalFrame;

public class IntFrameDataMapperCleaner extends InternalFrame{

	private CustomizedPanel pnlSideLeft, pnlBanner, pnlRight, pnlFooter, pnlRightSQL, pnlRightNoSQL;
	private JPanel pnlSideLeftSQL, pnlSideLeftNoSQL;
	private Box boxRightSQLProcess, boxRightSQLDB, boxRightSQLTrack, boxRightNoSQLProcess, 
	boxRightNoSQLDB, boxRightNoSQLTrack, boxFooter;
	private JTextArea jtaRightSQLProcess, jtaRightSQLDB, jtaRightSQLTrack, jtaRightNoSQLProcess, jtaRightNoSQLDB, 
	jtaRightNoSQLTrack, jtaFooter;
	private ImageIcon imgCompanyLogo;
	private JLabel jlCompanyLogo, jlCompanyLogo2;
	private String[] arraySQLCbox, arrayNoSQLCbox;
	private JComboBox cboxSQL, cboxNoSQL;
	public JButton runSQL, cancelSQL, runNoSQL, cancelNoSQL;
	private JPanel buttonPaneSQL, comboPaneSQL, buttonPaneNoSQL, comboPaneNoSQL;
	private List<DataMapWriteWorkerThread> tasks;
	private DataMapWriteWorkerThread worker;
	
	public IntFrameDataMapperCleaner() {
		super("Dashboard Data Updater");
		setPanelScaffolding();
		setComponentsInBanner();
		setComponentsInLeftPanel();
		setComponentsInRightPanel();
		setComponentsInFooter();
	}
	
	
	protected void setPanelScaffolding() {
		pnlRight = GroupContainer.createTitledCustomizedPanel("Task Tracker");
		BoxLayout layout = new BoxLayout(pnlRight, BoxLayout.Y_AXIS);
		pnlRight.setLayout(layout);
		
		pnlBanner = new CustomizedPanel();
		pnlBanner.setLayout(new BorderLayout());
        pnlBanner.setBorder(new EmptyBorder(20,20,20,20));
        pnlBanner.setBackground(Color.WHITE);
		
		pnlSideLeft = new CustomizedPanel();
        pnlSideLeft.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
		BoxLayout layout2 = new BoxLayout(pnlSideLeft, BoxLayout.Y_AXIS);
        pnlSideLeft.setLayout(layout2);

		pnlFooter = GroupContainer.createTitledCustomizedPanel("Error Tracker");
		BoxLayout layout1 = new BoxLayout(pnlFooter, BoxLayout.X_AXIS);
		pnlFooter.setLayout(layout1);

        add(pnlBanner, BorderLayout.NORTH);
        add(pnlSideLeft, BorderLayout.WEST);
        add(pnlRight, BorderLayout.CENTER);
        add(pnlFooter, BorderLayout.SOUTH);
	}

	protected void setComponentsInLeftPanel() {
		
		// Make SQL Writer Option Pane with components
		arraySQLCbox = new String[] {"Map Finance Booking Data in the SQL template", "Crawl and find Unique Names & Verticals", "Clean Booking Data and Write in SQL"};
		cboxSQL = new JComboBox(arraySQLCbox);
		cboxSQL.setSelectedIndex(-1);
		cboxSQL.addItemListener(new SQLComboItemHandler());
		comboPaneSQL = new JPanel();
		comboPaneSQL.add(cboxSQL);
		
		runSQL = new JButton("Run");
		runSQL.addActionListener(new ButtonEventHandler());
		runSQL.setEnabled(false);
		buttonPaneSQL = new JPanel();
		buttonPaneSQL.add(runSQL);

		cancelSQL = new JButton("Cancel");
		cancelSQL.addActionListener(new ButtonEventHandler());
		cancelSQL.setEnabled(false);
		buttonPaneSQL.add(cancelSQL);

		
		runSQL.setPreferredSize(new Dimension(80, 20));
		cancelSQL.setPreferredSize(new Dimension(80, 20));

		pnlSideLeftSQL = GroupContainer.groupContainer("SQL Writer", Component.CENTER_ALIGNMENT);
		pnlSideLeftSQL.add(comboPaneSQL);
		pnlSideLeftSQL.add(buttonPaneSQL, Component.CENTER_ALIGNMENT);
		pnlSideLeft.add(pnlSideLeftSQL);


		// Make NoSQL Writer Option Pane with components
		arrayNoSQLCbox = new String[] {"Write SQL Booking Data in to MongoDB", "Make Dashboard Data"};
		cboxNoSQL = new JComboBox(arrayNoSQLCbox);
		cboxNoSQL.setSelectedIndex(-1);
		cboxNoSQL.addItemListener(new NoSQLComboItemHandler());
		comboPaneNoSQL = new JPanel();
		comboPaneNoSQL.add(cboxNoSQL);

		runNoSQL = new JButton("Run");
		runNoSQL.addActionListener(new ButtonEventHandler());
		runNoSQL.setEnabled(false);
		buttonPaneNoSQL = new JPanel();
		buttonPaneNoSQL.add(runNoSQL);

		cancelNoSQL = new JButton("Cancel");
		cancelNoSQL.addActionListener(new ButtonEventHandler());
		cancelNoSQL.setEnabled(false);
		buttonPaneNoSQL.add(cancelNoSQL);

		runNoSQL.setPreferredSize(new Dimension(80, 20));
		cancelNoSQL.setPreferredSize(new Dimension(80, 20));

		pnlSideLeftNoSQL = GroupContainer.groupContainer("NoSQL Writer", Component.CENTER_ALIGNMENT);
		pnlSideLeftNoSQL.add(comboPaneNoSQL);
		pnlSideLeftNoSQL.add(buttonPaneNoSQL, Component.CENTER_ALIGNMENT);
		pnlSideLeft.add(pnlSideLeftNoSQL);
	}
	
	protected void setComponentsInRightPanel() {
		//Make Separate Layouts for SQL and No SQL
		pnlRightSQL = GroupContainer.createTitledCustomizedPanel("SQL Writer Process Tracker");
		BoxLayout layout1 = new BoxLayout(pnlRightSQL, BoxLayout.X_AXIS);
		pnlRightSQL.setLayout(layout1);
		pnlRightNoSQL = GroupContainer.createTitledCustomizedPanel("No SQL Writer Process Tracker");
		BoxLayout layout2 = new BoxLayout(pnlRightNoSQL, BoxLayout.X_AXIS);
		pnlRightNoSQL.setLayout(layout2);
		
		pnlRight.add(pnlRightSQL, Component.CENTER_ALIGNMENT);
		pnlRight.add(Box.createVerticalStrut(20));
		pnlRight.add(pnlRightNoSQL, Component.CENTER_ALIGNMENT);

		//Place components in SQL Processing Layoput
		boxRightSQLDB = GroupContainer.createBorderedBox("SQL Data Tracker");
		boxRightSQLProcess = GroupContainer.createBorderedBox("SQL Process Tracker");
		boxRightSQLTrack = GroupContainer.createBorderedBox("SQL Writer Timer");

		//Place components in No NoSQL Processing Layoput
		boxRightNoSQLDB = GroupContainer.createBorderedBox("NoSQL Data Tracker");
		boxRightNoSQLProcess = GroupContainer.createBorderedBox("NoSQL Process Tracker");
		boxRightNoSQLTrack = GroupContainer.createBorderedBox("NoSQL Writer Timer");

		
		// Add Process Layouts in Right SQL Panel
		pnlRightSQL.add(boxRightSQLDB);
		pnlRightSQL.add(boxRightSQLProcess);
		pnlRightSQL.add(boxRightSQLTrack);

		// Add Process Layouts in Right NoSQL Panel
		pnlRightNoSQL.add(boxRightNoSQLDB);
		pnlRightNoSQL.add(boxRightNoSQLProcess);
		pnlRightNoSQL.add(boxRightNoSQLTrack);
		
		//Place components in each of SQL Processing Boxes
		jtaRightSQLDB = GroupContainer.createTextAreaCustomized("----Database---", 20, 30);
		jtaRightSQLProcess = GroupContainer.createTextAreaCustomized("----No Process Initiated---", 20, 80);
		jtaRightSQLTrack = GroupContainer.createTextAreaCustomized("----Timer/Tracker---", 20, 20);
		boxRightSQLProcess.add(new JScrollPane(jtaRightSQLProcess));
		boxRightSQLDB.add(new JScrollPane(jtaRightSQLDB));
		boxRightSQLTrack.add(new JScrollPane(jtaRightSQLTrack));

		
		//Place components in each of NoSQL Processing Boxes
		jtaRightNoSQLDB = GroupContainer.createTextAreaCustomized("----Database---", 20, 30);
		jtaRightNoSQLProcess = GroupContainer.createTextAreaCustomized("----No Process Initiated---", 20, 80);
		jtaRightNoSQLTrack = GroupContainer.createTextAreaCustomized("----Timer/Tracker---", 20, 20);
		boxRightNoSQLProcess.add(new JScrollPane(jtaRightNoSQLProcess));
		boxRightNoSQLDB.add(new JScrollPane(jtaRightNoSQLDB));
		boxRightNoSQLTrack.add(new JScrollPane(jtaRightNoSQLTrack));


	}
	
	protected void setComponentsInBanner() {
		imgCompanyLogo = new ImageIcon(getClass().getResource(GeneralConstants.CISCO_LOGO));
		jlCompanyLogo = new JLabel(imgCompanyLogo);
		pnlBanner.add(jlCompanyLogo, BorderLayout.WEST);
	}
	protected void setComponentsInFooter() {
		boxFooter = Box.createHorizontalBox();
		jtaFooter = new JTextArea("----Error Message----", 10, 200);
		boxFooter.add(new JScrollPane(jtaFooter));
		pnlFooter.add(boxFooter);
	}

	
	private class ButtonEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				//getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				if (e.getSource() == runSQL) {
					String optionSelected = (String)cboxSQL.getSelectedItem(); 
					if (optionSelected == "Clean Booking Data and Write in SQL") {
						cancelSQL.setEnabled(true);
						jtaRightSQLProcess.setText(" Initiazling...\n");
						tasks = new ArrayList<DataMapWriteWorkerThread>();
						startSQLThread();
					} else if (optionSelected == "Map Finance Booking Data in the SQL template") {
						JOptionPane.showMessageDialog(null, "Under Construction, please stay tuned!");
					} else if (optionSelected == "Crawl and find Unique Names & Verticals") {
						JOptionPane.showMessageDialog(null, "Under Construction, please stay tuned!");
					}
				} else if (e.getSource() == cancelSQL) {
						String optionSelected = (String)cboxSQL.getSelectedItem(); 
						if (optionSelected == "Clean Booking Data and Write in SQL") {
							cancelSQL.setEnabled(false);
							for (DataMapWriteWorkerThread task: tasks) {
								if (!task.isDone()) {
									System.out.println("Is Done Before Cancelling: " + task.isDone());
									task.cancel(true);
									System.out.println("Is Done After Cancelling: " + task.isDone());
								}
							}
							cancelSQL.setEnabled(true);
							jtaRightSQLProcess.append(" Process Cancelled!\n");
						} else if (optionSelected == "Map Finance Booking Data in the SQL template") {
							JOptionPane.showMessageDialog(null, "Under Construction, please stay tuned!");
						} else if (optionSelected == "Crawl and find Unique Names & Verticals") {
							JOptionPane.showMessageDialog(null, "Under Construction, please stay tuned!");
						}
				} else if (e.getSource() == runNoSQL) {
					String optionSelected = (String)cboxNoSQL.getSelectedItem(); 
					if (optionSelected == "Write SQL Booking Data in to MongoDB") {
						JOptionPane.showMessageDialog(null, "Under Construction, please stay tuned!");
					} else if (optionSelected == "Make Dashboard Data") {
						JOptionPane.showMessageDialog(null, "Under Construction, please stay tuned!");
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, Comforter.StackTraceToString(ex));
			} finally {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
	}
	
	private void startSQLThread() {
		MySQLConnection sqlConnection = new MySQLConnection(Generics.LOW_MAX_ACTIVE_POOLING);
		double startTime = 0.0;
		JLabel label = new JLabel("If you are running this program for the first time, you got to Click on YES!");
		int selectedOption = JOptionPane.showConfirmDialog(null, label, "Run Initialization?", JOptionPane.YES_NO_OPTION);
		if (selectedOption == JOptionPane.YES_OPTION) {
			// ===================================================================
			// Preliminary clean up
			// ===================================================================
			
			//Deleting tech_master Table for clean up
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("\nDeleting 'tech_master' Table for clean up...\n");
			SQLQueryAssembly queryAssembly0 = DefaultSQLQueries.deleteTableRecords(GeneralConstants.TBLNAME_TECH_MASTER);
			Queryable sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryAssembly0.getQueryConfiguration());
			sqlEngine0.createPreparedStatement(jtaFooter);
			int affectedRows = sqlEngine0.deleteTableRecords(jtaFooter);
			jtaRightSQLProcess.append(affectedRows + " row(s) got affected in 'tech_master'!\n");
			jtaRightSQLDB.append("'tech_master' Table has been deleted!\n");
			double endTime = System.currentTimeMillis();
			double timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nDeleting 'tech_master'\nElapsed Time: %.3f (secs)", timeElapsed));
			
			//Inserting new data in to tech_master Table
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("Inserting new data in to 'tech_master' Table...\n");
			SQLQueryExtender queryExtender = DefaultSQLQueries.getExtenderTechMaster(GeneralConstants.TBLNAME_TECH_MASTER);
			sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryExtender);
			sqlEngine0.createExtendedPreparedStatement(jtaFooter);
			affectedRows = sqlEngine0.extendedInsertTableRecords(jtaFooter);
			jtaRightSQLProcess.append(affectedRows + " row(s) got affected in 'tech_master'!\n");
			jtaRightSQLDB.append("New data in to 'tech_master' Table has been inserted!\n");
			endTime = System.currentTimeMillis();
			timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nInserting new data in to 'tech_master'\nElapsed Time: %.3f (secs)", timeElapsed));
			
			//Deleting tech_master1 Table for clean up
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("Deleting 'tech_master1' Table for clean up...\n");
			queryAssembly0 = DefaultSQLQueries.deleteTableRecords(GeneralConstants.TBLNAME_TECH_MASTER1);
			sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryAssembly0.getQueryConfiguration());
			sqlEngine0.createPreparedStatement(jtaFooter);
			affectedRows = sqlEngine0.deleteTableRecords(jtaFooter);
			jtaRightSQLProcess.append(affectedRows + " row(s) got affected in 'tech_master1'!\n");
			jtaRightSQLDB.append("'tech_master1' Table has been deleted!\n");
			endTime = System.currentTimeMillis();
			timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nDeleting 'tech_master1'\nElapsed Time: %.3f (secs)", timeElapsed));
			
			//Inserting new data in to tech_master1 Table
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("Inserting new data in to 'tech_master1' Table...\n");
			queryExtender = DefaultSQLQueries.getExtenderTechMaster1(GeneralConstants.TBLNAME_TECH_MASTER1);
			sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryExtender);
			sqlEngine0.createExtendedPreparedStatement(jtaFooter);
			affectedRows = sqlEngine0.extendedInsertTableRecords(jtaFooter);
			jtaRightSQLProcess.append(affectedRows + " row(s) got affected in 'tech_master1'!\n");
			jtaRightSQLDB.append("New data in to 'tech_master1' Table has been inserted!\n");
			endTime = System.currentTimeMillis();
			timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nInserting new data in to 'tech_master1'\nElapsed Time: %.3f (secs)", timeElapsed));
			
			//Deleting tech_grand_master Table for clean up
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("Deleting 'tech_grand_master' Table for clean up...\n");
			queryAssembly0 = DefaultSQLQueries.deleteTableRecords(GeneralConstants.TBLNAME_TECH_GRAND_MASTER);
			sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryAssembly0.getQueryConfiguration());
			sqlEngine0.createPreparedStatement(jtaFooter);
			affectedRows = sqlEngine0.deleteTableRecords(jtaFooter);
			jtaRightSQLProcess.append(affectedRows + " row(s) got affected in 'tech_grand_master'!\n");
			jtaRightSQLDB.append("'tech_grand_master' Table has been deleted!\n");
			endTime = System.currentTimeMillis();
			timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nDeleting 'tech_grand_master'\nElapsed Time: %.3f (secs)", timeElapsed));
			
			//Inserting new data in to tech_grand_master Table
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("Inserting new data in to 'tech_grand_master' Table...\n");
			queryExtender = DefaultSQLQueries.getExtenderTechGrandMaster(GeneralConstants.TBLNAME_TECH_GRAND_MASTER);
			System.out.println(queryExtender.getExtendedQueryString());
			sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryExtender);
			sqlEngine0.createExtendedPreparedStatement(jtaFooter);
			affectedRows = sqlEngine0.extendedInsertTableRecords(jtaFooter);
			jtaRightSQLProcess.append(affectedRows + " row(s) got affected in 'tech_grand_master'!\n");
			jtaRightSQLDB.append("New data in to 'tech_grand_master' Table has been inserted!\n");
			endTime = System.currentTimeMillis();
			timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nInserting new data in to 'tech_grand_master'\nElapsed Time: %.3f (secs)", timeElapsed));
			
			//Dropping booking_dump Table
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("Dropping 'booking_dump' Table...\n");
			queryAssembly0 = DefaultSQLQueries.dropTableRecords(GeneralConstants.TBLNAME_BOOKING_DUMP);
			sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryAssembly0.getQueryConfiguration());
			sqlEngine0.createPreparedStatement(jtaFooter);
			sqlEngine0.dropTable(jtaFooter);
			jtaRightSQLProcess.append("'booking_dump' Table has been dropped permanently!\n");
			jtaRightSQLDB.append("'booking_dump' Table has been dropped!\n");
			endTime = System.currentTimeMillis();
			timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nDropping 'booking_dump' Table\nElapsed Time: %.3f (secs)", timeElapsed));
			
			//Creating booking_dump Table
			startTime = System.currentTimeMillis();
			jtaRightSQLDB.append("Creating 'booking_dump' Table...\n");
			queryAssembly0 = DefaultSQLQueries.createTableLikeTable(GeneralConstants.TBLNAME_BOOKING_DUMP);
			sqlEngine0 = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryAssembly0.getQueryConfiguration());
			sqlEngine0.createCreateLikePreparedStatement(jtaFooter, GeneralConstants.TBLNAME_BOOKING_DUMP_TEMPLATE);
			sqlEngine0.createTable(jtaFooter);
			jtaRightSQLProcess.append("'booking_dump' table has been created!\n");
			jtaRightSQLDB.append("'booking_dump' Table has been created!\n");
			endTime = System.currentTimeMillis();
			timeElapsed = (endTime - startTime)/1000;
			jtaRightSQLTrack.append(String.format("\nCreating 'booking_dump' Table\nElapsed Time: %.3f (secs)", timeElapsed));
		}
		// ================================================================================
		// Year Extraction and Worker Thread Preparation
		// ================================================================================
		SQLQueryAssembly queryAssembly = DefaultSQLQueries.getAssemblyDumpFromFinanceYear();
		Queryable sqlEngine = new SQLQueryEngine(sqlConnection.acquirePooledSQLConnection(), queryAssembly.getQueryConfiguration());
		sqlEngine.createPreparedStatement(jtaFooter);
		String[] yeararray = sqlEngine.getValues(null, jtaFooter);
		int counter = 0;
		int sizeofArray = yeararray.length;
		for (String element: yeararray) {
			++counter;
			int progress = (int)(((float)counter/(float)sizeofArray)*100.0);
			//System.out.println(String.format("Size of Array: %d | Counter: %d | progress: %d%%", sizeofArray, counter, progress));
			startTime = System.currentTimeMillis();
			worker = new DataMapWriteWorkerThread("Thread-" + element, Integer.parseInt(element), sqlConnection, true, jtaRightSQLDB, jtaRightSQLProcess, jtaRightSQLTrack, jtaFooter, runSQL, progress, startTime);
			tasks.add(worker);
			worker.execute();
		}
		sqlConnection = null;
	}
	
	private class SQLComboItemHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			try {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String optionSelected = (String)cboxSQL.getSelectedItem();
					if (optionSelected == "" || optionSelected == null) {
						runSQL.setEnabled(false);
					} else {
						runSQL.setEnabled(true);
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, Comforter.StackTraceToString(ex));
			} finally {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
	}

	
	private class NoSQLComboItemHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			try {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String optionSelected = (String)cboxNoSQL.getSelectedItem();
					if (optionSelected == "" || optionSelected == null) {
						runNoSQL.setEnabled(false);
					} else {
						runNoSQL.setEnabled(true);
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, Comforter.StackTraceToString(ex));
			} finally {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
	}

}
