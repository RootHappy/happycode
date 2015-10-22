package com.wang.bean;

public class User {
	private String name;
	private String password;
	private String phone;
	private String email;
	private String gender;
	private String birthday;
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Name: " + getName() + " Gender: " + getGender() + " Email: " + getEmail()  + " BirthDay: "
				+ getBirthday() + " Password: " + getPassword() + " Phone: " + getPhone();
	}
}
