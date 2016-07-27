package GeneralConstants;

public final class Generics {
	public static final int LOW_MAX_ACTIVE_POOLING = 100;
	public static final int MEDIUM_MAX_ACTIVE_POOLING = 500;
	public static final int HIGH_MAX_ACTIVE_POOLING = 100;
	

	public static enum FieldMode {
		FIELD(1), SUM(2), AVG(3), COUNT(4);
		
		private final int fieldModeCode;
		
		FieldMode (int code) { this.fieldModeCode = code; }
		
		public int asCode() { return this.fieldModeCode; }
	}
	
	public static enum SQLOperatorCode {
		DO_NOTHING(0), EQ(1), LT(2), LTE(3), GT(4), GTE(5), BETWEEN_CLOSED(6), BETWEEN_OPEN(7), 
		CONTAINS(8), BEGIN_WITH(9), END_WITH(10), NEQ(11), N_CONTAINS(12), 
		N_BEGIN_WITH(13), N_END_WITH(14);
		
		private final int sqlOperatorCode;
		
		SQLOperatorCode (int code) { this.sqlOperatorCode = code; }
		
		public int asCode() { return this.sqlOperatorCode; }
	}

	public static enum SQLLogicalOperatorCode {
		DO_NOTHING(0), AND(1), OR(2);
		
		private final int sqlLogicalOperatorCode;
		
		SQLLogicalOperatorCode (int code) { this.sqlLogicalOperatorCode = code; }
		
		public int asCode() { return this.sqlLogicalOperatorCode; }
	}

}
