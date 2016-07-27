package dataClasses;

import java.util.HashMap;
import java.util.Map;

public class MetricParamShare {

	private String fieldType;
	private Map<String, String> dataField;
	
	public MetricParamShare(String fieldType, Map<String, String> dataField) {
		this.dataField = new HashMap<String, String>();
		this.fieldType = fieldType;
		this.dataField = dataField;
	}
	
	public String getFieldType() {
		return this.fieldType;
	}
	
	public Map<String, String> getDataFieldMap() {
		return this.dataField;
	}
}
