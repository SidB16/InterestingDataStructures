/**
 * 
 */
package eecs2011;

/**
 * An interface that expresses the List ADT.
 * A ADT is a mathematical model that specifies:
 * 1) the type of data stored
 * 2) the operations on that data
 * 3) the parameters for those operations.
 * Adapted for the lecture slides.
 * This interface is implemented by AArrayList and LArrayList classes. 
 *
 *I have written about advantages and disadvantages of arrays vs linkedlists in the respective, 
 * array-based and linkedlist-based ArrayList and Queue classes.
 * 
 *To summarize, with respect to this interface: 
 *	Algorithms for:		Array-based implementation 			Linkedlist-based implementation
 *	size()  --> 				O(1)										O(1)
 *	isEmpty -->					O(1)										O(1)
 *	add(int i, E e) -->			O(n)										O(1)
 *	remove(int i) -->			O(n)										O(1)
 *	get(int i) -->				O(1)										O(n) 
 *	set( int i, E e) -->		O(1)										O(n)
 *
 *The edge for this implementation, in my opinion, goes to array-based implementation.
 *Though the algorithmic runtime complexities for both these implementation have their strengths and weaknesses.
 *I feel they balance eachother out. 
 *However, in my opinion, that ArrayList built on top of arrays just feels more intuitive and easier to understand. 
 *Most importantly, it takes less work to implement than implementing linkedlist-based Arraylist, in my opinion :P.
 *Though its definitely amazing to see the doubly linkedlist in action! 
 *The power that one can get from the flexibility of the linked nodes is very useful for a system with non-contagious memory allocation. 
 *And now, I can see why.
 *
 * @author sid16
 *
 */
public interface List<E> {
	/**
	 * Returns the number of elements in this list
	 * 
	 * @return number of elements in the list
	 */
	public int size();

	/**
	 * Returns whether the list is empty.
	 * 
	 * @return If the list is empty.
	 */
	public boolean isEmpty();

	/**
	 * Inserts an element e to be at index i, shifting all elements after this.
	 * 
	 * @param i
	 *            Index at which element e is added
	 * @param e
	 *            Element to be added
	 * @throws IndexOutOfBoundsException
	 */
	public void add(int i, E e) throws IndexOutOfBoundsException;

	/**
	 * Returns the element at index i, without removing it.
	 * 
	 * @param i
	 *            Index of element to get
	 * @return Element at i to get
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int i) throws IndexOutOfBoundsException;

	/**
	 * Removes and returns the element at index i, shifting the elements after this.
	 * 
	 * @param i
	 *            Index of element to remove
	 * @return Element at index i to remove
	 * @throws IndexOutOfBoundsException
	 */
	public E remove(int i) throws IndexOutOfBoundsException;

	/**
	 * Replaces the element at index I with e, returning the previous element at i.
	 * 
	 * @param i
	 *            Index of element to set
	 * @param e
	 *            Element to set at index i
	 * @return Previous element at index i
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int i, E e) throws IndexOutOfBoundsException;
}
