package configurations;

import GeneralConstants.Generics;

public abstract class SQLConditionConfigurator implements SQLConditionable{

	public String getConditions() { return null; }
	public String stringifyCondition(String criterionField, Generics.SQLOperatorCode opCode) { return null; }
}
