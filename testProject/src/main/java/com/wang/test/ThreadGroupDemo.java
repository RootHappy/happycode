package com.wang.test;

public class ThreadGroupDemo {
	public static void main(String[] args) {
		ThreadGroup g = new ThreadGroup("wangyunlong");

		System.out.println(g.getName());
		System.out.println(g.getParent());
		System.out.println(g.getMaxPriority());
		System.out.println(g.isDaemon());
		System.out.println(g.isDestroyed());
	}

}
