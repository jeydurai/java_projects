package configurations;

import GeneralConstants.Generics;

public interface SQLConditionable {

	public String getConditions();
	public String stringifyCondition(String criterionField, Generics.SQLOperatorCode opCode);
}
