package com.wang.junit;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestAssertions {
	@Test
	public void testAssertions() {
		String str1 = new String("abc");
		String str2 = new String("abc");
		String str3 = null;
		String str4 = "abc";
		String str5 = "abc";
		int val1 = 5;
		int val2 = 6;
		String[] expectedArray = {"one","two" , "threee"};
		String[] resultArray = {"one","two" , "threee"};
		
		//check that two object are equal 
		assertEquals(str1, str2);
		
		//Check that a condition is true
		assertTrue(val1 < val2);
		
		//Check that a condition is false
		assertFalse(val1 > val2);
		
		//Check that a object isn't null
		assertNotNull(str1);
		
		//Check that a object is null
		assertNull(str3);
		
		//Check if two object references point to the same object
		assertSame(str4, str5);
		
		//Check if two object references not point to the same object
		assertNotSame(str1,str3);
		//Check whether two arrays are equal to each other
		assertArrayEquals(expectedArray,resultArray);
	}

}
