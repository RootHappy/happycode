package com.wang.mongodb.javadriver;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class CRUDOperation {

	public static final MongoClient client;

	static {
		client = new MongoClient();
	}

	private MongoCollection<Document> getCollections(){
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("foo");
		return collection;
	}

	/**
	 * 插入一条记录到test.foo集合
	 * */
	@Test
	public void insertOne() {
		MongoCollection<Document> collection = getCollections();
//		{
//			"name" : "MongoDB",
//			"type" : "database",
//			"count" : 1,
//			"info" : {
//				x : 203,
//			    y : 102
//			}
//		}
		Document document = new Document("name","MongoDB")
			.append("type", "database")
			.append("count", 1)
			.append("info", new Document("x",203).append("y", 102));
		collection.insertOne(document);
	}

	/**
	 * 插入多条记录到test.foo集合
	 * */
	@Test
	public void insertMany() {
		MongoCollection<Document> collection = getCollections();
		List<Document> documents = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			documents.add(new Document("i",i).append("name", "wang"));
		}
		collection.insertMany(documents);
	}

	/**
	 * 查询文档
	 * */
	@Test
	public void queryDocument() {
		MongoCollection<Document> collection = getCollections();
		//查询第一个文档
		Document document = collection.find().first();
		System.out.println(document.toJson());
		//查找所有文档
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		}finally {
			cursor.close();
		}
		//指定条件的查询文档
		System.out.println(collection.find(Filters.eq("name", "MongoDB")).first().toJson());
		//查找一个文档集合
		Block<Document> documents = new Block<Document>() {
			@Override
			public void apply(Document t) {
				System.out.println(t.toJson());
			}
		};
		//i > 50
		collection.find(Filters.gt("i", 50)).forEach(documents);
		// 50 < i < 60
		collection.find(Filters.and(Filters.gt("i", 50),Filters.lt("i", 60) ) ).forEach(documents);;

		//排序文档
		System.out.println(collection.find(Filters.exists("i")).sort(new BasicDBObject("i",1)).first().toJson());
		//投影文档
		System.out.println(collection.find().projection(new BasicDBObject("i",1).append("username", 1)).first().toJson());
	}

	/**
	 * 更新文档
	 * */
	@Test
	public void update() {
		MongoCollection<Document> collection = getCollections();
		collection.updateOne(Filters.eq("i",5), new Document("$set",new Document("i",110)));
		UpdateResult result = collection.updateMany(Filters.lt("i",10), new Document("$inc",new Document("i",100)));
		System.out.println(result.getModifiedCount());
	}

	/**
	 * 删除文档
	 * */
	@Test
	public void delete() {
		MongoCollection<Document> collection = getCollections();
		DeleteResult result =  collection.deleteOne(new Document("i",10));
		System.out.println(result.getDeletedCount());
		DeleteResult deleteResult = collection.deleteMany(Filters.gte("i", 100));
		System.out.println(deleteResult.getDeletedCount());
	}

}
