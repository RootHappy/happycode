package com.wang.dataBinding;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.wang.bean.Student;

//JSON Type	Java Type

//	object	LinkedHashMap<String, Object>
//	array	ArrayList<Object>
//	string	String
//	complete number	Integer, Long or BigInteger
//	fractional number	Double / BigDecimal
//	true | false	Boolean
//	null	null


public class SimpleDataBinding {
	
	public static void main(String[] args) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> studentDataMap = new HashMap<>();
		int[] marks = {90,80,85};
		Student student = new Student();
		student.setAge(22);
		student.setName("wangyunlong");
		
		 // JAVA Object
	         studentDataMap.put("student", student);
	         // JAVA String
	         studentDataMap.put("name", "Mahesh Kumar");  
	         // JAVA Boolean
	         studentDataMap.put("verified", Boolean.FALSE);
	         // Array
	         studentDataMap.put("marks", marks);
	         mapper.writeValue(new File("src/main/resources/student.json"), studentDataMap);
		
	         @SuppressWarnings("unchecked")
	         Map<String,Object> result =  mapper.readValue(new File("src/main/resources/student.json"), Map.class);
	         System.out.println(result.get("student"));
	         System.out.println(result.get("name"));
	         System.out.println(result.get("verified"));
	         System.out.println(result.get("marks"));

	}

}
