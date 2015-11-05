package com.wang.annotations;

public class Person {
	@PersonName("wangyunlong")
	private String name;
	@PersonGender
	private String gender;
	@PersonBirthday(year="2016")
	private String birthday;
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
