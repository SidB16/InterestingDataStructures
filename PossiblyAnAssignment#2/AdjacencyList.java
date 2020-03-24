package A3;


/**
 * This a my solution to Assignment3. (The commandLineInterface class is where I implement the CLI features for part2)
 * This class is the blueprint for the Adjacency List Data Structure i.e. my solution to Part 1.
 * Furthermore, the public method shortestPathLenghts is my solution to Part 2. 
 * Kindly note that did not complete integrating my Backend with Frontend for part 2. The shortest-connection algorithm is defined and fully implemented however I did not have enough time to finish integration with Commands.
 * 
 * A graph is a collection of (V,E)-pairs, where V is a finite set of vertices and E is a binary relation on V called the Edge set.
 * This data uses a 4 different types of collections:
 * 1) It uses a LinkedPositional List to store the references to Vertex<V> and Edge<E> objects in a List Structure.
 * 2) Additionally this implementation uses a hash table, specifically implemented as the ChainedHashMap class, to store Vertex<V> objects for efficient O(1)expected efficiency. (I used the MAD compression strategy for my hash function). Kindly refer to my implementation for more comments. 
 * 3) Each Vertex<E> object maintains a list of Edge<E> it incident to called the incident collection, through which we can access any adjacent vertex also.
 * 4) Each Edge<E> object maintains a array of Vertices i.e. its Endpoints. For robustness, correctness and efficiency of implementation.
 * 
 * Few Additional things to note:
 * 1) Each Position i.e. Entry<k,v> in the PositionalList Primary Collection of Vertices and Edges and the Secondary Collection called the incident collection, are location aware.
 * 2) Vertex<E> object stores elements of generic type V and Edge<E> object stores element of generic type E.
 *  
 *  Method										Running Time
 *  numVertices( ), numEdges( ) 					O(1)
 *  vertices( )										O(n); n is the number of vertices
 *  edges( )										O(m); m is the number of edges
 *  getEdge(u, v)									O(min(deg(u),deg(v))); but for my implementation it depends of deg(u)
 *  outDegree(v), inDegree(v)						O(1)
 *  outgoingEdges(v), incomingEdges(v) 				O(deg(v))
 *  insertVertex(x), insertEdge(u, v, x) 			O(1)
 *  removeEdge(e)									O(1)
 *  removeVertex(v)									O(deg(v))
 * @author sid16
 *
 */
public class AdjacencyList<V,E> implements Graph<V,E> {
		//1) Nested Classes
		/**
		 * A vertex of an adjacency list representation.
		 * @author sid16
		 *
		 * @param <V> generic type element held by InnerVertex
		 */
		private class InnerVertex<V> implements Vertex<V>{
			//Feilds 
			private V element; // reference to element stored
			private Position<Vertex<V>> pos; //reference to position of this InnerVertex instance in PositionalList V ==> Location aware property
			private PositionalList<Edge<E>> outgoing, incoming; // incident collection ==> Secondary collection 
			//Constructors
			public InnerVertex(V elem, boolean graphIsDirected) { //boolean accounts for Directed or Undirected Graph!
				this.element = elem;
				this.outgoing = new LinkedPositionalList< >();
				if(graphIsDirected)
					this.incoming = new LinkedPositionalList< >(); // or ChainHashMap<Vertex<V>,Edge<E>>
				else
					this.incoming = this.outgoing;
			}
			//Getters
			/**
			 * Returns the element associated with the vertex
			 *@return the element associated with the vertex
			 */
			public V getElement() {
				return this.element;
			}
			/**
			 * Returns the position associated with the vertex
			 * @return the position associated with the vertex
			 */
			public Position<Vertex<V>> getPosition(){
				return this.pos;
			}
			
			/**
			 * Returns the reference to the underlying list of incoming edges
			 * @return the reference to the underlying list of incoming edges
			 */
			public PositionalList<Edge<E>> getIncoming(){
				return this.incoming;
			}
			/**
			 * Returns reference to the underlying list of outgoing edges
			 * @return reference to the underlying list of outgoing edges
			 */
			public PositionalList<Edge<E>> getOutgoing(){
				return this.outgoing;
			}
			//Setters
			
