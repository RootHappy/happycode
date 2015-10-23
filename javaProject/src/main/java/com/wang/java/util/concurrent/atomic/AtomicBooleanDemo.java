package com.wang.java.util.concurrent.atomic;


import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

import sun.misc.Unsafe;


/**
 *java.util.concurrent.atomic.AtomicBoolean类内容
 *2015-10-23 15:00
 * */
public class AtomicBooleanDemo {

	@SuppressWarnings("unused")
	private String name ="zhang";

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		getUnsafeObject();
		//默认false
		AtomicBoolean flag = new AtomicBoolean();
		System.out.println(flag.get());
		//原子操作，通过提交期望值与当前内存中的值是否相同，来考虑是否使用新值
		flag.compareAndSet(true, true);
		System.out.println(flag.get());

	}


	/**
	 * 利用反射获取com.misc.Unsafe的实例
	 * */
	public static void getUnsafeObject() {
		try {
			@SuppressWarnings("unchecked")
			Class<Unsafe> clazz = (Class<Unsafe>) Class.forName("sun.misc.Unsafe");
			Field field =  clazz.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			Unsafe theUnsafe = (Unsafe)field.get(null);
			long value = theUnsafe.objectFieldOffset(AtomicBooleanDemo.class.getDeclaredField("name"));
			System.out.println(value);
			System.out.println(field.get(null));
			System.out.println(field.getName());
			System.out.println(field.getType());
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

}
