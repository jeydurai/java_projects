package sql;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Queryable {

	public List<String> fetchStringList (Map<Integer, TypeCustomizable> sqlParams) throws SQLException;
	public List<Integer> fetchIntegerList (Map<Integer, TypeCustomizable> sqlparams) throws SQLException;
}
