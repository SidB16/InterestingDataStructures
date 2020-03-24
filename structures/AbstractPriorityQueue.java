import java.util.Comparator;

/**
 * 
 */

/**
 * @author sid16
 *
 */
public abstract class AbstractPriorityQueue<k,v> implements PriorityQueue<k,v> {

	/**
	 * Nested Entry Class
	 * Composition pattern: a single object composed of two or more.
	 */

	
	protected static class PQEntry<k,v> implements Entry<k,v>{
		//Feilds
		k key;
		v value;
		public PQEntry(k key, v value) {
			this.key = key;
			this.value = value;
		}
		//Getters
		public k getKey() {
			return this.key;
		}
		public v getValue() {
			return this.value;
		}
		//Setters
		public void setKey(k newKey) {
			this.key = newKey;
		}
		public void setValue(v newValue) {
			this.value = newValue;
		}
	}
	//instance var of AbstractPriorityQueue
	private Comparator<k> comp; //comparator defining order of keys in PQ.
	/**
	 * Constructors for PQ
	 */
	protected AbstractPriorityQueue(Comparator<k> c) {
		this.comp =c;
	}
	public AbstractPriorityQueue() {//create PQ by passing it comparator based on natural ordering of its keys from  java.util.Comparable.
		this(new DefaultComparator<k>());
	}
	/**
	 * Query Methods
	 */
	//Method for comparing two entries according to keys
	protected int compare(Entry<k,v> a, Entry<k,v> b) {
		return comp.compare(a.getKey(), b.getKey()); //compare keys of given entries using the comparator used in Construct of PQ
	}
	protected boolean checkKey(k key) throws IllegalArgumentException{
		try {
			return (this.comp.compare(key, key) == 0); //see if key can be compared to itself. What is the convention again??
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatiable key");
		}
	}
	public boolean isEmpty() {
		return size() == 0; // presumed size method ???
	}
}
