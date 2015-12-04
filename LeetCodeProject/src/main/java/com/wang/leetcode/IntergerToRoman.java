package com.wang.leetcode;

import java.util.ArrayList;
import java.util.List;


public class IntergerToRoman {

	public static void main(String[] args) {
		System.out.println(intToRoman(3999));
		List<Integer> element = new ArrayList<Integer>();


	}

	public static String intToRoman(int num) {
        if(num <=0 || num > 3999)
            return "";
        StringBuilder result = new StringBuilder();
        String[] romanNumeralBasic = {"I","V","X","L","C","D","M"};
        String[] currentNum = new String[3];
        int zeroNum = 0;
        while(num != 0) {
            int temp = num % 10;
            if(zeroNum == 3) {
                for(int i = 0; i < temp; i++) {
                    result.insert(0,"M");
                }
                return result.toString();
            }
            int start = zeroNum * 2;
            for(int i = 0; i < 3; i++) {
            	currentNum[i] = romanNumeralBasic[i+start];
            }
            if(temp < 4) {
               for(int i = 0; i < temp; i++) {
                   result.insert(0,currentNum[0]);
               }
            }else if(temp == 4) {
                result.insert(0,currentNum[1]);
                result.insert(0,currentNum[0]);
            }else if(temp < 9) {
                for(int i = 0; i < temp - 5; i++) {
                   result.insert(0,currentNum[0]);
                }
                result.insert(0,currentNum[1]);
            }else if(temp == 9){
                result.insert(0,currentNum[2]);
                result.insert(0,currentNum[0]);
            }
            zeroNum++;
            num = num / 10;
        }
        return result.toString();
    }

}
