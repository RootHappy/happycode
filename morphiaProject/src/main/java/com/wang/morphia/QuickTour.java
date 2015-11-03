package com.wang.morphia;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

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
		MongoCollection<Document> collection =  database.getCollection("test");
		Document doc = new Document("name","MongoDB")
			.append("type", "database")
			.append("count", 1)
			.append("info", new Document("x",203).append("y", 102));
		collection.insertOne(doc);

		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < 100; i++) {
		    documents.add(new Document("i", i));
		}
		collection.insertMany(documents);
		System.out.println(collection.count());

		Document test = collection.find().first();
		System.out.println(test.toJson());

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}

		Document myDoc = collection.find(eq("i", 71)).first();
		System.out.println(myDoc.toJson());

		mongoClient.close();
	}

}
