package com.wang.junit.testSuite;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test case failure
 * */
public class TestJunit2 extends TestCase{

	String message = "Robert";

	@Test
	public void testSalutationMessage() {
		 System.out.println("Inside testSalutationMessage()");
		 message = "Hi!" + "Robert";
		 assertEquals(message, "Robert");
	}

}
