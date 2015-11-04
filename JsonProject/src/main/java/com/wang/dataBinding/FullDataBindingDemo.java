package com.wang.dataBinding;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.wang.bean.User;
import com.wang.bean.User.Gender;
import com.wang.bean.User.Name;

public class FullDataBindingDemo {

	/**
	 * full data binding
	 * */
	public static void main(String[] args) throws Exception {
//		readValueDemo();
//		writeValueDemo();
//		rawDataRead();
		rawDataWrite();
	}

	/**
	 * raw data type (Map.class, List.class, String[].class )
	 * 	JSON Type				Java Type
	 * 	object				LinkedHashMap<String,Object>
	 *	array				ArrayList<Object>
	 *	string				String
	 *	number (no fraction)	Integer, Long or BigInteger (smallest applicable)
	 *	number (fraction)		Double (configurable to use BigDecimal)
	 *	true|false			Boolean
	 *	null					null
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static void rawDataRead() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(new File("src/main/resources/user.json"), Map.class);
		System.out.println(map);
	}

	public static void rawDataWrite() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> userData = new HashMap<String,Object>();
		Map<String,String> nameStruct = new HashMap<String,String>();
		nameStruct.put("first", "Joe");
		nameStruct.put("last", "Sixpack");
		userData.put("name", nameStruct);
		userData.put("gender", "MALE");
		userData.put("verified", Boolean.FALSE);
		userData.put("userImage", "Rm9vYmFyIQ==");
		mapper.writeValue(new File("src/main/resources/user.json"), userData);
	}

	/**
	 * read json to JavaBean
	 * */
	public static void readValueDemo() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("src/main/resources/user.json"), User.class);
		System.out.println(user);
	}

	/**
	 * write javaBean to json
	 * */
	public static void writeValueDemo() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		Name name = new Name();
		name.setFirst("yunlong");
		name.setLast("wang");
		user.setName(name);
		user.setGender(Gender.MALE);
		user.setVerified(true);
		user.setUserImage("asdfghjkl");
		mapper.writeValue(new File("src/main/resources/user.json"), user);
	}

}
