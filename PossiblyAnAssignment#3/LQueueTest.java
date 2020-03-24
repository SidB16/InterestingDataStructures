/**
 * 
 */
package eecs2011;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Junit Test Class for LQueue.
 * 
 * @author sid16
 *
 */
public class LQueueTest {
	private LQueue<Integer> qI; 
	private LQueue<String> qS;
	private LQueue<Double> qD;
	
	//Before annotations are executed before each test.
	@Before
	public void setUp() {
		qI = new LQueue<Integer>();
		qS = new LQueue<String>();
		qD = new LQueue<Double>();
	}
	 
	
	public void size() throws EmptyQueueException {
		Assert.assertNull(qS.front());
		Assert.assertEquals(0, qS.size());
		
	}
	@Test
	public void size2() throws EmptyQueueException {
		qS = new LQueue<String>("S"); //testing the 1 arg constructor
		Assert.assertEquals(1, qS.size());
		Assert.assertEquals("S", qS.front());
	}
	
	public void size3() throws EmptyQueueException {
		Assert.assertNull(qI.front());
		Assert.assertEquals(0, qI.size());
	}
	
	@Test
	public void isEmpty() {
		Assert.assertTrue(qI.isEmpty());
		Assert.assertTrue(qS.isEmpty());
	}
	@Test
	public void enqueue() throws EmptyQueueException{
		qI.enqueue(null);
		qI.enqueue(3);
		qI.enqueue(6);
		Assert.assertEquals(2, qI.size());
		System.out.println( " " + qI.front());
		Assert.assertTrue(qI.front().equals(3));
		}
	
	public void enqueue2(){
		qS.enqueue("Sid");
		qS.enqueue("Is");
		qS.enqueue("Di");
		qS.enqueue("Ha");
		Assert.assertEquals(4, qS.size());
		Assert.assertEquals("Sid", qS.front.getElement());
		Assert.assertTrue(qS.front.getElement().equals("Sid"));
		Assert.assertEquals("Ha", qS.back.getElement());
	}
	
	public void Dequeue(){
		try {
			qD.dequeue();
		} catch (EmptyQueueException e) {
			System.out.println("Exception Caught!");
		}
	}
	
	public void Dequeue2() throws EmptyQueueException{
		qI.enqueue(1);
		qI.enqueue(6);
		qI.dequeue();
		Assert.assertEquals(1, qI.size());
		Assert.assertTrue(qI.front.getElement().equals(6));
		qI.enqueue(8);
		qI.enqueue(10);
		Assert.assertTrue(qI.back.getElement().equals(10));
		Assert.assertTrue(qI.front.getNext().getElement().equals(8));
		Assert.assertTrue(qI.size() == 3);
		qI.dequeue();
		qI.dequeue();
		qI.dequeue();
		Assert.assertTrue(qI.size() == 0);
		
	}
	
	public void front() throws EmptyQueueException {
		qI.enqueue(1);
		qI.enqueue(6);
		qI.dequeue();
		Assert.assertEquals(1, qI.size());
		Assert.assertTrue(qI.front.getElement().equals(6));
		qI.enqueue(8);
		qI.enqueue(10);
		Assert.assertTrue(qI.back.getElement().equals(10));
		Assert.assertTrue(qI.front.getNext().getElement().equals(8));
		Assert.assertTrue(qI.size() == 3);
		qI.dequeue();
		qI.dequeue();
		qI.dequeue();
		Assert.assertTrue(qI.size() == 0);
		try {
			qI.front();
		} catch (EmptyQueueException e) {
			System.out.println("Exception from front() test, caught!");
		}
		
	}
	@Test
	public void front2() throws EmptyQueueException {
		qS.enqueue("Iamfirst");
		qS.enqueue("45");
		qS.enqueue(null); //NULL TEST :)
		qS.enqueue("Iamlast");
		Assert.assertTrue(qS.size() == 3);
	}
}
