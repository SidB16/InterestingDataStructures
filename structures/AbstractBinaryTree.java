import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author sid16
 *
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
	public Position<E> sibling(Position<E> p){
		Position<E> parent = parent(p);
		if (parent == null) return null; //bc its root. And root does not have parent.
		if(p == left(parent)) {
			return right(parent);
		}else {
			return left(parent);
		}
	}
	
	/**
	 *Iterative method to count the number of childern of Positon P,
	 */
	public int numChildren(Position<E> p){
		int count = 0;
		if(left(p) != null) count++;
		if(right(p) != null) count++;
		return count;
	}
	/**
	 * Using java.util.Collection<E> which is subclass of Iterable!- to essentially create a snapshot of children.
	 * I.e. create a new Arraylist using the collections framework and return this array! 
	 * (the return type is OK bc ArrayList is sublcass of List which is subclass of Collections which is subclass of Iterable)
	 *@return an iterable collection on positions representing p's children
	 */
	public Iterable<Position<E>> children( Position<E> p){
		List<Position<E>> snapshot = new ArrayList<>(2); 
		if(left(p) != null) snapshot.add(left(p));
		if(right(p) != null) snapshot.add(right(p));
		return snapshot;
	}
	//Inorder is only possible for binary tree bc its relies on explict checks for left and right!
	public List<Position<E>> inOrder(){
		//initialize single container for references.
		List<Position<E>> snapshot = new ArrayList<>();
		if(!(isEmpty()))
			snapshot = inOrderHelper(root(), snapshot);
		return snapshot;
	}
	private List<Position<E>> inOrderHelper(Position<E> p, List<Position<E>>snapshot){
		if(left(p) != null)
			inOrderHelper(left(p), snapshot);
		snapshot.add(p);
		if(right(p) != null)
			inOrderHelper(right(p), snapshot);
		return snapshot;
	}
}
