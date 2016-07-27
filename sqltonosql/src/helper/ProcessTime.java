package helper;

import dataClasses.UnitsTime;

public class ProcessTime {

	private long startTime, stopTime, elapsedTime;
	private String threadName;
	
	public ProcessTime (String threadName) {
		this.threadName = threadName;
		this.startTime = System.currentTimeMillis();
	}
	
	public void elapsedTime() {
		this.stopTime = System.currentTimeMillis();
		this.elapsedTime = stopTime - startTime;
		
		UnitsTime timeUnit = Calculator.getHoursFromMilliSeconds(this.elapsedTime);
		
		System.out.printf("%.2f Hour(s) %.2f Min(s) %.2f sec(s) %.2f millSec(s) elapsed for the thread %s\n", 
				timeUnit.getHour(), timeUnit.getMinute(), timeUnit.getSec(), timeUnit.getMillSec(), this.threadName);
	}
}
