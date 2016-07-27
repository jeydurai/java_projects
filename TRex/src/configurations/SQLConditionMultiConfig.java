package configurations;

import GeneralConstants.Generics;
import dataStructures.DSSQLCondition;
import dataStructures.DSSQLField;

public class SQLConditionMultiConfig extends SQLConditionConfigurator {

	private DSSQLCondition[] conditions;
	private Generics.SQLOperatorCode opCode;
	private Generics.SQLLogicalOperatorCode logicalCode;
	private String criterionField;
	private String conditionString;
	
	public SQLConditionMultiConfig(DSSQLCondition[] conditions) {
		this.conditions = conditions;
	}

	@Override
	public String getConditions() {
		int sizeOfArray = conditions.length;
		String eachCondition = "";
		String finalCondition = "";
		for (int i = 0; i < sizeOfArray; i++) {
			DSSQLCondition condition = conditions[i];
			String fieldString = condition.getConditionCriterion();
			Generics.SQLOperatorCode sqlQueryOperator = condition.getConditionOperatorCode();
			Generics.SQLLogicalOperatorCode sqlLogicalOperator = condition.getConditionLogicalOperatorCode();
			eachCondition = stringifyCondition(fieldString, sqlQueryOperator);
			if (i == sizeOfArray-1) {
				finalCondition = finalCondition + eachCondition;
			} else {
				finalCondition = finalCondition + eachCondition + getSQLLogicalOperator(sqlLogicalOperator);
			}
		}
		this.conditionString = (finalCondition.equals("") || finalCondition == null) ? finalCondition : " WHERE " + finalCondition;		
		return this.conditionString;
	}
	
	@Override
	public String stringifyCondition(String criterionField, Generics.SQLOperatorCode opCode) { 
		String tempString = null;
		String finalString = null;
		
		switch (opCode.asCode()) {
		case 1:
			tempString = criterionField + " = ?";
			break;
		case 2:
			tempString = criterionField + " < ?";
			break;
		case 3:
			tempString = criterionField + " <= ?";
			break;
		case 4:
			tempString = criterionField + " > ?";
			break;
		case 5:
			tempString = criterionField + " >= ?";
			break;
		case 6:
			tempString = "(" + criterionField + " >= ?" + " AND " + criterionField + " <= ?)";
			break;
		case 7:
			tempString = "(" + criterionField + " > ?" + " AND " + criterionField + " < ?)";
			break;
		case 8:
			tempString = criterionField + " LIKE '%" + "?" + "%'";
			break;
		case 9:
			tempString = criterionField + " LIKE '" + "?" + "%'";
			break;
		case 10:
			tempString = criterionField + " LIKE '%" + "?" + "'";
			break;
		case 11:
			tempString = criterionField + " <> ?";
			break;
		case 12:
			tempString = criterionField + " NOT LIKE '%" + "?" + "%'";
			break;
		case 13:
			tempString = criterionField + " NOT LIKE '" + "?" + "%'";
			break;
		case 14:
			tempString = criterionField + " NOT LIKE '%" + "?" + "'";
			break;
		default:
			tempString = "";
			break;
		}
		finalString = (tempString == "" || tempString == null) ? tempString : "" + tempString;
		return finalString; 
	}

	private String getSQLLogicalOperator(Generics.SQLLogicalOperatorCode code) {
		String operator = "";
		switch (code.asCode()) {
		case 1:
			operator = " AND ";
			break;
		case 2:
			operator = " OR ";
			break;
		default:
			operator = "";
			break;
		}
		return operator;
	}
	
}
