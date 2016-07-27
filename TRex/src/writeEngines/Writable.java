package writeEngines;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import dataStructures.DSCustomizable;
import dataStructures.DSToppers;

public interface Writable {
	public void createPreparedStatement(Object errDisplayObj);
	public PreparedStatement getPreparedStatementInsert();
	public void writeData(Map<Integer, DSCustomizable> params, Object errDisplayObj);
}
