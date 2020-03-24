package A1;

/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Winter 2019
 * Assignment 1: Polygon Hierarchy
 * Section:  Z 
 * Student Name:  Siddharth Bhardwaj 
 * Student eecs account:  sid16
 * Student ID number: 
 **********************************************************/

import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The class SimplePolygon implements the Polygon interface.
 * 
 * It is intended to be further extended by ConvexPolygon.
 */
public class SimplePolygon implements Polygon {

	/********* protected fields ************************/

	protected int n; // number of vertices of the polygon
	protected Point2D.Double[] vertices; // vertices[0..n-1] around the polygon boundary
	
	/********* protected constructors ******************/

	/**
	 * constructor used in the static factory method getNewPoly()
	 * 
	 * @param size
	 *            number of edges (also vertices) of the polygon
	 */
	protected SimplePolygon(int size, Point2D.Double[] vertices) {
		this.n = size;		
		// TODO: place the rest of your code here
		this.vertices = vertices;
		
	}
	
	protected SimplePolygon(int size) {
		//take size and create 0 
		//this is for when User uses newPoly
		//for dealing with user prompts
		this(size, new Point2D.Double [size]);
		
	}
	protected SimplePolygon( SimplePolygon p) {
		//used aggregation for the purpose my use
		//will use this copy construtor in the Convex Poly class
		// GOAL is to use getNewPoly() for convex
		this(p.getSize(), p.vertices );
	}
	/** default no-parameter constructor */
	protected SimplePolygon() {
		//used consturctor chaining
		//initialized a un-verified polygon with 0 verticies
		this(0, new Point2D.Double [0]); 
		// TODO: place your code here
	}

	/********* public getters & toString ***************/

	/**
	 * static factory method constructs and returns an unverified simple-polygon, initialised
	 * according to user provided input data. Runs in O(n) time.
	 * 
	 * @return an unverified simple-polygon instance
	 * @throws NonSimplePolygonException 
	 */
	public static SimplePolygon getNewPoly(String input) throws NonSimplePolygonException {	
		//SORRY FOR COMMENTS BUT I WANTED TO SHOW MY WORK :)
		
		//Method used to handle input!!
		//creating Size "  " Vertices fromat using split
		String[] inputSplit = input.split("  ");
		
		//size = element at 0 index
		int size = Integer.parseInt(inputSplit[0]);
		
		//splitting the verticies in the 2nd array
		//delimited using " " 
		String[] coordinates = inputSplit[1].split(" ");
		
		//creating verticies array of capacity = size
		Point2D.Double[] vertices = new Point2D.Double[size];
		
		//adding the points into 2D.Double array and 
		//appropaitate handle taking coordinates
		for(int i = 0; i < size; i++)
		{
			vertices[i] = new Point2D.Double(Double.parseDouble(coordinates[2*i]), Double.parseDouble(coordinates[2*i + 1]));
		}
		
		//create poly
		SimplePolygon p = new SimplePolygon(size, vertices);
		
		//check for isSimple()
		if (!p.isSimple()) {
			throw new NonSimplePolygonException();
		}//else 
		//if this new poly is simple, then return new Simple Polygon
		return p;
		
	}

	/**
	 * 
	 * @return n, the number of edges (equivalently, vertices) of the polygon.
	 */
	public int getSize() {
		return n; //best practices to use this method to get size 
					//as oppose to use the feild directly
	}
	/**
	 * 
	 * @param i
	 *            index of the vertex.
	 * @return the i-th vertex of the polygon.
	 * @throws IndexOutOfBoundsException
	 *             if {@code i < 0 || i >= n }.
	 */
	public Point2D.Double getVertex(int i) throws IndexOutOfBoundsException {
		try {
			return vertices[i];
		} // catch block will be triggered if i < 0 || i >= n
		catch (IndexOutOfBoundsException e	){
			throw new IndexOutOfBoundsException("Error: IndexOutOfBoundsException");
		}
		 // TODO: replace this line with a try-catch code
	}

