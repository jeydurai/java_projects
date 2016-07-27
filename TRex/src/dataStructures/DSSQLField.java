package dataStructures;

import GeneralConstants.Generics;

public class DSSQLField {

	private Generics.FieldMode fieldMode;
	private String fieldAlias, field;
	
	public DSSQLField(Generics.FieldMode field_mode, String field_alias, String field) {
		this.fieldMode = field_mode; this.fieldAlias = field_alias; this.field = field;
	}
	
	public Generics.FieldMode getFieldMode() { return this.fieldMode; }
	public String getFieldAlias() { return this.fieldAlias; }
	public String getField() { return this.field; }

}
