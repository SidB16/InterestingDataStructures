/**
 * 
 */

/**
 * Maps store entries.
 * keys have to be unique!
 * @author sid16
 *
 */
public interface Map<k,v> extends Iterable<Entry<k,v>> {
	int size();
	boolean isEmpty();
	v get(k key);
	v put(k key, v value);
	v remove(k key);
	Iterable<k> keySet();
	Iterable<v> values();
	Iterable<Entry<k,v>> entrySet();
}
