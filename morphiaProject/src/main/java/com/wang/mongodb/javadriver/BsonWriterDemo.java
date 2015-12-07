package com.wang.mongodb.javadriver;

import org.bson.BsonBinaryWriter;
import org.bson.BsonWriter;
import org.bson.io.BasicOutputBuffer;
import org.bson.io.BsonOutput;

/**
 *	{
 * 		"a" : "MongoDB",
 *  		"b" : [
 *          		{ "c": 1 }
 *        	]
 *	}
 * */
public class BsonWriterDemo {

	public static void main(String[] args) {
		BsonOutput output = new BasicOutputBuffer();
		@SuppressWarnings("resource")
		BsonWriter bson = new BsonBinaryWriter(output);

		bson.writeStartDocument();
		bson.writeName("a");
		bson.writeString("MongoDb");
		bson.writeName("b");
			bson.writeStartArray();
			bson.writeStartDocument();
			bson.writeName("c");
			bson.writeInt32(1);
			bson.writeEndDocument();
			bson.writeEndArray();
		bson.writeEndDocument();
		bson.flush();
		byte[] bytes = new byte[1024];
		output.writeBytes(bytes);
		System.out.println(new String(bytes));
		output.close();
	}

}
