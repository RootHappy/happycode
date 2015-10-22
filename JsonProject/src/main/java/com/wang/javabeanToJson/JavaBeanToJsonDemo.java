package com.wang.javabeanToJson;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wang.bean.User;


public class JavaBeanToJsonDemo {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		User user = new User();
		user.setName("wangyunlong");
		user.setGender("man");
		user.setBirthday("1993-04-22");
		user.setPassword("111111");
		user.setPhone("1234567890");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(user);
		System.out.println(str);
	}

}
