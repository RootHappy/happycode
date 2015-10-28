package com.wang.junit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.wang.junit.util.MessageUtil;

public class TestJunitFailure{
	private String message = "Hello world";
	private MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		message = "new world";
		assertEquals(message , messageUtil.printMessage());
	}
}
