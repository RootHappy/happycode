package com.wang.java.lang;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class SystemDemo {

	public static void main(String[] args) {
		// 1.系统环境变量
		Map<String ,String> envs = System.getenv();
		System.out.println("1. 系统环境变量");
		for(Map.Entry<String, String> env : envs.entrySet()) {
			System.out.println(env.getKey() + " = " + env.getValue());
		}
		System.out.println(System.getenv("HOME"));
		System.out.println("-----------------------------------");

		//2.数组复制——primitive Type(基本数据类型)
		System.out.println("2.数组复制");
		int[] arraySrc = {1,2,3,4,5,6,7,8,9};
		int[] arrayDest = new int[9];
		System.arraycopy(arraySrc, 0, arrayDest, 0, arraySrc.length);
		for(int i = 0; i < arrayDest.length; i++) {
			System.out.print(arrayDest[i] + " ");
		}
		System.out.println();
		System.out.println("-----------------------------------");


		//3.系统属性操作
		System.out.println("3.获取系统属性");
		Properties properties =  System.getProperties();
		for(Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
		System.out.println(System.lineSeparator());
		System.setProperty("TestKey", "TestValue");
		System.out.println(System.getProperty("TestKey"));

		System.out.println("-----------------------------------");

		//4.获取系统时间
		System.out.println("4.获取系统时间");
		long currentTime = System.currentTimeMillis();
		long currentTimeNano = System.nanoTime();
		System.out.println("Millis : "+ currentTime);
		System.out.println("nano : " + currentTimeNano);

		//5.加载
//		System.load("");
//		System.loadLibrary("");

		Object object = new Object();
		System.out.println(object.hashCode());
		System.out.println(System.identityHashCode(object));
		System.out.println(System.identityHashCode(null));

		//运行垃圾收集器
		System.gc();

		System.runFinalization();
		//退出程序
		System.exit(0);



	}

}
