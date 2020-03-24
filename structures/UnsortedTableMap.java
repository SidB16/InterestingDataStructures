import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * A simple and concerete implementation of Map ADT that relies on an Arrylist of MapEnty<k,v> keyvalue pairs for storagre.
 * Since it is an ArrayList it has no capacity limitation and allows for Random access using indices. 
 * 
 * Futhemore, MapEntries<k,v> can added in O(1) time as we keep our collection unsorted.
 * 
 * get(k), put(k, v), and remove(k) requires an initial scan of the array to determine whether an entry with key equal to k exists.
 * However, this makes out implementation rather inefficient as we our worst case is always O(n) time.
 * 
 * Also we by adopting the following convention:
 * Because the map is unordered, we prefer to fill the vacated cell of the array by relocating the last entry to that location. Suchan update step runs in constant time.
 * We are able perfom remove in O(1).
 * 
 * 
 * @author sid16
 *
 */
public class UnsortedTableMap<k,v> extends AbstractMap<k,v>{
	//feilds
	private ArrayList<MapEntry<k,v>> table = new ArrayList<>(); //underlying storage for map of entries.
	//constructor
	public UnsortedTableMap() {
		//creates an empty Unsorted Tablemap that upon call, creates a new instance of ArrayList<MapEntry<k,v>> as seen above!!!
	}
	//public methods (General, Qurey, Getters, Setters, Mutators)
	public int size() {
		return table.size();
	}
	public v get(k key) {//retruns value assocaited with specified key, else throws exception
		int targetKeyIndex = findKey(key);
		if(targetKeyIndex == -1)
			return null;
		return this.table.get(targetKeyIndex).getValue();
	}
	public v put(k key, v value) { //associates given key with value, replacing a previous value.
		int targetKeyIndex = findKey(key);
		if(targetKeyIndex == -1) {
			this.table.add(new MapEntry<>(key, value));
			return null;
		}//else
		//getting and setting in the same line! ==> VERY COOL!
		return this.table.get(targetKeyIndex).setValue(value);
	}
	public v remove(k key) { //removes the entry with specified key and returns it's value
		//REMEBR we adopt a speical remove convention for this!
		int targetKeyIndex = findKey(key);
		if(targetKeyIndex == -1)
			return null;
		//else
		v answer = this.table.get(targetKeyIndex).getValue();
		int n = table.size();
		if(targetKeyIndex != n-1)
			this.table.set(targetKeyIndex, this.table.get(n-1));
		this.table.remove(n-1);
		return answer;
	}
	//Iterable method
	public Iterable<Entry<k,v>> entrySet(){
		return new EntryIterable();
	}
	public Iterable<k> keySet() {
		return new keyIterable();
	}
	public Iterable<v> values() {
		return new elementIterable();
	}
	//Private Utility Methods
	private int findKey(k key) { //utility for scanning list for exisiting entries! O(n)
		int n = this.table.size();
		for(int i = 0; i < n; i++) {
			if(table.get(i).getKey().equals(key)) {return i;}
		}
		return -1;
	}
	//Private nested classes part of utilities
	private class EntryIterator implements Iterator<Entry<k,v>>{
		private int j = 0;
		public boolean hasNext() {
			return j > table.size();
		}
		public Entry<k,v> next(){
			if(j == table.size())
				throw new NoSuchElementException();
			 return table.get(j++); //here is where the MAGIC happens! :) 
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	private class EntryIterable implements Iterable<Entry<k,v>>{
		public Iterator<Entry<k,v>> iterator(){
			return new EntryIterator();
		}
	}
	private class keyIterator implements Iterator<k>{ //Key iterator
		private Iterator<Entry<k,v>> entryIterator = new EntryIterator();
		public boolean hasNext() {
			return entryIterator.hasNext();
		}
		public k next() {
			return entryIterator.next().getKey();
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	private class keyIterable implements Iterable<k>{ //encaspulating the key iterator into an Iterable Class so that it can be intanciated as an object (mutiple times concurrently if nessecary) and allow for for-each loop syntax to be used beacuse it is iterable.
		public Iterator<k> iterator(){
			return new keyIterator();
		}
	}
	private class elementIterator implements Iterator<v>{//Element iterator
		private Iterator<Entry<k,v>> entryIterator = new EntryIterator();
		public boolean hasNext() {
			return entryIterator.hasNext();
		}
		public v next() {
			return entryIterator.next().getValue();
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	private class elementIterable implements Iterable<v>{//encapsulating elementIterator into class as its allows for multiple concurrent instantiations and for-each loop syntax to be used. Also this is suppourt for valueSet method.
		public Iterator<v> iterator(){
			return new elementIterator();
		}
	}
}
