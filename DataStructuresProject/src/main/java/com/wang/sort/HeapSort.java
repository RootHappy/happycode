package com.wang.sort;

import com.wang.datastructures.priorityqueue.BinaryHeap;
import com.wang.tools.DisplayData;
import com.wang.tools.GenerateData;

/**
 * 堆排序
 * 
 * @author wangyunlong
 *
 */
public class HeapSort {
	
	/**
	 * 堆排序：不使用相应的数据结构，不增加额外的数组
	 * 
	 * @param data
	 * @param length
	 */
	public static void headSortInner(int[] data, int length){
		//Build Heap
		for(int i = length / 2; i >= 0;i--) {
			perDown(data,i,length);
		}
		for(int i = length - 1; i >= 0; i--) {
			swap(data,0,i);
			perDown(data,0,i);
		}
	}
	
	/**
	 * 交换数组中两个位置的元素值
	 * @param data
	 * @param first
	 * @param second
	 */
	private static void swap(int[] data , int first , int second){
		int temp = data[first];
		data[first] = data[second];
		data[second] = temp;
	}
	
	/**
	 * 使得指定的节点i满足堆性质
	 * 
	 * @param data
	 * @param i
	 * @param length
	 */
	private static void perDown(int[] data , int i , int length){
		int child;
		int temp;
		for(temp = data[i]; i * 2 + 1 < length; i = child){
			child = 2 * i + 1;
			if(child != length - 1 && data[child + 1 ] > data[child]) {
				child++;
			}
			if(temp < data[child]){
				data[i] = data[child];
			}else
				break;
		}
		data[i] = temp; 
	}
	
	/**
	 * 使用堆数据结构排序后，复制到另一个数组内
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static int[] heapSort(int[] data , int length){
		BinaryHeap heap = new BinaryHeap(data.length);
		heap.buildHeap(data);
		int[] result = new int[data.length];
		for(int i = 0; i < data.length;i++ ){
			result[i] = heap.deleteMin();
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] data = GenerateData.generateData(10);
		DisplayData.display(data);
		headSortInner(data, data.length);
		DisplayData.display(data);
	}

}
