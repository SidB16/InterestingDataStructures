/**
 * 
 */
package eecs2011;

/**
 * EmptyQueue exception object for a empty Queue. I chose to extend Exception in
 * order to implement a checked exception.
 * 
 * @author sid16
 *
 */
@SuppressWarnings("serial") // Since superclass of Exception, Throwable class, implements the Serialize
							// interface.
//I am not implementing it thus, I am suppressing the warning.
public class EmptyQueueException extends Exception {
	public EmptyQueueException(String s) {
		super(s);
	}

	public EmptyQueueException() {
		super();
	}
}
