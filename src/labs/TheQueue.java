package labs;

/* 
 * Name: Brian Jesse Gatukui Kimani
 * NetID: bkimani (bkimani@u.rochester.edu)
 * partner:rtusiime (rtusiime@u.rochester.edu)
 * Lab 7
 * Lab TR 14:00 - 15:15 
 * I collaborated with Kevin Tusiime on this assignment.		
 */
public class TheQueue<T> implements Queue<T>{
	
	private int size; //track of size
	URLinkedList<T> queue;
	
	public TheQueue() {//constructor
		queue= new URLinkedList<T>();
		size=0;
	}
	
	@Override
	public boolean isEmpty() {
		if(size!=0)
			return false;
		return true;
	}

	@Override
	public void enqueue(T x) { //adds elements to the back of the queue
		if(queue.isEmpty()) 
			queue.addFirst(x);
		else
			queue.addLast(x);
		size++;
	}

	@Override
	public T dequeue() { // removes elements from the front of the queue
		if(queue.isEmpty())
			return null;
		T data = queue.pollFirst();
		size--;
		return data;
	}

	@Override
	public T peek() { //returns element at the fo=rotn of the queue
		if(queue.isEmpty())
			return null;
		T data = queue.peekFirst();
		return data;
	}

	public static void main(String[] args) {
		TheQueue<Integer> queue = new TheQueue<>();
		System.out.println("Queue is empty? "+ queue.isEmpty());
		queue.enqueue(5);
		queue.dequeue();
		queue.dequeue();
		int i = queue.size;
		System.out.println("size: "+i);
		queue.enqueue(34);
		queue.enqueue(444);
		queue.enqueue(33);
		i=queue.size;
		System.out.println("Queue is empty ? " + queue.isEmpty());
		System.out.println("First in the queue is "+ queue.peek()+" "+" and queue size is " + i);
		System.out.println("remove first element: "+queue.dequeue()+ " from the list");
	}
}
