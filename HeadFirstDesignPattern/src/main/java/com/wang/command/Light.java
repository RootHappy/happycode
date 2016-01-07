package com.wang.command;

public class Light {
	String name;

	public Light(){
		name = "";
	}

	public Light(String name){
		this.name = name;
	}

	public void on() {
		System.out.println(name + " Light is on!");
	}

	public void off() {
		System.out.println(name + " Light is off!");
	}
}
