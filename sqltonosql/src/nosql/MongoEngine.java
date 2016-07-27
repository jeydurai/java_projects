package nosql;

import helper.ConnectionStrings;
import helper.ProcessTime;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoEngine {

	private ProcessTime timer;
	private MongoClient mongoClient;
	private DB db;
	private DBCollection coll, coll2;
	public MongoEngine (ProcessTime timer) {
		this.timer = timer;
		try {
			
			mongoClient = new MongoClient(ConnectionStrings.MONGO_HOST, ConnectionStrings.MONGO_PORT);
			db = mongoClient.getDB(ConnectionStrings.MONGO_DATABASE);
			coll = db.getCollection(ConnectionStrings.MONGO_COLLECTION_NODES);
			coll2 = db.getCollection(ConnectionStrings.MONGO_COLLECTION_BUSINESS_PARTNERS);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void insertNodes (String period, BasicDBObjectBuilder dbObject) {
			coll.insert(dbObject.get());
			System.out.printf("Node Coll. Document has been successfully inserted! (thread '%s')\n", period);
			timer.elapsedTime();
	}
	public void insertBusinessPartners (String period, BasicDBObjectBuilder dbObject) {
		coll2.insert(dbObject.get());
		System.out.printf("Business Partners Coll. Document has been successfully inserted! (thread '%s')\n", period);
		timer.elapsedTime();
}
	
}