	/**
	 * @return a String representation of the polygon in O(n) time.
	 */
	@Override
	public String toString() {
		// used StringBuffer so that I can modify(append) my String
		//based on number of vertices
		StringBuffer sb = new StringBuffer();
		sb.append(" Polygon of Size : " + this.getSize());
		sb.append(" : ");
		//O(n) time for worst case
		for ( Point2D.Double p : vertices) {
			sb.append(" " + " ( " + p.x + " , " + p.y + " ) ");
		}
		return sb.toString();
	}
	/************** utilities *********************/

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 *            three input points.
	 * @return twice the signed area of the oriented triangle (a,b,c). Runs in O(1) time.
	 */
	public static double delta(Point2D.Double a, Point2D.Double b,
			Point2D.Double c) {
		
		//signed area of oriented triangle (a,b,c)
		// is (1/2) * delta(a,b,c)
		//where sign is pos, neg or zero 
		//if orientation is counter-clockwise, clockwise or collinear, respectively.
		//Thus,
		
		//Step 1: calculate signed area of oriented triangle.
		//used destTest
		//O(1) eff
		double singedArea = (0.5) * SimplePolygon.deltaTest(a, b, c); // TODO: replace this line with your code
		
		//Step 2: return twice the signed area
		//O(1) time
		return 2 * singedArea;
	}
	/**
	 * @param a
	 * @param b
	 *            end points of line-segment (a,b).
	 * @param c
	 * @param d
	 *            end points of line-segment (c,d).
	 * @return true if closed line-segments (a,b) and (c,d) are disjoint. Runs in O(1) time.
	 */
	public static boolean disjointSegments(Point2D.Double a, Point2D.Double b,
			Point2D.Double c, Point2D.Double d) {
		return SimplePolygon.disjointTest(a, b, c, d); // TODO: replace this line with your code
	}

	/**
	 * @param i
	 * @param j
	 *            indices of two edges of the polygon.
	 * @return true if the i-th and j-th edges of the polygon are disjoint. Runs in O(1) time.
	 * @throws IndexOutOfBoundsException
	 *             if i or j are outside the index range [0..n-1].
	 */
	public boolean disjointEdges(int i, int j) throws IndexOutOfBoundsException {
		//Edge is line segment that connects 2 cyclically consecutive vertices togather
		//between vertex Vi and it's next vertex V((i+1) mod n)
		try {
			//since getVertex is not static
			//i can acesss it using the instance 
			//through the use of the keyword this! =======> CHECK
			
			//get the endpoints of the edge at i
			Point2D.Double a = this.getVertex(i); 
			Point2D.Double b = this.getVertex((i + 1) % this.getSize());
			
			//get the endpoints of the edge at j
			Point2D.Double c = this.getVertex(j);
			Point2D.Double d = this.getVertex(((j + 1) % this.getSize()));
			
			//perform disjoint test on the endpoints
			//to determine if points of the two edges are disjoint
			//returns true if disjoint 
			return SimplePolygon.disjointTest(a, b, c, d);
			
		} // catch block will be triggered if i < 0 || i >= n
		catch (IndexOutOfBoundsException e	){
			throw new IndexOutOfBoundsException("Error: IndexOutOfBoundsException");
		}
	}

	
	/**
	 * This method verifies whether the claimed "simple-polygon" is indeed simple.
	 * 
	 * @return true if the polygon is simple. Runs in O(n^2) time.
	 */
	public boolean isSimple() {
		//O(n^2) means two for loops on the same array
		//get vertices from start
		boolean value = false;
		if ( this.getSize() == 3) {
			if (SimplePolygon.orientation(this.getVertex(0), this.getVertex(1), this.getVertex(2)).equals("collinear")){
				return false;
			}
			//handle special case
			return true;
		}
		
		//used labels to get out of nested loop 
		outerloop:
		for ( int i = 0; i < this.getSize(); i++) {
			//get vertices from bottom 
			for ( int j = i; j < this.getSize(); j++ ){ 
			
				//use the inequality!!!
				if (  1 < j - i && j - i < this.getSize() - 1 ) {
					
					value =  this.disjointEdges(i, j) == true ? true : false;
					
					if (value == false){ break outerloop ;}
				
				}
				//compare two non-adjacent
				//Simple ==> if no two non-adjecent pairs intersect
				//if any
				
			//if at any point two given edges are not disjoint. 
			//then break 
			}
		}
		return value; // TODO: replace this line with your code
	}

	/************ perimeter & area ***************/

	/**
	 * 
	 * @return the sum of the edge lengths of the polygon. Runs in O(n) time.
	 */
	public double perimeter() {
		double sum = 0.0;

		for (int i = 0; i < this.getSize(); i++) {
			Point2D.Double endPoint1 = this.getVertex(i);
			Point2D.Double endPoint2 = this.getVertex((i+1) % this.getSize());
			sum = sum + SimplePolygon.edgeLength(endPoint1, endPoint2);
		}
		return sum;
	}

