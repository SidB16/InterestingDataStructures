package A3;
/**
 * 
 */

/**
 * @author sid16
 *Graph ADT
 */
public interface Graph<V, E> {
	public int numVerticies();
	public Iterable<Vertex<V>> verticies();
	public int numEdges();
	public Iterable<Edge<E>> edges();
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v);
	public Vertex<V>[] endVerticies(Edge<E> e);
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e);
	public int outDegree(Vertex<V> v);
	public int inDegree(Vertex<V> v);
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v);
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v);
	public Vertex<V> insertVertex(V v);
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E x);
	public void removeVertex(Vertex<V> v);
	public void removeEdge(Edge<E> edge);
}
