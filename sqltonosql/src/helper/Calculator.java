package helper;

import dataClasses.UnitsTime;

public final class Calculator {

	public static UnitsTime getHoursFromMilliSeconds(Long millSec) {
		UnitsTime unit = null;
		Double hour = (millSec.doubleValue()/1000/(60*60));
		Double minute = (millSec.doubleValue()/1000/60);
		Double sec = (millSec.doubleValue()/1000);
		unit = new UnitsTime(hour, minute, sec, millSec.doubleValue());
		return unit;
	}
	
	public static Double getRatio(Double numerator, Double denominator) {
		return (denominator == 0D) ? 0D : numerator/denominator;
	}
}
