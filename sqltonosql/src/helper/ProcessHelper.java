package helper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ProcessHelper {

	public static final String BASE_TABLE_NAME = "booking_dump";
	public static final String DOT_ESC_STR = "D0T";
	public static final String DOLLAR_ESC_STR = "D0LLAR";
	public static enum Key {
		YEAR("year"), PERIOD("period"), ALL("all"), GTMU("gtmu"), SUB_SCMS("sub_scms"), REGION("region"), SL6("sales_level_6"),
		SA("sales_agent"), PAR("partner"), CUS("customer"), PL("pl"), PLV("plv"), PL_V("plv"),
		PLS("plv"), PL_S("plv"), 
		NODES("nodes"), METRICS("metrics"), BOOKING("booking"), DISCOUNT("discount"), ABSOLUTE("abs"), PERCENTAGE("per"),
		TECHNOLOGIES("techs"), CUSTOMERS("customers"), PARTNERS("partners"), SALES_AGENTS("sales_agents"), AT_ATTACH("at_attach"), TOTAL("total"),
		BOOKING_TOPPERS("booking_toppers"), TECHNOLOGY_TOPPERS("tech_toppers"), DISCOUNT_TOPPERS("discount_toppers"), BASE_LIST("list"), 
		SORT_ASCEN("ASCEN"), SORT_DESC("DESC");
		
		private String key;
		private Key(String s) {
			this.key = s;
		}
		public String asKey() {
			return this.key;
		}
	}
	
	public static enum TYPE {
		SORT_ASCEN("ASCEN"), SORT_DESC("DESC");
		
		private String type;
		private TYPE(String s) {
			this.type = s;
		}
		public String asString() {
			return this.type;
		}
	}

	
	public static String escDot(String str) {
		String clnStr = "";
		String finalStr = "";
		String dotPattern = "\\.";
		String dollarPattern = "\\$";
		Pattern p = Pattern.compile(dotPattern);
		Matcher m = p.matcher(str);
		if (m.find()) {
			clnStr = str.replaceAll(dotPattern, ProcessHelper.DOT_ESC_STR);
			System.out.print(".");
			//System.out.printf("\t\t!!!!!! Dot Found in %s and replaced by %s\n", str, clnStr);
		} else {
			clnStr = str;
		}
		p = Pattern.compile(dollarPattern);
		m = p.matcher(clnStr);
		if (m.find()) {
			finalStr = clnStr.replaceAll(dollarPattern, ProcessHelper.DOLLAR_ESC_STR);
			System.out.print(".");
			//System.out.printf("\t\t!!!!!! Dollar Found in %s and replaced by %s\n", clnStr, finalStr);
		} else {
			finalStr = clnStr;
		}
		
		return finalStr;
	}
	
	public static String reviveDotDollar(String str) {
		String clnStr = "";
		String finalStr = "";
		String dotPattern = ProcessHelper.DOT_ESC_STR;
		String dollarPattern = ProcessHelper.DOLLAR_ESC_STR;
		Pattern p = Pattern.compile(dotPattern);
		Matcher m = p.matcher(str);
		if (m.find()) {
			clnStr = str.replaceAll(dotPattern, ".");
			System.out.print(".");
			//System.out.printf("\t\t!!!!!! Dot Esc String in %s is revived by %s\n", str, clnStr);
		} else {
			clnStr = str;
		}
		p = Pattern.compile(dollarPattern);
		m = p.matcher(clnStr);
		if (m.find()) {
			finalStr = clnStr.replaceAll(dollarPattern, "$");
			System.out.print(".");
			//System.out.printf("\t\t!!!!!! Dollar Esc string in %s is revived by %s\n", clnStr, finalStr);
		} else {
			finalStr = clnStr;
		}
		
		return finalStr;
	}

	
	public static <T> List<T> getCleanArrayList (List<T> list) {
		Set<T> deDupSet = new TreeSet<T>(list);
		list.clear();
		list.addAll(deDupSet);
		return list;
	}
	
	public static <T> TreeSet<T> sortSet (Set<T> set) {
		TreeSet<T> sortedSet = new TreeSet<T>(set);
		return sortedSet;
	}
	
	public static <T> void printArray(String descri, T[] inputArray, BufferedWriter bw) {
		try {
			bw.write(descri + "\n");
			System.out.printf("%s\n", descri);
			for (T element : inputArray) {
				bw.write("\t" + (String)element + "\n");
				System.out.printf("\t%s\n", element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static <T> void printMappedArray(Map<T, T[]> inputMap, BufferedWriter bw) {
		Set<T> keys = inputMap.keySet();
		for (T key : keys) {
			for (T element : inputMap.get(key)) {
				try {
					bw.write("\t" + key + ":" + element + "\n");
					System.out.printf("%s: %s\n", key, element);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
}
