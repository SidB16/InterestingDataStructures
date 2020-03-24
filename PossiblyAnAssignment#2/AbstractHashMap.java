package A3;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 */

/**
 * In our design, the AbstractHashMap class presumes the
following to be abstract methods—to be implemented by each concrete subclass:

createTable( ): This method should create an initially empty table having
size equal to a designated capacity instance variable.

bucketGet(h, k): This method should mimic the semantics of the public get
method, but for a key k that is known to hash to bucket h.

bucketPut(h, k, v): This method should mimic the semantics of the public put
method, but for a key k that is known to hash to bucket h.

bucketRemove(h, k): This method should mimic the semantics of the public
remove method, but for a key k known to hash to bucket h.

entrySet( ): This standard map method iterates through all entries of the
map. We do not delegate this on a per-bucket basis because
“buckets�? in open addressing are not inherently disjoint.

 * AbstractHashMap class does provide is mathematical support in
the form of a hash compression function using a randomized Multiply-Add-and-
Divide (MAD) formula, and support for automatically resizing the underlying hash
table when the load factor reaches a certain threshold.
 * @author sid16
 *
 */
public abstract class AbstractHashMap<k,v> extends AbstractMap<k,v> {
	//Feilds
	protected int n = 0; //number of entires inside the hashtable
	protected int capacity;
	private int prime;
	private long scale, shift;
	//Absract Constructor for abstract hashMap ==> Very bc its still abstract yet a constructor!
	public AbstractHashMap(int cap, int p) {
		this.prime = p;
		this.capacity = cap;
		Random rand =  new Random();
		this.scale = rand.nextInt(this.prime-1) + 1;
		this.shift = rand.nextInt(prime);
		createTable(); //this function will be delcared in this abstract class but will be defined by the subclass!
	}
	public AbstractHashMap(int cap) {//Abstract Constructor 2
		this(cap,109345121);
	}
	public AbstractHashMap() {//Abstract Constructor 3
		this(17);
	}
	//Public methods(general, query, Getters and Setter)
	public int size() {
		return this.n;
	}
	public v get(k key) {
		return bucketGet(hashValue(key), key);
	}
	public v remove(k key) {
		return bucketRemove(hashValue(key), key);
	}
	public v put(k key, v value) {
		v answer = bucketPut(hashValue(key), key, value);
		//since the above statment impacts this.n (look at implementation of bucketPut)
		if(n > capacity/2)
			resize(2 * this.capacity -1); //keep load factor <= 0.5
		return answer;
	}
	//private utilities
	private int hashValue(k key){ //Usim
		return (int) ((Math.abs(key.hashCode()*this.scale+ this.shift) % prime) % capacity);
	}
	private void resize(int newCap) {
		ArrayList<Entry<k,v>> buffer = new ArrayList<>(n);
		for(Entry<k,v> e : entrySet())
			buffer.add(e);
		this.capacity = newCap;
		createTable();
		this.n = 0; //Bucket put will take are updating size!
		for(Entry<k,v> e : buffer)
			put(e.getKey(), e.getValue()); //here^
	}
	//Methods we rely on specific defnition. Implemented by subclasses!
	protected abstract void createTable();
	protected abstract v bucketGet(int h, k k);
	protected abstract v bucketPut(int h, k k, v v);
	protected abstract v bucketRemove(int h, k k);
}
