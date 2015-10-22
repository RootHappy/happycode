package com.wang.java.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * java.lang.ref包中的内容
 * 引用分为四种：强引用、软引用（SoftReference）、弱引用(WeakReference)、虚引用(PhantomReference)
 *	1.强引用不会被GC回收，并且在java.lang.ref里也没有实际的对应类型
 *	2.软引用在JVM报告内存不足的时候才会被GC回收，否则不会回收
 *	3.当GC一但发现了弱引用对象，将会释放WeakReference所引用的对象
 *	4.当GC一但发现了虚引用对象，将会将PhantomReference对象
 *		插入ReferenceQueue队列，而此时PhantomReference所指向的对象
 *		并没有被GC回收，而是要等到ReferenceQueue被你真正的处理后才会被回收
 * */
public class ReferenceDemo {

	public static void fourTypeReference() {
		//强引用
		Object obj = new Object();
		System.out.println(obj);
		//软引用
		SoftReference<Object> soft = new SoftReference<Object>(obj);
		System.out.println(soft.get());
		//弱引用
		WeakReference<Object> weak = new WeakReference<Object>(obj);
		System.out.println(weak.get());
		//虚引用
		ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
		PhantomReference<Object> phanRef = new PhantomReference<Object>(obj, refQueue);
		System.out.println(phanRef.get());	//返回null
	}

	//弱引用测试代码
	/**
	 * 通过调用weakRef.get()我们得到了obj对象，
	 * 由于没有执行GC,因此refQueue.poll()返回的null，
	 * 当我们把obj = null;此时没有引用指向堆中的obj对象了，
	 * 这里JVM执行了一次GC，我们通过weakRef.get()发现返回了null，
	 * 而refQueue.poll()返回了WeakReference对象，
	 * 因此JVM在对obj进行了回收之后，
	 * 才将weakRef插入到refQueue队列中。
	 * */
	public static void weakReferenceTest() {
		Object obj = new Object();
		ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
		WeakReference<Object> weakRef = new WeakReference<Object>(obj,refQueue);
		System.out.println(weakRef.get());
		System.out.println(refQueue.poll());
		obj = null;
		System.gc();
		System.out.println(weakRef.get());
		System.out.println(refQueue.poll());
	}

	public static void phantomReferenceTest() {
		Object obj = new Object();
		ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
		PhantomReference<Object> phantomRef = new PhantomReference<Object>(obj, refQueue);
		System.out.println(phantomRef.get());
		System.out.println(refQueue.poll());
		obj = null;
		System.gc();
		System.out.println(phantomRef.get());
		System.out.println(refQueue.poll());
	}

	public static void main(String[] args) {
		fourTypeReference();
		weakReferenceTest();
		phantomReferenceTest();
	}

}
