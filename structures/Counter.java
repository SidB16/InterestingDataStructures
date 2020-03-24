
public class Counter {	
	private int count;
	public Counter() { } //empty constructor	
	public Counter( int initial) { count = initial;} //alternate construtor
	public int getCount() { return count;}
	public void increment() { count++; } // void = doesnt return anything
	public void increment( int delta) { count += delta;} // often for utility
	public void reset() { count =0;}
	
	private byte b;
	private char c;
	private short s;
	private int i;
	private long l;
	private float f = 1.3F;
	private double d = 1.2D;
	private boolean bool = false;
	 
	
	public static void main( String[] args) {
		Counter c; // declare variable
		c = new Counter(); // assign variable to a newly created object 
		c.increment();
		c.increment(3);
		int temp = c.getCount();
		System.out.println("temp" + " = " + temp);
		c.reset();
		Counter d = new Counter(5);
		d.increment();
		Counter e = d; // e is an alias
		temp = e.getCount();
		e.increment(2);
		System.out.println("temp" + " = " + temp);
		e = null;
		c = null;
		d = null; // refrence variables store mem address of instance
				// and can store special value null
		
	}
	
}
