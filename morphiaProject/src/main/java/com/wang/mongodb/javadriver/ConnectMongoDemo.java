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
import org.junit.Test;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/***
 * 1. The following example shows five ways to connect to the database on the local machine.
 * 	If the database does not exist,MongoDB will create it for you
 * */
public class ConnectMongoDemo {
	/**
	 * 方法1：默认参数，本机host,27017端口
	 * */
	@Test
	public void connectMongoByDefaultHostAndProt(){
		MongoClient client = new MongoClient();
		find(client);
		client.close();
	}
	/**
	 * 方法2 ： 默认端口，指定主机
	 * */
	@Test
	public  void connectMongoByDefaultPort(){
		MongoClient client = new MongoClient("localhost");
		find(client);
		client.close();;
	}

	/**
	 * 方法3 : 指定主机，指定端口
	 * */
	@Test
	public void connectMongo(){
		MongoClient client = new MongoClient("localhost",27017);
		find(client);
		client.close();
	}

	/**
	 * 方法4 : 指定主机列表
	 * */
	@Test
	public void connectMongoByServerAddress(){
		ServerAddress serverAddress = new ServerAddress("localhost",27017);
		MongoClient client = new MongoClient(serverAddress);
		find(client);
		client.close();
	}

	/**
	 * 方法4 : 指定主机列表,连接多个分布式库
	 * */
	@Test
	public void connectMongoByServerAddresses(){
		ServerAddress serverAddress1 = new ServerAddress("localhost",27017);
//		ServerAddress serverAddress2 = new ServerAddress("localhost",27018);
//		ServerAddress serverAddress3 = new ServerAddress("localhost",27019);
		MongoClient client = new MongoClient(Arrays.asList(serverAddress1));//,serverAddress2,serverAddress3));
		find(client);
		client.close();
	}

	/**
	 * 方法5:是由mongo uri
	 *mongodb://username:password@ip1:27017,ip2:27017,ip3:27017/databaseName
	 * */
	@Test
	public void connectMongoByURI() {
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");//,localhost:27018,localhost:27019");
		MongoClient client = new MongoClient(connectionString);
		find(client);
		client.close();
	}


	/**
	 * 查找tests数据库中foo集合的元素：db.foo.find();
	 * @param client 连接的客户端
	 * */
	private static void find(MongoClient client) {
		MongoDatabase testDB =  client.getDatabase("test");
		MongoCollection<Document> fooColl = testDB.getCollection("foo");
		System.out.println("Count:  " + fooColl.count());
		MongoCursor<Document> documents = fooColl.find().iterator();
		while(documents.hasNext()) {
			System.out.println(documents.next().toJson());
		}
	}


	public static void main(String[] args) {
		MongoClient client2 = new MongoClient("localhost");
		MongoDatabase database = client2.getDatabase("mydb");
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
