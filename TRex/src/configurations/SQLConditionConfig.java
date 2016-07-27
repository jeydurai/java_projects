package configurations;

import dataStructures.DSSQLCondition;
import GeneralConstants.Generics;

public class SQLConditionConfig extends SQLConditionConfigurator{

	private Generics.SQLOperatorCode opCode;
	private String criterionField;
	private String conditionString;
	
	public SQLConditionConfig(DSSQLCondition condition) {
		this.criterionField = condition.getConditionCriterion();
		this.opCode = condition.getConditionOperatorCode();
	}
	
	@Override
	public String getConditions() {
		String finalString = null;
		
		switch (this.opCode.asCode()) {
		case 1:
			finalString = this.criterionField + " = ?";
			break;
		case 2:
			finalString = this.criterionField + " < ?";
			break;
		case 3:
			finalString = this.criterionField + " <= ?";
			break;
		case 4:
			finalString = this.criterionField + " > ?";
			break;
		case 5:
			finalString = this.criterionField + " >= ?";
			break;
		case 6:
			finalString = "(" + this.criterionField + " >= ?" + " AND " + this.criterionField + " <= ?)";
			break;
		case 7:
			finalString = "(" + this.criterionField + " > ?" + " AND " + this.criterionField + " < ?)";
			break;
		case 8:
			finalString = this.criterionField + " LIKE '%" + "?" + "%'";
			break;
		case 9:
			finalString = this.criterionField + " LIKE '" + "?" + "%'";
			break;
		case 10:
			finalString = this.criterionField + " LIKE '%" + "?" + "'";
			break;
		case 11:
			finalString = this.criterionField + " <> ?";
			break;
		case 12:
			finalString = this.criterionField + " NOT LIKE '%" + "?" + "%'";
			break;
		case 13:
			finalString = this.criterionField + " NOT LIKE '" + "?" + "%'";
			break;
		case 14:
			finalString = this.criterionField + " NOT LIKE '%" + "?" + "'";
			break;
		default:
			finalString = "";
			break;
		}
		this.conditionString = (finalString == "" || finalString == null) ? finalString : " WHERE " + finalString;
		return this.conditionString; 
	}
	
}
