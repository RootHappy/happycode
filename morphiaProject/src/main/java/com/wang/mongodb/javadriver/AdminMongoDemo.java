package com.wang.mongodb.javadriver;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class AdminMongoDemo {

	public static final MongoClient client = new MongoClient("localhost",27017);

	@Test
	public void databaseOperator() {
		//创建数据库——只获取数据库不会创建
//		MongoDatabase database =  client.getDatabase("mydb");
//		database.createCollection("ff");
		//列出数据库的名字
		for(String name : client.listDatabaseNames()) {
			System.out.println(name);
		}
		//删除数据库
//		client.dropDatabase("mydb");
//		client.getDatabase("mydb").drop();
	}

	@Test
	public void collectionOperator() {
		//对集合进行操作，如果集合不存在则创建集合
		MongoDatabase database = client.getDatabase("mydb");
		MongoCollection<Document> collection =  database.getCollection("ff");
		//		database.createCollection("ff");
		for(String name : database.listCollectionNames()) {
			System.out.println(name);
		}
		collection.drop();
	}

	public void indexOperator() {
		MongoDatabase database =  client.getDatabase("mydb");
	 	MongoCollection<Document> collection =  database.getCollection("test");

	 // create a text index on the "content" field
	 	collection.createIndex(new Document("content", "text"));

		// Insert some documents
		collection.insertOne(new Document("_id", 0).append("content", "textual content"));
		collection.insertOne(new Document("_id", 1).append("content", "additional content"));
		collection.insertOne(new Document("_id", 2).append("content", "irrelevant content"));

	}



	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase database = client.getDatabase("mydb");
		MongoCollection<Document> collection = 	database.getCollection("test");
		collection.insertOne(new Document("key","value"));
		// get a list of databases
		for(String name : client.listDatabaseNames()) {
			System.out.println(name);
		}
		//drop a database
//		database.drop();
//		client.dropDatabase("mydb");
		//create a collection
//		database.createCollection("cappedCollection",new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));

		//Get a list of Collections
		for(String name : database.listCollectionNames()) {
			System.out.println(name);
		}

		//drop a collection
//		collection.drop();

		//create an ascending index on the "i" field:-----1:ascending,-1:descending
		collection.createIndex(new Document("i",1));
		for(final Document index : collection.listIndexes()){
			System.out.println(index.toJson());
		}

		// create a text index on the "content" field
		collection.createIndex(new Document("content", "text"));

		// Insert some documents
		collection.insertOne(new Document("_id", 0).append("content", "textual content"));
		collection.insertOne(new Document("_id", 1).append("content", "additional content"));
		collection.insertOne(new Document("_id", 2).append("content", "irrelevant content"));

		// Find using the text index
		long matchCount = collection.count(Filters.text("textual content -irrelevant"));
		System.out.println("Text search matches: " + matchCount);

		// Find using the $language operator
		Bson textSearch = Filters.text("textual content -irrelevant", "english");
		matchCount = collection.count(textSearch);
		System.out.println("Text search matches (english): " + matchCount);

		// Find the highest scoring match
		Document projection = new Document("score", new Document("$meta", "textScore"));
		Document myDoc = collection.find(textSearch).projection(projection).first();
		System.out.println("Highest scoring document: " + myDoc.toJson());

		client.close();

	}

}
