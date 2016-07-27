package configurations;

import java.util.Map;
import java.util.Set;

public class SQLOrderByConfig {

	private String orderBy;
	
	public SQLOrderByConfig(Map<String, String> orderByList) {
		String stringFormat = "";
		if (orderByList != null) {
			Set<String> fields = orderByList.keySet();
			int howMany = fields.size();
			if (howMany > 0) {
				int fieldCount = 0;
				for (String field : fields) {
					fieldCount++;
					String sortDirection = (orderByList.get(field) != null && orderByList.get(field) != "") ? orderByList.get(field) : "";
					if (fieldCount == howMany) {
						stringFormat = field + " " + sortDirection;
					} else {
						stringFormat = field + " " + sortDirection + ", ";
					}
				}
				this.orderBy = " Order by " + stringFormat;
			} else {
				this.orderBy = "";
			}
		} else {
			this.orderBy = "";
		}
	}
	
	public String getOrderbyString() { return this.orderBy; }
}
