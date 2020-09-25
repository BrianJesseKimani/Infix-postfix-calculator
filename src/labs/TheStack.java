package labs;

/* 
 * Name: Brian Jesse Gatukui Kimani
 * NetID: bkimani (bkimani@u.rochester.edu)
 * partner:rtusiime (rtusiime@u.rochester.edu)
 * Lab 6
 * Lab TR 14:00 - 15:15 
 * I collaborated with Kevin Tusiime on this assignment.		
 */

public class TheStack<T> implements Stack<T>{
	 private int size; //to keep track of size of the stack
	 URLinkedList<T> stack;
	public TheStack() {	//constructor
		stack = new URLinkedList<T>();
		size=0;
	}

	@Override
	public boolean isEmpty() {
		if(size!=0)
			return false;

		return true;
	}

	@Override
	public void push(T x) { // adds an element on top of the stack
		stack.addFirst(x);
		size++;
	}

	@Override
	public T pop() { //removes element from the top of the stack
		if(stack.isEmpty())
			return null;
		T data = stack.pollFirst();
		size--;
		return data;
	}

	@Override
	public T peek() { // returns the element at the top of the stack 
		if(stack.isEmpty())
			return null;
		T data = stack.peekFirst();
		return data;
	}
	
	public static void main(String[] args) {
		
		TheStack<Integer> stack = new TheStack<>();
		stack.push(5);
		stack.pop();
		stack.push(6);
		stack.push(7);
		stack.push(23);
		System.out.println("top: "+stack.peek());
		System.out.println("remove from top: " + stack.pop());
		stack.pop();
		stack.pop();
		stack.pop();
		stack.push(23);
		stack.push(23);
		stack.push(23);
		stack.push(234333);
		System.out.println("top: "+stack.peek());
		System.out.println("empty? "+stack.isEmpty());
		int i = stack.size;
		System.out.println("size: "+ i);
	}
	
	
	

}
