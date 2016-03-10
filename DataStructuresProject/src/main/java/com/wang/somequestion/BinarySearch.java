package com.wang.somequestion;

/**
 * 二分查找算法
 * 
 * @author wangyunlong
 *
 */
public class BinarySearch {
	
	public static int binarySearch(int[] data, int length, int x){
		int low = 0;
		int high = length -1;
		int mid;
		while(low <= high) {
			mid = (low + high) / 2;
			if( data[mid] < x){
				low = mid + 1;
			}else if( data[mid] > x) {
				high = mid -1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int data[] = {1, 2, 3, 4, 5, 6, 7, 8};
		System.out.println(binarySearch(data, data.length, 8));
	}
	
}
