package configurations;

import GeneralConstants.Generics;
import dataStructures.DSSQLField;

public class SQLFieldConfig {

	private String fieldString;
	private String insertParamValuePlaceHolderString;
	private DSSQLField[] sqlFieldDS;
	private int NoOfFields;
	
	public SQLFieldConfig(DSSQLField[] sqlFieldDS) {
		this.sqlFieldDS = sqlFieldDS;
		int sizeOfArray = sqlFieldDS.length;
		this.NoOfFields = sqlFieldDS.length;
		String fieldStructured = null;
		String fieldString = "";
		String insertParamValuePlaceHolderString = "";
		for (int i = 0; i < sizeOfArray; i++) {
			DSSQLField fieldDS = sqlFieldDS[i];
			fieldStructured = this.StringifyFieldDS(fieldDS);
			if (i == sizeOfArray-1) {
				fieldString = fieldString + fieldStructured;
				insertParamValuePlaceHolderString = insertParamValuePlaceHolderString + "?";
			} else {
				fieldString = fieldString + fieldStructured + ", ";
				insertParamValuePlaceHolderString = insertParamValuePlaceHolderString + "?" + ", ";
			}
		}
		
		this.fieldString = fieldString;
		this.insertParamValuePlaceHolderString = insertParamValuePlaceHolderString;
		//System.out.println(fieldString);
	}
	
	
	public String getParamValueString() { return this.insertParamValuePlaceHolderString; }
	public int getHowmanyFields() { return this.NoOfFields; }
	public String getFieldsAsString() { return this.fieldString; }
	public DSSQLField[] getSQLFieldDataStructure() { return this.sqlFieldDS; }
	
	private String StringifyFieldDS (DSSQLField fieldDS) {
		String fieldStructured = null;
		Generics.FieldMode fieldMode = fieldDS.getFieldMode();
		String field = fieldDS.getField();
		String fieldAlias = fieldDS.getFieldAlias();
		
		switch (fieldMode.asCode()) {
		case 1:
			fieldStructured = "" + field;
			break;
		case 2:
			fieldStructured = "SUM(" + field + ")";
			break;
		case 3:
			fieldStructured = "AVG(" + field + ")";
			break;
		case 4:
			fieldStructured = "COUNT(" + field + ")";
			break;
		}
		fieldStructured = (fieldAlias == null) ? fieldStructured : fieldStructured + " As " + fieldAlias;
		return fieldStructured;
	}
	
}