	/**
	 * 
	 * @return area of the polygon interior. Runs in O(n) time not counting the simplicity test.
	 * @throws NonSimplePolygonException
	 *             if the polygon is non-simple.
	 */
	public double area() throws NonSimplePolygonException {
		try {
		//check if it is simple
			if (this.isSimple() == false) { throw new NonSimplePolygonException("Error: NonSimplePolygonException");};
			
			double a = 0.0;
			for (int i = 0; i < this.getSize(); i++) {
		//Step1: Calculate the sum
				a = a + SimplePolygon.delta(new Point2D.Double(0, 0), this.getVertex(i), this.getVertex( (i+1) % this.getSize()));
			}
		//Step2: Abs value of sum
		a = Math.abs(a);
		
		//Step3: (1/2) of abs value of sum
		a = (0.5) * a;
		
			return a;
		} catch ( NonSimplePolygonException e	){
			throw new NonSimplePolygonException("Error: NonSimplePolygonException");
		}
	 // TODO: replace this line with a try-catch code
	}

				/************ Test and Helper Methods ***************/
	
	private static double deltaTest(Point2D.Double a, Point2D.Double b,
			Point2D.Double c )
	{
		double xa = a.x; double ya = a.y;
		double xb = b.x; double yb = b.y;
		double xc = c.x; double yc = c.y;
		
		// O(n) time efficiency
		//Yes I admit I could have used recursion or some other clever way
		//to compute this but ........... :P
		double determinent = (xa*((yb * 1) - (1 * yc))) - (ya*( (xb * 1) - (1 * xc))) + (1 *( (xb * yc) - (yb * xc)));
		return determinent;
	}
	
	
	//for utility
	//checks to see if two given closed line segments (a,b) and (c,d) are disjoint 
	//uses orientation() utility method
	//returns true if they are disjoint
	//false if not disjoint. Look at comments in test for more details
	//runs in O(1) time
	private static boolean disjointTest( Point2D.Double a, Point2D.Double b,
			Point2D.Double c, Point2D.Double d) {
		//two close line segments (a,b) & (c, d)
		//inersect IFF one of the two following conditions are verified
		
		//1st condtion
		//If (a,b,c) and (a,b,d) have diffrent orientations
		//AND 
		//If (c,d,a) and (c,d,b) have diffrent orientations
	
		//2nd condition
		//If (a,b,c), (a,b,d), (c,d,a), (c,d,b) are all collinear orientation
		//AND
		//x-projection of (a,b) and (c,d) intersect ==> did not check 
		//the y-projection of (p1,q1) and (p2,q2) intersect ==> did not check
		
		//to check orientation we will use deltaTest()
		String abc = SimplePolygon.orientation(a, b, c);
		String abd = SimplePolygon.orientation(a, b, d);
		String cda = SimplePolygon.orientation(c, d, a);
		String cdb = SimplePolygon.orientation(c, d, b);
		
		if ( !abc.equals(abd) && !cda.equals(cdb)) {
			//diffrent orientations for both
			//they intersect
			//thus not disjointed
			return false; 
		}
		
		//check if a, b, c are collinear and if c lies on segment (a,b)
		//returns false if true because that means they intersect
		if (abc.equals("collinear") &&  onSegment(a,c,b)) {
			return false;
		}
		//same test but for other points
		if (abd.equals("collinear") &&  onSegment(a,d,b)) {
			return false;
		}
		if (cda.equals("collinear") &&  onSegment(c,a,d)) {
			return false;
		}
		if (cdb.equals("collinear") &&  onSegment(c,b,d)) {
			return false;
		}
			//else if does not trigger any of the conditions above
			//it is disjoint
			//thus
			return true;
		
	}
	
	
	//for computational utility
	//used in disjointTest method
	//runs in O(1) time
	//protected so ConvexPolygon can use this method.
	protected static String orientation(Point2D.Double a, Point2D.Double b,
			Point2D.Double c) {
				return deltaTest(a, b, c) == 0 ? "collinear" : ( deltaTest(a,b,c) < 0 ? "clockwise" : "counter-clockwise");
	}
	
	//Utility method to help with collinear check in disjointSegments()
	//return true if c is on closed line segment (a,b)
	//returns false if not 
	private static boolean onSegment(Point2D.Double a, Point2D.Double c, Point2D.Double b ) {
		//if c is on closed line segment (a,b)
		if( c.getX() <= Math.max(a.getX(), b.getX()) && c.getX() >= Math.min(a.getX(), b.getX()) 
		&&	c.getY() <= Math.max(a.getY(), b.getY()) && c.getY() >= Math.min(a.getY(), b.getY())) { 
			return true;
		}//else 
		return false;
	}
		//utility method to help calcuate lenght of a given edge
		//uses the endpoints of the edge
		//retruns the length as a double
		private static double edgeLength(Point2D.Double endPoint1, Point2D.Double endPoint2) {
			//distance() from Point2D class
			return Point2D.distance(endPoint1.getX(), endPoint1.getY(), endPoint2.getX(), endPoint2.getY()); 
		}
}
	
