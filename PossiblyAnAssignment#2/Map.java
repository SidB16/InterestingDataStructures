package A3;
/**
 * 
 */

/**
 * Maps store entries.
 * keys have to be unique!
 * @author sid16
 *
 */
public interface Map<K,V> extends Iterable<Entry<K,V>> {
	int size();
	boolean isEmpty();
	V get(K key);
	V put(K key, V value);
	V remove(K key);
	Iterable<K> keySet();
	Iterable<V> values();
	Iterable<Entry<K,V>> entrySet();
}
