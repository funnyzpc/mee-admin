package com.mee.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author funnyzpc
 * @description oracle 数据库映射使用
 */
public class MapOrclBean<K, V> extends HashMap<K, V> {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化
	 */
	public MapOrclBean() {
		super();
	}

	/**
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public MapOrclBean(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * @param initialCapacity
	 */
	public MapOrclBean(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * @param m
	 */
	public MapOrclBean(Map<? extends K, ? extends V> m) {
		super(m);
	}
	
	/**
	 * Only for castor
	 * @return
	 */
	public Map getMap(){
		return this;
	}

	/* (non-Javadoc)
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	@Override
	public V get(Object key) {
		Object key1 = key; 
		if (key instanceof String){
			key1 = ((String)key).toLowerCase();
		}
		return super.get(key1);
	}

	/* (non-Javadoc)
	 * @see java.util.HashMap#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		Object key1 = key; 
		if (key instanceof String){
			key1 = ((String)key).toLowerCase();
		}
		return super.containsKey(key1);
	}

	/* (non-Javadoc)
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		K key1 = key; 
		if (key instanceof String){
			key1 = (K)((String)key).toLowerCase();
		}
		return super.put(key1, value);
	}

	/* (non-Javadoc)
	 * @see java.util.HashMap#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		Object key1 = key; 
		if (key instanceof String){
			key1 = ((String)key).toLowerCase();
		}
		return super.remove(key1);
	}
}
