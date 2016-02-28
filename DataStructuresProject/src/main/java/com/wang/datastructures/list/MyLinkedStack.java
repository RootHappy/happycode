package com.wang.datastructures.list;

/**
 * 栈：限制插入和删除只能在一个位置上进行的表
 * 
 * LIFO
 * @author wangyunlong
 *
 */
public class MyLinkedStack {
	
	private Node head;
	
	public MyLinkedStack(){
		head = new Node();
	}
	
	
	/**
	 * 栈是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return head.next == null;
	}
	
	/**
	 * 栈空
	 * */
	public void makeEmpty(){
		this.head = null;
	}
	
	/**
	 * 出栈
	 * 
	 * @return
	 */
	public int pop(){
		Node node = null;
		if(!isEmpty()){
			node = this.head.next;
			this.head.next = node.next;
			return node.element;
		}else{
			return -1;
		}
	}
	
	/**
	 * 入栈
	 * 
	 * @param value
	 */
	public void push(int value){
		Node node = new Node(value,this.head.next);
		this.head.next = node;
	}
	
	private static class Node{
		int element;
		Node next;
		
		Node(){
			this(-1,null);
		}
		
		Node(int element , Node next){
			this.element = element;
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		MyLinkedStack stack = new MyLinkedStack();
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
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
