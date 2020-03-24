/**
 * 
 */
package eecs2011;

/**
 * This class is a blueprint for the LArrayList data structure, which is a
 * linkedlist-based ArrayList.
 * 
 * A LinkedList is simply a series of Nodes that are linked together using
 * pointer. A few things to remember about LinkedLists:
 *  1) They do not have a fixed size, thus, can grow dynamically (unlike arrays which have a fixed
 * capacity). 
 * 2)They do not allow for random access, unlike arrays. So, get() and set() are no longer O(1) runtime 
 * 3) However, with linkedlist based implementation add and remove algorithms have O(1) runtime through pointer manipulation technique. 
 * 4) The central construct of linkedlist is the Node, which can hold any type of element, 
 * and allows for great degree of flexibility as you make your Node smart ( i.e. hold more information like prev, next references)
 * 
 * @author sid16
 *
 */
public class LArrayList<E> implements List<E> {

	public class Node<E> {
		private E element; // the element
		private Integer index; // the index of the node in the ArrayList. Used a wrapper class to initialize
								// state of object as null.
		private Node<E> next; // reference to the next node
		private Node<E> prev; // MAKE VISIBILITY PRIVATE!!!!!! for these feilds!!!!

		private Node(E e) {
			this.element = e;
			this.index = null; // assign null so default index in not 0 (wanted to avoid any bugs for this
								// case).
			this.next = null;
			this.prev = null;

		}

		public Node() {
			this(null);
		}
	}

	private int size;
	private int capacity;
	private Node<E> head; // head.prev == null!
	private Node<E> tail; // tail.next == null!

	// Constructors
	public LArrayList(int initialCapacity) {
		this.size = 0;
		this.createLinkedList(initialCapacity);
	}

	public LArrayList() {
		this(1);
	}

	/**
	 * //FOR TESTING public int capacity() { return this.capacity; } public E
	 * getHeadElement() { return this.head.element; } public E getTailElement() {
	 * return this.tail.element; }
	 **/
	/// Other Methods from ADT
	/**
	 * Returns the number of elements in this list. Runtime complexity of this
	 * algorithm = O(1).
	 * 
	 * @return number of elements in the list
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns whether the list is empty. Runtime complexity of this algorithm =
	 * O(1)
	 * 
	 * @return If the list is empty.
	 */
	public boolean isEmpty() {
		return this.size() == 0 ? true : false;
	}

