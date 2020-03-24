package A3;
/**
 * 
 */

/**
 * We use positional lists to represent each of the primary lists V and E, as originally described in the edge list representation.
 * For each vertex v, we use a hash-based map to represent the secondary incidence map I(v).
 * To gracefully support both undirected and directed graphs, each vertex maintains two different map references: outgoing and incoming.
 * In the directed case, these are initialized to two distinct map instances, representing I out (v) and I in (v). Else the reference vars point to the same map instance, for undirected graph.
 * 
 * @author sid16
 *
 */
//Position Aware Entries are only possible for a Node. Where for linked representation of arraylist it(Node) has a third feild for index &
//For a linkedlist-based implementation where node points back to the position(i.e. node) pointing to it.
public class AdjacencyMapGraph<V, E> implements Graph<V,E> {
	//1) Nested Classes
	/**
	 * A Graph is collection of (V,E)-pairs.
	 * Thus, below we define two private nested classes for InnerVertex and  InnerEdge. The reason behind them behind inner is b/c they are nested classes inner classes!
	 */
	private class InnerVertex<V> implements Vertex<V>{
		//Feilds 
		private V element; // reference to element stored
		private Position<Vertex<V>> pos; //reference to position of this InnerVertex instance in PositionalList V
		private Map<Vertex<V>,Edge<E>> outgoing, incoming;
		//Constructors
		public InnerVertex(V elem, boolean graphIsDirected) { //boolean accounts for Directed or Undirected Graph!
			this.element = elem;
			this.outgoing = new ChainHashMap< >();
			if(graphIsDirected)
				this.incoming = new ChainHashMap< >(); // or ChainHashMap<Vertex<V>,Edge<E>>
			else
				this.incoming = this.outgoing;
		}
		//Getters
		public V getElement() {
			return this.element;
		}
		public Position<Vertex<V>> getPosition(){
			return this.pos;
		}
		public Map<Vertex<V>, Edge<E>> getIncoming(){
			return this.incoming;
		}
		public Map<Vertex<V>, Edge<E>> getOutgoing(){
			return this.outgoing;
		}
		//Setters
		public void setPosition(Position<Vertex<V>> newPos) {
			this.pos = newPos;
		}
	}
	private class InnerEdge<E> implements Edge<E>{
		//Feilds
		private E element;
		private Position<Edge<E>> pos; //poisition of this InnerEdge intance in PositionalList<Edge<E>> E.
		private Vertex<V>[] endpoints;
		//Constructor
		public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {//creates an instnace of InnerEdge from u to v
			this.element = elem;
			this.endpoints = (Vertex<V>[]) new Vertex[]{u,v}; //create in memory an contigious array of Vertex[] then cast it specify that this contigious memory block will hold elements of type V (by explicity casting call Vertex<V>[])
			//Array of length 2 is created from the above statment. Be mindful of the array instantiation syntax!
		}
		//Getters
		public E getElement() {
			return this.element;
		}
		public Position<Edge<E>> getPosition(){
			return this.pos;
		}
		public Vertex[] getEndpoints() {
			return this.endpoints;
		}
		//Setters
		public void setPosition(Position<Edge<E>> newPos) {
			this.pos = newPos;
		}
	}
	//2) Feilds
	
	private boolean isDirected;
	private PositionalList<Vertex<V>> verticies  = new LinkedPositionalList<>();
	private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();
	//3) Constructor 
	public AdjacencyMapGraph(boolean directed) {
		this.isDirected = directed; 
		//list of verticies and edges is null! thus nothing
	}
	//4) Public methods(Getters, Setters, Query, Mutators and Genenral Methods)
	public int numVerticies() {
		return this.verticies.size();
	}
	public int numEdges() {
		return this.edges.size();
	}
	public Iterable<Vertex<V>> verticies(){
		return this.verticies;
	}
	public Iterable<Edge<E>> edges(){
		return this.edges;
	}
	public int inDegree(Vertex<V> vertex) {
		InnerVertex<V> v = validate(vertex);
		return v.incoming.size();
	}
	public int outDegree(Vertex<V> vertex) {
		InnerVertex<V> v = validate(vertex);
		return v.outgoing.size();
	}
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex){
		InnerVertex<V> v = validate(vertex);
		return v.outgoing.values();
	}
	public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex){
		InnerVertex<V> v = validate(vertex);
		return v.incoming.values();
		
	}
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v ){
		InnerVertex<V> origin = validate(u);
		return origin.getOutgoing().get(validate(v));
	}
	public Vertex<V>[] endVerticies(Edge<E> edge){
		InnerEdge<E> e = validate(edge);
		return e.endpoints;
	}
	public Vertex<V> opposite(Vertex<V> v, Edge<E> edge) throws IllegalArgumentException{
		InnerEdge<E> e = validate(edge);
		Vertex<V>[] endpoints = e.getEndpoints();
		if(endpoints[0] == v)
			return endpoints[1];
		else if(endpoints[1] == v)
			return endpoints[0];
		else
			throw new IllegalArgumentException("v is not incident to this edge");
	}
	public Vertex<V> insertVertex(V element){
		InnerVertex<V> newVertex = new InnerVertex< >(element, this.isDirected); //using the isDirected global var
		newVertex.setPosition(this.verticies.addLast(newVertex)); //Very Interesting what is happening here. 1) we are adding new Vertex to PositionalList of verticies and 2) at the same time we are setting the pos of InnerVertex newVertex by using the setter methods of InnerVertex.
		return newVertex;
	}
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException{
		if(getEdge(u,v) == null) {
			InnerEdge<E> e = new InnerEdge< >(u,v,element);
			e.setPosition(this.edges.addLast(e)); //Again interesting stuff here
			InnerVertex<V> origin = validate(u);
			InnerVertex<V> destination = validate(v);
			origin.getOutgoing().put(v, e);
			destination.getIncoming().put(u, e);
			return e;
		}else
			throw new IllegalArgumentException("Edge between u and v exsists!");
	}
	public void removeVertex(Vertex<V> v) {
		InnerVertex<V> vert = validate(v);
		for(Edge<E> e: vert.getOutgoing().values())
			removeEdge(e);
		for(Edge<E> e: vert.getIncoming().values())
			removeEdge(e);
		this.verticies.remove(vert.getPosition());
	}
	public void removeEdge(Edge<E> edge) {
		InnerEdge<E> e = validate(edge);
		InnerVertex<V> origin = validate(e.endpoints[0]);
		InnerVertex<V> dest = validate(e.endpoints[1]);
		origin.getOutgoing().remove(dest);
		dest.getIncoming().remove(origin); 
		this.edges.remove(e.getPosition());
	}
	//5) Private Utility Methods
	private InnerVertex<V> validate(Vertex<V> vertex){
		if(!(vertex instanceof InnerVertex))
			throw new IllegalArgumentException("Invalid Vertex!");
		InnerVertex<V> rtnVertex = (InnerVertex<V>) vertex; //explicit statment requred for downcasting!
		return rtnVertex;
	}
	private InnerEdge<E> validate(Edge<E> edge){
		if(!(edge instanceof InnerEdge))
			throw new IllegalArgumentException("Invalid Edge");
		InnerEdge<E> rtnEdge = (InnerEdge<E>) edge;
		return rtnEdge;
	}
}
