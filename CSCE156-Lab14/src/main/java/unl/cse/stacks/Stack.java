package unl.cse.stacks;

import unl.cse.linked_list.LinkedList;


public class Stack<T> {

	private final LinkedList<T> list = new LinkedList<T>();
	
	
	public T pop() {
		//TODO: implement this method
		if(this.list.isEmpty()) {
			return null;
		}
		T item = list.getElementFromHead();
		list.removeElementFromHead();
		return item;
		
	}
	
	public void push(T item) {
		//TODO: implement this method
		list.addElementToHead(item);
	}

	public int size() {
		//TODO: implement this method
		return list.size();
	}
	
	public boolean isEmpty() {
		//TODO: implement this method
		return this.list.isEmpty();
	}
	
	public static void main(String args[]) {
		
		Stack<Integer> s = new Stack<>();
		s.push(8);
		s.push(5);
		s.push(3);
		s.push(10);
		
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());

		
	}
	
}
