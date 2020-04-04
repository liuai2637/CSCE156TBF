package unl.cse.trucks;

/**
 * A linked list implementation for <code>Truck</code> instances.
 *
 */
public class TruckList {

	// TODO: add the head of your list here

	private TruckListNode head;
	public int size;
	
	/**
	 * This function returns the size of the list, the number of elements currently
	 * stored in it.
	 * 
	 * @return
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * This function clears out the contents of the list, making it an empty list.
	 */
	public void clear() {
		if(this.size == 0) {
			throw new IllegalStateException("You cannot remove from an empty list");
		}
		TruckListNode curr = this.head;
		while(curr != null) {
			this.head = this.head.getNext();
			this.size--;
			curr = this.head;
		}
	}

	/**
	 * This method adds the given {@link Truck} instance to the beginning of the
	 * list.
	 * 
	 * @param t
	 */
	public void addToStart(Truck t) {
		TruckListNode newHead = new TruckListNode (t);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
	}

	/**
	 * This method adds the given {@link Truck} instance to the end of the list.
	 * 
	 * @param t
	 */
	public void addToEnd(Truck t) {
		if(this.size == 0) {
			addToStart(t);
			return;
		}
		TruckListNode curr = this.head;
		while(curr.getNext() != null) {
			curr = curr.getNext();
		}
		TruckListNode newTail = new TruckListNode(t);
		curr.setNext(newTail);
		this.size++;
	}

	/**
	 * This method removes the {@link Truck} from the given <code>position</code>,
	 * indices start at 0. Implicitly, the remaining elements' indices are reduced.
	 * 
	 * @param position
	 */
	public void remove(int position) {
		if(position < 0 || position >= this.size) {
			System.out.println("position out of bounds");
		}
		
		if(position == 0) {
			this.head = this.head.getNext();
			this.size--;		
		} else {
		TruckListNode prev = getTruckListNode(position-1);
		TruckListNode curr = prev.getNext();
		
		//make prev point to curr's next node:
		prev.setNext(curr.getNext());
		this.size--;
		}
	}

	/**
	 * This is a private helper method that returns a {@link TruckListNode}
	 * corresponding to the given position. Implementing this method is optional but
	 * may help you with other methods.
	 * 
	 * @param position
	 * @return
	 */
	private TruckListNode getTruckListNode(int position) {
		if(position < 0 || position >= this.size) {
			throw new IllegalArgumentException("index out of bounds!");
		}
		
		//find the node at the given index...
		TruckListNode curr = this.head;
		for(int i=0; i<position; i++) {
			curr = curr.getNext();
		}
		return curr;
	}

	/**
	 * Returns the {@link Truck} element stored at the given <code>position</code>.
	 * 
	 * @param position
	 * @return
	 */
	public Truck getTruck(int position) {
		return getTruckListNode(position).getTruck();
	}

	/**
	 * Prints this list to the standard output.
	 */
	public void print() {

		StringBuilder sb = new StringBuilder();		
		TruckListNode curr = this.head;
		while(curr != null) {
			sb.append(curr.getTruck() + "\n");
			curr = curr.getNext();
		}
		System.out.println(sb);
	}

}
