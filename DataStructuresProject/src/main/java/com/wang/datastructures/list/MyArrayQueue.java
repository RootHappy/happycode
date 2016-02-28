package com.wang.datastructures.list;

/**
 * 队列：使用队列时插入在一端进行而删除在另一端进行
 * 
 * @author wangyunlong
 *
 */
public class MyArrayQueue {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 20;

	private int[] elements;
	
	private int capacity;
	
	@SuppressWarnings("unused")
	private int front;
	
	private int rear;
	
	private int size;
	
	public MyArrayQueue(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public MyArrayQueue(int initialCapacity){
		elements = new int[initialCapacity];
		size = 0;
		capacity = initialCapacity;
		front = 1;
		rear = 0;
	}
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public boolean isFull(){
		return this.size == capacity - 1;
	}
	
	public void enqueue(int value)throws Exception{
		if(isFull()){
			throw new Exception("队列已满");
		}
		this.size++;
		this.rear = succ(this.rear);
		elements[this.rear] = value;
	}
	
	public void dequeue(){
		
	}
	
	/**
	 * 返回下次尾部位置
	 * 
	 * @param value
	 * @return
	 */
	private int succ(int value){
		if(++value == this.capacity) {
			value = 0;
		}
		return value;
	}
	
	public static void main(String[] args) {

	}

}
