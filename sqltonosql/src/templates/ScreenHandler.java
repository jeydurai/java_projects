package templates;

public class ScreenHandler {
	public static final String HEADER = " WELCOME TO SQLtoNOSQL DATA CONVERTER ";
	public static final String SUB_HEADER1 = " Version 1.01.01";
	public static final String SUB_HEADER2 = " a unique Java based Data Converter Console Application";
	public static final String SUB_HEADER3 = " Owner: D. Jeyaraj";
	public static final String SUB_HEADER4 = " Divsion: Commercial";
	public static final String SUB_HEADER5 = " Profile: Data Analytics";
	public static final String SUB_HEADER6 = " SQLtoNOSQL is meant to be for internal use only and doesn't have any copyright";

	public static final String footer = " THANK YOU FOR USING SQLtoNOSQL DATA CONVERTER ";
	public static final String SUB_FOOTER1 = " Hope you have had a fun using SQLtoNOSQL";
	public static final String SUB_FOOTER2 = " SQLtoNOSQL is still under modern development";
	public static final String SUB_FOOTER3 = " New versions will have more features that will ease you in accomplishing your data conversion";
	public static final String SUB_FOOTER4 = " 'Imagination is more important than knowledge -- Albert Einstein'";
	public static final String SUB_FOOTER5 = " You can write to us your feedbacks to jeydurai@cisco.com";
	
	public static final int TOTAL_LENGTH = 90;
	public static final int SHORT_LENGTH = 10;
	public static final int PAGE_SIZE = 30; 

	public static void printPrompt(boolean shouldIncludeHeaderFooter) {
		if (shouldIncludeHeaderFooter) {
			clearScreen2();
			printWelcomeMessage();
			prompt();
		} else {
			putsb();
			prompt();
		}
	}

	public static void prompt () {
		puts("<jcdh::jeydurai@cisco.com>> ");
	}

	public static void printWelcomeMessage () {
		int tempLength = SHORT_LENGTH;
		//Header
		putsb();
		putsl(rptString("=", TOTAL_LENGTH));
		putsb();
		puts(rptString("*", lenSideDecorator(HEADER, 2)));
		puts(HEADER);
		putsl(rptString("*", lenSideDecorator(HEADER, 2)));
		putsb();
		putsl(rptString("=", TOTAL_LENGTH));
		//Sub Header1
		puts(rptString("#", tempLength--));
		putsl(SUB_HEADER1);
		//Sub Header2
		puts(rptString("#",  tempLength--));
		putsl(SUB_HEADER2);
		//Sub Header3
		puts(rptString("#",  tempLength--));
		putsl(SUB_HEADER3);
		//Sub Header4
		puts(rptString("#",  tempLength--));
		putsl(SUB_HEADER4);
		//Sub Header5
		puts(rptString("#",  tempLength--));
		putsl(SUB_HEADER5);
		//Sub Header6
		puts(rptString("#",  tempLength--));
		putsl(SUB_HEADER6);
		putsl(rptString("=", TOTAL_LENGTH));
	}
	
	public static void printExitMessage () {
		int tempLength = SHORT_LENGTH;
		//Footer
		putsl(rptString("=", TOTAL_LENGTH));
		putsb();
		puts(rptString("*",  tempLength--));
		puts(footer);
		putsl(rptString("*",  tempLength--));
		putsb();
		putsl(rptString("=", TOTAL_LENGTH));
		//Sub Footer1
		puts(rptString("#",  tempLength--));
		putsl(SUB_FOOTER1);
		//Sub Footer2
		puts(rptString("#",  tempLength--));
		putsl(SUB_FOOTER2);
		//Sub Footer3
		puts(rptString("#",  tempLength--));
		putsl(SUB_FOOTER3);
		//Sub Footer4
		puts(rptString("#",  tempLength--));
		putsl(SUB_FOOTER4);
		//Sub Footer5
		puts(rptString("#",  tempLength--));
		putsl(SUB_FOOTER5);
		putsl(rptString("=", TOTAL_LENGTH));
	}

	
	public static String rptString(String string, int times) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i=0; i<times; i++) strBuilder.append(string);
		return strBuilder.toString();
	}
	public static void puts(String str) {
		System.out.print(str);
	}
	public static void putsl(String str) {
		System.out.println(str);
	}
	public static void putsb() {
		System.out.println();
	}
	public static int lenSideDecorator (String text, int length) {
		return (TOTAL_LENGTH - text.length())/length;
	}
	
	public static void clearScreen() {
		//Runtime.getRuntime().exec("cls");
		//System.out.print("\u001b[2J");
		System.out.flush();
	}
	public static void clearScreen2() {
		for (int i=0; i<PAGE_SIZE; i++) System.out.println();
	}
}
