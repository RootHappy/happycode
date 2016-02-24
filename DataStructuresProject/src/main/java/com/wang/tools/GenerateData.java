package com.wang.tools;

import java.util.Random;

/**
 * 生成数组数据
 * */
public class GenerateData {
	private static final int DEFAULT_SIZE = 1000;
	
	
	/**
	 * 生成n个元素的int数组
	 * @param n 需要的数组个数
	 * @return 生成的数组
	 * */
	public static int[] generateData(int n){
		int[] result = new int[n];
		Random random = new Random();
		for(int i = 0; i < n; i++) {
			result[i] = random.nextInt(5000);
			
		}
		return result;
	}
	
	/**
	 * 生成1000个元素的int数组
	 * @return 生成的数组
	 * */
	public static int[] generateData(){
		int[] result = new int[DEFAULT_SIZE];
		Random random = new Random();
		for(int i = 0; i < DEFAULT_SIZE; i++) {
			result[i] = random.nextInt(DEFAULT_SIZE * 10);
			
		}
		return result;
	}

}
