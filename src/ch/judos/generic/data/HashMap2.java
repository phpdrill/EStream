package ch.judos.generic.data;

import java.util.HashMap;

/**
 * a hashmap with two keys
 * 
 * @since 23.02.2013
 * @author Julian Schelker
 * @version 1.0 / 23.02.2013
 * @param <K>
 * @param <K2>
 * @param <V>
 */
public class HashMap2<K, K2, V> {

	/**
	 * the map
	 */
	protected HashMap<K, HashMap<K2, V>> map1;

	/**
	 * creates the map
	 */
	public HashMap2() {
		this.map1 = new HashMap<>();
	}

	/**
	 * puts a value to the specified two keys
	 * 
	 * @param key1
	 * @param key2
	 * @param value
	 */
	public void put(K key1, K2 key2, V value) {
		HashMap<K2, V> map2 = this.map1.get(key1);
		if (map2 == null) {
			map2 = new HashMap<>();
			this.map1.put(key1, map2);
		}
		map2.put(key2, value);
	}

	/**
	 * @param key1
	 * @param key2
	 * @return gets the value back, needs both keys
	 */
	public V get(K key1, K2 key2) {
		HashMap<K2, V> map2 = this.map1.get(key1);
		if (map2 == null)
			return null;
		return map2.get(key2);
	}

	/**
	 * @param key1
	 * @param key2
	 * @return if the given two keys exist
	 */
	public boolean containsKey(K key1, K2 key2) {
		if (this.map1.containsKey(key1)) {
			return this.map1.get(key1).containsKey(key2);
		}// else
		return false;
	}
}
