package com.wang.junit;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestTestCase extends TestCase{
	
	private double value1;
	private double value2;
	
	@Before
	public void setUp(){
		value1 = 2.0;
		value2 = 3.0;
	}
	@Test
	public void test() {
		System.out.println("No of Test Case = "+ this.countTestCases());
		String name = this.getName();
		System.out.println("Test Case name = " + name);
		this.setName("TestNewName");
		String newName = this.getName();
		System.out.println("updated Test case Name = " + newName);
	}
	
	public void tearDown() {
		System.out.println("tear down");
	}

}