	/**
	 * Inserts an element e to be at index i, shifting all elements after this.
	 * Runtime complexity of this algorithm = O(1) because of simple pointer
	 * manipulation technique. This is only possible with linkedlists-based
	 * implementation.
	 * 
	 * @param i Index at which element e is added
	 * @param e Element to be added
	 * @throws IndexOutOfBoundsException
	 */
	// Same logic as in my AArrayList implementation.
	// I am assuming that the adding to back of ArrayList,
	// Immediately after the index of last element in array (@ index =
	// LArrayList.size()-1)
	// is valid.
	// In essence if index of last element is LArrayList.size()-1 then
	// adding a element immediately after it at index = LArrayList.size() is valid.
	// Thus, adding to the back of the LArrayList.
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if (i < 0 || i > this.size()) {
			throw new IndexOutOfBoundsException("Enter valid index!");
		} else {
			// For empty ArrayList
			if (this.size() == 0) {
				this.head.element = e;
				// tail is already = head
				this.size++;
			} else {// adding at head
				Node<E> newNode = new Node<E>(e);
				if (this.head.index.intValue() == i) {
					this.head.prev = newNode;
					newNode.next = this.head;
					newNode.index = 0;
					this.updateIndexUp(this.head); // update indices
					this.head = newNode; // set pointer for new head
					this.size++;
				} else if ((this.tail.index.intValue() + 1) == i) { // adding to the back of list, which should be i =
																	// this.size();
					newNode.index = this.tail.index.intValue() + 1; // so need to update indices, as this new node will
																	// does not affect them.
					this.tail.next = newNode;
					newNode.prev = this.tail;
					this.tail = newNode;
					this.size++; // but size is affected. So update size.
				} else {
					// walk to down the series of linked node (TILL the last node) to find the
					// target index
					Node<E> current = this.head;
					while (current != null) {
						if (current.index.intValue() == i) {
							current.prev.next = newNode;
							newNode.next = current;
							current.prev = newNode;
							newNode.index = current.index.intValue();
							this.updateIndexUp(current); // this method can handle if current == null
							this.size++;
							return; // EXIT HERE if condition is met.
						} else {
							current = current.next;
						}
					}
				}
			}
		}
	}

	/**
	 * Returns the element at index i, without removing it. This algorithm is O(n)
	 * as for the worst case as we would have to walk down the whole length of
	 * linkedlist to reach target index.
	 * 
	 * @param i Index of element to get
	 * @return Element at i to get
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > this.size - 1) {
			throw new IndexOutOfBoundsException("Enter valid index!");
		}
		Node<E> current = this.head;
		// If head is the target index
		if (current.index.intValue() == i) {
			return current.element;
		} else {
			// walk to till tail, which has its .next == null
			// i.e. by the end of this iterative loop current should be equal to this.tail!!
			while (current.next != null) {
				if (current.index.intValue() == i) {
					return current.element;
				} else {
					current = current.next;
				}
				// at the last node
				if (current.index.intValue() == i) {
					return current.element;
				}
			} // if we did not return anything before reaching this branch, then...
			throw new IndexOutOfBoundsException("Enter valid index!");
		}
	}

	/**
	 * Removes and returns the element at index i, shifting the elements after this.
	 * Runtime complexity of this algorithm = O(1) because of simple pointer
	 * manipulation technique. This is only possible with linkedlists-based
	 * implementation.
	 * 
	 * @param i Index of element to remove
	 * @return Element at index i to remove
	 * @throws IndexOutOfBoundsException
	 */
	public E remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > this.size - 1) {
			throw new IndexOutOfBoundsException("Index entered in invalid!");
		} else {
			Node<E> rtnNode;
			if (this.head.index.intValue() == i) {
				rtnNode = this.head;
				this.updateIndexDown(this.head.next);// staring from next node, reduce index value by 1.
				// this.head.next.prev = null;
				this.head = this.head.next;
				this.size--;
				return rtnNode.element;
			} else if (this.tail.index.intValue() == i) {
				rtnNode = this.tail;
				this.tail.prev.next = null;
				this.tail.prev = null; // clean up pointers
				this.tail = this.tail.prev;
				size--;
				return rtnNode.element;
			} else {
				Node<E> current = this.head;
				while (current != null) {
					if (current.index.intValue() == i) {
						rtnNode = current;
						this.updateIndexDown(current.next);
						current.next.prev = current.prev;
						current.prev.next = current.next;
						size--;
						return rtnNode.element;
					} else {
						current = current.next;
					}
				}
				// If you reached this branch then,
				throw new IndexOutOfBoundsException("Invalid index entered!");
			}
		}

	}

	/**
	 * Removes and returns the element at index i, shifting the elements after this.
	 * This algorithm is O(n) as for the worst case as we would have to walk down
	 * the whole length of linkedlist to reach target index.
	 * 
	 * @param i Index of element to remove
	 * @return Element at index i to remove
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if (i < 0 || i > this.size - 1) {
			throw new IndexOutOfBoundsException("Index entered in invalid!");
		} else {
			E rtnElement = null;
			if (this.head.index.intValue() == i) {
				rtnElement = this.head.element;
				this.head.element = e;
				return rtnElement;
			} else {
				Node<E> current = head.next;
				while (current != null) {
					if (current.index.intValue() == i) {
						rtnElement = current.element;
						current.element = e;
						return rtnElement;
					} else {
						current = current.next;
					}
				}
			} // Unlikey this branch will be reached but if it is, then:
			throw new IndexOutOfBoundsException("Index entered in invalid!");
		}
	}

	// Helper methods
	// Helper method for constructing linkedlist based ArrayList
	// O(1) runtime for this algo.
	private void createLinkedList(int desiredCapacity) {
		if (this.size() == 0) { // to make code robust
			if (desiredCapacity == 1) {
				this.head = new Node<E>();
				this.head.index = 0;
				this.capacity++;
				this.tail = this.head;
			} else if (desiredCapacity == 2) {
				this.head = new Node<E>();
				Node<E> current = new Node<E>();
				head.next = current;// next
				current.prev = this.head;// prev
				this.tail = current;
				this.capacity = this.capacity + 2;
				// Setting indexes
				this.head.index = 0;
				this.tail.index = this.head.index.intValue() + 1; // for 2 nodes only!
			} else {
				this.head = new Node<E>();
				Node<E> current = new Node<E>();
				this.head.next = current;
				current.prev = this.head;
				this.head.index = 0;
				current.index = this.head.index.intValue() + 1;
				// So,
				this.capacity = this.capacity + 2; // update capacity
				desiredCapacity = desiredCapacity - 2; // since we linked head and current together.
				while (desiredCapacity > 0) {
					Node<E> newNode = new Node<E>();
					current.next = newNode;
					newNode.prev = current;
					newNode.index = current.index + 1;
					desiredCapacity--;
					this.capacity++;
					current = newNode;
				}
				this.tail = current;
			}
		}
	}

	// Helper method to adjust indices of nodes by incrementing them. Used in add()
	// O(n) runtime for this algo.
	private void updateIndexUp(Node<E> e) {
		Node<E> current = e;
		while (current != null) {
			current.index = current.index.intValue() + 1;
			current = current.next;
		}
	}

	// Helper method to adjust indices of nodes by decrementing them. Used in
	// remove()
	// O(n) runtime for this algo.
	private void updateIndexDown(Node<E> e) {
		Node<E> current = e;
		while (current != null) {
			current.index = current.index.intValue() - 1;
			current = current.next;
		}
	}

}
