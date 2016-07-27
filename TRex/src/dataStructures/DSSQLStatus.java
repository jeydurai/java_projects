package dataStructures;

public class DSSQLStatus {

	private int recs;
	private String errorString;
	
	public DSSQLStatus(int recs, String error) {
		this.recs = recs; this.errorString = error;
	}
	
	public int getRecordCount() { return this.recs; }
	public String getErrorString() { return this.errorString; }

}
