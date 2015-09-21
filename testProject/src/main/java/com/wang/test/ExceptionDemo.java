package com.wang.test;

public class ExceptionDemo {

	public static void main(String[] args) {
		System.out.println("*************self-definition exception************");
		try {
			food(1);
			food(10);
		}catch(MyException e) {
			System.out.println("Exception Caught " + e);
		}

	}

	public static void food(int apple) throws MyException {
		System.out.println("Called food " + apple + ".");
		if( apple < 10 )
			throw new MyException(apple);
		System.out.println("Normal exit.");
	}
}
