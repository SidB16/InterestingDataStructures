import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 */

/**
 * Use use the MAD method where, 
 * For example, if we insert keys with hash
codes {200, 205, 210, 215, 220, . . . , 600} into a bucket array of size 100, then each
hash code will collide with three others. But if we use a bucket array of size 101,
then there will be no collisions. If a hash function is chosen well, it should ensure
that the probability of two different keys getting hashed to the same bucket is 1/N.

 * The entire hash table is then represented as a fixed-capacity array A of the
secondary maps. Each cell, A[h], is initially a null reference; we only create a
secondary map when an entry is first hashed to a particular bucket

As a general rule, we implement bucketGet(h, k) by calling A[h].get(k), we
implement bucketPut(h, k, v) by calling A[h].put(k, v), and bucketRemove(h, k)
by calling A[h].remove(k). However, care is needed for two reasons.
First, because we choose to leave table cells as null until a secondary map is
needed, each of these fundamental operations must begin by checking to see if
A[h] is null. In the case of bucketGet and bucketRemove, if the bucket does not
yet exist, we can simply return null as there can not be any entry matching key k.
In the case of bucketPut, a new entry must be inserted, so we instantiate a new
UnsortedTableMap for A[h] before continuing.
The second issue is that, in our AbstractHashMap framework, the subclass
has the responsibility to properly maintain the instance variable n when an entry is
newly inserted or deleted. Remember that when put(k, v) is called on a map, the
size of the map only increases if key k is new to the map (otherwise, the value of an
existing entry is reassigned). Similarly, a call to remove(k) only decreases the size
of the map when an entry with key equal to k is found. In our implementation, we
determine the change in the overall size of the map, by determining if there is any
change in the size of the relevant secondary map before and after an operation.
 *
 * The advantage of using a map for each bucket is that it becomes
easy to delegate responsibilities for top-level map operations to the appropriate
bucket.
 *
 *The entire hash table can be then represented by fixced capactity array of secondary maps.
 *Each cell, A[h], is initially a null reference; we only create a secondary map when an entry is first hashed to a particular bucket.
 *
 *As a general rule, we implement bucketGet(h, k) by calling A[h].get(k), we
implement bucketPut(h, k, v) by calling A[h].put(k, v), and bucketRemove(h, k)
by calling A[h].remove(k). However, care is needed for two reasons.
 * 
 *1) In the case of bucketPut, a new entry must be inserted, so we instantiate a new
UnsortedTableMap for A[h] before continuing. But in the case of bucketGet and bucketRemove you can return null!
2)n our implementation, we
determine the change in the overall size of the map, by determining if there is any
change in the size of the relevant secondary map before and after an operation.
 * ChainHashMap
class, which implements a hash table with separate chaining. If we assume that the
hash function performs well, a map with n entries and a table of capacity N will
have an expected bucket size of n/N (recall, this is its load factor). So even though
the individual buckets, implemented as UnsortedTableMap instances, are not par-
ticularly efficient, each bucket has expected O(1) size, provided that n is O(N),
as in our implementation. Therefore, the expected running time of operations get,
put, and remove for this map is O(1). The entrySet method (and thus the related
keySet and values) runs in O(n + N) time, as it loops through the length of the table
(with length N) and through all buckets (which have cumulative lengths n).
 * 
 * @author sid16
 *
 */
public class ChainHashMap<k, v> extends AbstractHashMap<k,v>{
	//Essentially a fixed size array of secondary maps. The MOST important part is the abstract superclass!
	//Feilds
	private UnsortedTableMap<k,v>[] table;
	//Constructors
	public ChainHashMap() {
		super();
	}
	public ChainHashMap(int cap) {
		super(cap);
	}
	public ChainHashMap(int cap, int p) {
		super(cap, p);
	}
	//Implementation of abstract methods declared in AbstractHashmap
	//Protected methods which are speicifc for this chainmap implementation. OOP principle of abstraction to allow for code resuabiluity and implementing multiple solutions.
	//Protected = only visible to subclasses
	protected void createTable() {
		this.table = (UnsortedTableMap<k,v>[]) new UnsortedTableMap[this.capacity];
	}
	protected v bucketGet(int h, k key) { //returns value assosiated with key k located in bucket h
		//VERY INTERESTING!!!
		UnsortedTableMap<k,v> bucket = this.table[h];
		if(bucket == null)
			return null;
		//if bucket not null,
		return bucket.get(key);
	}
	protected v bucketPut(int hash, k key, v value) {
		UnsortedTableMap<k,v> bucket = this.table[hash];
		if (bucket == null) 
			bucket = this.table[hash] = new UnsortedTableMap< >();
		int oldSize = bucket.size();
		v answer = bucket.put(key, value);
		n += (oldSize - bucket.size()); //size may have increased!
		return answer;
	}
	protected v bucketRemove(int hash, k key) {
		UnsortedTableMap<k,v> bucket = this.table[hash];
		if (bucket == null)
			return null;
		int oldSize = bucket.size();
		v answer = bucket.remove(key);
		n -= (oldSize - bucket.size()); //size may have decreased!

		return answer;
	}
	public Iterable<Entry<k,v>> entrySet(){
		ArrayList<Entry<k,v>> buffer = new ArrayList<>();
		for(int h = 0; h < this.capacity; h++) 
			if(this.table[h] != null)
				for(Entry<k,v> entry : table[h].entrySet())
					buffer.add(entry);
		return buffer;
	}
	@Override
	public Iterable<k> keySet() {
		//Since this ChainedHashMap is a array of unsortedmap. We have to iterate through the array and then throught the map
		ArrayList<k> buffer = new ArrayList< >();
		for(int i = 0; i < this.capacity; i++) {
			if (this.table[i] != null) {
				for(Entry<k,v> e : this.table[i]) 
					buffer.add(e.getKey());
			}
		}
		return buffer;
	}
	@Override
	public Iterable<v> values() {
		ArrayList<v> buffer = new ArrayList< >();
		for(int i = 0; i < this.capacity; i++) 
			if(this.table[i] != null)
				for(Entry<k,v> e : this.table[i])
					buffer.add(e.getValue());
		return buffer;
	}
	@Override
	public Iterator<Entry<k, v>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
