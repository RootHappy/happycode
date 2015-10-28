package com.wang.junit.util;

/**
 * This class prints the given message on console.
 * 2015-10-28 18:00
 * */
public class MessageUtil {

	private String message;

	//Constructor
	public MessageUtil(String message) {
		this.message = message;
	}

	//prints the message
	public String printMessage() {
		System.out.println(message);
		return message;
	}



}
