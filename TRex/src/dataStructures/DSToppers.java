package dataStructures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DSToppers implements Comparator<DSToppers>, Comparable<DSToppers>{

	private String name;
	private Double value;
	
	public DSToppers(String name, Double value) {
		this.name = name;
		this.value = value;
	}
	
	public Map<String, Double> getTopperData() {
		Map<String, Double> dataMap = new HashMap<String, Double>();
		dataMap.put(this.name, this.value);
		return dataMap;
	}
	
	@Override
	public int compareTo(DSToppers arg0) {
		if (this.value < arg0.value) return 1;
		if (this.value > arg0.value) return -1;
		return 0;
	}

	@Override
	public int compare(DSToppers arg0, DSToppers arg1) {
		Double v1 = arg0.value;
		Double v2 = arg1.value;
		
		if (v1 == v2) {
			return 0;
		} else if (v1 > v2) {
			return -1;
		} else {
			return 0;
		}
	}

}
