package com.wang.dataBinding;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.wang.bean.Student;
import com.wang.bean.UserData;

public class GenericsDataBinding {
	
	public static void main(String[] args) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<String,UserData> userDataMap = new HashMap<>();
		UserData studentData = new UserData();
		int[] marks = {1,2,3};
		Student student = new Student();
	        student.setAge(10);
	        student.setName("Mahesh");
	        // JAVA Object
	         studentData.setStudent(student);
	         // JAVA String
	         studentData.setName("Mahesh Kumar");
	         // JAVA Boolean
	         studentData.setVerified(Boolean.FALSE);
	         // Array
	         studentData.setMarks(marks);
	         TypeReference<Map<String,UserData>> ref  = new TypeReference<Map<String,UserData>>() {};
	         userDataMap.put("studentData1", studentData);
	         mapper.writeValue( new File("src/main/resources/student.json"), userDataMap );
	         
	         userDataMap = mapper.readValue(new File("src/main/resources/student.json"), ref);

	         System.out.println( userDataMap.get("studentData1").getStudent() );
	         System.out.println( userDataMap.get("studentData1").getName() );
	         System.out.println( userDataMap.get("studentData1").getVerified() );
	         System.out.println( Arrays.toString(userDataMap.get("studentData1").getMarks()) );
				
	}

}
