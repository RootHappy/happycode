package com.wang.bean;

public class User {
	public enum Gender {MALE,FEMALE}

	public static class Name  {
		private String first;
		private String last;
		public String getFirst() {
			return first;
		}
		public String getLast() {
			return last;
		}
		public void setFirst(String first) {
			this.first = first;
		}
		public void setLast(String last) {
			this.last = last;
		}
		@Override
		public String toString() {
			return first + " " + last;
		}
	}

	private Name name;
	private Gender gender;
	private boolean verified;
	private String userImage;
	public Name getName() {
		return name;
	}
	public Gender getGender() {
		return gender;
	}
	public boolean isVerified() {
		return verified;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	@Override
	public String toString() {
		return "name: " + name + "\ngender:  " + gender + "\nverified: " + verified + "\nuserImage: " + userImage;
	}
}
