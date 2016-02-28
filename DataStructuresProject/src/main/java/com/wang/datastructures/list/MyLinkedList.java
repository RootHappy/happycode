package com.wang.datastructures.list;

/**
 * 使用链表来实现表的功能
 * 
 * 链表：由一系列不必在内存中连续存储的结构组成
 * */
public class MyLinkedList<E>{
	
	private Node<E> head;		//头节点
	
	int size = 0;
	
	
	public MyLinkedList() {
		head = new Node<E>(null,null);
	}
	
	/**
	 * 判断表是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return this.head.next == null;
	}
	
	/**
	 * 判断该节点是否是最后一个节点
	 * */
	public boolean isLast(Node<E> node){
		Node<E> last = getLast();
		if(last == null) {
			return node == null;
		}
		return node.element.equals(last.element)&& node.next == null;
	}
	
	/**
	 * 查找给定值的节点
	 * 
	 * @param value
	 * @return
	 */
	public Node<E> get(E value){
		Node<E> currentNode = this.head.next;
		while(currentNode != null && currentNode.element != value) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	/**
	 * 删除指定位置元素
	 * @param index
	 * @return
	 */
	public Node<E> remove(int index){
		Node<E> deleteNode = null;
		Node<E> preNode = getNode(index - 1);
		if(!isLast(preNode)) {
			deleteNode = preNode.next;
			preNode.next = deleteNode.next;
		}
		size--;
		return deleteNode;
	}
	
	/**
	 * 删除第一个他元素
	 * @return
	 */
	public Node<E> removeFirst(){
		Node<E> currentNode = this.head.next;
		if(this.head.next != null) {
			this.head.next = currentNode.next;
		}
		size--;
		return currentNode;
	}
	
	/**
	 * 默认删除第一个元素
	 * @return
	 */
	public Node<E> remove(){
		return removeFirst();
	}
	
	/**
	 * 返回给定元素值的前一个节点
	 * 
	 * @param value
	 * @return
	 */
	public Node<E> getPreviouse(E value){
		Node<E> result = this.head;
		while(result.next != null && !result.next.element.equals(value)){
			result = result.next;
		}
		return result;
	}
	
	public Node<E> getNode(int index){
		Node<E> currentNode = this.head;
		for(int i = 0; i <= index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	/**
	 * 查找表的最后一个元素
	 * 
	 * 如果表为空，则null
	 * @return
	 */
	public Node<E> getLast(){
		Node<E> preIndex = null;
		Node<E> index = this.head.next;
		while(index != null){
			preIndex = index;
			index = index.next;
		}
		return preIndex;
	}
	
	/**
	 * 链表头部增加一个元素
	 * 
	 * @param value
	 */
	public void addFirst(E value){
		Node<E> node = new Node<E>(value,this.head.next);
		this.head.next = node;
		size++;
	}
	
	/**
	 * 链表尾部插入
	 * */
	public void addLast(E value){
		Node<E> lastNode = getLast();
		Node<E> node = new Node<E>(value,null);
		if(lastNode != null){
			lastNode.next = node;
		}else{
			this.head.next = node; 
		}
		size++;
	}
	
	/**
	 * 增加指定元素，默认方式为载开头添加
	 * @param value
	 */
	public void add(E value){
		addFirst(value);
	}
	/**
	 * 在指定位置增加特定的值
	 * 
	 * @param index
	 * @param value
	 */
	public void add(int index , E value){
		if(index > size){
			throw new RuntimeException("index position greater than size of the list");
		}
		Node<E> node = new Node<E>(value,null);
		Node<E> preNode = getNode(index);
		if(preNode != null){
			node.next = preNode.next;
			preNode.next = node;
		}else{
			addLast(value);
		}
		size++;
	}
	
	/**
	 * 清空表
	 */
	public void clear() {
		for (Node<E> x = this.head; x != null;) {
            Node<E> next = x.next;
            x.element = null;
            x.next = null;
            x = next;
        }
        size = 0;
	}
	
	/**
	 * 转换成数组
	 * @return
	 */
	public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = this.head.next; x != null; x = x.next)
            result[i++] = x.element;
        return result;
    }
	
	@SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[])java.lang.reflect.Array.newInstance(
                                a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = this.head.next; x != null; x = x.next)
            result[i++] = x.element;

        if (a.length > size)
            a[size] = null;

        return a;
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
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(4);
		list.addFirst(5);
		list.addFirst(6);
		list.addFirst(7);
		list.addFirst(8);
		list.addFirst(9);
		list.addFirst(10);
		list.addFirst(11);
		list.addFirst(12);
		list.addFirst(13);
		list.addFirst(14);
		list.display();
	}
	
	/**
	 * 链表节点数据结构
	 * */
	private static class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E i,Node<E> next){
			this.element = i;
			this.next = next;
		}
	}

}
