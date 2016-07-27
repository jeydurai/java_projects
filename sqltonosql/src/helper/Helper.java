package helper;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class Helper {

	public static final String BASE_TABLE_NAME = "booking_dump";
	
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
	
}
