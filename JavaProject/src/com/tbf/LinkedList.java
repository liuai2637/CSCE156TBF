package com.tbf;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

	private Comparator<T> comp;
	private Node<T> head;
	private int size;

	public LinkedList(Comparator<T> comp) {
		this.comp = comp;
		this.head = null;
		this.size = 0;
	}

	public T getElementAtIndex(int index) {
		return getNodeAtIndex(index).getItem();
	}

	private Node<T> getNodeAtIndex(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("index out of bounds!");
		}
		Node<T> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		return curr;
	}

	public void insertAtIndex(T item, int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("index out of bounds!");
		}
		if (index == 0) {
			this.insertAtHead(item);
		} else if (index == this.size) {
			this.insertAtTail(item);
		} else {
			Node<T> newNode = new Node(item);
			Node<T> prevNode = this.getNodeAtIndex(index - 1);
			Node<T> currNode = prevNode.getNext();
			newNode.setNext(currNode);
			prevNode.setNext(newNode);
			this.size++;
		}

	}

	public void insertAtHead(T item) {
		Node<T> newHead = new Node<>(item);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
	}

	public void insertAtTail(T item) {
		if (this.isEmpty()) {
			insertAtHead(item);
			return;
		}
		Node<T> curr = this.head;
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		Node<T> newTail = new Node<>(item);
		curr.setNext(newTail);
		this.size++;
	}

	public int getSize() {
		return this.size;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public void insertIntoSorted(T itemA) {
		if (this.isEmpty()) {
			insertAtHead(itemA);
			return;
		} else {
			int i = 0;
			int x = 1;
			int flag = 0;

			while (x > 0) {
				T itemB = getElementAtIndex(i);
				x = comp.compare(itemA, itemB);

				if (x <= 0) {
					insertAtIndex(itemA, i);
					flag = 1;
					break;
				}

				i++;
				if (i == this.getSize()) {
					break;
				}
			}
			if (flag == 0) {
				insertAtIndex(itemA, i);
			}

		}
	}

	public static final Comparator<Portfolio> sortByOwnerLastName = new Comparator<Portfolio>() {
		public int compare(Portfolio a, Portfolio b) {
			int x = a.getOwner().getName().getLastName().compareTo(b.getOwner().getName().getLastName());
			if (x == 0) {
				x = a.getOwner().getName().getFirstName().compareTo(b.getOwner().getName().getFirstName());
			}
			return x;
		}
	};

	public static final Comparator<Portfolio> sortByPortfolioValueDesc = new Comparator<Portfolio>() {
		public int compare(Portfolio a, Portfolio b) {
			if (a.getPortValue() == b.getPortValue()) {
				return 0;
			} else if (a.getPortValue() > b.getPortValue()) {
				return -1;
			} else {
				return 1;
			}
		}
	};

	public static final Comparator<Portfolio> sortByManager = new Comparator<Portfolio>() {
		public int compare(Portfolio a, Portfolio b) {
			int x = a.getManager().getBroker().getType().compareTo(b.getManager().getBroker().getType());
			if (x == 0) {
				x = a.getManager().getName().getLastName().compareTo(b.getManager().getName().getLastName());
			}
			return x;
		}
	};

	@Override
	public Iterator<T> iterator() {
		return new MyLinkedListIterator();
	}

	class MyLinkedListIterator implements Iterator<T> {

		Node<T> current = null;

		@Override
		public boolean hasNext() {
			if (current == null && head != null) {
				return true;
			} else if (current != null) {
				return current.getNext() != null;
			}
			return false;
		}

		@Override
		public T next() {
			if (current == null) {
				current = head;
				return head.getItem();
			} else if (current != null) {
				current = current.getNext();
				return current.getItem();
			}
			throw new NoSuchElementException();
		}

	}

}
