package unl.cse.queues;

import unl.cse.linked_list.LinkedList;
import unl.cse.stacks.Stack;

public class Queue<T> {

	private final LinkedList<T> list = new LinkedList<T>();
	
	public T dequeue() {
		//TODO: implement this method
		if(this.list.isEmpty()) {
			return null;
		}
		T item = list.getElementFromHead();
		list.removeElementFromHead();
		return item;
	}
	
	public void enqueue(T item) {
		list.addElementToTail(item);
	}

	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		//TODO: implement this method
		return this.list.isEmpty();
	}
	
	public static void main(String args[]) {
		
		Queue<Integer> q = new Queue<>();
		
		q.enqueue(8);
		q.enqueue(3);
		q.enqueue(10);
		q.enqueue(26);
		
		
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		
	}
	
}
