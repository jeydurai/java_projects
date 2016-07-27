package main;

import java.util.Map;

import sql.TypeCustomizable;

public interface Periodical {

	public Integer[] getYear();
	public String[] getQuarter(Integer year);
	public Integer[] getMonth(String quarter);
	public String[] getWeek(Integer month);
}
