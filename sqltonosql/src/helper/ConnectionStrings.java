package helper;

public final class ConnectionStrings {
/*
 * MySQL Connection String
 * */
	public static final String REGISTER_STRING = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/mysourcedata";
//	public static final String URL = "jdbc:mysql://10.105.20.177:3306/mysourcedata";
	public static final String USR = "test";
	public static final String PWD = "test@123";

/*
 * MongoDB Connection String
 * */

	public static final String MONGO_HOST = "localhost";
	public static final Integer MONGO_PORT = 27017;
	public static final String MONGO_DATABASE = "truenorth";
	public static final String MONGO_COLLECTION_NODES = "nodes";
	public static final String MONGO_COLLECTION_BUSINESS_PARTNERS = "business_partners";	
}
