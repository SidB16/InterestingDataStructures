
public class LinkedStack{

	//nested node class
	public class Node {
		int data;
		Node nextNode;
		
		public Node ( int data, Node nextNode) {
			this.data = data;
			this.nextNode = nextNode;
		}
		public Node( int data) {
			this(data, null);
		}
	}
		
	public Node head;
	public int size;
	
	public void push( int e) {
		Node newNode = new Node(e);
		
		//check for empty list
		//aka no head
		if (head == null) { head = newNode;}
		
		//if stack has head
		newNode.nextNode = head;
		head = newNode;
	}
	
	public int pop() {
		int data = head.data;
		head = head.nextNode;
		return data;
	}
	
	public int peek() {
		return head.data;
	}
}
