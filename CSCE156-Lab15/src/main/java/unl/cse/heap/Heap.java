package unl.cse.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap<T> {

	private final PriorityQueue<T> pq;
	private final Comparator<T> comparator;
	
	public Heap(int initialCapacity, Comparator<T> comparator) {
		this.pq = new PriorityQueue<T>(initialCapacity, comparator);
		this.comparator = comparator;
	}
	
	public T getTop() {
		return pq.poll();
	}

	public void addElement(T item) {	
		this.pq.add(item);		
	}
	
	public int size() {
		return pq.size();
	}
	
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
}
