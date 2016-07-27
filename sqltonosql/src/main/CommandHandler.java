package main;

import java.util.regex.*;

public class CommandHandler{
	private String inString, command, param;
	public CommandHandler(String str) {
		this.inString = str;
		String pattern = "(\\w+)(\\s+)(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(this.inString);
		if (m.find()) {
			this.command = m.group(1);
			this.param = m.group(3);
		} else {
			pattern = "^(\\w+)";
			Pattern p2 = Pattern.compile(pattern);
			Matcher m2 = p2.matcher(this.inString);
			if (m2.find()) {
				this.command = m2.group(1);
				this.param = "";
			} else {
				this.command = "Command not matching!";
				this.param = "Parameters capture is NOT done!";
			}
		}
	}
	public boolean chooseScreen() {
		boolean shallExit = false;
		switch (this.command) {
			case "nav": {
				startProcess(this.param);
				shallExit = false;
				break;
			}
			case "quit": {
				shallExit = true;
				break;
			}
			default: {
				shallExit = false;
				break;
				
			}
		}
		return shallExit;
	}
	
	public void startProcess(String param) {
		String pattern = "[-](\\w+)(\\s+)(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		//ConverterMaster master = new ConverterMaster();
		Master master2 = new Master();
		if (m.find()) {
			switch (param) {
				case "sqltonosql": {
					//System.out.print("Matched Option: ");
					//System.out.println(m.group(1));
					//System.out.print("Matched Option Parameter: ");
					//System.out.println(m.group(3));
					master2.masterThread();
					break;
				}
				default: {
					System.out.print("Unmatched Option: ");
					System.out.println(m.group(1));
					System.out.print("Unmatched Option Parameter: ");
					System.out.println(m.group(3));
				}
			}
		} else {
			pattern = "(\\w+)";
			Pattern p2 = Pattern.compile(pattern);
			Matcher m2 = p2.matcher(param);
			if (m2.find()) {
				//System.out.print("Option Less Parameter is ");
				//System.out.println(m2.group(1));
				switch (param) {
					case "sqltonosql": {
						//System.out.print("Matched Option: ");
						//System.out.println(m.group(1));
						//System.out.print("Matched Option Parameter: ");
						//System.out.println(m.group(3));
//						master.masterThread();
						master2.masterThread();
						break;
					}
					default: {
						System.out.print("Unmatched Parameter Option: ");
						System.out.println(m.group(1));
						System.out.print("Unmatched Option Parameter: ");
						System.out.println(m.group(3));
					}
				}
			} else {
				System.out.println("No Parameters matching!");
			}
		}
	}
	
}
