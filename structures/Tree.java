import java.util.Iterator;

/**
 * 
 */

/**
 * Thus far we dealt data structures that are linear i.e. they dealt with linear sequence of elements. Where there is a "before" and "after" relationship between given elements of a linear sequence.
 * We saw Arrays and Linkedlists which are primitive data structures.
 * && Stacks and Queues, and java.util.List implementations ArrayLists (index-based operations) and PositionalList (position-based operations), 
 * which are APIs and Collections built on top of Arrays or Linkedlists.
 * Thus, we introduced to:
 * the Position ADT- positions are marker/tocken in the broader list that store elements,
 * the Iterator ADT - abstraction of the technique used to iterate through elements of any given Collection,
 * the Iterable ADT- providing a way to iterate through positions of a Collection (part of the java Collection hierarchy) & thereby, allowing for use of for-each loop syntax. Be mindful of elements vs Positions.
 *
 * Then we got to Trees, which is a non-linear data structure, where there is a heairachical relationship between objects. This is bc some elements are "above" others and some elements are below.
 * We again apply the concept of positions here. We view nodes of tree as positions. 
 * Thus, we can create an -Iterator<E> iterator() ==> for iterating through elements of the tree.
 * 						  -Iterable<Position<E>> positions() ==> generating a new iterator to create a collection of positions that can travesered through using the for-each loop!
 * However, there several ways to iterate through a Tree, which may differ based on it being a Ordered Tree or un-Ordered Tree.
 * Thus here we are. At the part of declaring the interface and then creating a paritially implemented- abstract class, to use as base class to further create LinkedBinaryTree. 
 *  We then under how to recursively determine depth of position i.e. node in O(n) time and height in O(n^2) and O(n) time; for a GENERAL Tree.
 *  Then extend the Tree interface using BinaryTree interface, which we implement into a concrete class as a LinkedBinaryTree.
 *  
 *  Later itragreate the diffrerent tree traversal algorithms and understand where to use them and HOW!!
 *  
 * 
 * @author sid16
 *
 */
public interface Tree<E> extends Iterable<E> {
	//Acessor Methods
	Position<E> root();
	Position<E> parent(Position<E> p);
	Iterable<Position<E>> children (Position<E> p); //returns a iterable collection containing children of p
	int numChildren(Position<E> p);
	//Query methods (these methods are made for convinence when dealing with for or while loops, and are possible bc of properties of trees!)
	boolean isInternal(Position<E> p);
	boolean isExternal(Position<E> p);
	boolean isRoot(Position<E> p);
	//General Methods (from object hieararchy or interface implementations)
	int size();
	boolean isEmpty();
	Iterator<E> iterator(); //element iterator
	Iterable<Position<E>> positions(); //position iterator
}
