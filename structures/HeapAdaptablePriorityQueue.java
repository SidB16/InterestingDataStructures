import java.util.Comparator;

/**
 * 
 */

/**
 * An implementation of an adaptable priority queue using a array-based heap.
 * Method					Running Time
 * size, isEmpty, min			O(1)
 * insert						O(log n)
 * remove						O(log n)
 * removeMin					O(log n)
 * replaceKey					O(log n)
 * replaceValue					O(1)
 * Space requirement is O(n)
 * 
 * @author sid16
 *
 */
public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V> implements AdaptablePriorityQueue<K,V> {
	//Nested AdaptablePQEntry class, it simple extends the super class by adding an index feilds- obtaining the property of location aware entry!
	protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V>{
		//feilds
		private int index;
		//Constructor
		public AdaptablePQEntry(K key, V value, int j) {
			super(key, value);
			this.index = j;
		}
		//Getter (only need for new index feild1)
		public int getIndex() {
			return this.index;
		}
		//Setter
		public void setIndex(int newIndex) {
			this.index = newIndex;
		}
	}
	//Feilds
	//Same feilds as the super class no change!
	//Constructor
	public HeapAdaptablePriorityQueue(){
		super(); //no change! just a explicit call to super. Even if this explicit statment was not written then eclipse would make an implicit class to super in the first line!
	}
	public HeapAdaptablePriorityQueue( Comparator<K> comp) {
		super(comp);
	}
	//public methods (General, Query, Getters, Setters, Mutators)
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException { //Inserts a key-value pair and returns the created entry
		checkKey(key);//might throw exception!
		Entry<K,V> newest = new AdaptablePQEntry< >(key, value, heap.size());
		heap.add(newest);//add to end of list
		upheap(heap.size()-1); //upheap newly added entry
		return newest;
	}
	public void remove(Entry<K,V> entry) {//removes the given entry from the PQ
		AdaptablePQEntry<K,V> locator = validate(entry);
		int j = locator.getIndex();
		if(j == heap.size() - 1) //entry is at last position
			heap.remove(heap.size() - 1); //so just remove it
		else {
			swap(j, heap.size()-1); //swap entry with last position
			heap.remove(heap.size()-1); // then remove it
			bubble(j); //and fix entry displaced by swap
		}
	}
	public void replaceKey(Entry<K,V> entry, K key) throws IllegalArgumentException { //replaces the key of the entry
		AdaptablePQEntry<K,V> locator = validate(entry);
		checkKey(key); //might throw an exception
		locator.setKey(key); //method inherited from PQEntry
		bubble(locator.getIndex());  //with new key, may need to move
	}
	public void replaceValue(Entry<K,V> entry, V value) throws IllegalArgumentException { //replaces value of an entry
		AdaptablePQEntry<K,V> locator = validate(entry);
		locator.setValue(value); //method inherited from PQEntry
	}
	//Protected Utilities
	protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException{//validates an entry to ensure it is location-aware
		if(!(entry instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("Invalid Entry");
		AdaptablePQEntry<K,V> locator = (AdaptablePQEntry<K,V>) entry; //safe
		int j = locator.getIndex();
		if(j >= heap.size() || heap.get(j) != locator)
			throw new IllegalArgumentException("Invalid Entry");
		return locator;
	}
	protected void swap(int i, int j) { //Exchanges the entries a indicies i and j of the array list
		super.swap(i, j); //perfom swap using super class method
		((AdaptablePQEntry<K,V>) heap.get(i)).setIndex(i); //reset entry's index
		((AdaptablePQEntry<K,V>) heap.get(j)).setIndex(j); //reset entry's index
	}
	protected void bubble(int j) {//Restores the heap property by moving entry at index j upward/downward
		if(j > 0 && compare(heap.get(j),heap.get(parent(j))) < 0)
			upheap(j);
		else
			downheap(j);
	}
}
