/**
 * 
 */
package eecs2011;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sid16
 *
 */
public class LArrayListTest {
	private LArrayList<Integer>  laI;
	private LArrayList<String>  laS;
	private LArrayList<Object>  laO;
	
	@Before
	public void setUp() {
		laI = new LArrayList<Integer>();
		laS = new LArrayList<String>();
		//laO = new LArrayList<Object>();
	}
	@Test
	public void constructorTest1() {
		laI = new LArrayList<Integer>(3);
		Assert.assertEquals(0, laI.size());
		//Assert.assertEquals(3, laI.capacity());
		//Assert.assertTrue(laI.getHeadElement() == null);
	//	Assert.assertTrue(laI.getTailElement() == null);
	}
	@Test
	public void constructorTest2() {
		laI = new LArrayList<Integer>(2);
		Assert.assertEquals(0, laI.size());
	//	Assert.assertEquals(2, laI.capacity());
	//	Assert.assertTrue(laI.getHeadElement() == null);
	//	Assert.assertTrue(laI.getTailElement() == null);
	}
	@Test
	public void constructorTest3() {
		laI = new LArrayList<Integer>(1);
		Assert.assertEquals(0, laI.size());
	//	Assert.assertEquals(1, laI.capacity());
	//	Assert.assertTrue(laI.getHeadElement() == null);
	//	Assert.assertTrue(laI.getTailElement() == null);
	}
	@Test
	public void constructorTest4() {
		laI = new LArrayList<Integer>(100);
		Assert.assertEquals(0, laI.size());
	//	Assert.assertEquals(100, laI.capacity());
	//	Assert.assertTrue(laI.getHeadElement() == null);
	//	Assert.assertTrue(laI.getTailElement() == null);
	}
	@Test
	public void constructorTest5() {
		laI = new LArrayList<Integer>();
		Assert.assertEquals(0, laI.size());
		///Assert.assertEquals(1, laI.capacity()); //could hold 1 element
		//Assert.assertTrue(laI.getHeadElement() == null);
	//	Assert.assertTrue(laI.getTailElement() == null);
	}
	@Test
	public void constructorTest6() {
		laI = new LArrayList<Integer>(3);
		Assert.assertEquals(0, laI.size());
		//Assert.assertEquals(3, laI.capacity()); //could hold 1 element
		//Assert.assertTrue(laI.head.index.equals(0));
	//	Assert.assertTrue(laI.tail.index.equals(2));
	//	System.out.println(laI.head.next.index.intValue());
	//	System.out.println(laI.tail.prev.index.intValue());
	//	Assert.assertTrue(laI.head.next.index.equals(laI.tail.prev.index));
	}
	@Test
	public void get() {
		Assert.assertEquals(0, laI.size());
		//Assert.assertEquals(1, laI.capacity()); //could hold 1 element
		//Assert.assertTrue(laI.getHeadElement() == null);
		//Assert.assertTrue(laI.getTailElement() == null);
	}
	@Test
	public void add() {
		laI.add(0, 1);
		Assert.assertEquals(1, laI.size());
	}
	@Test
	public void add2() {
		laI.add(0, 1);
		laI.add(0, 3);
		Assert.assertTrue(laI.get(0) == 3);
		Assert.assertTrue(laI.get(1) == 1);
		laI.add(2, 23);
		Assert.assertEquals(3, laI.size());
	}
	@Test
	public void add3() {
		laI.add(0, 1);
		laI.add(0, 1);
		laI.add(2, 23);
		try {
			laI.add(4, 9);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("add3 Exception caught!");
		}
		Assert.assertEquals(3, laI.size());
	}
	@Test
	public void add4() {
		laI.add(0, 1);
		laI.add(0, null);
		Assert.assertTrue(laI.get(0) == null);
		laI.add(2, 23);
		try {
			laI.add(4, 9);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("add3 Exception caught!");
		}
		Assert.assertEquals(3, laI.size());
	}
	@Test
	public void add5() {
		//Assert.assertFalse(laS.get(0) == null);
		laS.add(0, "S");
		Assert.assertTrue(laS.get(0).equals("S"));
		laS.add(0, "B");
		Assert.assertTrue(laS.get(0).equals("B"));
		laS.add(1, "S");
		Assert.assertTrue(laS.get(2).equals("S"));
	}
	@Test
	public void remove() {
		//Assert.assertFalse(laS.get(0) == null);
		laS.add(0, "S");
		//String a = laS.remove(0);
		//Assert.assertEquals("S", a);
		laS.add(0, "a");
		String b = laS.remove(1);
		Assert.assertEquals("S", b);
		Assert.assertTrue(laS.get(0).equals("a"));
	}
	@Test
	public void remove2() {
		//Assert.assertFalse(laS.get(0) == null);
		laS.add(0, "S");
		laS.add(0, "B");
		Assert.assertEquals("B", laS.get(0));
		laS.add(2, "C");
		String b = laS.remove(0);
		Assert.assertEquals("B", b);
		Assert.assertEquals("S", laS.get(0));
		Assert.assertTrue(laS.get(1).equals("C"));
		Assert.assertTrue(laS.get(1).equals("C"));
	}
	@Test
	public void remove3() {
		//Assert.assertFalse(laS.get(0) == null);
		laS.add(0, "S");
		laS.add(0, "B");
		laS.add(0, "A");
		laS.add(0, "B");
		laS.add(4, "A");
		String a = laS.remove(2);
		Assert.assertEquals("B", a);
		Assert.assertEquals("S", laS.get(2));
	}
	@Test
	public void remove4() {
		//Assert.assertFalse(laS.get(0) == null);
		laS.add(0, "S");
		laS.add(0, "B");
		laS.add(0, "A");
		laS.add(0, "B");
		laS.add(4, "A");
		String a = laS.remove(0);
		Assert.assertEquals("B", a);
		Assert.assertEquals("A", laS.get(0));
	}
	@Test
	public void remove5() {
		//Assert.assertFalse(laS.get(0) == null);
		laS.add(0, "S");
		laS.add(0, "B");
		laS.add(0, "A");
		laS.add(0, "B");
		laS.add(4, "A");
		String a = laS.remove(4);
		Assert.assertTrue(laS.size() == 4);
		Assert.assertEquals("A", a);
		Assert.assertEquals("S", laS.get(laS.size()-1));
	}
	@Test
	public void get2() {
		//Assert.assertFalse(laS.get(0) == null);
		laS.add(0, "S");
		laS.add(0, "B");
		laS.add(0, "A");
		laS.add(0, "B");
		laS.add(4, "A");
		laS.add(2, "D");
		Assert.assertEquals("A", laS.get(laS.size()-1));
		Assert.assertEquals("A", laS.get(1));
		Assert.assertEquals("D", laS.get(2));
	}
	@Test
	public void add6() {
		laS.add(0, "S");
		laS.add(1, null);
		laS.add(2, "S");
		try { //If I try and add TWO spaces from last index, leaving a hold in middle.
			laS.add(4, "S"); 
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception for hole caught!");
		}
		//Thus element will not be added. So, 
		Assert.assertTrue(laS.size() == 3);
	}
	@Test
	public void remove8() throws IndexOutOfBoundsException {
		laS.add(0, "A");
		  laS.add(1, "B"); 
		  laS.add(2, "C"); 
		  laS.add(3, "D");
		  Assert.assertEquals("A", laS.get(0));
		  Assert.assertEquals("C", laS.get(2));
		  Assert.assertTrue(laS.size()== 4); 
		 String a = laS.remove(1);
		 Assert.assertEquals("B", a); 
		Assert.assertEquals("C", laS.get(1));
		String b = laS.remove(0);
		 Assert.assertEquals("A", b);
		Assert.assertEquals("C", laS.get(0));
		String c = laS.remove(laS.size()-1);
		 Assert.assertEquals("D", c);
		Assert.assertEquals("C", laS.get(0));
		String d = laS.remove(laS.size()-1);
		 Assert.assertEquals("C", d);
		 try {
			 Assert.assertEquals(null, laS.get(0)); //Empty arraylist
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception for remove caught!");
		}
	}
	@Test
	public void set() {
		laS.add(0, "A");
		  laS.add(1, "B"); 
		  laS.add(2, "C"); 
		  laS.add(3, "D");
		  laS.set(0, "B");
		  Assert.assertEquals("B", laS.get(0));
		  laS.set(laS.size()-1, "G");
		  Assert.assertEquals("G", laS.get(laS.size()-1));
	}
	

}
