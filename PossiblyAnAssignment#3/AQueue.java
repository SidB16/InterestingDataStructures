/**
 * 
 */
package eecs2011;

/**
 * This class is the blueprint for array-based Queue.
 * 
 * Few important things to remember about the array: 
 * 1) Arrays can hold anything 
 * 2)Arrays always have a fixed size. (Linkedlists have advantage since they dont have fixed capacity!)
 * 3) Arrays have the ability to random access, i.e. get() and set() algorithms for arrays have rumtime complexity of O(1) time. 
 * This an advantage arrays have over linkedlists, 
 * where the runtime complexity for get() and set() algorithms is O(n), for linkedlists based algorithms.
 * 4)However, in the opposite case, add/insert and remove/delete algorithms have O(n) runtime complexity for arrays, 
 * and O(1) runtime complexity for linkedlist-based algorithms.
 * @author sid16
 *
 */
public class AQueue<E> implements Queue<E> {
	/**
	 * PREVIOUS //Feilds private int capacity = 0; private int size = 0; private E[]
	 * ary = null;
	 * 
	 * private E front; private E back;
	 * 
	 * public AQueue(E e) { this.capacity = 2; this.size = 1; this.ary = (E[]) new
	 * Object[this.capacity]; this.ary[0] = e; this.front = this.ary[0]; this.back =
	 * this.ary[1]; //which is null } public AQueue() { this.capacity = 1; this.size
	 * = 0; this.ary = (E[]) new Object[this.capacity]; this.ary[0] = null;
	 * this.front = this.ary[0]; this.back = this.ary[0]; }
	 **/

	private E[] arry; // generic array reference used to implement queue
	private int front; // front index of queue
	private int rear; // rear index of queue, which is the next available location for new enqueue
						// object
//	private int size; //number of elements in the queue

	// Constructor Methods
	@SuppressWarnings("unchecked")
	public AQueue(int capacity) {
		this.arry = (E[]) new Object[capacity];
		this.front = 0;
		this.rear = 0;
		// this.size = 0;
	}

	public AQueue() { // create Queue with capacity 1!
		this(1);
	}

	/**
	 * Returns the number of elements stored in the Queue. This algorithm has O(1)
	 * runtime complexity.
	 * 
	 * @return Number of elements in the Queue.
	 */
	public int size() {
		return (this.rear + this.arry.length - this.front) % (this.arry.length);
	}

	/**
	 * Indicates whether no elements are stored in Queue. This algorithm has O(1)
	 * runtime complexity.
	 * 
	 * @return True if empty, else false.
	 */
	public boolean isEmpty() {
		return (this.front == this.rear);
	}

	/**
	 * Returns the element at the front of the Queue without removing it. This
	 * algorithm has O(1) runtime complexity.
	 * 
	 * @return The front element.
	 * @throws EmptyQueueException If the Queue if empty.
	 */
	public Object front() throws EmptyQueueException {
		if (this.size() == 0) {
			throw new EmptyQueueException("AQueue is Empty!");
		} else {
			return this.arry[front];
		}
	}

	/**
	 * Inserts an element to the end of Queue. This algorithm has O(1) runtime
	 * complexity. I did create FullQueueException class but did not want cause
	 * error by changing method signature.
	 * 
	 * @param o The element that will be added the end of the Queue.
	 */
	public void enqueue(Object o) {
		int nextRear = (this.rear + 1) % (arry.length);
		if (front == nextRear) { // or size == this.arry.length-1
			System.out.println("Queue is full!");
		} else {
			arry[this.rear] = (E) o;
			this.rear = nextRear;
		}
	}

	/**
	 * Removes and returns the element at the front of the Queue. This algorithm has
	 * O(1) runtime complexity.
	 * 
	 * @return The former first element of the Queue.
	 * @throws EmptyQueueException If Queue is empty.
	 */
	public Object dequeue() throws EmptyQueueException {
		if (this.isEmpty()) {
			throw new EmptyQueueException("Queue is empty!");
		} else {
			Object rtnE = this.arry[front];
			this.arry[front] = null;
			this.front = (this.front + 1) % (this.arry.length);
			return rtnE;
		}
	}
}
