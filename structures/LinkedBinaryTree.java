import java.util.Iterator;

/**
 * 
 */

/**
 * @author sid16
 *
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> implements BinaryTree<E> {
	/**
	 * This LinkedBinary has the following:
	 * 1) Nodes abstracted as Positions
	 * 2) A Iterator that allows for traversal of Elements. Thereby, implementing Iterable ADT. A nested class will be generated to create this instance of a ElementIterator. 
	 * 3) A collection of iterable positions. Realized through the use of a (position) iterator (which is a subclass of iterable, thus, can use for-each loop shorthand syntax, as opposed to writing out (initializing a new instance + implementing a while-loop traversal strategy using hasNext() and next for this iterables collections iterator).
	 * 4) This abstraction for iterable positions is important bc there are mutiple ways to traverse trees. Breath-first and Depth-first. So given which order we choose can define the abstract positions() methods accordingly.
	 * 5) Feilds: 1)size and 2)reference to root node
	 * 
	 * Nested Classes include:
	 * 1) Nested Node class that implements positions
	 * 2) PositionIteator nested class that impelements Iterator<Position<E>>
	 * 3) ElementIterator nested class that implementes Iterator<E> and used a new instance of the PositionIterator
	 * 4) Iterable PositionIterable class that implements the Iterable<E> interface and defines its only method, Iterator<Position<E>> Iterator() by creating a new instance if Position iterator.
	 * 
	 * Notable private utility methods:
	 * 1) validate (Position as Node) ==> to handle Position to Node conversion
	 * 2) node (Node to Position) ==> to handle Node to position conversion.
	 * 3) Attach 
	 * 4) Remove
	 */
	
	/**
	 * Nested Node Class
	 */
	private class Node<E> implements Position<E>{
		E element;
		Node<E> next;
		Node<E> prev;
		//Getters
		public E getElement() { return this.element;}
		public Node<E> getNext(){ return this.next;}
		public Node<E> getPrev(){ return this.prev;}
		//Setters
		public void setElement(E e) { this.element =e;}
		public void setNext(Node<E> next) { this.next=next;}
		public void setPrev(Node<E> prev) {this.prev=prev;}
	}
	private class TreeNode<E> extends Node<E>{
		TreeNode<E> left;
		TreeNode<E> right;
		TreeNode<E> parent;
		//Argument constructor to be used in createNode, outerclass method.
		public TreeNode(E element, TreeNode<E> parent, TreeNode<E> leftChild, TreeNode<E> rightChild) {
			this.element = element;
			this.parent = parent;
			this.left = leftChild;
			this.right = rightChild;
		}
		//Getters
		public TreeNode<E> getLeft(){ return this.left;}
		public TreeNode<E> getRight(){ return this.right;}
		public TreeNode<E> getParent(){return this.parent;}
		//Setters
		public void setLeftNode(TreeNode<E> t) { this.left = t;}
		public void setRightNode(TreeNode<E> t) { this.right = t;}
		public void setParentNode(TreeNode<E> t) {this.parent=t;}
	}
	/**
	 * Feilds of LinkedBinaryTree
	 */
	int size;
	TreeNode<E> root = null;
	
	/**
	 * Private Utilty Methods
	 * 1)Factory method for creating Node, for reusebility and abstraction.
	 * 2) validate ==> position as treeNode then return TreeNode
	 * 3) treeNode==> return TreeNode as Positon<E>
	 */
	protected TreeNode<E> createNode(E element, TreeNode<E> parent, TreeNode<E> leftChild, TreeNode<E> rightChild){		 
		TreeNode<E> newNode = new TreeNode<>(element, parent, leftChild, rightChild);
		return newNode;
	}
	private TreeNode<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof TreeNode)) throw new IllegalArgumentException("Invalid Positon!");
		TreeNode<E> t = (TreeNode<E>) p;
		if(t.getParent() == t) throw new IllegalArgumentException("Invalid Positon!");
		return t;
	}
	//Widening cast is automatic and is not nessecary. 
	private Position<E> treeNode(TreeNode<E> t){
		return t;
	}
	/**
	 * Assessor Methods
	 * 
	 * -Positon<E> sibling(p) 
	 * -int numChildren(p)
	 * -Iterable<Position<E>> children(p)
	 * are all implemented by AbstractBinaryTree
	 */
	public Position<E> root(){
		return this.root;
	}
	public Position<E> parent(Position<E> p) throws IllegalArgumentException{
		TreeNode<E> t = validate(p);
		return t.getParent();
	}
	//BinaryTree specific methods
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		TreeNode<E> t = validate(p);
		return t.getLeft();
	}
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		TreeNode<E> t = validate(p);
		return t.getRight();
	}
	/**
	 * Query Methods
	 * 
	 * -isInternal
	 * -isExternal
	 * -isRoot
	 * are already implemented by AbstractTree class
	 */
	//Depth of a node, x is the lenght of the simple path from the root node, R to x.
	public int depth(Position<E> p) throws IllegalArgumentException {
		TreeNode<E> n = validate(p);
		// Strategy for recursion: Walk up to root. Keep the value 1 aside on each recursive case then add them all up when you reach the base case.
		//1-->1-->1-->0
		//child -edge-> parent -edge-> parent's parent -edge-> root.
		//Root is always at depth 0. Depth corresponds to height to root is always a level. Thus, we can imply, level 0 should only have 1 node.
		if(isRoot(n)) 
			return 0;
		else 
			return 1 + depth(n);
	}
	//Height of node, x is the number of edges on the LONGEST simple path from x to any leaf.
	public int height(Position<E> p) throws IllegalArgumentException{
		TreeNode<E> n = validate(p); //to handle error checking. bc this statment can throw expception.
		//Strategy for recursion: for each child of p, keep 1 aside and cache the value using Math.max. At end when we have treversed all childre. Retun h.
		int h = 0;
		//Using Postion<E> instead of TreeNode<E> here. 
		for(Position<E> t : children(p))
			h = Math.max(h, 1+height(t));
		return h;
	}
	/**
	 * Update Methods
	 */
	public void addRoot(E e) throws IllegalArgumentException {
		if(!(this.isEmpty())) throw new IllegalArgumentException("Tree is not empty!");
		this.root = createNode(e, null, null, null);//Widening cast here.
		this.size = 1;
	}
	public void addLeft(Position<E> p, E e) throws IllegalArgumentException {
		TreeNode<E> nodeP = validate(p);
		if(nodeP.getLeft() != null) {
			this.size++;
			nodeP.setLeftNode(createNode(e,nodeP,null,null));
		}else {
			throw new IllegalArgumentException("This position already has a left node");
		}
	}
	public void addRight(Position<E> p, E e) throws IllegalArgumentException {
		TreeNode<E> nodeP = validate(p);
		if(nodeP.getRight() != null) {
			this.size++;
			nodeP.setRightNode(createNode(e,nodeP,null,null));
		}else {
			throw new IllegalArgumentException("This position already has a right node");
		}
	}
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		TreeNode<E> nodeP = validate(p);
		E buffer = nodeP.getElement();
		nodeP.setElement(e);
		return buffer;
	}
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		TreeNode<E> n = validate(p);
		if(numChildren(p) > 0) //or !(isExternal(p)) or isInternal(p)
			throw new IllegalArgumentException("p must be left");
		else {
			//1) set size first
			this.size = this.size() + t1.size() + t2.size();
			
			//2) check if t1 is empty 
			if(!(t1.isEmpty())) {
				t1.root.setParentNode(n);
				n.setLeftNode(t1.root);
				//t1 is no longer a tree, so adjust its attributes.
				t1.root = null;
				t1.size = 0;
			}
			//3) check if t2 is empty
			if (!(t2.isEmpty())) {
				t2.root.setParentNode(n);
				n.setRightNode(t2.root);
				//t2 is no longer a tree, so:
				t2.root = null;
				t2.size = 0;
			}
		}
	}
	public E remove(Position<E> p) {
		TreeNode<E> rmNode = validate(p);
		if(numChildren(p) == 2)
			throw new IllegalArgumentException("Position passed has 2 children!");
		//If p has 1 child
		TreeNode<E> child = rmNode.getLeft() != null ? rmNode.getLeft() : rmNode.getRight();
		if(child != null)
			child.setParentNode(rmNode.getParent());
		if(rmNode == this.root)
			this.root = child;
		else {
			TreeNode<E> parent = rmNode.getParent();
			if(rmNode == parent.getLeft())
				parent.setLeftNode(child);
			else
				parent.setRightNode(child);
		}
		 this.size--;//update size
		//remove and defunct target node
		E buffer = rmNode.getElement();
		rmNode.setElement(null);
		rmNode.setLeftNode(null);
		rmNode.setRightNode(null);
		rmNode.setParentNode(rmNode); //set parentNode to itself to follow our defined convention of defunct node!
		return buffer;
	}
	/**
	 * General Methods
	 * -isEmpty
	 * is already implemented by AbsractTree class
	 */
	public int size() {
		return this.size;
	}
	/**
	 * Iterator for elements of positions(same thing as PositionList class here)
	 *  &&
	 * Iterable collection of Positions
	 */
	public Iterator<E> iterator() {
		return null;
	}
	public Iterable<Position<E>> positions() {
		return inOrder();
	}	
}
