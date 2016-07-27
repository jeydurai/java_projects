package controllers;

import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import viewsCommandLine.CommandHandler;
import viewsCommandLine.ScreenHandler;
import viewsGUI.HomeScreen;

public class MainInit {

	private static CommandHandler comm;

	public static void main(String[] args) {
		//chooseHandlers();
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//showGUI();
		showCommandLineView();
	}
	
	
	private static void showGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				HomeScreen homeScreen;
				homeScreen = new HomeScreen();
				homeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				homeScreen.setTitle("truenorth");
				homeScreen.setPreferredSize(new Dimension(1100, 500));
				homeScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
				homeScreen.pack();
				homeScreen.setVisible(true);
			}
			
		});
	}
	
	
	private static void showCommandLineView() {
		boolean shallExit = false;
		Scanner scanIn;
		ScreenHandler.printPrompt(true);
		while (true) {
			scanIn = new Scanner(System.in);
			String inString = scanIn.nextLine();
			try {
				comm = new CommandHandler(inString);
				shallExit = comm.chooseScreen();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (shallExit) {break;}
			ScreenHandler.printPrompt(false);
		}
		ScreenHandler.printExitMessage();
		scanIn.close();
		System.exit(0);
	}

}
