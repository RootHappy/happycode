package com.wang.datastructures.hashing;

/**
 * 使用平方探测处理冲突
 * H[i] = ( Hash(x) + F(i)) mod tableSize; 且 F(0) = 0
 * 
 * F{i) = i * i;
 * 
 * F(i) = F(i -1) + 2i - 1
 * 
 * @author wangyunlong
 *
 * @param <E>
 */
public class OpenAddressingHashing<E> {

	private static final int INITIAL_CAPACITY = 17;
	
	private HashEntry<E>[] tables;
	
	private int size;
	
	public OpenAddressingHashing(){
		this(INITIAL_CAPACITY);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OpenAddressingHashing(int capacity){
		if(capacity < 1){
			throw new RuntimeException("capacity is error");
		}
		tables = new HashEntry[capacity];
		for(int i = 0;i < capacity; i++){
			tables[i] = new HashEntry(null, KindOfEntry.Empty);
		}
		this.size = capacity;
	}
	
	/**
	 * 查找指定元素
	 * 
	 * @param key
	 * @return
	 */
	public int find(E key) {
		int index;
		int collisionNum = 0;
		if(key.getClass() == Integer.class){
			index = HashFunction.hashInt((int)key, size);
		}else if(key.getClass() == String.class){
			index = HashFunction.hashStr((String)key, size);
		}else{
			index = HashFunction.hashObject(key, size);
		}
		while(this.tables[index].info != KindOfEntry.Empty 
				&& this.tables[index].element != null 
				&&!this.tables[index].element.equals(key)){
			index +=  2 * ++collisionNum - 1;
			if(index >= size){
				index -= size;
			}
		}
		return index;
	}
	
	/**
	 * 添加指定元素
	 * 
	 * @param key
	 */
	public boolean insert(E key){
		int index = find(key);
		if(this.tables[index].info != KindOfEntry.Legitimate){
			this.tables[index].info = KindOfEntry.Legitimate;
			this.tables[index].element = key;
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param key
	 */
	public boolean remove(E key){
		int index = find(key);
		if(this.tables[index].info == KindOfEntry.Legitimate){
			this.tables[index].info = KindOfEntry.Deleted;
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		OpenAddressingHashing<Integer> list = new OpenAddressingHashing<>();
		list.insert(1);
		System.out.println(list.remove(2));
	}
	
	private static class HashEntry<E>{
		private E element;
		private KindOfEntry info;
		public HashEntry(E value , KindOfEntry info){
			this.element = value;
			this.info = info;
		}
	}
	
	enum KindOfEntry{
		Legitimate, Empty,Deleted;
	}

}
