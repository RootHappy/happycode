package com.wang.datastructures.list;

/**
 * 栈的数组实现方式
 * 
 * @author wangyunlong
 *
 */
public class MyArrayStack {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	
	private static final int EMPTY_STACK = -1;
	
	private int[] elements;
	
	private int capacity;
	
	private int topOfStack = EMPTY_STACK;
	
	public MyArrayStack(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public MyArrayStack(int capacity){
		if(capacity < 1){
			throw new IllegalArgumentException("Initial capacity error: " + capacity);
		}
		this.elements = new int[capacity];
		this.capacity = capacity;
		this.topOfStack = EMPTY_STACK;
	}
	
	
	public boolean isEmpty(){
		return this.topOfStack == EMPTY_STACK;
	}
	
	public boolean isFull(){
		return this.topOfStack + 1 == capacity;
	}
	
	public void push(int value) throws Exception{
		if(isFull())
			throw new Exception("the stack is full.");
		this.elements[++topOfStack] = value;
	}
	
	public int pop()throws Exception{
		if(isEmpty())
			throw new Exception("the stack is empty");
		int result = this.elements[topOfStack];
		this.topOfStack--;
		return result;
	}
	
	public int top() throws Exception{
		if(isEmpty())
			throw new Exception("the stack is empty");
		return this.elements[topOfStack];
	}
	
	public static void main(String[] args) throws Exception{
		MyArrayStack stack = new MyArrayStack();
		stack.push(1);
		stack.push(23);
		stack.push(3);
		stack.push(32);
		stack.push(56);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
