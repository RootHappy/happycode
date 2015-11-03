package com.wang.mongodb.javadriver;

import com.mongodb.MongoClient;

/***
 * 1. The following example shows five ways to connect to the database on the local machine.
 * 	If the database does not exist,MongoDB will create it for you
 * */
public class BasicCourse {

	public static void main(String[] args) {
	//1. host:localhost,port: 27017
		MongoClient clientDefault = new MongoClient();
	//2.host:localhost,port: 27017
		MongoClient client2 = new MongoClient("localhost");
	//3. host:localhost,port: 27017
		MongoClient client3 = new MongoClient();

	}

}
