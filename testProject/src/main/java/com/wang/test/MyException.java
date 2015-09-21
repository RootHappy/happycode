package com.wang.test;

public class MyException extends Exception{
	private int fruit;

	public MyException(int apple) {
		this.fruit = apple;
	}

	public String toString() {
		return "MyException[" + fruit + "]";
	}

}
