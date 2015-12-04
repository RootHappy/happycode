package com.wang.java.lang;

public class ClassDemo{

	public static void main(String[] args) {
		Class<ClassDemo> clazz = ClassDemo.class;

		Object object = new Object();
		System.out.println(clazz.toString());
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getSuperclass());
		System.out.println(clazz.getGenericSuperclass());
		System.out.println(clazz.getPackage());
		System.out.println(clazz.getInterfaces());
		System.out.println(clazz.getModifiers());

		System.out.println(clazz.isInstance(object));
		System.out.println(Object.class.isAssignableFrom(clazz));
		System.out.println();
	}

}