			/**
			 * Stores the position of this vertex within the graph's vertex list.
			 * @param newPos new position of this vertex
			 */
			public void setPosition(Position<Vertex<V>> newPos) {
				this.pos = newPos;
			}
		}
		/**
		 * A edge of an adjacency list representation.
		 * @author sid16
		 *
		 * @param <E> generic type element help by InnerEdge
		 */
		private class InnerEdge<E> implements Edge<E>{
			//Feilds
			private E element;
			private Position<Edge<E>> primaryPosition; //Position of this InnerEdge instance in PositionalList<Edge<E>> E. ==> Location Aware property established here.
			private Position<Edge<E>> originPosition; //Reference to the incidence list of origin vertex ==> Location Aware property established here.
			private Position<Edge<E>> destinationPosition; //Reference to the incidence list of destination vertex. Location Aware property established here.
			private Vertex<V>[] endpoints; //Secondary collection i.e. array of endpoints of edges
			//Constructor
			public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {//creates an instance of InnerEdge from u to v
				this.element = elem;
				this.endpoints = (Vertex<V>[]) new Vertex[]{u,v}; //create in memory an contigious array of Vertex[] then cast it specify that this contigious memory block will hold elements of type V (by explicity casting call Vertex<V>[])
				//Array of length 2 is created from the above statment. Be mindful of the array instantiation syntax!
			}
			//Getters
			/**
			 *Returns the element stroed by this edge
			 */
			public E getElement() {
				return this.element;
			}
			/**
			 * Returns the reference to the position of edge stored in the list of edges
			 * @return he reference to the position of edge stored in the list of edges
			 */
			public Position<Edge<E>> getPrimaryPosition(){
				return this.primaryPosition;
			}
			/**
			 * @return reference to position of edge in list of outgoing edges of origin endpoint
			 */
			public Position<Edge<E>> getOriginPosition(){
				return this.originPosition;
			}
			/**
			 * @return reference to position of edge in list of outgoing edges of destination endpoint
			 */
			public Position<Edge<E>> getDestinationPosition(){
				return this.destinationPosition;
			}
			/**
			 * @return Reference to endpoints array
			 */
			public Vertex[] getEndpoints() {
				return this.endpoints;
			}
			//Setters
			/**
			 * Stores position of this edge within graph's edge list
			 * @param newPos new position to store
			 */
			public void setPrimaryPosition(Position<Edge<E>> newPos) {
				this.primaryPosition = newPos;
			}
			/**
			 * Stores position of this edge within origin's outgoing edge list
			 * @param newPos new Position to store
			 */
			public void setOriginPosition(Position<Edge<E>> newPos) {
				this.originPosition = newPos;
			}
			/**
			 * Stores position of this edge within destinations's  incoming edge list
			 * @param newPos new position to store
			 */
			public void setDestinationPosition(Position<Edge<E>> newPos) {
				this.destinationPosition = newPos;
			}
		}
		/************************************************************OUTER CLASS STARTS HERE************************************************************/
	//2) Feilds 
	private boolean isDirected; //to properly handle directed and in-directed graphs
	private ChainHashMap<V, Vertex<V>> vertexTable = new ChainHashMap< >();//First Primary Collection for efficient access to Vertices. ==> hash table of verticies
	private PositionalList<Vertex<V>> verticies  = new LinkedPositionalList<>(); //Second Primary collection to hold vertices. Takes more space but is personally convenient for me :P
	private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();//Primary collection of Edges i.e. List of Edges
	// 3) Constructor
	public AdjacencyList(boolean directed) {
		this.isDirected = directed;
		// list of verticies and edges is null! thus nothing
	}
	// 4) Public methods(Getters, Setters, Query, Mutators and Genenral Methods)
	/**
	 * @return the number of vertices of the graph
	 */
	public int numVerticies() {
		return this.verticies.size();
	}

	/**
	 * @return the number of edges of the graph
	 */
	public int numEdges() {
		return this.edges.size();
	}

	/**
	 * @return the verticies of the graph as an iterable collection
	 */
	public Iterable<Vertex<V>> verticies() {
		return this.verticies;
	}

	/**
	 * @return the edges of the graph as an iterable collection
	 */
	public Iterable<Edge<E>> edges() {
		return this.edges;
	}

	/**
	 * @param vertex the destination vertex
	 * @return the number of edges for which this vertex param is destination
	 *         vertex.
	 */
	public int inDegree(Vertex<V> vertex) {
		InnerVertex<V> v = validate(vertex);
		return v.incoming.size();
	}

	/**
	 * @param vertex the origin vertex
	 * @return the number of edges for which this vertex param is a origin vertex
	 */
	public int outDegree(Vertex<V> vertex) {
		InnerVertex<V> v = validate(vertex);
		return v.outgoing.size();
	}

