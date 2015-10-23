package com.wang.test;

import java.io.File;
import java.util.Properties;




public class Test {
	public static String  name = "wangyunlong";

	public static void main(String[] args) {
//		File file = new File("/home/wangyunlong/wang.txt");
//		File srcFile = new File("/home/wangyunlong/wangTest.txt");
//		srcFile.renameTo(file);
//		System.out.println(file.getFreeSpace());
//		System.out.println(file.getName());
		Properties p =  System.getProperties();
		System.out.println(p.get("java.version"));
		System.out.println(p.get("java.vendor"));
		System.out.println(p.get("java.vendor.url"));
		System.out.println(p.get("java.home"));
		System.out.println(p.get("java.class.version"));
		System.out.println(p.get("java.class.path"));
		System.out.println(p.get("os.name"));
		System.out.println(p.get("os.arch"));
		System.out.println(p.get("os.version"));
		System.out.println(p.get("os.version"));
		System.out.println(p.get("path.separator"));
		System.out.println(p.get("line.separator"));
		System.out.println(p.get("user.name"));
		System.out.println(p.get("user.home"));
		System.out.println(p.get("user.dir"));

		String str = "D1";

		for(EdgeType edge : EdgeType.values()) {
			if(str.equals(edge.toString())) {
				System.out.println(str);
			}
		}

	}

	enum EdgeType {
		D1,D2,D3
	}

}
