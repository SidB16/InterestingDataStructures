package eecs2011;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AQueueTest {
	private AQueue<Integer> qI = null;
	private AQueue<String> qS = null;
	
	@Before
	public void setUp() {
		qI = new AQueue<Integer>();
		qS = new AQueue<String>();
	}
	@Test
	public void size() {
		Assert.assertTrue(qI.size() == 0);
		Assert.assertTrue(qS.size() == 0);

		Assert.assertTrue(qI.capacity() == 1);
		Assert.assertTrue(qS.capacity() == 1);
	}
	@Test
	public void size2() {
		qI = new AQueue<Integer>(2);
		qS = new AQueue<String>("HEHE");
		Assert.assertTrue(qI.size() == 1);
		Assert.assertTrue(qS.size() == 1);

		Assert.assertTrue(qI.capacity() == 2);
		Assert.assertTrue(qS.capacity() == 2);
	}
	@Test
	public void front() throws EmptyQueueException {
		qI = new AQueue<Integer>(2);
		qS = new AQueue<String>("HEHE");
		Assert.assertTrue(qI.size() == 1);
		Assert.assertTrue(qS.size() == 1);
		Assert.assertTrue(qI.capacity() == 2);
		Assert.assertTrue(qS.capacity() == 2);
		Assert.assertEquals(2, qI.front());
		Assert.assertEquals("HEHE", qS.front());
		
	}
	@Test
	public void size4() throws EmptyQueueException {
		Assert.assertTrue(qI.size() == 0);
		Assert.assertTrue(qS.size() == 0);
		Assert.assertTrue(qI.capacity() == 1);
		Assert.assertTrue(qS.capacity() == 1);
	try {
		Assert.assertEquals(qI.front(), null);
		
	} catch (EmptyQueueException e) {
		System.out.println("Exception1 from size4() test caught!");
	}
	try {
		Assert.assertEquals(qI.back(), null);
		
	} catch (EmptyQueueException e) {
		System.out.println("Exception2 from size4() test caught!");
	}
	try {
		Assert.assertEquals(qI.front(), null);
		
	} catch (EmptyQueueException e) {
		System.out.println("Exception3 from size4() test caught!");
	}
	try {
		Assert.assertEquals(qS.back(), null);
		
	} catch (EmptyQueueException e) {
		System.out.println("Exception4 from size4() test caught!");
	}
	
	
	
		
		
	}
	@Test
	public void size5() throws EmptyQueueException {
		qI = new AQueue<Integer>(2);
		qS = new AQueue<String>("HEHE");
		Assert.assertTrue(qI.size() == 1);
		Assert.assertTrue(qS.size() == 1);
		Assert.assertTrue(qI.capacity() == 2);
		Assert.assertTrue(qS.capacity() == 2);
		Assert.assertFalse(qI.front().equals(null));
		Assert.assertEquals(qI.back(), null);
		Assert.assertFalse(qI.front().equals(null));
		Assert.assertEquals(qS.back(), null);
		
	}
	@Test
	public void enqueue() throws EmptyQueueException {
		qI = new AQueue<Integer>(2);
		qS = new AQueue<String>("HEHE");
		Assert.assertTrue(qI.size() == 1);
		Assert.assertTrue(qS.size() == 1);
		Assert.assertTrue(qI.capacity() == 2);
		Assert.assertTrue(qS.capacity() == 2);
		Assert.assertEquals(qI.back(), null);
	}

}
