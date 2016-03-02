package com.wang.sort;

import com.wang.tools.DisplayData;
import com.wang.tools.GenerateData;

/**
 * 归并排序：将数据分为左右两部分，对左右两部分分别排序后合并
 * 
 * 基本操作是合并两个有序的数组
 * 
 * O(N Log N)
 * 
 * @author wangyunlong
 *
 */
public class MergeSort {

	public static void mergeSort(int[] data, int length){
		int[] tempArr = new int[length];
		mSort(data, tempArr, 0, length - 1);
	}
	
	private static void mSort(int[] data, int[] tempArr, int left , int right){
		int center;
		
		if( left < right) {
			center = ( left + right ) / 2;
			mSort(data, tempArr, left, center);
			mSort(data, tempArr, center + 1, right);
			merge(data,tempArr,left,center + 1, right);
		}
	}
	
	/**
	 * 合并两个排序后的部分
	 * 
	 * @param data
	 * @param tempArr 中间变量数组，防止递归同时存在过多的小数组
	 * @param leftPos
	 * @param rightPos
	 * @param rightEnd
	 */
	private static void merge(int[] data, int[] tempArr,int leftPos, int rightPos,int rightEnd){
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements  = rightEnd - leftPos + 1;
		while( leftPos <= leftEnd && rightPos <= rightEnd){
			if(data[leftPos] <= data[rightPos]){
				tempArr[tempPos++] = data[leftPos++];
			}else{
				tempArr[tempPos++] = data[rightPos++];
			}
		}
		while(leftPos <= leftEnd){
			tempArr[tempPos++] = data[leftPos++];
		}
		while(rightPos <= rightEnd){
			tempArr[tempPos++] = data[rightPos++];
		}
		for(int i = 0; i < numElements;i++,rightEnd--){
			data[rightEnd] = tempArr[rightEnd];
		}
	}
	
	public static void main(String[] args) {
		int[] data = GenerateData.generateData(10);
		MergeSort.mergeSort(data, data.length);
		DisplayData.display(data);
	}
	
}
