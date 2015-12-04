package com.wang.java.lang;

import java.util.Set;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
		Set<Data> datas = new TreeSet<>();
		datas.add(new Data("2123","wang","www.wang.com"));
		datas.add(new Data("1112","wang","www.wang.com"));
		datas.add(new Data("3132","wang","www.wang.com"));
		System.out.println(datas.toString());
	}

	static class Data implements Comparable<Data>{
		String id;
		String name;
		String fieldName;

		public Data(String id , String name ,String fieldName) {
			this.id = id;
			this.name = name;
			this.fieldName = fieldName;
		}

		@Override
		public String toString() {
			return "id: " + id + " fieldName : " + fieldName + " name : " + name ;
		}

		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		@Override
		public int compareTo(Data o) {
			return this.id.compareTo(o.id);
		}
	}
}
