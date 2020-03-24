package A3;
/**
 * 
 */

/**
 * Composition pattern: two or more objects make a single object.
 * Entry = key and value.
 * Key is what represents priority in PriorityQueue.
 * @author sid16
 *
 */
public interface Entry<K,V> {
	K getKey();
	V getValue();
}
