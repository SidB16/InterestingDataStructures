/**
 * 
 */
package eecs2011;

/**
 * An interface that expresses the Queue ADT.
 * An ADT is a mathematical model that specifies:
 * 1) the type of data stored
 * 2) the operation of that data
 * 3) the parameters of the operations 
 * Adapted from the slides.
 * This interface is implemented by the AQueue and LQueue classes. 
 * 
 * I have written about advantages and disadvantages of arrays vs linkedlists in the respective, 
 * array-based and linkedlist-based ArrayList and Queue classes.
 * 
 *To summarize, with respect to this interface: 
 *	Algorithms for:		Array-based implementation 			Linkedlist-based implementation
 *	size()  --> 				O(1)										O(1)
 *	isEmpty -->					O(1)										O(1)
 *	enqueue(Object o) -->		O(1)										O(1)
 *	dequeue() -->				O(1)										O(1)
 *	front() -->					O(1)										O(1)
 *
 *The edge for this implementation, in my opinion, goes to the linkedlist-based implementations.
 *Though the algorithmic runtime complexity for both these implementation are essentially the same. 
 *I personally feel, building a queue using a linkedlist is far more intuitive and easier to grasp.
 *Though I do not want to take from the efficiency we obtain from the power of the modulus operator, for arrays.
 *It is very fascinating what we able to achieve with such simple mathematics. 
 *
 * @author sid16
 *
 */
public interface Queue<E>{
	
	/**
	 * Returns the number of elements stored in the Queue.
	 * @return Number of elements in the Queue.
	 */
	public int size();
	
	/** 
	 * Indicates whether no elements are stored in Queue.
	 * @return True if empty, else false.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the element at the front of the Queue without removing it.
	 * @return The front element.
	 * @throws EmptyQueueException If the Queue if empty.
	 */
	public Object front() throws EmptyQueueException;
	
	/**
	 * Inserts an element to the end of Queue. 
	 * @param o The element that will be added the end of the Queue.
	 * @throws FullQueueException 
	 */
	public void enqueue(Object o) throws FullQueueException;
	
	/**
	 * Removes and returns the element at the front of the Queue.
	 * @return The former first element of the Queue.
	 * @throws EmptyQueueException If Queue is empty.
	 */
	public Object dequeue() throws EmptyQueueException;

}
