package com.wang.java.lang;

public class RuntimeDemo {

	public static void main(String[] args) {
		//获取Runtime实例
		Runtime runtime = Runtime.getRuntime();

		//1.内存
		runtime.gc();
		System.out.println(runtime.totalMemory());
		System.out.println(runtime.freeMemory());
		System.out.println(runtime.maxMemory()/1024 / 1024 / 1024);

		//2.加载
//		runtime.load("");
//		runtime.loadLibrary("");

		//3.处理器数目
		System.out.println(runtime.availableProcessors());

		runtime.runFinalization();
//		runtime.addShutdownHook(null);
//		runtime.removeShutdownHook(hook);

		runtime.exit(0);
	}

}
