package dataClasses;

public class UnitsTime {

	private Double hour, minute, sec, millSec;
	
	public UnitsTime(Double hour, Double minute, Double sec, Double millSec) {
		this.hour = hour;
		this.minute = minute;
		this.sec = sec;
		this.millSec = millSec;
	}
	
	public Double getHour() {
		return this.hour;
	}
	
	public Double getMinute() {
		return this.minute;
	}
	
	public Double getSec() {
		return this.sec;
	}
	
	public Double getMillSec() {
		return this.millSec;
	}
}
