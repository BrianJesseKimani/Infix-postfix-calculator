package labs;
/* 
 * Name: Brian Jesse Gatukui Kimani
 * NetID: bkimani (bkimani@u.rochester.edu)
 * partner:rtusiime (rtusiime@u.rochester.edu)
 * Lab 5
 * Lab TR 14:00 - 15:15 
 * I collaborated with Kevin Tusiime on this assignment.		
 */

public interface Queue<T> {
	
	public boolean isEmpty();
	public void enqueue(T x);
	public T dequeue();
	public T peek();
}

