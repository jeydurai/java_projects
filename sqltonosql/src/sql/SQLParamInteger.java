package sql;

import dataClasses.MapDataInteger;

public class SQLParamInteger extends MapDataInteger{

	@Override
	public void setData(Integer integer) {
		this.integerData = integer;
	}

	@Override
	public Integer getData() {
		return this.integerData;
	}

}
