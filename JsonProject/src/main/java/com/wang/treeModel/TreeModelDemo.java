package com.wang.treeModel;

import java.io.File;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
/**
 * tree model
 * */
public class TreeModelDemo {

	public static void main(String[] args) throws Exception{
		readValueTreeModel();
	}

	public static void readValueTreeModel() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(new File("src/main/resources/user.json"));
		JsonNode nameNode = rootNode.path("name");
		JsonNode genderNode = rootNode.path("gender");
		JsonNode verifiedNode = rootNode.path("verified");
		String firstName = nameNode.path("first").getTextValue();
		String lastName = nameNode.path("last").getTextValue();
		System.out.println(firstName + " " + lastName);
		System.out.println(genderNode.getTextValue());
		System.out.println(verifiedNode.getBooleanValue());
	}

	public static void writeValueTreeModel() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeTree(jgen, rootNode);
		ObjectNode user = new ObjectNode(nc);
//		TreeMapper treeMapper = new TreeMapper();
//		ObjectNode userOb = treeMapper.objectNode();
//		Object nameOb = userRoot.putObject("name");
//		nameOb.put("first", "Joe");
//		nameOb.put("last", "Sixpack");
//		userOb.put("gender", User.Gender.MALE.toString());
//		userOb.put("verified", false);
//		byte[] imageData = getImageData(); // or wherever it comes from
//		userOb.put("userImage", imageData);

	}

}
