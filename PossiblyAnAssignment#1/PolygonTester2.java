/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Winter 2019
 * Assignment 1: Polygon Hierarchy
 * Section:  M  or  Z ?
 * Student Name:   ?
 * Student eecs account:  ?
 * Student ID number:  ? 
 **********************************************************/
package A1;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.*;
/**
 * PolygonTester should enable a thorough testing of the polygon hierarchy.
 */
public class PolygonTester2 {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void getNewPoly1() 
	{
			int expectedSize = 1;
		
		Point2D.Double[] expectedVertices = 
			{new Point2D.Double(0, 0)};
	try 
	{	
		SimplePolygon p = SimplePolygon.getNewPoly("1  0 0");
		assertEquals(expectedSize, p.getSize());
		for(int i = 0; i < expectedSize; i++)
		{
			assertEquals(expectedVertices[i], p.getVertex(i));
		}
		System.out.println(p.toString());
	} catch ( NonSimplePolygonException e) {
			System.out.println("Polygon is non simple");
		}
	}
	
	@Test
	public void getNewPoly2() 
	{
		int expectedSize = 4;
		Point2D.Double[] expectedVertices = 
			{new Point2D.Double(0, 0),
				new Point2D.Double(1, 1),
				new Point2D.Double(2, 2),
				new Point2D.Double(3, 3)};
		try {
		//this part should trigger exception
		SimplePolygon p = SimplePolygon.getNewPoly("4  0 0 1 1 2 2 3 3");
		assertEquals(expectedSize, p.getSize());
		for(int i = 0; i < expectedSize; i++)
		{
			assertEquals(expectedVertices[i], p.getVertex(i));
		}
		System.out.println(p.toString());
		}catch (NonSimplePolygonException e) {
			System.out.println("Polygon is non-simple");
		}

	}
	@Test
	public void delta1()
	{
		double expectedDelta = 6;
		double deltaNum = SimplePolygon.delta(new Point2D.Double(1, 6),
				new Point2D.Double(11, 2),
				new Point2D.Double(5, 5));
		if(expectedDelta != deltaNum)
		{
			fail();
		}
	}
	
	@Test
	public void disjoint1()
	{
		
		Point2D.Double a = new Point2D.Double(0,0);
		Point2D.Double b = new Point2D.Double(0,1);
		Point2D.Double c = new Point2D.Double(1,1);
		Point2D.Double d = new Point2D.Double(0,1);
		assertFalse("" ,SimplePolygon.disjointSegments(a, b, c, d));
	}
	
	@Test
	public void disjoint2()
	{
		Point2D.Double a = new Point2D.Double(0,0);
		Point2D.Double b = new Point2D.Double(0, 2);
		Point2D.Double c = new Point2D.Double(0,1);
		Point2D.Double d = new Point2D.Double(0, -1);
		assertFalse("" ,SimplePolygon.disjointSegments(a, b, c, d));

	}
	@Test
	public void disjoint3()
	{
		
		Point2D.Double a = new Point2D.Double(1,0);
		Point2D.Double b = new Point2D.Double(0,1);
		Point2D.Double c = new Point2D.Double(0,0);
		Point2D.Double d = new Point2D.Double(1,1);
		assertFalse("" ,SimplePolygon.disjointSegments(a, b, c, d));
	}
	
	@Test
	public void disjoint4()
	{
		Point2D.Double a = new Point2D.Double(0,0);
		Point2D.Double b = new Point2D.Double(1, 1);
		Point2D.Double c = new Point2D.Double(2,2);
		Point2D.Double d = new Point2D.Double(3, 2);
		assertTrue("" ,SimplePolygon.disjointSegments(a, b, c, d));

	}@Test
	public void disjoint5()
	{
		
		Point2D.Double a = new Point2D.Double(0,0);
		Point2D.Double b = new Point2D.Double(0,1);
		Point2D.Double c = new Point2D.Double(1,0);
		Point2D.Double d = new Point2D.Double(0,0);
		assertFalse("" ,SimplePolygon.disjointSegments(a, b, c, d));
	}
	
	@Test
	public void disjoint6()
	{
		Point2D.Double a = new Point2D.Double(0,0);
		Point2D.Double b = new Point2D.Double(0, 2);
		Point2D.Double c = new Point2D.Double(1, 4);
		Point2D.Double d = new Point2D.Double(-2,1);
		assertTrue("" ,SimplePolygon.disjointSegments(a, b, c, d));

	}@Test
	public void disjoint7()
	{
		
		Point2D.Double a = new Point2D.Double(0,0);
		Point2D.Double b = new Point2D.Double(0, 2);
		Point2D.Double c = new Point2D.Double(1, 4);
		Point2D.Double d = new Point2D.Double(-1,2);
		assertTrue("" ,SimplePolygon.disjointSegments(a, b, c, d));
	}
	
	@Test
	public void disjoint8()
	{
		Point2D.Double a = new Point2D.Double(0,0);
		Point2D.Double b = new Point2D.Double(2, 0);
		Point2D.Double c = new Point2D.Double(4, 1);
		Point2D.Double d = new Point2D.Double(2, -1);
		assertTrue("" ,SimplePolygon.disjointSegments(a, b, c, d));
	}
	
	@Test
	public void isSimple1() 
	{
		SimplePolygon p;
		try {
			p = SimplePolygon.getNewPoly("5  0 0 1 1 2 2 3 3 3 4");
			assertTrue(p.isSimple());
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void isSimple2() 
	{
		SimplePolygon p;
		try {
			p = SimplePolygon.getNewPoly("3  0 0 1 1 2 2");
			assertFalse(p.isSimple());
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void isSimple3()
	{
		SimplePolygon p;
		try {
			p = SimplePolygon.getNewPoly("3  0 0 1 1 2 3");
			assertTrue(p.isSimple());
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void isSimple4() 
	{
		SimplePolygon p;
		try {
			p = SimplePolygon.getNewPoly("7  0 0 1 1 1 0 0 1 0 2 0.5 0 1 3");
			assertFalse(p.isSimple());
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void isSimple5() 
	{
		SimplePolygon p;
		try {
			p = SimplePolygon.getNewPoly("13  5 6 13 2 12 6 20 2 18 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6");
			assertTrue(p.isSimple());
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void isSimple6()
	{
		SimplePolygon p;
		try {
			p = SimplePolygon.getNewPoly("22  14 7 15 8 17 7 17 5 15 6 14 4 12 6 11 9 15 11 7 12 8 11 7 9 10 11 8 6 10 5 11 3 16 3 18 4 19 8 16 9 14 9 13 18");
			assertFalse(p.isSimple());
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void isSimple7()
	{
		ConvexPolygon p;
		try {
			p = ConvexPolygon.getNewPoly("4  4 10 9 7 11 2 2 2");
			assertTrue(p.isSimple());
			assertTrue(p.isConvex());
		} catch (NonSimplePolygonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
	}

}
