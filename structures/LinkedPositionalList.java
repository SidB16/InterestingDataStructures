import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * @author sid16
 *
 */
public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {
	private class Node<E> implements Position<E>{
		//best practices of encapsulation and abstraction are implemented!
		//Set fields as private and use accesor methods to access feilds
		private E element;
		private Node<E> prev;
		private Node<E> next;
		public Node(E e, Node<E> p, Node<E> n) { //constructor
			this.element = e;
			this.prev = p;
			this.next = n;
		}
		/**
		 * Accessor methods
		 */
		public E getElement() throws IllegalStateException {
			if (this.next == null)  //condition for DEFUNT NODE
				throw new IllegalArgumentException();
			return this.element;
		}
		public Node<E> getPrev() {
			return this.prev;
		}
		public Node<E> getNext(){
			return this.next;
		}
		/**
		 * Modifier methods
		 */
		public void setElement(E e) {
			this.element = e;
		}
		public void setPrev(Node<E> p) {
			this.prev = p;
		}
		public void setNext(Node<E> n) {
			this.next = n;
		}
	}
	//Feilds
	private Node<E> header; //sentinel node
	private Node<E> trailer; //sentinel node
	private int size;
	
	/**
	 * Constructor
	 */
	public LinkedPositionalList() {
		this.size = 0;
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, null, null);
		this.header.setNext(trailer);
		this.trailer.setPrev(header);
	}
	
	
	public int size() {
		return this.size;
	}

	
	public boolean isEmpty() {
		return this.size == 0 ? true : false;
	}
	//Private utility method to convert from Node to Position!
	private Position<E> position(Node<E> node){
			//EXACTLY EQUAL, meaning in memory!
		if(node == this.trailer || node == this.trailer) 
			return null; //dont expose the sentinel nodes!
		return node;
	}
	public Position<E> first() {
			//returning position
		return this.position(this.header.getNext());
	}

	public Position<E> last() {
			//returning position
		return this.position(this.trailer.getPrev());
	}

	
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
			Node<E> node = this.node(p);
			/*if(node.prev == header) 
				return null;*/ //no need to handle special case here bc it's handled by position method()
			return this.position(node.getPrev());
	}

	
	public Position<E> after(Position<E> p) {
		Node<E> node = this.node(p);
		/*if(node.getNext() == this.trailer)
			return null;*/ //No need to handle special case here again!
		return this.position(node.getNext());
	}
	//Private utility methods to CONVERT Position<E> to Node<E> (i.e. My version of VALIDATE)
	private Node<E> node(Position<E> p) throws IllegalArgumentException{
		//Steps here:
		//1) Check for see if p is instance of Node!
		//2) Check for defunt node!
		//3) Throw illegal Argument Exception
		if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> rtnNode = (Node<E>) p; //safe cast
		if(rtnNode.getNext() == null) {
			throw new IllegalArgumentException("Defunt Node i.e. p is no longer in list");
		}
		return rtnNode;
	}
	
	private Position<E> addBetween(E element, Node<E> pred, Node<E> succ){
		Node<E> newNode = new Node<>(element, pred, succ);
		pred.setNext(newNode);
		succ.setPrev(newNode);
		this.size++;
		return newNode;
	}
	
	public Position<E> addFirst(E e) {
		Node<E> newPosition = new Node<>(e,this.header,this.header.getNext());
		this.header.getNext().setPrev(newPosition);
		this.header.setNext(newPosition);
		this.size++;
		return this.position(newPosition);
	}

	@Override
	public Position<E> addLast(E e) {
		Node<E> newPosition = new Node<>(e, this.trailer.getPrev(), this.trailer);
		this.trailer.getPrev().setNext(newPosition);
		this.trailer.setPrev(newPosition);
		this.size++;
		return this.position(newPosition);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.node(p); //node() handles special cases and throws exception
		Node<E> newPosition = new Node<>(e, node.getPrev(), node);
		node.getPrev().setNext(newPosition);
		node.setPrev(newPosition);
		this.size++;
		return this.position(newPosition);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.node(p); //node() handles special cases and throws exceptions
		Node<E> newNode = new Node<>(e, node, node.getNext());
		node.getNext().setPrev(newNode);
		node.setNext(newNode);
		this.size++;
		return this.position(newNode);
	}

	
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.node(p);//node() handles special cases and throws exception
		E replacedE = node.getElement();
		node.setElement(e);
		return replacedE;
	}

	
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.node(p); //node() handles special cases and throws exception
		E rtnElement = node.getElement();
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
		
		//Made node defunct + help with grabage collection!
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		
		this.size--;
		
		return rtnElement;
	}
	/**
	 * Adding iterator for PositionalList.
	 * Thus, iterator iterator() ==> to iterate over elements of the Positional List
	 * 		 iterable positions() ==> to iterate over positions of the Positional List
	 */
	public Iterator<E> iterator(){
		return new ElementIterator();
	}
	public Iterable<Position<E>> positions(){
		return new PositionIterable();
	}
	/**
	 * Thus, we require a few private utlities that include new objects.
	 * Plan: 
	 * 1)Create a PositionIterator that implements Iterator and defines the methods using PositionalList methods and logic
	 * 2)Extend this position-based iterator by creating a new ElementIterator that simply returns the elements usign the PositionalIterator.
	 * 3)Encapsulate this PositionalIterator in the Iterable PositionIterable class by defining the Iterable Iterator() and returning PositionIterator as Iterable.
	 */
	//Step1
	private class PositionIterator implements Iterator<Position<E>>{
		Position<E> cursor = first(); //next element to be reported.
		Position<E> recent = null; //most recent element to be reported.
		public boolean hasNext() {
			return (cursor != null);
		}
		public Position<E> next() throws NoSuchElementException {
			if (cursor == null) throw new NoSuchElementException("nothing left!");
			recent = cursor;
			cursor = after(cursor); //set cursor to position after prevision cursor
			return recent;
		}
		public void remove() throws IllegalArgumentException {
			if(recent == null) throw new IllegalArgumentException("Nothing to Remove!");
			LinkedPositionalList.this.remove(recent); //remove from outer class aka List
			recent = null; //to not allow remove till next is called.
		}
	}
	//Step2
	private class ElementIterator implements Iterator<E>{
		//not need to extend PositonIterator. Just instantiate a new one and 
		//bc it's type is Position<E>, where position stores elements.
		//we can use getElement to return element at that position.
		PositionIterator p = new PositionIterator();
		public boolean hasNext() {
			return p.hasNext();
		}
		public E next() throws NoSuchElementException {
			return p.next().getElement();
		}
		public void remove() {
			 p.remove();
		}
	}//A new Instance of this ElementIterator can be returned by Iterator iterator(), for outer class.
	//Step3
	private class PositionIterable implements Iterable<Position<E>>{
		/**
		 *@returns a new intance of a position-based iterator everytime this class is used, in Iterable positions()
		 */
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}//An instance of this class will be returned by the iterable positions() method in the outer class
}
