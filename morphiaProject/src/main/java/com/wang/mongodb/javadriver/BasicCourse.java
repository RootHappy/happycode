package com.wang.mongodb.javadriver;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/***
 * 1. The following example shows five ways to connect to the database on the local machine.
 * 	If the database does not exist,MongoDB will create it for you
 * */
public class BasicCourse {

	public static void main(String[] args) {
	//1.1. host:localhost,port: 27017
//		MongoClient clientDefault = new MongoClient();
	//1.2.host:localhost,port: 27017
		MongoClient client2 = new MongoClient("localhost");
	//1.3. host:localhost,port: 27017
//		MongoClient client3 = new MongoClient("localhost",27017);
	//1.4. ServerAddress
//		MongoClient client4 = new MongoClient(
//				Arrays.asList(new ServerAddress("localhost",27017),
//						new ServerAddress("localhost",27018),
//						new ServerAddress("localhost",27019))
//				);
	//1.5.use a connection String
//		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
//		MongoClient client5 = new MongoClient(connectionString);
	//2. get a database
		MongoDatabase database = client2.getDatabase("mydb");
	//3.get a collection
		MongoCollection<Document> collection =database.getCollection("test");
		System.out.println(collection.count());
	//4.insert a document
//		{
//			"name" : "MongoDB",
//			"type" : "database",
//			"count" : 1,
//			"info" : {
//				x : 203,
//				 y : 102
//			}
//		}
		Document doc = new Document("name","MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("info", new Document("x",203).append("y", 102));
//		collection.insertOne(doc);

	//5. add multiple documents
		List<Document> documents = new ArrayList<Document>();
		for(int i = 0; i < 100; i++)
			documents.add(new Document("i",i));
//		collection.insertMany(documents);

	//6. count documents in a Collection
		System.out.println(collection.count());
	//7.find the first document in a collection
		Document myDoc = collection.find().first();
		System.out.println(myDoc);

	//8. find All documents in a collection
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		}finally {
			cursor.close();
		}

	//9. get a single document with a query filter
//		myDoc = collection.find(eq("i",71)).first();
//		System.out.println(myDoc.toJson());
	//10. get a set of Documents with a Query
		Block<Document> printBlock = new Block<Document>(){
			public void apply(final Document document) {
				System.out.println(document.toJson());
			}
		};
		collection.find(and(gt("i", 50),lte("i",100))).forEach(printBlock);
	//11. Sorting Documents
//		myDoc = collection.find(exists("i")).sort(descending("i")).first();
//		System.out.println(myDoc.toJson());

//		myDoc = collection.find().projection(excludeId()).first();
//		System.out.println(myDoc.toJson());

	//12 updating a single document
		collection.updateMany(eq("i",10), new Document("$set",new Document("i",110)));

	//13. updating all documents
		UpdateResult updateResult =  collection.updateMany(lt("i",80), new Document("$inc",new Document("i",80)));
		System.out.println(updateResult.getModifiedCount());
	//14. deleting Documents
		collection.deleteOne(eq("i",110));
	//15 bulk operations
		// 2. Ordered bulk operation - order is guarenteed
//		collection.bulkWrite(
//		  Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
//		                new InsertOneModel<>(new Document("_id", 5)),
//		                new InsertOneModel<>(new Document("_id", 6)),
//		                new UpdateOneModel<>(new Document("_id", 1),
//		                                     new Document("$set", new Document("x", 2))),
//		                new DeleteOneModel<>(new Document("_id", 2)),
//		                new ReplaceOneModel<>(new Document("_id", 3),
//		                                      new Document("_id", 3).append("x", 4))));

		 // 2. Unordered bulk operation - no guarantee of order of operation
//		collection.bulkWrite(
//		  Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
//		                new InsertOneModel<>(new Document("_id", 5)),
//		                new InsertOneModel<>(new Document("_id", 6)),
//		                new UpdateOneModel<>(new Document("_id", 1),
//		                                     new Document("$set", new Document("x", 2))),
//		                new DeleteOneModel<>(new Document("_id", 2)),
//		                new ReplaceOneModel<>(new Document("_id", 3),
//		                                      new Document("_id", 3).append("x", 4))),
//		  new BulkWriteOptions().ordered(false));

		DeleteResult deleteResult = collection.deleteMany(gte("i", 100));
		System.out.println(deleteResult.getDeletedCount());
		client2.close();
	}

}
