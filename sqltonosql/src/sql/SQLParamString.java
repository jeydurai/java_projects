package sql;

import dataClasses.MapDataString;

public class SQLParamString extends MapDataString{

	
	@Override
	public void setData(String string) {
		this.stringData = string;
	}

	@Override
	public String getData() {
		return this.stringData;
	}

}
