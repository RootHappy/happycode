package com.wang.strategy;

public class Quack implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("Quack");
	}

}
