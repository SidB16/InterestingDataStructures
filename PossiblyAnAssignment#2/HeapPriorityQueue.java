package A3;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 */

/**
 * A priority queue using an array-based heap
 * Be mindful of 2 main properties:
 * 1) heap property
 * 2) stuctural property  ==> enforce complete binary tree!
 * @author sid16
 *
 */
public class HeapPriorityQueue<k,v> extends AbstractPriorityQueue<k,v> {
	protected ArrayList<Entry<k,v>> heap = new ArrayList<>(); //primary collection of PQ entries.
	public HeapPriorityQueue() { super();} //creates PQ based on natural ordering of it's keys.
	public HeapPriorityQueue(Comparator<k> comp) { super(comp);}//creats heap PQ with a comparator given to order keys.
	//protected utilities
	protected int parent(int j) { return (j-1) / 2;}
	protected int left(int j) { return 2*j + 1; }
	protected int right(int j) { return 2*j + 2;}
	protected boolean hasLeft(int j) { return left(j) < heap.size();}
	protected boolean hasRight(int j) { return right(j) < heap.size();}
	protected void swap(int i, int j) { //swap entries at i and j of the (heap) arraylist.
		Entry<k,v> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	protected void upheap(int j) { //move entry at index j higher, if nessecary to restore heap order.
		while(j>0) { //continue until reaching root or break
			int p = parent(j);
			if(compare(heap.get(j), heap.get(p)) >= 0) break; //if this statment is triggerd then heaporder property is verified.
			swap(j,p); //swap child j with parent p
			j = p; //set j to p (which is swaped child) to check if heaporder property is still violated
		}
	}
	protected void downheap(int j) { //moves entry lower, if nessecary to maintain heap order.
		while(hasLeft(j)) { //continue to bottom (or break statment))
			int leftIndex = left(j);
			int smallChildIndex = leftIndex; //although right may be smaller
			if(hasRight(j)) {
				int rightIndex = right(j);
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
					smallChildIndex = rightIndex; //right child is smaller
				}
				if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
					break;  //heap property has been restored.
				}
				swap(j, smallChildIndex);
				j = smallChildIndex; //continue at position child of the recently replaced entry.
			}
		}
	}
	//public methods
	public int size() {		
		return this.heap.size();
	}
	public Entry<k, v> min() {
		if(this.heap.isEmpty()) return null;
		return this.heap.get(0);
	}
	//inserts a key-value par and returns the entry created
	public Entry<k, v> insert(k key, v value) throws IllegalArgumentException {
		checkKey(key); //auxilary key-checking method which could throw exception
		Entry<k,v> newest = new PQEntry<k,v>(key,value);
		heap.add(newest); //add to the end of list
		upheap(heap.size()-1); //upheap newly added entry
		return newest;
	}
	public Entry<k, v> removeMin() {//removes and returns entry with minimal key
		if(heap.isEmpty()) return null;
		Entry<k,v> answer = heap.get(0);
		swap(0,heap.size()-1);
		heap.remove(heap.size()-1);
		downheap(0);
		return answer;
	}
}