	/**
	 * @param vertex the origin vertex
	 * @return an iterable collection of edges for which this param vertex is the
	 *         origin
	 */
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
		InnerVertex<V> v = validate(vertex);
		return v.outgoing;// Returns Edges part of the Outgoing list for this given vertex
	}

	/**
	 * @param vertex the incoming vertex
	 * @return an iterable collection of edges for which this param vertex is the
	 *         destination
	 */
	public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
		InnerVertex<V> v = validate(vertex);
		return v.incoming;// Returns Edges part of the Incoming list for this given vertex

	}

	/**
	 * Returns the edge from u to v, or null if they are not adjacent
	 * 
	 * @param u origin vertex
	 * @param v destination vertex
	 * @return returns edge from u to v, or null if they are not adjacent
	 */
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
		InnerVertex<V> origin = validate(u);
		Edge<E> targetEdge = null;
		for (Edge<E> e : origin.getOutgoing())
			if (validate(e).endpoints[1] == v) // hidden validate here to convert from Edge to InnerEdge to access
												// endpoints.
				targetEdge = e;
		return targetEdge; // will be null if not adjacent!
	}

	/**
	 * @param element key used to locate corresponding value i.e. Vertex<E> in
	 *                hashtable
	 * @return the reference to Vertex<V> object that stores V
	 */
	public Vertex<V> getVertex(V element) {// Using the chained hash table here to access Vertex<V> using V. Note this
											// is an additional I addded just for this class and is not not part of the
											// Graph Interface.
		return this.vertexTable.get(element);
	}

	/**
	 * Returns the vertices of edge e as an array of length two
	 * 
	 * @param edge whose endpoints are to be returned
	 * @return the reference to endpoints array of edge
	 */
	public Vertex<V>[] endVerticies(Edge<E> edge) {
		InnerEdge<E> e = validate(edge);
		return e.endpoints;
	}

	/**
	 * Returns the vertex that is opposite vertex v on edge e
	 * 
	 * @param v    vertex incident on edge
	 * @param edge to which vertex v is incident
	 * @return reference to vertex instance that is opposite to v on the given edge
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> edge) throws IllegalArgumentException {
		InnerEdge<E> e = validate(edge);
		Vertex<V>[] endpoints = e.getEndpoints();
		if (endpoints[0] == v)
			return endpoints[1];
		else if (endpoints[1] == v)
			return endpoints[0];
		else
			throw new IllegalArgumentException("v is not incident to this edge");
	}

	/**
	 * Inserts and returns a new vertex with the given element.
	 * 
	 * @param element the element of new vertex instance
	 * @return the reference to the new vertex object created
	 */
	public Vertex<V> insertVertex(V element) {
		InnerVertex<V> newVertex = new InnerVertex<>(element, this.isDirected); // using the isDirected global var
		newVertex.setPosition(this.verticies.addLast(newVertex)); // Very Interesting what is happening here. 1) we are  adding new Vertex to PositionalList of verticies and 2) at the same time we are setting the pos of InnerVertex newVertex by using the setter methods of InnerVertex.
		this.vertexTable.put(element, newVertex);// Also Put into hash table for quick reference
		return newVertex;
	}

	/**
	 * Inserts and returns a new edge between u and v, storing given element.
	 * 
	 * @param u origin vertex
	 * @param v destination vertex
	 * @param e element to be stored in new edge instance
	 * @return returns edge from u to v, or null if they are not adjacent
	 */
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
		if (getEdge(u, v) == null) {
			InnerEdge<E> e = new InnerEdge<>(u, v, element);
			e.setPrimaryPosition(this.edges.addLast(e)); // Again interesting stuff here
			InnerVertex<V> origin = validate(u);
			InnerVertex<V> destination = validate(v);
			e.setOriginPosition(origin.getOutgoing().addLast(e)); // Set the origin position of edge as it's position in
																	// the origin incidence list
			e.setDestinationPosition(destination.getIncoming().addLast(e)); // set the destination position of edge as
																			// it's position in the destination
																			// incidence list
			return e;
		} else
			throw new IllegalArgumentException("Edge between u and v exsists!");
	}

	/**
	 * Removes a vertex and all its incident edges from the graph
	 * 
	 * @param v the vertex to be removed
	 */
	public void removeVertex(Vertex<V> v) {
		InnerVertex<V> vert = validate(v);
		for (Edge<E> e : vert.getOutgoing())
			removeEdge(e);
		for (Edge<E> e : vert.getIncoming())
			removeEdge(e);
		this.verticies.remove(vert.getPosition());
	}

	/**
	 * Removes a edge and all it's references from all primary and secondary
	 * collections.
	 * 
	 * @param edge the edge to be removed
	 */
	public void removeEdge(Edge<E> edge) {
		InnerEdge<E> e = validate(edge);
		InnerVertex<V> origin = validate(e.endpoints[0]);
		InnerVertex<V> dest = validate(e.endpoints[1]);
		origin.getOutgoing().remove(e.getOriginPosition());
		dest.getIncoming().remove(e.getDestinationPosition());
		e.endpoints = null;
		this.edges.remove(e.getPrimaryPosition());
	}
	/**
	 * Computes shortest-path distances from src vertex to all reachable vertices of.
	 * (SOLUTION TO QUESTION 2!:) )
	 * My implementation of the shortest path i.e. shortest connection algorithm models Dijkstra's algorithm, 
	 * for computing the distance from a source s to all vertices in a Weighted Graph with n vertices and m edges. 
	 * Runs in O(n^2) time! 
	 * 
	 * It takes a graph and designated source vertex as parameters.
	 * It returns a map, named cloud, storing the shortest-path distance d(s,v) for each vertex v that is reachable from the source.
	 * I use a data structure that we studied in this course and one that I implemented with reference to the course textbook, called the HeapAdaptablePriorityQueue.(Kindly refer to the interface or concerete class for more details)
	 * I also again rely on hash-based map which I implemented before, specifically the ChainHashMap, to store the mapping of v to it's distance bound D[v] and its corresponding Adaptable PQ entry.
	 * Note, this implementation does not handle non-negative weight. 
	 * Thus, explicit PRECONDITION: no negative weights!
	 * 
	 * Also, The pseudocode for Dijkstra’s algorithm begins by assigning D[v] = ∞ for each v other than the source.
	 * I rely on special value Integer.MAX VALUE in Java
	 * 
	 * @param g Graph in which path is to be found
	 * @param src source vertex in Graph g
	 * @return a map, named cloud, storing the shortest-path distance d(s,v) for each vertex v that is reachable from the source.
	 */
	public static<V> Map<Vertex<V>, Integer> shortestPathLenghts(Graph<V,Integer> g, Vertex<V> src){
		//d.get(v) is upper bound on distance from src to v
		Map<Vertex<V>,Integer> d = new ChainHashMap< >();
		
		//map reachable v to its d value
		Map<Vertex<V>,Integer> cloud = new ChainHashMap< >();
		
		//pq will have vertices as elements, with d.get(v) as key
		AdaptablePriorityQueue<Integer, Vertex<V>> pq; 
		pq = new HeapAdaptablePriorityQueue< >();
		
		//maps from vertex to its pq locator 
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens;
		pqTokens = new ChainHashMap< >();
		
		//for each vertex v of the graph, add an entry to the PQ.
		//source has distance of 0 and all others have infinite distance
		for(Vertex<V> v : g.verticies()) {
			if(v == src)
				d.put(v, 0);
			else
				d.put(v, Integer.MAX_VALUE);
			pqTokens.put(v, pq.insert(d.get(v), v)); //save entry for future updates
		}
		while(!pq.isEmpty()) { //now being adding reachable verticies to the cloud
			Entry<Integer, Vertex<V>> entry  = pq.removeMin();
			int key = entry.getKey();
			Vertex<V> u = entry.getValue();
			cloud.put(u, key);
			pqTokens.remove(u);
			for(Edge<Integer> e : g.outgoingEdges(u)) {
				Vertex<V> v = g.opposite(u, e);
				if(cloud.get(v) == null) {
					//peform step on edge
					int wgt = e.getElement();
					if(d.get(u) + wgt < d.get(v)) { //better path to v?
						d.put(v, d.get(u) + wgt);//update the distance
						pq.replaceKey(pqTokens.get(v), d.get(v));//update pq entry
					}
				}
			}
		}
		return cloud; //only includes reachable verticies
	}
	// 5) Private Utility Methods
	/**
	 * A utility method to validate Vertex as InnerVertex
	 * 
	 * @param vertex the vertex to be validated
	 * @return validated InnerVertex
	 */
	private InnerVertex<V> validate(Vertex<V> vertex) {
		if (!(vertex instanceof InnerVertex))
			throw new IllegalArgumentException("Invalid Vertex!");
		InnerVertex<V> rtnVertex = (InnerVertex<V>) vertex; // explicit statment requred for downcasting!
		return rtnVertex;
	}

	/**
	 * A utility method to validate Edge as InnerEdge
	 * 
	 * @param edge the edge to be validated
	 * @return validated InnerEdge
	 */
	private InnerEdge<E> validate(Edge<E> edge) {
		if (!(edge instanceof InnerEdge))
			throw new IllegalArgumentException("Invalid Edge");
		InnerEdge<E> rtnEdge = (InnerEdge<E>) edge;
		return rtnEdge;
	}
}
