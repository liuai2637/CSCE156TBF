package unl.cse.linked_list;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

	private Node<T> head = null;
	private Node<T> tail = null;
	
	public void addElementToHead(T item) {
		if(item == null)
			throw new IllegalArgumentException("This LinkedList implementation does not allow null elements");
		Node<T> newHead = new Node<T>(item);
		if(this.tail == null) {
			this.head = newHead;
			this.tail = newHead;
		} else {
			newHead.setNext(this.head);
			this.head.setPrevious(newHead);
			this.head = newHead;
		}
	}
	
	public T removeElementFromHead() {
		T item = null;
		if(this.size() == 0) {
			throw new IllegalStateException("Cannot remove from an empty list");
		} else if(this.size() == 1) {
			item = this.head.getItem();
			this.head = null;
			this.tail = null;
		} else {
			item = this.head.getItem();
			this.head = this.head.getNext();
			this.head.setPrevious(null);
		}
		return item;
	}
	
	/**
	 * Returns the element at the head of this list, but does not remove it.
	 * @return
	 */
	public T getElementFromHead() {
		if(this.size() == 0) {
			throw new IllegalStateException("Cannot retrieve from an empty list");
		} else {
			return this.head.getItem();
		}
	}
	
	public void addElementToTail(T item) {
		if(item == null)
			throw new IllegalArgumentException("This LinkedList implementation does not allow null elements");
		Node<T> newTail = new Node<T>(item);
		if(this.tail == null) {
			this.tail = newTail;
			this.head = newTail;
		} else {
			newTail.setPrevious(this.tail);
			this.tail.setNext(newTail);
			this.tail = newTail;
		}
	}
	
	public T removeElementFromTail() {
		T item = null;
		if(this.size() == 0) {
			throw new IllegalStateException("Cannot remove from an empty list");
		} else if(this.size() == 1) {
			item = this.tail.getItem();
			this.head = null;
			this.tail = null;
		} else {
			item = this.tail.getItem();
			this.tail = this.tail.getPrevious();
			this.tail.setNext(null);
		}
		return item;
	}

	/**
	 * Returns the element at the tail of this list, but does not remove it.
	 * @return
	 */
	public T getElementFromTail() {
		if(this.size() == 0) {
			throw new IllegalStateException("Cannot retrieve from an empty list");
		} else {
			return this.tail.getItem();
		}
	}

	public int size() {
		int count = 0;
		for(T item : this) {
			count++;
		}
		return count;
	}
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> curr = head;
			@Override
			public boolean hasNext() {
				if(curr == null)
					return false;
				else
					return true;
			}
			@Override
			public T next() {
				T item = curr.getItem();
				curr = curr.getNext();
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("not implemented");
			}};
	}

	@Override
	public String toString() {
		if(this.head == null) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> curr = head;
		while(curr != null) {
			sb.append(curr.getItem());
			if(curr.getNext() != null)
				sb.append(", ");
			curr = curr.getNext();
		}
		sb.append("]");
		return sb.toString();
	}

	public static void main(String args[]) {
		LinkedList<Integer> llist = new LinkedList<Integer>();
		llist.addElementToHead(10);
		llist.addElementToHead(20);
		llist.addElementToTail(-10);
		llist.addElementToTail(-20);
		System.out.println(llist);
		System.out.println(llist.removeElementFromHead());
		System.out.println(llist.removeElementFromTail());
		System.out.println(llist);
		llist.addElementToTail(-50);
		llist.addElementToTail(-60);
		System.out.println(llist);
		System.out.println(llist.isEmpty());
		System.out.println(llist.removeElementFromTail());
		System.out.println(llist.removeElementFromTail());
		System.out.println(llist.removeElementFromTail());
		System.out.println(llist.removeElementFromTail());
		System.out.println(llist.isEmpty());
		llist.addElementToTail(-50);
		llist.addElementToTail(-60);
		System.out.println(llist);
	}



}
