package companion;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class Comrade {

	public static <T> List<T> getDeDupList (List<T> list) {
		Set<T> deDupSet = new TreeSet<T>(list);
		list.clear();
		list.addAll(deDupSet);
		return list;
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


}
