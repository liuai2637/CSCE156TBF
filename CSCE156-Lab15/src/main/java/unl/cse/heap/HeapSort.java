package unl.cse.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HeapSort {

	/**
	 * Sorts the given {@link List} using the provided {@link Comparator} using a
	 * heap sort algorithm.
	 * 
	 * @param <T>
	 * @param list
	 * @param comp
	 */
	public static <T> void heapSort(List<T> list, Comparator<T> comp) {

		// TODO: implement this
		Heap<T> h = new Heap<T>(list.size(), comp);

		for (T a : list) {
			h.addElement(a);
		}
		list.clear();
		while (!h.isEmpty()) {
			list.add(h.getTop());
		}

		return;
	}

	/**
	 * An ad-hoc randomized test for the {@link #heapSort(List, Comparator)} method.
	 * 
	 * @param args
	 */
	public static void runTest() {

		int n = 25;
		Random r = new Random();
		List<Integer> A = new ArrayList<Integer>();
		List<Integer> B = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			Integer a = r.nextInt(1000);
			A.add(a);
			B.add(a);
		}
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg1.compareTo(arg0);
			}
		};

		heapSort(A, comp);
		Collections.sort(B, comp);
		System.out.println(A);
		System.out.println(B);
		if (!A.equals(B)) {
			throw new IllegalStateException("A does not appear to be sorted");
		}
	}

	public static void main(String args[]) {
		HeapSort.runTest();
	}
}
