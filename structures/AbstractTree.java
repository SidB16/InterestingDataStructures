import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author sid16
 *
 */
public abstract class AbstractTree<E> implements Tree<E> {
	public boolean isInternal(Position<E> p) { return numChildren(p) > 0;	}
	public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }
	public boolean isRoot(Position<E> p){return p == root(); }
	public boolean isEmpty() { return size()==0; }
	/**
	 * General Tree traversal algorithms
	 * 1) preorder
	 * 2) postorder
	 */
	public List<Position<E>> preOrder() {
		//Create a single container for references.
		List<Position<E>> snapshot = new ArrayList<Position<E>>();
		//if this is tree is non-empty
		if(!(isEmpty())){
			snapshot = preOrderHelper(root(),snapshot);
		}
		return snapshot;
	}
	private List<Position<E>> preOrderHelper(Position<E> p, List<Position<E>> snapshot) {
		//Strategy: node is visited before it's children.
		snapshot.add(p);
		for(Position<E> c : children(p))
			preOrderHelper(c, snapshot);
		return snapshot;	
	}
	public List<Position<E>> postOrder(){
		List<Position<E>> snapshot = new ArrayList<Position<E>>();
		if(!(isEmpty()))
			snapshot = postOrderHelper(root(),snapshot);
		return snapshot;
	}
	private List<Position<E>> postOrderHelper(Position<E> p, List<Position<E>> snapshot){
		//Strategy: node is visited after its parents.
		for(Position<E> c : children(p))
			postOrderHelper(c,snapshot);
		snapshot.add(p);
		return snapshot;
	}
}
