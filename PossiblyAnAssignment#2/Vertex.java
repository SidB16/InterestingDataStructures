package A3;
/**
 * 
 */

/**Vertex Objects
The vertex object for a vertex v storing element x has instance variables for:
• A reference to element x, to support the getElement( ) method. (Var defined is subclass)
• A reference to the position of the vertex instance in the list V , thereby allow-
ing v to be efficiently removed from V if it were removed from the graph. (Var defined in subclass)
 * @author sid16
 *
 */
public interface Vertex<V> extends Position<V> {
	public V getElement() throws IllegalArgumentException;
}
