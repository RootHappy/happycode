package com.wang.java.lang;

public class ThrowableDemo {

	public static void main(String[] args) {
		Throwable t = new Throwable("adsfghjkl");
		System.out.println(t.getMessage());
		System.out.println(t.getLocalizedMessage());
		System.out.println(t);
		t.printStackTrace();
		StackTraceElement[] stackTraces = t.getStackTrace();
		for(StackTraceElement stack : stackTraces) {
			System.out.println(stack.toString());
		}
	}
}
