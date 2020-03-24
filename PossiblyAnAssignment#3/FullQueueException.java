/**
 * 
 */
package eecs2011;

/**
 * FullQueue exception object for a full Queue. I chose to extend Exception in
 * order to implement a checked exception.
 * 
 * @author sid16
 *
 */
@SuppressWarnings("serial") // Since superclass of Exception, Throwable class, implements the Serialize
							// interface.
//I am not implementing it thus, I am suppressing the warning.
public class FullQueueException extends Exception {
	public FullQueueException(String s) {
		super(s);
	}

	public FullQueueException() {
		super();
	}
}
