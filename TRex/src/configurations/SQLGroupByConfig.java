package configurations;

import javax.swing.JOptionPane;

import companion.Comrade;

public class SQLGroupByConfig {
	
	private String groupBy;

	public SQLGroupByConfig(String[] fields) {
			if (Comrade.isEmpty(fields)) {
				this.groupBy = ""; 
			} else {
				int sizeOfFields = fields.length;
				String stringStructured = "";
				for (int i=0; i<sizeOfFields; i++) {
					if (i == sizeOfFields-1) {
						stringStructured = stringStructured + fields[i];
					} else {
						stringStructured = stringStructured + fields[i] + ", ";
					}
				}
				this.groupBy = " GROUP BY " + stringStructured; 
			}
	}
	
	public String getGroubyString() { return this.groupBy; }
}
