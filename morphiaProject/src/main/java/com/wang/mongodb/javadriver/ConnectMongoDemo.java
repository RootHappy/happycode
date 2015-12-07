package com.wang.mongodb.javadriver;

import java.util.Arrays;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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
}
