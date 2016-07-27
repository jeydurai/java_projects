package dataClasses;

import helper.ProcessHelper;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class ReverseTopperData implements Comparator<ReverseTopperData>, Comparable<ReverseTopperData>{

	public String name;
	public Double value;
	
	public ReverseTopperData(String name, Double value) {
		this.name = JSONObject.escape(ProcessHelper.escDot(name));
		this.value = value;
	}
	
	public ReverseTopperData() {
	}
	
	public Map<String, Double> getTopperData() {
		Map<String, Double> dataMap = new HashMap<String, Double>();
		dataMap.put(this.name, this.value);
		return dataMap;
	}

	@Override
	public int compareTo(ReverseTopperData o) {
		if(this.value < o.value) return 1;
		if(this.value > o.value) return -1;
		return 0;
	}

	@Override
	public int compare(ReverseTopperData o1, ReverseTopperData o2) {
		Double v1 = o1.value;
		Double v2 = o2.value;
		
		if (v1 == v2) {
			return 0;
		} else if (v1 < v2) {
			return -1;
		} else {
			return 1;
		}
		
	}
}
