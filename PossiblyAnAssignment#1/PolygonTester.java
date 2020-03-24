package A1;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;

/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Winter 2019
 * Assignment 1: Polygon Hierarchy
 * Section:  Z 
 * Student Name:  Siddharth Bhardwaj 
 * Student eecs account:  sid16
 * Student ID number:  213584396
 **********************************************************/

/**
 * PolygonTester should enable a thorough testing of the polygon hierarchy.
 * 
 * It should provide an easy to read input-output recording of the test cases.
 * 
 * The student should also submit these recorded test results in TestIO.txt file as part
 * of Assignment1.
 * 
 * Comments are in-code. Sincerely Apologize.
 * Kindly refer to my comments for help or insight!
 * I stated my steps and showed my though process
 * for solving these problems!
 * 
 * @author Andy Mirzaian
 */
public class PolygonTester {
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	// TODO: place additional test-helper methods here if you like
	public static void main(String[] args) {
		// TODO: place your tester code here
		double area, perimeter;
		String sim;
		/*Poly1: 5  8.9 21.8 29.1 8.8 39.2 20.3 14 11 28 25
		Poly2: 7  28 2 31 5 28 10 14 14 5 10 8 4 18 1
		Poly3: 9  6 10 20 3 23 3 23 8 27 3 30 3 20 15 16 5 20 14
		Poly4: 13  5 6 13 2 12 6 20 2 16 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6
		Poly5: 13  5 6 13 2 12 6 20 2 18 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6
		Poly6: 22  14 7 15 8 17 7 17 5 15 6 14 4 12 6 11 9 15 11 7 12 8 11 7 9 10 11 8 6 10 5 11 3 16 3 18 4 19 8 16 9 14 9 13 8
		Poly7: 4  6 1 9 5 5 8 2 4 */
		
		
		
		//using online tools I was able deterimine the expected output for:
		// 1) isSimple
		// 2) area
		// 3) perimeter
	
		//Note: through using and testing the isSimple() method, I am able to test 
		//my Disjoint Segments and Disjoint Edges method!
		//But I still included some test cases
		
		
		//A new SimplePolygon
		SimplePolygon Poly1;
		try {
			//Just change the statement below to test other Polys
			Poly1 = SimplePolygon.getNewPoly("5  8.9 21.8 29.1 8.8 39.2 20.3 14 11 28 25");
			
			//put print statment inside try block, and not outside, so it does not print null
			System.out.println( "Your input was ==> " + Poly1.toString());
			
			sim = Poly1.isSimple() == true ? "Simple" : "not Simple";
			System.out.println("Polygon1 is : " + sim + " ; " + "Expected was  : Non-simple" );	
		
			area = Poly1.area();	
			System.out.println("Area of Polygon1 is : " + df2.format(area) + " ;" + "Expected Area is : " + "INSERT ACTUAL AREA" );
			
			perimeter= Poly1.perimeter();
			System.out.println("Perimeter of Polygon1 is : " + df2.format(perimeter) + "Expected Perimeter is : " + "INSERT ACTUAL Perimeter" );
		
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon1 is non-simple or invalid ");
		}
		
		//FOR FORMATTING
		System.out.println(" ");
		
		SimplePolygon Poly2;
		try {
			//Just change this statment below to test other Polys
			Poly2 = SimplePolygon.getNewPoly("7  28 2 31 5 28 10 14 14 5 10 8 4 18 1");
			
			System.out.println( "Your input was ==> " + Poly2.toString());
			
			sim = Poly2.isSimple() == true ? "Simple" : "not Simple";
			System.out.println("Polygon2 is : " + sim + " ; " + "Expected was  : Simple" );	
		
			area = Poly2.area();	
			System.out.println("Area of Polygon2 is : " + df2.format(area) + " ; " + "Expected Area is : " + "INSERT ACTUAL AREA" );
			
			perimeter= Poly2.perimeter();
			System.out.println("Perimeter of Polygon2 is : " + df2.format(perimeter) + "Expected Perimeter is : " + "INSERT ACTUAL Perimeter" );
			
		
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon2 is non-simple or invalid");
		}
		
		//FOR FORMATTING
				System.out.println(" ");
				
		SimplePolygon Poly3;
		try {
			Poly3 = SimplePolygon.getNewPoly("9  6 10 20 3 23 3 23 8 27 3 30 3 20 15 16 5 20 14");
			
			System.out.println( "Your input was ==> " + Poly3.toString());
			
			sim = Poly3.isSimple() == true ? "Simple" : "not Simple";
			System.out.println("Polygon3 is : " + sim + " ; " + "Expected was  : Non-simple" );	
		
			area = Poly3.area();	
			System.out.println("Area of Polygon3 is : " + df2.format(area) + " ; " + "Expected Area is : " + "INSERT ACTUAL AREA" );
			
			perimeter= Poly3.perimeter();
			System.out.println("Perimeter of Polygon3 is : " + df2.format(perimeter) + " ; " + "Expected Perimeter is : " + "INSERT ACTUAL Perimeter" );
		
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon3 is non-simple or invalid");
		}
		
		//FOR FORMATTING
				System.out.println(" ");
		SimplePolygon Poly4;
		
		try {
			Poly4 = SimplePolygon.getNewPoly("13  5 6 13 2 12 6 20 2 16 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6");
			
			System.out.println( "Your input was ==> " + Poly4.toString());
			
			sim = Poly4.isSimple() == true ? "Simple" : "not Simple";
			System.out.println("Polygon4 is : " + sim + " ; " + "Expected was  : Non-simple" );	
		
			area = Poly4.area();	
			System.out.println("Area of Polygon4 is : " + df2.format(area) +  " ; " + "Expected Area is : " + "INSERT ACTUAL AREA" );
			
			perimeter= Poly4.perimeter();
			System.out.println("Perimeter of Polygon4 is : " + df2.format(perimeter) + " ; " + "Expected Perimeter is : " + "INSERT ACTUAL Perimeter" );
	
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon4 is non-simple or invalid");
		}
		
		//FOR FORMATTING
				System.out.println(" ");
				
		SimplePolygon Poly5;
		try {
			Poly5 = SimplePolygon.getNewPoly("13  5 6 13 2 12 6 20 2 18 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6");
			
			System.out.println( "Your input was ==> " + Poly5.toString());
			
			sim = Poly5.isSimple() == true ? "Simple" : "not Simple";
			System.out.println("Polygon5 is : " + sim + " ; " + "Expected was  : Simple" );	
		
			area = Poly5.area();	
			System.out.println("Area of Polygon5 is : " + df2.format(area) +  " ; " + "Expected Area is : " + "82.0" );
			
			perimeter= Poly5.perimeter();
			System.out.println("Perimeter of Polygon5 is : " + df2.format(perimeter) + " ; " + "Expected Perimeter is : " + "95.11" );
			
		
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon5 is non-simple or invalid");
		}
		
		//FOR FORMATTING
				System.out.println(" ");
				
		
		SimplePolygon Poly6;
		try {
			Poly6 = SimplePolygon.getNewPoly("22  14 7 15 8 17 7 17 5 15 6 14 4 12 6 11 9 15 11 7 12 8 11 7 9 10 11 8 6 10 5 11 3 16 3 18 4 19 8 16 9 14 9 13 8");
			
			System.out.println( "Your input was ==> " + Poly6.toString());
			
			sim = Poly6.isSimple() == true ? "Simple" : "not Simple";
			System.out.println("Polygon6 is : " + sim + " ; " + "Expected was  : Simple" );	
		
			area = Poly6.area();	
			System.out.println("Area of Polygon6 is : " + df2.format(area) +  " ; " + "Expected Area is : " + "50.0" );
			
			perimeter= Poly6.perimeter();
			System.out.println("Perimeter of Polygon6 is : " + df2.format(perimeter) + " ; " + "Expected Perimeter is : " + "65.11" );
			
		
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon6 is non-simple or invalid");
		}
		
		//FOR FORMATTING
				System.out.println(" ");
				
		
		SimplePolygon Poly7;
		try {
			Poly7 = SimplePolygon.getNewPoly("4  6 1 9 5 5 8 2 4");
		
			System.out.println( "Your input was ==> " + Poly7.toString());
			
			sim = Poly7.isSimple() == true ? "Simple" : "not Simple";
			System.out.println("Polygon7 is : " + sim + " ; " + "Expected was  : Simple" );	
		
			area = Poly7.area();	
			System.out.println("Area of Polygon7 is : " + df2.format(area) +  " ; " + "Expected Area is : " + "25" );
			
			perimeter= Poly7.perimeter();
			System.out.println("Perimeter of Polygon7 is : " + df2.format(perimeter) + " ; " + "Expected Perimeter is : " + "20" );
			
		
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon7 is non-simple or invalid");
		}
		 //for formatting!
		System.out.println(" ");
		
		/*
		 * Polygons that are simple from the tests above:
		 * Poly2: 7  28 2 31 5 28 10 14 14 5 10 8 4 18 1 ==> is Poly8
		 * 
		 * Poly5 = SimplePolygon.getNewPoly("13  5 6 13 2 12 6 20 2 18 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6"); 
		 *  ==> is Poly 11
		 * 
		 * Poly6 = SimplePolygon.getNewPoly("22  14 7 15 8 17 7 17 5 15 6 14 4 12 6 11 9 15 11 7 12 8 11 7 9 10 11 8 6 10 5 11 3 16 3 18 4 19 8 16 9 14 9 13 8");
		 * ==> is Poly9
		 * 
		 * Poly7 = SimplePolygon.getNewPoly("4  6 1 9 5 5 8 2 4");
		 * ==> is Poly10
		 * 
		 */
		
		ConvexPolygon Poly8;
		try {
			//REMEBER ==> Precondition, which is something user has to ensure, is
			//that input polygon is Simple!
			
			Poly8 = ConvexPolygon.getNewPoly("7  28 2 31 5 28 10 14 14 5 10 8 4 18 1");
			//put print statment inside try block, and not outside, so it does not print null
			System.out.println( "Your input was ==> " + Poly8.toString());
			
			sim = Poly8.isConvex() == true ? "Convex" : "Convex";
			System.out.println("Polygon8 is : " + sim + " ; " + "Expected was  : Convex" );	
		
		} catch (NonSimplePolygonException e) {
			// This print stament is like this b/c we a using Convex .getNewPoly() method!!!!1
			System.out.println("Polygon8 is non-convex or invalid ");
		}
		//for formmating
		System.out.println( " ");
	
		
		ConvexPolygon Poly9;
		try {
			//REMEBER ==> Precondition, which is something user has to ensure, is
			//that input polygon is Simple!
			
			Poly9 = ConvexPolygon.getNewPoly("22  14 7 15 8 17 7 17 5 15 6 14 4 12 6 11 9 15 11 7 12 8 11 7 9 10 11 8 6 10 5 11 3 16 3 18 4 19 8 16 9 14 9 13 8");
			//put print statment inside try block, and not outside, so it does not print null
			System.out.println( "Your input was ==> " + Poly9.toString());
			
			sim = Poly9.isConvex() == true ? "Convex" : "not Convex";
			System.out.println("Polygon9 is : " + sim + " ; " + "Expected was  : not Convex" );	
		
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			System.out.println("Polygon9 is non-convex or invalid ");
		}
		//for formmating
				System.out.println( " ");
				
		ConvexPolygon Poly10;
		try {
			//REMEBER ==> Precondition, which is something user has to ensure, is
			//that input polygon is Simple!
			
			
			Poly10 = ConvexPolygon.getNewPoly("4  6 1 9 5 5 8 2 4");
			//put print statment inside try block, and not outside, so it does not print null
			System.out.println( "Your input was ==> " + Poly10.toString());
			
			sim = Poly10.isConvex() == true ? "Convex" : "not Convex";
			System.out.println("Polygon10 is : " + sim + " ; " + "Expected was  : Convex" );	
		
		} catch (NonSimplePolygonException e) {
			// This print stament is like this b/c we a using Convex .getNewPoly() method!!!!1
			System.out.println("Polygon10 is non-convex or invalid ");
		}
		
		//for formmating
				System.out.println(" ");	
				
				ConvexPolygon Poly11;
				try {
					//REMEBER ==> Precondition, which is something user has to ensure, is
					//that input polygon is Simple!
					
					
					Poly11= ConvexPolygon.getNewPoly("13  5 6 13 2 12 6 20 2 18 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6");
					//put print statment inside try block, and not outside, so it does not print null
					System.out.println( "Your input was ==> " + Poly11.toString());
					
					sim = Poly11.isConvex() == true ? "Convex" : "not Convex";
					System.out.println("Polygon11 is : " + sim + " ; " + "Expected was  : Convex" );	
				
				} catch (NonSimplePolygonException e) {
					// This print stament is like this b/c we a using Convex .getNewPoly() method!!!!1
					System.out.println("Polygon11 is non-convex or invalid ");
				}	
				
				//for formmating
				System.out.println(" ");
				
				//Delta Method Tester 
				double expDelta = 6;
				double actDelta = SimplePolygon.delta(new Point2D.Double(1, 6), new Point2D.Double(11, 2), new Point2D.Double(5, 5));
				String val1 = expDelta != actDelta ? "Value Matches!" : "Value does not Match!";
				String expt = "Value does not Match!";
				String rtn = val1.equals(expt) ? "pass!" : "fail!";
				System.out.println("Result from Delta Test ==> " + rtn);
		
	}//Main bracket
	
}
