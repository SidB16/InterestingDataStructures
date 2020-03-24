import java.util.Iterator;

/**
 * 
 */

/**
 * Be sure to study the map ADT functionality example, which shows what to return for each method.
 * @author sid16
 *
 */
public abstract class AbstractMap<k,v> implements Map<k,v> {
	//nested MapEntry class
	protected class MapEntry<k,v> implements Entry<k,v>{
		//Feilds
		k key;
		v value;
		//constructor
		public MapEntry() { }
		public MapEntry(k key, v value) {
			this.key = key;
			this.value = value;
		}
		//getter
		public k getKey() {
			return this.key;
		}
		public v getValue() {
			return this.value;
		}
		//setter
		public void setKey(k newKey) {
			this.key = newKey;
		}
		public v setValue(v newValue) { //return the old value tho!
			v rtn = this.value; 
			this.value = newValue;
			return rtn;
		}
	}
	//general methods that can be abstractly defined!
	public boolean isEmpty() {
		return this.size() == 0; //this can be defined now bc we are using the size() which we can define concretely later but can use bc we declared it eariler
	}
	//support for keySet method (uses entrySet() method abstractly again as we declared entrySet in our interface but will provide a concerete impelementation in a our concerete class
	private class KeyIterator implements Iterator<k>{ //this iterator will iterate over keys thus it implemets Iterator<k>
		private Iterator<Entry<k,v>> entries = entrySet().iterator(); //this iterator iterates over Entry<k,v> thus when we implement it in our
		public boolean hasNext() {
			return entries.hasNext();
		}
		public k next() {
			return entries.next().getKey();
		}
		public void remove() {throw new UnsupportedOperationException();}
	}
	private class KeyIterable implements Iterable<k>{ //this class uses an iterator that iterates over keys thus, it will implement iterable<k>
		public Iterator<k> iterator(){ //
			return new KeyIterator(); //return the iterator we created!
		}
	}
	//suppourt for valueSet method!
	private class valueIterator implements Iterator<v>{ //this method iterates over type value thus implements an iteraor<k>
		private Iterator<Entry<k,v>> entries = entrySet().iterator(); //it uses the abstract entrySet() method which is declared in our interface and will be concretely defined in our class
		public boolean hasNext() {
			return entries.hasNext();
		}
		public v next() {
			return entries.next().getValue();
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	private class valueIterable implements Iterable<v>{
		public Iterator<v> iterator(){
			return new valueIterator();
		}
	}
}
