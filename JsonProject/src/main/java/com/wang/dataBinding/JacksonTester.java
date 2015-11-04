package com.wang.dataBinding;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.wang.bean.Student;

public class JacksonTester {
	
	public static void main(String[] args) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
		Student student = mapper.readValue(jsonString,Student.class);
		System.out.println(student);
		//设置允许启用格式缩进
		mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		String result = mapper.writeValueAsString(student);
		System.out.println(result);
	}

}
