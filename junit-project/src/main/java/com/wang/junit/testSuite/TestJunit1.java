package com.wang.junit.testSuite;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test case success
 * */
public class TestJunit1 extends TestCase{

	String message = "Robert";

	@Test
	public void testPrintMessage() {
		 System.out.println("Inside testPrintMessage()");
		 assertEquals(message, "Robert");
	}
}
