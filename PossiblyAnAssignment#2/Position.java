package A3;
/**
 * 
 */

/**
 *
 * @author sid16
 *
 */
public interface Position<E> {
	
	/**
	 * @return the element at position.
	 * @throws IllegalStateException
	 */
	E getElement() throws IllegalStateException;
}
	