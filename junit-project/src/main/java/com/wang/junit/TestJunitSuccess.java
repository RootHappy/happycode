package com.wang.junit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.wang.junit.util.MessageUtil;

public class TestJunitSuccess{
	private String message = "Hello world";
	private MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		assertEquals(message , messageUtil.printMessage());
	}
}
