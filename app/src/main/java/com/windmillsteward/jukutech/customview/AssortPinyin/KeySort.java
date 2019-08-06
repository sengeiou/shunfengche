package com.windmillsteward.jukutech.customview.AssortPinyin;

/**
 * 
 * @author zhuxian 获取键名
 * @param <K>
 * @param <V>
 */
public interface KeySort<K, V> {
	public K getKey(V v);
}
