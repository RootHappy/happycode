package com.wang.tools;

/**
 * 打印数据，显示到控制台
 * 2012-02-23
 * */
public class DisplayData {
	
	/**
	 * 按照顺序显示数组中的元素
	 * */
	public static void display(int[] data){
		System.out.print("Current Data :");
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	public static void displayPart(int[] data,int size){
		if(size > data.length){
			size = data.length;
		}
		System.out.print("Current Data :");
		for(int i = 0; i < size; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

}
