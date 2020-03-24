
public class LinkedQeue {
	//nested node class
	public class Node {
		int data;
		Node nextNode;
		
		public Node ( int data, Node nextNode) {
			this.data = data;
			this.nextNode = nextNode;
		}
		
		public Node (int data) {
			this(data, null);
		}
	}
	
	//start of qeue class 
	public Node head;
	public Node tail;
	
	public int size; 
	
	public void add ( int e) {
		Node newNode = new Node(e);
		
		if (tail != null) {
			tail.nextNode = newNode;
		}
		tail = newNode;
		
		//if list is empty
		if (head == null) {
			head = tail;
		}
	}
	
	public int remove () {
		int data = head.data;
		head = head.nextNode;
		return data;
	}
}
