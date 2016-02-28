package com.wang.datastructures.list;

/**
 * 双向链表实现
 * */
public class MyDoublyLinkedList<E> {
	
	private Node<E> head = null;
	
	
	public MyDoublyLinkedList(){
		head = new Node<E>(null,null,null);
	}
	
	/**
	 * 在表的开头增加元素
	 * 
	 * @param value
	 */
	public boolean addFirst(E value){
		Node<E> node = new Node<E>(null,value,null);
		if(isEmpty()){
			this.head.next = node;
			node.previous = this.head;
		}else{
			node.previous =this.head;
			node.next = this.head.next;
			this.head.next.previous = node;
			this.head.next = node;
		}
		return true;
	}
	
	/**
	 * 在表尾增加一个元素
	 * 
	 * @param value
	 * @return
	 */
	public boolean addLast(E value){
		Node<E> lastNode = getLast();
		Node<E> currentNode = new Node<E>(lastNode,value,null);
		if(lastNode == null) {
			currentNode.previous = this.head;
			this.head.next = currentNode;
		}else{
			lastNode.next = currentNode;
		}
		return true;
	}
	
	/**
	 * 默认的增加方式
	 * 
	 * @param value
	 * @return
	 */
	public boolean add(E value){
		return addFirst(value);
	}
	
	/**
	 * 删除开头元素
	 * 
	 * @return
	 */
	public Node<E> removeFrst(){
		Node<E> deleteNode = this.head.next;
		if(deleteNode != null){
			deleteNode.previous.next = deleteNode.next;
			deleteNode.next.previous = deleteNode.previous;
		}
		return deleteNode;
	}
	
	/**
	 * 删除末尾的元素
	 * 
	 * 如果列表为空，怎么返回空
	 * @return
	 */
	public Node<E> removeLast(){
		Node<E> deleteNode = getLast();
		if(deleteNode != null){
			deleteNode.previous.next = null;
		}
		return deleteNode;
	}
	
	/**
	 * 默认删除方式
	 * 
	 * @return
	 */
	public Node<E> remove(){
		return removeFrst();
	}
	
	/**
	 * 查找指定元素的节点
	 * 
	 * 如果未找到则返回null
	 * 
	 * @param value
	 * @return
	 */
	public Node<E> get(E value){
		Node<E> result = this.head.next;
		while(result != null && result.element.equals(value)){
			result = result.next;
		}
		return result;
	}
	
	/**
	 * 返回末尾元素
	 * 如果为空则返回null
	 * @return
	 */
	public Node<E> getLast(){
		if(isEmpty())
			return null;
		Node<E> current = this.head;
		while(current.next != null) {
			current = current.next;
		}
		return current;
	}
	
	/**
	 * 判断是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return this.head.next == null;
	}
	
	/**
	 * 打印链表元素
	 * */
	public void display(){
		System.out.println("Current data: ");
		Node<E> index = this.head.next;
		while(index != null) {
			System.out.print(index.element + " ");
			index = index.next;
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		MyDoublyLinkedList<Integer> list = new MyDoublyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.display();
	}
	
	private static class Node<E>{
		E element;
		Node<E> next;
		Node<E> previous;
		
		Node(Node<E> previous, E value, Node<E> next) {
			this.element = value;
			this.previous = previous;
			this.next = next;
		}
	}

}
