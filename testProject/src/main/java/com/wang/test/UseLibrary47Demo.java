package com.wang.test;

import java.util.Random;

public class UseLibrary47Demo {

	static Random random = new Random();

	public static void main(String[] args) {
		int n = 2 * (Integer.MAX_VALUE / 3);
		int low = 0;
		for(int i =0; i < 1000000; i++)
			if(random(n) < n / 2)
				low++;
		System.out.println(low);

		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;

		for(int i = 0; i < 10000; i++){
			int value =  random(3);
			if(value == 0) {
				count1++;
			}else if(value ==1) {
				count2++;
			}else if(value ==2) {
				count3++;
			}else if(value ==3) {
				count4++;
			}
		}

		System.out.println("0: " + count1 + " 1: " + count2 + " 2: " + count3 + " 3: " + count4);
	}

	static int random(int n) {
		return Math.abs(random.nextInt()) % n;
	}

}
