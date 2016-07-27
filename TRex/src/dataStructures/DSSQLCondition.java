package dataStructures;

import GeneralConstants.Generics;

public class DSSQLCondition {

	private String criterion;
	private Generics.SQLOperatorCode opCode;
	private Generics.SQLLogicalOperatorCode logicalCode;
	
	public DSSQLCondition(String cri, Generics.SQLOperatorCode code, Generics.SQLLogicalOperatorCode logical) {
		this.criterion = cri; this.opCode = code; this.logicalCode = logical;
	}
	
	public String getConditionCriterion() { return this.criterion; }
	public Generics.SQLOperatorCode getConditionOperatorCode() { return this.opCode; }
	public Generics.SQLLogicalOperatorCode getConditionLogicalOperatorCode() { return this.logicalCode; }
}
