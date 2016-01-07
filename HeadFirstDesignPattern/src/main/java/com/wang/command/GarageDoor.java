package com.wang.command;

public class GarageDoor {
	String name;

	public GarageDoor() {
		name ="";
	}

	public GarageDoor(String name){
		this.name = name;
	}

	public void up() {
		System.out.println(name + " Garage Door is open");
	}

	public void down() {
		System.out.println( name + " Garage Door is close");
	}

}
