package com.wang.datastructures.list;

import java.util.Arrays;

/**
 * 表：A[1],A[2]....A[N]
 * 
 * 使用数组实现表的功能
 * 
 * */
public class MyArrayList<E> {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	
	 private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	private Object[] elements;
	
	private int size = 0;
	
	public MyArrayList(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public MyArrayList(int initialCapacity){
		if(initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		}
		this.elements = new Object[initialCapacity];
	}
	
	/**
	 * 返回列表大小
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 增加特定的元素到末尾
	 * 
	 * @param value
	 * @return
	 */
	public boolean add(E value){
		ensureCapacityInterna(size + 1);
		this.elements[size++] = value;
		return true;
	}
	
	/**
	 * 增加元素到指定位置
	 * 
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean add(int index,E value){
		rangeCheckForAdd(index);
		ensureCapacityInterna(size + 1);
		System.arraycopy(elements, index, elements, index + 1, size - index);
		size++;
		return true;
	}
	
	/**
	 * 删除指定位置的元素
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		rangeCheck(index);
		@SuppressWarnings("unchecked")
		E oldValue = (E)this.elements[index];
		
		int numMoved = size - index - 1;
		if (numMoved > 0)
            System.arraycopy(elements, index+1, elements, index,numMoved);
		elements[--size] = null; // Let gc do its work
		return oldValue;
	}
	
	/**
	 * 删除指定值的元素
	 * 
	 * @param o
	 * @return
	 */
	public boolean remove(Object o){
		if(o == null) {
			for (int index = 0; index < size; index++)
		        if (elements[index] == null) {
		            fastRemove(index);
		            return true;
		        }
		}else{
			for(int index = 0; index < size; index++) {
				if(o.equals(elements[index])){
					fastRemove(index);
					return true;
				}
			}
		}
		return false;
	}
	
	private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index+1, elements, index,
                             numMoved);
        elements[--size] = null; // Let gc do its work
    }
	
	 private void rangeCheck(int index) {
	        if (index >= size)
	            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	 }
	
	private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
	
	private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
	
	private void ensureCapacityInterna(int minCapacity){
		if (minCapacity - elements.length > 0){
			grow(minCapacity);
		}
	}
	
	private void grow(int minCapacity) {
		int oldCapacity = elements.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0)
			 newCapacity = minCapacity;
		if (newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
		elements = Arrays.copyOf(elements, newCapacity);
	}
	
	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
	            MAX_ARRAY_SIZE;
	}
	
	public static void main(String[] args) {
	}

}
