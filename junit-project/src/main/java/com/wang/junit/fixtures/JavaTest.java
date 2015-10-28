package com.wang.junit.fixtures;

import org.junit.Test;

import junit.framework.TestCase;


/**
 * Junit运行固定的测试值，可多次重复执行
 * */
public class JavaTest extends TestCase{
	protected int value1;
	protected int value2;

	//assigning the values
	protected void setUp() {
		this.value1 = 3;
		this.value2 = 3;
	}
	// test method to add two values
	@Test
	public void testAdd() {
		double result = value1 + value2;
		assertTrue(result == 6);
	}
}
