package quicksort;

import java.util.concurrent.RecursiveAction;

public class QuickSorter extends RecursiveAction {

	int[] array;
	int start;
	int end;

	public QuickSorter(int[] array, int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {

		if (start >= end) {
			return;
		}
		int pivot = partition();
		
		invokeAll(new QuickSorter(array, start, pivot - 1), new QuickSorter(array, pivot + 1, end));
	}

	private int partition() {
		int marker = start;
		for (int i = start; i <= end; i++) {
			if (array[i] <= array[end]) {
				int temp = array[marker]; // swap
				array[marker] = array[i];
				array[i] = temp;
				marker += 1;
			}
		}
		return marker - 1;
	}
}