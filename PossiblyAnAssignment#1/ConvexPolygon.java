package A1;

import java.awt.geom.Point2D;

/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Winter 2019
 * Assignment 1: Polygon Hierarchy
 * Section:  Z 
 * Student Name:  Siddharth Bhardwaj 
 * Student eecs account:  sid16
 * Student ID number:  
 **********************************************************/

/**
 * The class ConvexPolygon extends SimplePolygon.
 * 
 * Comments are in-code. Sincerely Apologize.
 * Kindly refer to my comments for help or insight!
 * I stated my steps and showed my though process
 * for solving these problems!
 * 
 * @author Andy Mirzaian
 */
public class ConvexPolygon extends SimplePolygon {
	// TODO: add your code here
	
	public ConvexPolygon(SimplePolygon p) {
		super(p);
		
	}
	public static ConvexPolygon getNewPoly(String input) throws NonSimplePolygonException {
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
				vertices[i] = new Point2D.Double(
						Double.parseDouble(coordinates[2*i]), Double.parseDouble(coordinates[2*i + 1]));
			}
			
			//create poly
			SimplePolygon p1 = new SimplePolygon(size, vertices);
			ConvexPolygon p2 = new ConvexPolygon(p1);
			
			//check for isSimple()
			if (!p1.isSimple() && p2.isConvex()) {
				throw new NonSimplePolygonException();
			}//else 
			//if this new poly is simple, then return new Simple Polygon
			//AND so 
			
			
			//check for isConvex()
			if (!p2.isConvex()) {
				//sorry I thought about using illegal argument exception
				//but because of the way I implemented my tester
				//it works!
				throw new NonSimplePolygonException("This poly is not Convex");
			}
			return p2;
			
		}
	
	//PRECONDITION ==> polygon is simple. 
	//this means the client must ensure isSimple() condition is immediately 
	//true before method is invoked 

	//is convex is every TURN is consistently 
	//in the same orientation  (clockwise or counter-clockwise)
	//as we walk around the polygon boundy 
	public boolean isConvex() {	
		boolean value = true;
		
		//if size = 3 and passed the isSimple condition then 
		//it is a non-collinear polygon, aka Triangle,
		//which is convez
		if ( this.getSize() == 3) {
			if (this.isSimple() != true) {
				return false;
			}//else
			return true;
		} 
		String zeroIndexOrientation = SimplePolygon.orientation(this.getVertex(0),
				this.getVertex((0 + 1) % this.getSize()),
				this.getVertex((1 + 1) % this.getSize()));
		
			//starting at index 1 not 0
			for ( int i = 1; i < this.getSize(); i++) {
			//first point
			Point2D.Double a = this.getVertex(i);
			
			//second point with respect to first point
			int j = (i+1) % this.getSize();
			Point2D.Double b = this.getVertex(j);
			
			//third point with respect to second point, 
			//which is with respect to first
			Point2D.Double c = this.getVertex((j + 1) % this.getSize());
			
			if ( !SimplePolygon.orientation(a, b, c).equals(zeroIndexOrientation)) {
				value = false;
				break;
			}
		}
		return value; // TODO: replace this line with your code
	}
}
