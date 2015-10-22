package com.wang.jsonTojavabean;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wang.bean.User;

public class JsonToJavabeanDemo {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String src = "{\"name\":\"wangyunlong\",\"password\":\"111111\",\"phone\":\"1234567890\",\"email\":null,\"gender\":\"man\",\"birthday\":\"1993-04-22\"}";
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(src, User.class);
		System.out.println(user);
	}

}
