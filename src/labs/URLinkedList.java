package labs;


/* 
 * Name: Brian Jesse Gatukui Kimani
 * NetID: bkimani (bkimani@u.rochester.edu)
 * partner:rtusiime (rtusiime@u.rochester.edu)
 * Lab 6
 * Lab TR 14:00 - 15:15 
 * I collaborated with Kevin Tusiime on this assignment.		
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class URLinkedList<E> implements Comparable<E>,URList<E>{
	
	private int size;
	private URNode<E> head=null;
	private URNode<E> tail =null;
	
	private class URNode<E> { // Doubly linked list node

		private E e; // Value for this node
		private URNode<E> n; // Reference to next node in list
		private URNode<E> p; // Reference to previous node

		// Constructors
		URNode(E it, URNode<E> inp, URNode<E> inn) { e = it; p = inp; n = inn; }
		URNode(URNode<E> inp, URNode<E> inn) { p = inp; n = inn; }

		// Get and set methods for the data members
		public E element() { return e; } // Return the value
		public E setElement(E it) { return e = it; } // Set element value
		public URNode<E> next() { return n; } // Return next link
		public URNode<E> setNext(URNode<E> nextval) { return n = nextval; } // Set next link
		public URNode<E> prev() { return p; } // Return prev link
		public URNode<E> setPrev(URNode<E> prevval) { return p = prevval; } // Set prev link

	}
	

	public int compareTo(E e) {
		if(this.compareTo(e)>0)
			return 1;
		else if(this.compareTo(e)==0)
			return 0;
		return -1;
	}
	public int size() { //returns the number of elements currently in the list
		if(this.isEmpty())
			return 0;
		return size;
		
	}
	public boolean isEmpty() { //if head is null list is empty
		if(head==null)
			return true;
		return false;
	}
	
	
	public boolean add(E e) { // appends an element to the end of the list
		if(this.isEmpty()) {
			URNode<E> node = new URNode<E>(e,null,null);
			head=tail=node;
			size++;
			return true;
		}
		else {
			URNode<E> node = new URNode<E>(e,tail,null);
			tail.setNext(node);
			tail=node;
			size++;
			return true;
		}
	}
	public void addFirst(E e) {// adds element to the front of the list
		if (this.isEmpty())
			head = tail =  new URNode<E>(e,null,null);
		else {
			URNode<E> node = new URNode<E>(e,null,head);
			head.setPrev(node);
			head = node;
		}
		size++;	
	}
	public void addLast(E e) { //adds an element to the end of the list
		URNode<E> node = new URNode<E>(e,tail,null);
		tail.setNext(node);
		tail=node;
		size++;
	}
	public void add(int index, E element) { // adds an element at the specified index in the list
		if(index<0||index>=size)
			throw new IllegalArgumentException();
		
		URNode<E> node = new URNode<E>(element,null,null);
		URNode<E> pointer = head; // keeps track of the elements as we traverse the list
		for(int i=0;i<index;i++) 
			pointer=pointer.next();
		node.setNext(pointer); //change for pointers to insert the element
		node.setPrev(pointer.prev());
		pointer.prev().setNext(node);
		pointer.setPrev(node);	
		size++;
	}
	
	
	@Override
	public boolean addAll(Collection<? extends E> c) { // adds all the elements in a collection to the end of the list
		Iterator it = c.iterator();
		while(it.hasNext()) {
			this.addLast((E)it.next());
		}
		return true;
	}
	@Override
	//uses an iterator to individually add elements at the given index
	public boolean addAll(int index, Collection<? extends E> c) { 
		Iterator it = c.iterator();
		while(it.hasNext())
			this.add(index++,(E)it.next());
		return true;
	}
	@Override
	public void clear() {//traverses the list and sets everything to null;

		while(head!=null) {
			URNode<E> temp = head.next();
			head.setPrev(null);
			head.setNext(null);
			head.setElement(null);
			head = temp;
		}
		head=tail=null;
		size=0;
	}
	@Override
	public boolean contains(Object o) {//iterates through each item in the list and checks for equality.
		URNode<E> pointer = head; //keeps track of elements as we traverse list
		while(pointer!=null) {
			if(pointer.element().equals(o))
				return true;
			pointer=pointer.next();
		}
			
		return false;
	}
	@Override
	//uses a counter to iterate and count the number of elements contained in the list 
	//returns true if all elements in the collection are in the list.
	public boolean containsAll(Collection<?> c) { 
		int count = 0;
		Iterator it = c.iterator();
		while(it.hasNext()) {
			if(this.contains(it.next()))
				count++;
		}
		if(count==c.size())
			return true;
		return false;
	}
	@Override
	//returns the element at the specified index
	public E get(int index) {
		if(index<0||index>=size)
			throw new IllegalArgumentException();
		
		URNode<E> pointer = head;
		for(int i=0;i<index;i++)
			pointer=pointer.next();
		return pointer.element();
	}
	@Override
	//counts from 0 up until the location of the element and returns the count as the index
	public int indexOf(Object o) {
		if(!this.contains(o))
			return -1;
		int count=0;
		URNode<E> pointer = head;
		while(pointer!=null) {
			if(pointer.element().equals(o))
				break;
			count++;
			
			pointer=pointer.next();
		}
		return count;
	}
	@Override
	//instantiates a new iterator that can be used to traverse the list
	// by overriding the methods from the Iterator class in java
	public Iterator<E> iterator() {
		 Iterator<E> it = new Iterator<E>() {
			private URNode<E> pointer =head;
			public boolean hasNext() {
				return pointer!=null;
			}
			public E next() {
				E data = pointer.element();
				pointer = pointer.next();
				return data;
			}
		 };
		return it;
	}
	@Override
	//uses the get(index) method which returns the object to be removed 
	// the uses the remove object method to remove the element from the list
	public E remove(int index) {
		 E data = this.get(index);
		this.remove(data);
		return data;
	}
	@Override
	public boolean remove(Object o) { //searches for the element in the array and removes it
		if(!this.contains(o))
			return false;
		URNode<E> pointer = head;
		while(pointer!=null) {
			if(o.equals(pointer.element())) {
				if(pointer.next()==null) {// in case the element is the last node in the list
					this.pollLast();
					return true;
				}
				pointer.prev().setNext(pointer.next());
				pointer.next().setPrev(pointer.prev());
				size--;
				return true;
			}
			pointer=pointer.next();	
		}
		return false;
	}
	@Override
	//iterates through the collection and uses the remove method to remove every item from the list
	public boolean removeAll(Collection<?> c) {
		Iterator it = c.iterator();
		while(it.hasNext())
			this.remove((E)it.next());
		return true;
	}
	@Override
	//uses add(index) and remove methods to set new element at position specified
	public E set(int index, E element) throws IllegalArgumentException {
		if(index<0||index>size)
			throw new IllegalArgumentException();
		this.remove(index);
		this.add(index, element);
		return element;
	}
	//prints using iterator
	public void print() {
		Iterator it = this.iterator();
		System.out.print("[");
		while(it.hasNext())
			System.out.print(it.next()+" ");
		
		System.out.println("]");
		
	}
	@Override
	//creates a subList from another list inclusive of the fromIndex, exclusive of the to Index.
	public URLinkedList<E> subList(int fromIndex, int toIndex) {
		
		URLinkedList<E> subList = new URLinkedList<>();
		for(int i=fromIndex;i<toIndex;i++)
			subList.add(this.get(i));
		return subList;
	}
	@Override
	//creates a new E array and copies all the items from the list using an iterator.
	public Object[] toArray() {
		Iterator it = this.iterator();
		E[] array = (E[])new Object[size];
		int i=0;
		while(it.hasNext())
			array[i++]= (E)it.next();

		return array;
	}
	// Retrieves, but does not remove, the first element of this list, or returns null if
	//this list is empty.
	public E peekFirst() {
		if(this.isEmpty())
			return null;
		return head.element();
	}
	// Retrieves, but does not remove, the last element of this list, or returns null if
	//this list is empty.
	public E peekLast() {
		if(this.isEmpty())
			return null;
		return tail.element();
	}
	// Retrieves and removes the first element of this list, or returns null if this list
	//is empty.
	public E pollFirst() {
		if(this.isEmpty())
			return null;
		E data = head.element();
		head=head.next();
		size--;
		if(this.isEmpty())
			tail=null;
		else
			head.setPrev(null);
		return data;
	}
	// Retrieves and removes the last element of this list, or returns null if this list
	//is empty.
	public E pollLast() {
		if(size==1) {
			E data = tail.element();
			tail=head=null;
			return data;
		}
		E data = tail.element();
		tail = tail.prev();
		size--;
		if(this.isEmpty())
			head=null;
		else
			tail.setNext(null);
		return data;
		
	}
	

}
