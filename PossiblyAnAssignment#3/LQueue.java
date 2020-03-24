/**
 * 
 */
package eecs2011;

/**
 * This class is a blueprint for the LQueue data structure, which is a  
 * linkedlist-based Queue.
 * 
 * A LinkedList is simply a series of Nodes that are linked together using pointer. 
 * A few things to remember about LinkedLists: 
 * 1) They do not have a fixed size, thus, can grow dynamically (unlike arrys which have a fixed capacity).
 * 2)They do not allow for random access, unlike arrays. So, get() and set() are no longer O(1) runtime 
 * 3) However, with linkedlist based implementation Add and remove algorithms have O(1) runtime 
 * through pointer manipulation technique.
 * 4) The central construct of linkedlist is the Node, which can hold any type of element, and allows
 * for great degree of flexibility as you make your Node smart ( i.e. hold more information like prev, next references)
 * 
 * @author sid16
 *
 */
public class LQueue<E> implements Queue<E> {

	// Nested Node Class
	public static class Node<E> {// private and static to reduce visibility and mutability.

		private E element; // reference to element stored at this node.
		private Node<E> next; // reference to next node.
		// Constructor- create a newa node with Element e, which has next as null.
		public Node(E e) {
			this.element = e;
			this.next = null;
		}
		// Methods for good OOP design (e.g.encapsulation), and testing convenience.
		public E getElement() {
			return this.element;
		}
		public Node<E> getNext() {
			return this.next;
		}
		public void setNext(Node<E> n) {
			this.next = n;
		}
	}

	// Feilds
	private Node<E> front = null; // points to the first node the Queue.
	private Node<E> back = null; // points to the last made of the Queue. (back.next ponts to null!)
	private int size = 0;
	// dont have to worry about size for Linked List implementation.

	// Constructors
	public LQueue(E e) {
		if (!(e == null)) {
			this.front = new Node(e);
			this.back = this.front;
			this.size++;
		} else {
			// do nothing
			// front still points to null
			// back still points to null
			// size is still = 0.
		}
	}

	public LQueue() {
		this(null); // constructor chaining here
	}
	
	/*
	 * public Node<E> getFront() { return this.front; } public Node<E> getBack(){
	 * return this.back; }
	 */
	/**
	 * Returns the number of elements stored in the Queue.
	 * This algorithm has O(1) runtime complexity.
	 * @return Number of elements in the Queue.
	 */
	public int size() {
		return this.size;
	}
	/** 
	 * Indicates whether no elements are stored in Queue.
	 * This algorithm has O(1) runtime complexity.
	 * @return True if empty, else false.
	 */
	public boolean isEmpty() {
		return this.front == null ? true : false;
	}
	/**
	 * Returns the element at the front of the Queue without removing it.
	 * This algorithm has O(1) runtime complexity.
	 * @return The front element.
	 * @throws EmptyQueueException If the Queue if empty.
	 */
	public Object front() throws EmptyQueueException {
		if (this.front == null) {
			throw new EmptyQueueException("Queue is empty!");
		} else {// Queue is not empty. So,
			return this.front.element;
		}
	}
	/**
	 * Inserts an element to the end of Queue. 
	 * This algorithm has O(1) runtime complexity.
	 * Note:For my implementations, IF Object o is NULL then it will not be enqueued.
	 * Trying to implement good design choices where possible.
	 * 
	 * @param o The element that will be added the end of the Queue.
	 */
	public void enqueue(Object o) {
		//For my implementations,
		//IF OBJECT IS NOT NULL
		//then it will not be enqueued.
		if ( !(o == null) ) {	
		// 1)Create new node
		Node<E> newNode = new Node(o);
		// 2.1) If Queue is empty:
		if (this.size == 0) {
			this.front = newNode;
			this.back = newNode;
			this.size++; // update size of Queue
		} else {
			// 2.1 If Queue is not Empty
			// Pointer manipulation to add the newNode to back
			this.back.next = newNode;
			// 3) make the new node the back of queue
			this.back = newNode;
			// Update size of Queue
			this.size++;
		}
	}else { //IF OBJECT IS NULL
		//do nothing 
		System.out.println("Enter a valid element that != null");
	}

	}
	/**
	 * Removes and returns the element at the front of the Queue.
	 * This algorithm has O(1) runtime complexity.
	 * @return The former first element of the Queue.
	 * @throws EmptyQueueException If Queue is empty.
	 */
	public Object dequeue() throws EmptyQueueException {
		if (this.isEmpty() == true) {
			throw new EmptyQueueException("Queue is empty!");
		} else {
			Object rtnE = this.front.element; // return node
			this.front = this.front.next;
			size--; // update size
			return rtnE;
		}
	}

}
