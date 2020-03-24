/**
 * 
 */
package eecs2011;

/**
 * @author sid16
 *
 */
public class AAQueue<E> implements Queue<E> {
    /**
     * Storage array of objects for the queue.
     */
    protected Object[] s;
    /**
     * the front index of the queue.
     */
    protected int front;
    /**
     * the rear index of the queue, which is the next available location
     * for the next enqueue object into this queue.
     */
    protected int rear;

    /**
     * Constructs an ArrayQueue with an array of n objects.
     *
     * @param  n  size of storage array for the queue.
     */
    public AAQueue(int n) {
        s = new Object[n];
        front = 0;
        rear = 0;
    }

    /**
     * Returns true if queue is empty.
     */
    public boolean isEmpty() { return (front == rear); }

    /**
     * Adds a new object into the end of the queue.
     * Throws a <code>QueueFullException</code> object if queue is full.
     *
     * @param  obj  object to be added to the queue.
     */
    public void enqueue(Object obj) {
       int nextRear = (rear+1)%(s.length);
       if ( front == nextRear )
           throw new FullQueueException("Full queue: enqueue object ="+obj);
       s[rear] = obj;
       rear = nextRear;
    }

    /**
     * Removes an object from the queue.
     * Throws a <code>QueueEmptyException</code> object if queue is empty.
     *
     * @return  object from the front of the queue.
     */
    public Object dequeue() {
       if ( isEmpty() )
           throw new EmptyQueueException("Location of front "+front);
       Object obj = s[front];
       s[front] = null;
       front = (front+1)%(s.length);
       return obj;
    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object front() throws EmptyQueueException {
		// TODO Auto-generated method stub
		return null;
	}
}

