package helperUtilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import dataStructures.DSCustomizable;

public final class Comforter {

	public static String StackTraceToString(Object e) {
		String returnString = "";
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		if (e instanceof Exception) {
			((Exception) e).printStackTrace(printWriter);
			returnString = writer.toString();
		}
		
		return returnString;
	}
	
	public static <T> boolean isEmpty(T[] array) {
		boolean empty = true;
		for (int i=0; i<array.length; i++) {
			if (array[i] != null) {
				empty = false;
				break;
			}
		}
		return empty;
	}

	
	
	public static Integer getParamMapKeySize(Map<Integer, DSCustomizable> sqlParams) {
		Integer key = 1;
		try {
			Set<Integer> keys = sqlParams.keySet();
			TreeSet<Integer> sortedKeys = new TreeSet<Integer>(keys);
			key = sortedKeys.size() + 1;
		} catch (NullPointerException e) {
			key = 1;
		}
		return key;
	}

	public static void removeParamMapKeys(Integer keyBefore, Map<Integer, DSCustomizable> sqlParams) {
		Integer keyAfter = getParamMapKeySize(sqlParams);
		if (keyAfter > keyBefore) {
			Integer keyInit = ++keyBefore;
			for (Integer key = keyInit; key < keyAfter; key++) {
				sqlParams.remove(key);
			}
		}
	}

}
