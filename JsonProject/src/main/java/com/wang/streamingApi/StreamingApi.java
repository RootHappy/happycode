package com.wang.streamingApi;

import java.io.File;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class StreamingApi {
	
	public static void main(String[] args) throws Exception {
		writeBeanToJson();
		readJsonToBena();
	}
	
	public static void readJsonToBena() throws Exception {
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser parser = jsonFactory.createJsonParser(new File("src/main/resources/student.json"));
		while(parser.nextToken() != JsonToken.END_OBJECT) {
			String fieldName = parser.getCurrentName();
			if ("name".equals(fieldName)) {
				//move to next token
				parser.nextToken();
			        System.out.println(parser.getText());        	 
			}
			if("age".equals(fieldName)){
				//move to next token
				parser.nextToken();
				System.out.println(parser.getNumberValue());        	 
			}
			if("verified".equals(fieldName)){
				//move to next token
				parser.nextToken();
				System.out.println(parser.getBooleanValue());        	 
			}
			if("marks".equals(fieldName)){
				//move to [ 
				parser.nextToken();
				// loop till token equal to "]"
				while (parser.nextToken() != JsonToken.END_ARRAY) {
					System.out.println(parser.getNumberValue()); 
				}
			}
		}
	}
	
	public static void writeBeanToJson() throws Exception{
		JsonFactory jsonFactory = new JsonFactory();
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(new File("src/main/resources/student.json"), JsonEncoding.UTF8);
		//{
		jsonGenerator.writeStartObject();
		 // "name" : "zhangsan"
		jsonGenerator.writeStringField("name", "zhangsan");
		// "age" :22
		jsonGenerator.writeNumberField("age", 22);
		 // "verified" : false
		jsonGenerator.writeBooleanField("verified", false);
		// "marks" : [100, 90, 85]
		jsonGenerator.writeFieldName("marks");
		//[
		jsonGenerator.writeStartArray();
			jsonGenerator.writeNumber(100); 
	               jsonGenerator.writeNumber(90); 
	               jsonGenerator.writeNumber(85); 
		//]
		jsonGenerator.writeEndArray();
		//}
		jsonGenerator.writeEndObject();
		jsonGenerator.close();
	}

}
