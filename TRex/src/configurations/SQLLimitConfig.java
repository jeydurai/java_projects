package configurations;

import dataStructures.DSLimit2D;

public class SQLLimitConfig {
	private String limitString;
	
	public SQLLimitConfig(DSLimit2D limit2d) {
		String tempString = "";
		int fromLimit = limit2d.getFromLimit();
		int toLimit = limit2d.getToLimit();
		if (toLimit == 0 || (toLimit < fromLimit)) {
			tempString = "";
		} else {
			tempString = " Limit " + fromLimit + ", " + toLimit;
		}
		this.limitString = tempString;
	}
	
	public String getLimitString() {
		return this.limitString;
	}
}
