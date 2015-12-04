package com.wang.leetcode;


public class RemoveElement {

	public static void main(String[] args) {
		int[] nums = {4,5};
		
		System.out.println(removeElement(nums,4));
	}

	public static  int removeElement(int[] nums, int val) {
		int length = nums.length;
		int start = 0;
		for(int i = 0; i < length; i++) {
			if(nums[i] != val) {
				nums[start++] = nums[i];
			}
		}
		return start;
    }
}
