package A3;
/**
 * 
 */

/**
 * Edge Objects
The edge object for an edge e storing element x has instance variables for:
• A reference to element x, to support the getElement( ) method. (Var defined in subclass)
• References to the vertex objects associated with the endpoint vertices of e.
These will allow for constant-time support for methods endVertices(e) and (Vars defined in subclass)
opposite(v, e).
• A reference to the position of the edge instance in list E, thereby allowing e
to be efficiently removed from E if it were removed from the graph. (Var defined in subclass)
 * @author sid16
 *
 */
public interface Edge<E>  extends Position<E>{
	public E getElement();
}
