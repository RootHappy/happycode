package com.wang.command;

public class Stereo {
	String name;

	public Stereo() {
		name = "";
	}

	public Stereo(String name) {
		this.name = name;
	}

	public void on(){
		System.out.println(name + " stereo is on");
	}

	public void off(){
		System.out.println(name + " stereo is off");
	}


	public void setCd() {
		System.out.println(name +" stereo is set for CD input");
	}

	public void setDvd(){
		System.out.println(name + " stereo is set for Dvd input");
	}

	public void setRadio(){
		System.out.println(name + "stereo is set for Radio input");
	}

	public void setVolume(int value) {
		System.out.println(name + " stereo volume set ot " + value);
	}
}
