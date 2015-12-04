package com.wang.java.lang;

public class StackTrackElementDemo {
	/**
	 * 使用toString打印内存地址
	 * */
	public String toString() {
		return super.toString();
	}

	public static void main(String[] arg) {
		StackTraceElement stackTrace = new StackTraceElement("com.wang.java.lang.ThrowableDemo", "main", "StackTrackElementDemo.java", 9);
		System.out.println(stackTrace.getClassName());
		System.out.println(stackTrace.getMethodName());
		System.out.println(stackTrace.getFileName());
		System.out.println(stackTrace.getLineNumber());
		System.out.println(stackTrace);
		StackTrackElementDemo stackTrackElementDemo = new StackTrackElementDemo();
		System.out.println(stackTrackElementDemo);
	}

}
