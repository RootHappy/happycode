package com.wang.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

	public static void main(String[] args) {
		System.out.println(romanToInt("MCDLXXVI"));
		int[] num = {0,0,0};
		Arrays.sort(num);
		for(int i = 0; i < num.length; i++)
			System.out.println(num[i]);

	}


	public static int romanToInt(String s) {
		if(s == null)
			return 0;
		int result = 0;
		Map<Character,Integer> maps = new HashMap<>();
		maps.put('I', 1);maps.put('V', 5);
		maps.put('X', 10);maps.put('L', 50);
		maps.put('C', 100);maps.put('D', 500);
		maps.put('M', 1000);
		for(int i = 0; i < s.length();i++) {
			char tempCh = s.charAt(i);
			if((i+1) < s.length()) {
				char nextCh = s.charAt(i+1);
				if( (tempCh =='I' && nextCh == 'V' ) || (tempCh =='I' && nextCh == 'X' )
						|| (tempCh == 'X' && nextCh == 'L')  || (tempCh == 'X' && nextCh == 'C')
						|| (tempCh == 'C' && nextCh == 'D') ||(tempCh == 'C' && nextCh == 'M')) {
					int firstNum = maps.get(tempCh);
					int secondNum = maps.get(nextCh);
					result = result + secondNum - firstNum;
					i++;
					continue;
				}
			}
			int num = maps.get(tempCh);
			result += num;
		}
		return result;
    }
}
