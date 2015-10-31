package com.wang.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAssert {
	@Test
	public void test() {
		int num = 5;
		String temp = null;
		String str = "Junit is working fine.";
		assertEquals("Junit is working fine.", str);
		assertFalse(num > 6);
		assertNotNull(temp);		
	}
}
