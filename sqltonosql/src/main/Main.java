/**
 * 
 */
package main;

import java.util.Scanner;

import templates.ScreenHandler;

/**
 * @author jeydurai
 *
 */
public class Main {

	/**
	 * @param args
	 */
	private static CommandHandler comm;
	public static void main(String[] args) {
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
