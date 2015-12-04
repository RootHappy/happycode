package com.wang.java.lang;

public class MathDemo {

	public static void main(String[] args) {
		//常量
		System.out.println("Math.E : "+Math.E);
		System.out.println("Math.PI : "+Math.PI);
		//绝对值
		System.out.println("Math.abs() : " + Math.abs(-2.1));
		//立方根 平方根
		System.out.println("Math.cbrt() : " + Math.cbrt(8));
		System.out.println("Math.sqrt() : " + Math.sqrt(4));

		//大小
		System.out.println("Math.max() : " +Math.max(2.1, 2.2));
		System.out.println("Math.min() : " +Math.min(1, 0));

		//三角函数
		System.out.println("Math.sin() : " + Math.sin(0));
		System.out.println("Math.sin() : " + Math.sin(Math.PI / 2.0));
		System.out.println("Math.sin() : " + Math.sin(Math.PI));
		System.out.println("-----------------------------------------------");
		System.out.println("Math.cos() : " + Math.cos(0));
		System.out.println("Math.cos() : " + Math.cos(Math.PI / 2.0));
		System.out.println("Math.cos() : " + Math.cos(Math.PI));
		System.out.println("-----------------------------------------------");
		System.out.println("Math.tan() : " + Math.tan(0));
		System.out.println("Math.tan() : " + Math.tan(Math.PI /2.0 ));
		System.out.println("Math.tan() : " + Math.tan(Math.PI));
		System.out.println("-----------------------------------------------");
		//小数舍入
		System.out.println("Math.ceil() : " + Math.ceil(0));
		System.out.println("Math.ceil() : " + Math.ceil(0));
		System.out.println("Math.ceil() : " + Math.ceil(-0.1));
		System.out.println("Math.ceil() : " + Math.ceil(-1.2));
		System.out.println("Math.ceil() : " + Math.ceil(2.8));
		System.out.println("Math.ceil() : " + Math.ceil(2.3));
		System.out.println("Math.floor() : " + Math.floor(2.3));
		System.out.println("Math.floor() : " + Math.floor(2.8));

		//e x
		System.out.println(Math.exp(3));
		System.out.println(Math.pow(Math.E, 3));

		//sqrt(x2 +y2)
		System.out.println(Math.hypot(1, 1));

		System.out.println(Math.random());
		System.out.println(Math.rint(2.5));
		System.out.println(Math.round(2.5));
		System.out.println(Math.round(2.4));

		System.out.println(Math.signum(3.4));
		System.out.println(Math.signum(-3.4));
		System.out.println(Math.signum(0));

		System.out.println(Math.ulp(2.1));

		System.out.println(Math.nextUp(2.3));
		System.out.println(Math.nextUp(2.8));
		System.out.println(Math.nextAfter(2.3, 1));
	}

}
