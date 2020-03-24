package A3;
/**
 * 
 */

/**
 * @author sid16
 *
 */
public interface PriorityQueue<k,v> {
	int size();
	boolean isEmpty();
	Entry<k,v> insert(k key, v value) throws IllegalArgumentException;
	Entry<k,v> min();
	Entry<k,v> removeMin();	
}
