package com.wang.leetcode;

public class ReverseInteger {

	public static void main(String[] args) {
//		System.out.println(Integer.valueOf("9646324351"));
		System.out.println(Integer.MAX_VALUE);

		System.out.println(reverse(-2147483648));
	}

	public static int reverse(int x) {
		long src = x;
		StringBuilder result = new StringBuilder();
		if(src < 0) {
			result.append("-");
			src = -src;
		}
		int length = String.valueOf(src).length();
		for( int i = 0; i < length;  i++) {
			result.append(src % 10);
			src = src / 10;
		}
		long value =  Long.valueOf(result.toString());
		if(value > Integer.MAX_VALUE || value < Integer.MIN_VALUE)
			value = 0;
		return (int)value;
    }

}
