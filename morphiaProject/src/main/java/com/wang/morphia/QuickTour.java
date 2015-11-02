package com.wang.morphia;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class QuickTour {

	public static void main(String[] args) {
	// 1 .localhost default-port
		MongoClient mongoClient = new MongoClient();
	//2.
//		MongoClient mongoClient = new MongoClient("localhost");
	//3.
//		MongoClient mongoClient = new MongoClient("localhost",27017);
	//4.
//		MongoClient mongoClient = new MongoClient(
//				Arrays.asList(
//						new ServerAddress(),
//						new ServerAddress(),
//						new ServerAddress()));
	//5.
//		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
//		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("mydb");
		database.getCollection("test");

		mongoClient.close();
	}

}
