package com.wang.treeModel;

import java.io.File;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
/**
 * tree model
 * */
public class TreeModelDemo {

	public static void main(String[] args) throws Exception{
		readValueTreeModel();
		writeValueTreeModel();
	}

	public static void readValueTreeModel() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(new File("src/main/resources/user.json"));
		JsonNode nameNode = rootNode.path("name");
		String firstName = nameNode.path("first").getTextValue();
		String lastName = nameNode.path("last").getTextValue();
		
		JsonNode genderNode = rootNode.path("gender");
		JsonNode verifiedNode = rootNode.path("verified");
		System.out.println(firstName + " " + lastName);
		System.out.println(genderNode.getTextValue());
		System.out.println(verifiedNode.getBooleanValue());
	}

	public static void writeValueTreeModel() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		JsonNode marksNode = mapper.createArrayNode();
		((ArrayNode)marksNode).add(100);
		((ArrayNode)marksNode).add(90);
		((ArrayNode)marksNode).add(85);
		((ObjectNode)rootNode).put("name", "wangyunlong");
		((ObjectNode)rootNode).put("age", 22);
		((ObjectNode)rootNode).put("verified", true);
		((ObjectNode)rootNode).put("marks",marksNode);
		mapper.writeValue(new File("src/main/resources/student.json"), rootNode);
	}

}
