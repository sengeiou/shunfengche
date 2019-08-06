package com.windmillsteward.jukutech.customview.AssortPinyin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 键值对索引排序的工具类 HashMap简单排序的一种实现
 * 
 * @author cyq
 * 
 * @param <K>
 * @param <V>
 */
public class HashList<K, V> {
	/**
	 * 健集合
	 */
	private List<K> keyArr = new ArrayList<K>();
	/**
	 * 健值對映射
	 */
	private HashMap<K, List<V>> map = new HashMap<K, List<V>>();
	/**
	 * 健值分类
	 */
	private KeySort<K, V> keySort;

	public HashList(KeySort<K, V> keySort) {
		this.keySort = keySort;
	}

	/**
	 * 根据value值返回key
	 * */

	public K getKey(V v) {
		return keySort.getKey(v);
	}

	/**
	 * 键值对排序
	 * 
	 * @param comparator
	 */
	public void sortKeyComparator(Comparator<K> comparator) {
		Collections.sort(keyArr, comparator);
	}

	/**
	 * 根据索引返回键值
	 * 
	 * @param key
	 * @return
	 */
	public K getKeyIndex(int key) {
		return keyArr.get(key);
	}

	/**
	 * 根据索引返回键值对
	 * 
	 * @param key
	 * @return
	 */
	public List<V> getValueListIndex(int key) {
		return map.get(getKeyIndex(key));
	}

	public V getValueIndex(int key, int value) {
		return getValueListIndex(key).get(value);

	}

	public int size() {
		return keyArr.size();
	}

	public void clear() {
		keyArr.clear();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()){
			it.next();
			if (true){
				it.remove();
			}
		}
//		for (Iterator<K> it = map.keySet().iterator(); it.hasNext();){
//			it.remove();
//		}
	}

	public boolean contains(Object object) {
		return false;
	}

	public boolean isEmpty() {
		return false;
	}

	public Object remove(int location) {
		return null;
	}

	public boolean remove(Object object) {
		return false;
	}

	public boolean removeAll(Collection arg0) {
		return false;
	}

	public boolean retainAll(Collection arg0) {
		return false;
	}

	public Object set(int location, Object object) {
		return keyArr.set(location, (K) object);
	}

	public List subList(int start, int end) {
		return keyArr.subList(start, end);
	}

	public Object[] toArray() {
		return keyArr.toArray();
	}

	public Object[] toArray(Object[] array) {
		return keyArr.toArray(array);
	}

	/**
	 * 这里就已经分门别类了
	 * @param object
	 * @return
	 */
	public boolean add(Object object) {
		V v = (V) object;
		K key = getKey(v);
		if (!map.containsKey(key)) {
			List<V> list = new ArrayList<V>();
			list.add(v);
			keyArr.add(key);
			map.put(key, list);
		} else {
			map.get(key).add(v);
		}
		return false;
	}

	public int indexOfKey(K k) {
		return keyArr.indexOf(k);
	}
}
