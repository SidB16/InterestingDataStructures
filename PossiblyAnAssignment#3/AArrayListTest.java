/**
 * 
 */
package eecs2011;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sid16
 *
 */
public class AArrayListTest {

	AArrayList<Integer> aI;
	AArrayList<String> aS;
	AArrayList<Float> aF;
	
	@Before
	public void setUp() {
		aI = new AArrayList<Integer>();
		aS = new AArrayList<String>(); 
		aF = new AArrayList<Float>();
	}
	@Test
	public void size() {
		Assert.assertTrue(aI.size() == 0);
	}
	@Test
	public void nulltest() {
		Assert.assertFalse(aI.equals(null));
	}
	@Test
	public void size2() {
		Assert.assertTrue(aS.size() == 0);
	}
	@Test
	public void nulltest2() {
		Assert.assertFalse(aS.equals(null));
	}
	@Test 
	public void isEmpty() {
		Assert.assertTrue(aI.isEmpty());
		Assert.assertTrue(aS.isEmpty());
		Assert.assertTrue(aF.isEmpty());
	}
	@Test 
	public void add() {
		Assert.assertTrue(aI.size() == 0);
		aI.add(0, 1);
		Assert.assertTrue(aI.size() == 1);
	}
	@Test
	public void add2() {
		aS.add(0, "S");
		aS.add(1, "S");
		aS.add(2, "S");
		aS.add(3, "S"); //If I want to add to the back of ArrayList
		Assert.assertTrue(aS.size() == 4);
	}
	@Test
	public void add3() {
		aS.add(0, "S");
		aS.add(1, "S");
		aS.add(2, "S");
		aS.add(2, "S"); //If I want to add at last index then array will resize;
		Assert.assertTrue(aS.size() == 4);
	}
	@Test
	public void add4() {
		aS.add(0, "S");
		aS.add(1, null);
		aS.add(2, "S");
		try { //If I try and add TWO spaces from last index, leaving a hold in middle.
			aS.add(4, "S"); 
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception for hole caught!");
		}
		//Thus element will not be added. So, 
		Assert.assertTrue(aS.size() == 3);
	}
	@Test
	public void remove() throws IndexOutOfBoundsException {
		aS.add(0, "A");
		  aS.add(1, "B"); 
		  aS.add(2, "C"); 
		  aS.add(3, "D");
		  Assert.assertEquals("A", aS.get(0));
		  Assert.assertEquals("C", aS.get(2));
		  Assert.assertTrue(aS.size()== 4); 
		 String a = aS.remove(1);
		 Assert.assertEquals("B", a); 
		Assert.assertEquals("C", aS.get(1));
		String b = aS.remove(0);
		 Assert.assertEquals("A", b);
		Assert.assertEquals("C", aS.get(0));
		String c = aS.remove(aS.size()-1);
		 Assert.assertEquals("D", c);
		Assert.assertEquals("C", aS.get(0));
		String d = aS.remove(aS.size()-1);
		 Assert.assertEquals("C", d);
		 try {
			 Assert.assertEquals(null, aS.get(0)); //Empty arraylist
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception for remove caught!");
		}
	}
	@Test
	public void set() {
		aS.add(0, "A");
		  aS.add(1, "B"); 
		  aS.add(2, "C"); 
		  aS.add(3, "D");
		  aS.set(0, "B");
		  Assert.assertEquals("B", aS.get(0));
		  aS.set(aS.size()-1, "G");
		  Assert.assertEquals("G", aS.get(aS.size()-1));
	}
	
}
