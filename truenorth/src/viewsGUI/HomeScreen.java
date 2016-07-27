package viewsGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import helperUtilities.Comforter;
import helperUtilities.GeneralConstants;
import viewsGUIComponents.CustomizedPanel;
import viewsGUIComponents.CustomizedPanel2;
import viewsGUIComponents.MenuEngine;
import viewsGUIComponents.MenuItemEngine;
import viewsGUIComponents.PanelGradient;
import viewsGUIComponents.DesktopPane;

public class HomeScreen extends JFrame implements ActionListener{

	protected DesktopPane desktop;
	protected JMenuBar menuBar;
	protected MenuEngine menuApplication, menuDashboard, menuData, menuQuery, menuHelp;
	protected MenuItemEngine menuItemAppCfgUser, menuItemAppCfgApp, menuItemAppExit;
	protected MenuItemEngine menuItemDashboardBooking, menuItemDashboardScorecard, menuItemDashboardStackRanking, menuItemDashboardBEScorecard;
	protected MenuItemEngine menuItemDataClnBooking, menuItemDataDashboard, menuItemDataAccountAssignment;
	protected MenuItemEngine menuItemQueryCI, menuItemQueryPI, menuItemQuerySAI, menuItemQueryGI;
	protected MenuItemEngine menuItemHelpAbout, menuItemHelpUpdateCheck; 
	protected BufferedImage faviIcon;
	
	
	public HomeScreen() { // Constructor
		this.desktop = new DesktopPane();
		this.setContentPane(this.desktop);
		this.setJMenuBar(this.createMenu());
		try {
			this.faviIcon = ImageIO.read(this.getClass().getResource(GeneralConstants.FAVI_ICON));
			this.setIconImage(this.faviIcon);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.desktop, Comforter.StackTraceToString(e));
		}
	}
	
	protected JMenuBar createMenu() {
		this.menuBar = new JMenuBar();
		
		this.menuApplication = new MenuEngine("Application", KeyEvent.VK_A);
		this.menuBar.add(this.menuApplication);

		this.menuDashboard = new MenuEngine("Dashboard", KeyEvent.VK_D);
		this.menuBar.add(this.menuDashboard);

		this.menuData = new MenuEngine("Data", KeyEvent.VK_T);
		this.menuBar.add(this.menuData);

		this.menuQuery = new MenuEngine("Query", KeyEvent.VK_Q);
		this.menuBar.add(this.menuQuery);

		this.menuHelp = new MenuEngine("Help", KeyEvent.VK_H);
		this.menuBar.add(this.menuHelp);
		// ========================================================
		
		// Set up MenuItems
		this.menuItemAppCfgUser = new MenuItemEngine("Configure User", KeyEvent.VK_U, ActionEvent.ALT_MASK); this.menuItemAppCfgUser.addActionListener(this);
		this.menuItemAppCfgApp = new MenuItemEngine("Configure Application", KeyEvent.VK_A, ActionEvent.ALT_MASK); this.menuItemAppCfgApp.addActionListener(this);
		this.menuItemAppExit = new MenuItemEngine("Exit", KeyEvent.VK_X, ActionEvent.ALT_MASK); this.menuItemAppExit.addActionListener(this);
		
		this.menuItemDashboardBooking = new MenuItemEngine("Booking Dashboard", KeyEvent.VK_B, ActionEvent.ALT_MASK); this.menuItemDashboardBooking.addActionListener(this);
		this.menuItemDashboardScorecard = new MenuItemEngine("AM Scorecard", KeyEvent.VK_A, ActionEvent.ALT_MASK); this.menuItemDashboardScorecard.addActionListener(this);
		this.menuItemDashboardStackRanking = new MenuItemEngine("AM Stack Ranking", KeyEvent.VK_S, ActionEvent.ALT_MASK); this.menuItemDashboardStackRanking.addActionListener(this);
		this.menuItemDashboardBEScorecard = new MenuItemEngine("BE Scorecard", KeyEvent.VK_B, ActionEvent.ALT_MASK); this.menuItemDashboardBEScorecard.addActionListener(this);
		
		this.menuItemDataDashboard = new MenuItemEngine("Mapper/Cleaner", KeyEvent.VK_M, ActionEvent.ALT_MASK); this.menuItemDataDashboard.addActionListener(this);
		this.menuItemDataAccountAssignment = new MenuItemEngine("Account Assignment", KeyEvent.VK_D, ActionEvent.ALT_MASK); this.menuItemDataAccountAssignment.addActionListener(this);
		
		this.menuItemQueryCI = new MenuItemEngine("Customer Intelligence", KeyEvent.VK_C, ActionEvent.ALT_MASK); this.menuItemQueryCI.addActionListener(this);
		this.menuItemQueryPI = new MenuItemEngine("Partner Intelligence", KeyEvent.VK_P, ActionEvent.ALT_MASK); this.menuItemQueryPI.addActionListener(this);
		this.menuItemQuerySAI = new MenuItemEngine("Sales Agent Intelligence", KeyEvent.VK_S, ActionEvent.ALT_MASK); this.menuItemQuerySAI.addActionListener(this);
		this.menuItemQueryGI = new MenuItemEngine("Goal Intelligence", KeyEvent.VK_G, ActionEvent.ALT_MASK); this.menuItemQueryGI.addActionListener(this);

		this.menuItemHelpAbout = new MenuItemEngine("About ComInSaaBI", KeyEvent.VK_B, ActionEvent.ALT_MASK); this.menuItemHelpAbout.addActionListener(this);
		this.menuItemHelpUpdateCheck = new MenuItemEngine("Check for Updates", KeyEvent.VK_K, ActionEvent.ALT_MASK); this.menuItemHelpUpdateCheck.addActionListener(this);

		//Add Menu Items in Menus
		this.menuApplication.add(this.menuItemAppCfgUser); this.menuApplication.add(this.menuItemAppCfgApp); this.menuApplication.add(this.menuItemAppExit);
		this.menuDashboard.add(this.menuItemDashboardBooking); this.menuDashboard.add(this.menuItemDashboardScorecard); this.menuDashboard.add(this.menuItemDashboardStackRanking); this.menuDashboard.add(this.menuItemDashboardBEScorecard);
		this.menuData.add(this.menuItemDataDashboard); this.menuData.add(this.menuItemDataAccountAssignment);
		this.menuQuery.add(this.menuItemQueryCI); this.menuQuery.add(this.menuItemQueryPI); this.menuQuery.add(this.menuItemQuerySAI); this.menuQuery.add(this.menuItemQueryGI);
		this.menuHelp.add(this.menuItemHelpAbout); this.menuHelp.add(this.menuItemHelpUpdateCheck);
		
		return this.menuBar;
		// ========================================================
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if("Configure User".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Configure Application".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Exit".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Booking Dashboard".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("AM Scorecard".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("AM Stack Ranking".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("BE Scorecard".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Mapper/Cleaner".equals(arg0.getActionCommand())) {
				this.showIntFrameUpdateDashboardData();
				//JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Account Assignment".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Customer Intelligence".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Partner Intelligence".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Sales Agent Intelligence".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Goal Intelligence".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("About ComInSaaBI".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			} else if ("Check for Updates".equals(arg0.getActionCommand())) {
				JOptionPane.showMessageDialog(this.desktop, arg0.getActionCommand() + " Uner Construction...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.desktop, Comforter.StackTraceToString(e));
		} finally {
			this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	
	private void showIntFrameUpdateDashboardData() {
		IntFrameDataMapperCleaner frame = new IntFrameDataMapperCleaner();
		this.desktop.add(frame);
		frame.setVisible(true);
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(this.desktop, Comforter.StackTraceToString(e));
		}
	}

}
