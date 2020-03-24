/**
 * Packages and imports.
 */
package eecs2011;

import java.lang.IndexOutOfBoundsException;//For throwing the IndexOutofBoundsException

/**
 * This class is a blueprint for an ArrayList that uses Arrays internally. An
 * instance of this class will be an data structure called the AArrayList.
 * 
 * Few important things to remember about the array: 
 * 1) Arrays can hold anything
 * 2)Arrays always have a fixed size. (Linkedlists have advantage since they dont have fixed capacity!) 
 * 3) Arrays have the ability to random access, i.e. get() and set() algorithms for arrays have rumtime complexity of O(1) time.
 * 	  This an advantage arrays have over linkedlists, where the runtime complexity for get() and set() algorithms is O(n), for linkedlists based algorithms.
 * 4)However, in the opposite case, add/insert and remove/delete algorithms have O(n) runtime complexity for arrays, and O(1) runtime complexity for linkedlist-based algorithms.
 * 
 * @author sid16
 *
 */
public class AArrayList<E> implements List<E> {

	// Feilds
	private int size = 0; // number of elements in the list
	private int capacity = 0; // the number of possible elements this list can hold
	private E[] ary = null; // reference variable of type Object to handle working with generic arrays

	// Constructor methods
	@SuppressWarnings("unchecked")
	public AArrayList(int initialCapacity) {
		this.size = 0;
		this.capacity = initialCapacity;
		this.ary = (E[]) new Object[capacity]; // creating an Array of type object with specified capacity
		// ^strong typing here. (Narrowing type conversion thus, explicit call is made).
		// AArrayList knows explicitly what type of objects it contains (i.e. its
		// constructor was explicitly called with a Class<E> argument,
		// and methods will throw an exception when they are passed arguments that are
		// not of type E
	}

	// creates empty ArrayList with size = 0 and Capacity = 1
	public AArrayList() {
		this(1); // constructor chaining
	}

	/**
	 * Returns the number of elements in this list. Runtime complexity of this
	 * algorithm = O(1)
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
		return this.size == 0 ? true : false;
	}

	/**
	 * Inserts an element e to be at index i, shifting all elements after this. This
	 * algorithm is O(n) as for the worst case as we would have to walk down the
	 * whole length of the array to copy up, then add target element.
	 * 
	 * @param i Index at which element e is added
	 * @param e Element to be added
	 * @throws IndexOutOfBoundsException
	 */
	public void add(int i, E e) throws IndexOutOfBoundsException {
		// 1)) Check if index is invalid.
		/**
		 * For index to be valid: a) index > 0 (so no negatives) and b) index <= one
		 * index space after last element
		 * 
		 * To elaborate, if the index passed is two spaces after last element. Then our
		 * structure does not really maintain the property of a linear data structure.
		 * One could say we would have holes in our list. A possible alternative would
		 * be insert null till multi-spaced index to fill up the holes. But for my
		 * implementation I assume null is a invalid entry.
		 * 
		 * 
		 * In essence, if i = this.size() (the immediate index after index of last
		 * element of ArrayList), it means Element E would be added to the back of the
		 * array. At the index immediately after the index of last element of our array
		 * based ArrayList. Thus, it would not create holes, and allow us to maintain
		 * linearity.
		 * 
		 * Also, passing e as null will cause null pointer exception
		 **/

		// So,
		if (i < 0 || i > this.size()) {
			throw new IndexOutOfBoundsException("Index entered in invalid!");
		}
		// 2.1) if empty ArrayList
		if (this.size() == 0) {
			this.ary[0] = e;
			this.size++;
		} else {
			// 2.2.0)check to see to if adding element would require resize
			if ((this.size() + 1) >= this.capacity) {
				this.resize();
			}
			// 2.2.1)copy up all elements after index i to make space:
			// Starting from index of last element till the index of target
			// walk down the array, and copy up
			for (int j = this.size() - 1; j >= i; j--) {
				// copy up
				this.ary[j + 1] = this.ary[j];
			} // assuming all elements till target index have been moved up

			// 2.2.2) insert element
			this.ary[i] = e;

			// 2.2.3) update size
			this.size++;
		}
	}

	/**
	 * Returns the element at index i, without removing it. Runtime complexity of
	 * this algorithm = O(1). This because arrays allows us to perform random access
	 * using index.
	 * 
	 * @param i Index of element to get
	 * @return Element at i to get
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > this.size - 1) {
			throw new IndexOutOfBoundsException("Index entered in invalid!");
		}
		return (E) this.ary[i];
	}

	/**
	 * Removes and returns the element at index i, shifting the elements after this.
	 * This algorithm is O(n) runtime. As for the worst case as we would have to
	 * walk up the whole length of the array to overwrite and then copy down the
	 * elements.
	 * 
	 * @param i Index of element to remove
	 * @return Element at index i to remove
	 * @throws IndexOutOfBoundsException
	 */
	public E remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > this.size - 1) {
			throw new IndexOutOfBoundsException("Index entered in invalid!");
		} else {
			// Reference to return target element
			E removedE = this.get(i);

			// To remove:
			// 1) copy down to overwrite element at index i
			// 2) size--;

			// We dont have to check for resizing to remove an element!
			// So
			// copy down and overwrite the target element
			// Starting at target index, pull elements down
			for (int j = i; j <= this.size() - 1; j++) {
				this.ary[j] = this.ary[j + 1];
			} // assume target element has been overwritten

			// update size
			this.size--;

			return removedE;

		}
	}

	/**
	 * Replaces the element at index I with e, returning the previous element at i.
	 * This algorithm has O(1) runtime complexity. Due to property of arrays which
	 * allow for random access.
	 * 
	 * @param i Index of element to set
	 * @param e Element to set at index i
	 * @return Previous element at index i
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if (i < 0 || i > this.size - 1) {
			throw new IndexOutOfBoundsException("Index entered in invalid!");
		} else {
			E previousE = this.get(i);
			this.ary[i] = e;
			return previousE;
		}
	}

	// Helper Method!
	// Method to dynamically grow arrays
	// returns new array with same elements but capacity*2
	// runtime complexity for this algo is O(n)!
	@SuppressWarnings("unchecked")
	private void resize() {
		// create new array with capacity of old array*2
		E[] result = (E[]) new Object[this.capacity * 2];
		// Copy elements into new array
		for (int i = 0; i < this.capacity; i++) {
			result[i] = this.ary[i];
		}
		this.ary = result;
		this.capacity = this.capacity * 2;
	}

}
