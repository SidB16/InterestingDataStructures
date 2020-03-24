package A3;
/**
 * 
 */

/**
 * @author sid16
 *
 */
public interface AdaptablePriorityQueue<k,v> extends PriorityQueue<k,v> {
	public void remove(Entry<k,v> entry) throws IllegalArgumentException;
	public void replaceKey(Entry<k,v> entry, k key);
	public void replaceValue(Entry<k,v> entry, v value);
}
