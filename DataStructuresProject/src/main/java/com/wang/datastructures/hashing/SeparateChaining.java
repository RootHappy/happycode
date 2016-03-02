package com.wang.datastructures.hashing;

import java.util.LinkedList;

/**
 * 使用分离链表法解决冲突（Separate chaining）
 * 
 * @author wangyunlong
 *
 */
public class SeparateChaining<E> {
	
	private static final int INITIAL_CAPACITY = 17;
	
	private LinkedList<E>[] hastTable;
	
	private int size;
	
	public SeparateChaining(){
		this(INITIAL_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public SeparateChaining(int capacity){
		if( capacity < 1){
			throw new IllegalArgumentException("capacity is error.");
		}
		hastTable = new LinkedList[capacity];
		for(int i = 0; i < capacity; i++){
			hastTable[i] = new LinkedList<>();
		}
		this.size = capacity;
	}
	
	/**
	 * 查找key在表中的位置，返回对应的下表索引
	 * 
	 * @param key
	 * @return
	 */
	public int find(E key){
		int index;
		if(key.getClass() == Integer.class){
			index = HashFunction.hashInt((int)key, size);
		}else if(key.getClass() == String.class){
			index = HashFunction.hashStr((String)key, size);
		}else{
			index = HashFunction.hashObject(key, size);
		}
		return this.hastTable[index].indexOf(key);
	}
	
	/**
	 * 插入元素到表中，如果元素存在，则忽略。
	 * 
	 * @param key
	 */
	public void insert(E key){
		int index = find(key);
		if( index == -1){
			if(key.getClass() == Integer.class){
				index = HashFunction.hashInt((int)key, size);
			}else if(key.getClass() == String.class){
				index = HashFunction.hashStr((String)key, size);
			}else{
				index = HashFunction.hashObject(key, size);
			}
			this.hastTable[index].add(key);
		}
	}
	
	/**
	 * 删除指定元素
	 * 
	 * @param key
	 */
	public E remove(E key){
		int index = find(key);
		if( index != -1){
			int tableIndex;
			if(key.getClass() == Integer.class){
				tableIndex = HashFunction.hashInt((int)key, size);
			}else if(key.getClass() == String.class){
				tableIndex = HashFunction.hashStr((String)key, size);
			}else{
				tableIndex = HashFunction.hashObject(key, size);
			}
			return this.hastTable[tableIndex].remove(index);
		}
		return null;
	}
	
	public static void main(String[] args) {
		SeparateChaining<Integer> tables = new SeparateChaining<>();
		tables.insert(1);
		tables.insert(2);
		tables.insert(3);
		System.out.println(tables.remove(1));
		System.out.println(tables.remove(6));
	}
}
