package com.wang.sort;

import com.wang.datastructures.list.MyLinkedList;
import com.wang.tools.DisplayData;

/**
 * 基数排序：使用多趟桶式排序,从低位开始实施多次桶式排序
 * 
 * 桶式排序的次数与位置有关
 * 
 * 时间复杂度：O( P(N + B)) P是排序的趟数，N是被排序的元素个数，B桶数
 * 
 * @author wangyunlong
 *
 */
public class RadixSort {
	
	/**
	 * 用链表实现基数排序
	 * @param data
	 * @param digit
	 */
	public static void redixSortByLinked(Integer[] data , int digit){
		@SuppressWarnings("unchecked")
		MyLinkedList<Integer>[] lists = new MyLinkedList[10];
		
		int index = 0;
		
		for(int i = 0; i < 10; i++) {
			lists[i] = new MyLinkedList<Integer>();
		}
		for(int i = 0; i < digit; i++){
			for(int j = 0; j < data.length; j++){
				int currentDigit = (data[j] /(int)Math.pow(10, i)) % 10;
				lists[currentDigit].addLast(data[j]);
			}
			for(int j = 0; j < 10; j++ ) {
				Integer[] temp = lists[j].toArray(new Integer[0]);
				System.arraycopy(temp, 0, data, index, temp.length);
				index += temp.length;
				lists[j].clear();
			}
			index = 0;
			
		}
		
	}
	
	
	/**
	 * 用数组实现基数排序
	 * @param data
	 * @param digit
	 */
	public static void radixSort(int[] data , int digit){
		
		int[][] bucket = new int[10][data.length];
		
		int[] numbers = new int[10];
		
		int index = 0;
		
		for(int i = 0; i < digit; i++) {
			for(int j = 0; j < data.length; j++) {
				int currentDigit = (data[j] /(int)Math.pow(10, i)) % 10;
				bucket[currentDigit][numbers[currentDigit]] = data[j];
				numbers[currentDigit]++;
			}
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k < numbers[j];k++){
					data[index++] = bucket[j][k];
				}
				numbers[j] = 0;
			}
			index = 0;
		}
	}
	
	 public static int[] toPrimitive(Integer[] array) {
	        if (array == null) {
	            return null;
	        } else if (array.length == 0) {
	            return new int[0];
	        }
	        final int[] result = new int[array.length];
	        for (int i = 0; i < array.length; i++) {
	            result[i] = array[i].intValue();
	        }
	        return result;
	    }
	
	
	public static void main(String[] args) {
		Integer[] data = {100,999,300,25,1,0,23,56,123,456,89};
		RadixSort.redixSortByLinked(data, 3);
		DisplayData.display(toPrimitive(data));
	}

}
