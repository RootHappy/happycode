package com.wang.datastructures.priorityqueue;

import com.wang.tools.DisplayData;

/**
 * 结构性：堆是一颗被完全填满的二叉树，有可能的例外在底层，
 * 			底层上的元素从左到右填入，这样的树被称为完全二叉树。
 * 堆序性：使操作被快速执行的性质，任一节点都小于(或大于)其父节点（根节点除外）
 * 
 * 最小堆
 * */
public class BinaryHeap {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	private static final int MIN_VALUE = Integer.MIN_VALUE;
	
	private int[] elements;
	
	private int capacity;
	
	private int size = 0;
	
	public BinaryHeap(){
		elements = new int[DEFAULT_INITIAL_CAPACITY + 1];
		elements[0] = MIN_VALUE;
		capacity = DEFAULT_INITIAL_CAPACITY;
		size = 0;
	}
	
	public BinaryHeap(int maxElements){
		if(maxElements < 1) {
			throw new IllegalArgumentException("capacity is less than one");
		}
		elements = new int[maxElements + 1];
		elements[0] = MIN_VALUE;
		capacity = maxElements;
		size = 0;
	}
	
	/**
	 * 插入元素到堆中
	 * */
	public void insert(int value) {
		if(isFull()){
			throw new IllegalStateException("堆满");
		}
		int i;
		for(i = ++size; elements[i / 2] > value; i /= 2) {
			elements[i] = elements[ i / 2];
		}
		elements[i] = value;
	}
	
	public void buildHeap(int[] data){
		if(data.length > capacity) {
			throw new IllegalStateException();
		}
		int temp , child , k;
		for(int i = data.length / 2; i >= 0; i--) {
			temp = data[i];
			for(k = i; k * 2 + 1 < data.length;k = child) {
				child = k * 2 + 1;
				if( child != data.length -1 && data[child] > data[child +1]) {
					child ++;
				}
				if( temp > data[child]){
					data[k] = data[child]; 
				}else{
					break;
				}
			}
			data[k] =temp; 
		}
		System.arraycopy(data, 0, this.elements, 1, data.length);
		this.size = data.length;
		DisplayData.displayPart(this.elements,this.size +1);
	}
	
	/**
	 * 删除最小的元素
	 * */
	public int deleteMin(){
		if(isEmpty()){
			throw new IllegalStateException("堆为空");
		}
		int minElement = elements[1];
		int lastElement = elements[size--];
		int child , i;
		for(i = 1; i * 2 <= size;i = child) {
			child = i * 2;
			if(child != size && elements[child] > elements[child+1]) {
				child++;
			}
			if(lastElement > elements[child]){
				elements[i] = elements[child];
			}else
				break;
		}
		elements[i] = lastElement;
		return minElement;
	}
	
	/**
	 * Priority Queue is Full.
	 * */
	public boolean isFull(){
		return this.size == this.capacity;
	}
	
	/**
	 * Priority Queue is Empty.
	 * */
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public static void main(String[] args) {
//		int[] datas = GenerateData.generateData(9);
		int[] datas = {1192 ,3879 ,1844 ,1412 ,2148 ,3486 ,4279 ,2325 };
		BinaryHeap binaryHeap = new BinaryHeap(datas.length);
		binaryHeap.buildHeap(datas);
		System.out.println(binaryHeap.deleteMin());
		System.out.println(binaryHeap.deleteMin());
		System.out.println(binaryHeap.deleteMin());
		System.out.println(binaryHeap.deleteMin());
		System.out.println(binaryHeap.deleteMin());
		System.out.println(binaryHeap.deleteMin());
		System.out.println(binaryHeap.deleteMin());
		System.out.println(binaryHeap.deleteMin());
		
		
//		DisplayData.displayPart(binaryHeap.elements,binaryHeap.size);
	}
}