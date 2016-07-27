package viewsCommandLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHandler{
	private String inString, command, param;
	public CommandHandler(String str) {
		this.inString = str;
		String pattern = "(\\w+)(\\s+)-(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(this.inString);
		if (m.find()) {
			this.command = m.group(1);
			this.param = m.group(3);
			//System.out.print("Command: ");
			//System.out.println(m.group(1));
			//System.out.print("Matched Option Parameter: ");
			//System.out.println(m.group(3));
		} else {
			pattern = "^(\\w+)";
			Pattern p2 = Pattern.compile(pattern);
			Matcher m2 = p2.matcher(this.inString);
			if (m2.find()) {
				this.command = m2.group(1);
				this.param = "";
			} else {
				this.command = "Unrecognized/Unknown Command!";
				this.param = "";
			}
		}
	}
	public boolean chooseScreen() {
		boolean shallExit = false;
		switch (this.command) {
			case "auth": { // subset
				System.out.println("This is an Authorization Command!");
				this.cmdAuthExecutor(this.param);
				shallExit = false;
				break;
			}
			case "cvrt": { // subset
				System.out.println("This is a Conversion Command!");
				this.cmdCvrtExecutor(this.param);
				shallExit = false;
				break;
			}
			case "modi": { // subset
				System.out.println("This is a Modification Command!");
				shallExit = false;
				break;
			}
			case "subs": { // subset
				//startProcess(this.param);
				this.cmdSubsetExecutor(this.param);
				shallExit = false;
				break;
			}
			case "bkup": { // subset
				System.out.println("This is a Backup Command!");
				this.cmdBackupExecutor(this.param);
				shallExit = false;
				break;
			}
			case "rstr": { // subset
				System.out.println("This is a Restore Command!");
				this.cmdRestoreExecutor(this.param);
				shallExit = false;
				break;
			}
			case "help": { // subset
				System.out.println("This is the Help Command!");
				this.cmdHelpExecutor(this.param);
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
	
	private void cmdAuthExecutor(String param) {
		String pattern = "(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		if (m.find()) {
			switch (param) {
				case "tn:usr-act": { 
					printChosenCommand(true, m.group(1));
					break;
				}
				case "tn:usr-act-all": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "tn:usr-dac": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "tn:usr-ref": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "tn:usr-res": {
					printChosenCommand(true, m.group(1));
					break;
				}
				default: {
					printChosenCommand(false, m.group(1));
				}
			}
		} else {
			System.out.println("No Parameters matching!");
		}

	}
	private void cmdCvrtExecutor(String param) {
		String pattern = "(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		if (m.find()) {
			switch (param) {
				case "fd:xlsq-sq:sq-nsq": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "bd:sq-nsq": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "fd:xlsq-init": {
					printChosenCommand(true, m.group(1));
					break;
				}
				default: {
					printChosenCommand(false, m.group(1));
				}
			}
		} else {
			System.out.println("No Parameters matching!");
		}
	}
	private void cmdBackupExecutor(String param) {
		String pattern = "(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		if (m.find()) {
			switch (param) {
				case "all:nsq-nsq-w": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "all:nsq-nsq-x": {
					printChosenCommand(true, m.group(1));
					break;
				}
				default: {
					printChosenCommand(false, m.group(1));
				}
			}
		} else {
			System.out.println("No Parameters matching!");
		}
	}
	private void cmdRestoreExecutor(String param) {
		String pattern = "(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		if (m.find()) {
			switch (param) {
				case "coll:nsq-nsq-w": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "coll:nsq-nsq-x": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "all:nsq-nsq-w": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "all:nsq-nsq-x": {
					printChosenCommand(true, m.group(1));
					break;
				}
				default: {
					printChosenCommand(false, m.group(1));
				}
			}
		} else {
			System.out.println("No Parameters matching!");
		}
	}
	private void cmdSubsetExecutor(String param) {
		String pattern = "(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		if (m.find()) {
			switch (param) {
				case "bd:nsq-nsq-und": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "bd:nsq-nsq-gd": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "gd:nsq-nsq-gdyoy": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "bd:nsq-nsq-gdps": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "gd:nsq-nsq-gdprodyoy": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "gd:nsq-nsq-gdservyoy": {
					printChosenCommand(true, m.group(1));
					break;
				}
				default: {
					printChosenCommand(false, m.group(1));
				}
			}
		} else {
			System.out.println("No Parameters matching!");
		}
	}
	private void cmdHelpExecutor(String param) {
		String pattern = "(.*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		if (m.find()) {
			switch (param) {
				case "auth": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "cvrt": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "subs": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "modi": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "bkup": {
					printChosenCommand(true, m.group(1));
					break;
				}
				case "rstr": {
					printChosenCommand(true, m.group(1));
					break;
				}
				default: {
					printChosenCommand(false, m.group(1));
				}
			}
		} else {
			System.out.println("No Parameters matching!");
		}
	}

	private void printChosenCommand(boolean isMatched, String str) {
		if (isMatched) {
			System.out.print("Matched Option: "); System.out.println(str);
		} else {
			System.out.print("Unmatched Option: "); System.out.println(str);
		}
	}
	
}
