package com.wang.test;

import java.util.Arrays;
import java.util.Set;

public class ReflectiveDemo {

	public static void main(String[] args) {
		Class<?> c1 = null;
		try {
			c1 = Class.forName(args[0]);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found.");
			System.exit(1);
		}
		Set<String> s  = null;
		try {
			s = (Set<String>)c1.newInstance();
		} catch (InstantiationException e) {
			System.err.println("Class not Instantiable.");
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.err.println("Class not Accessible.");
			System.exit(1);
		}
		s.addAll(Arrays.asList(args).subList(1, args.length));
		System.out.println(s);
	}

}
