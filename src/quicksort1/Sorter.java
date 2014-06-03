package quicksort1;

import java.util.concurrent.BlockingQueue;

public class Sorter implements Runnable {

	private BlockingQueue<Runnable> sortersQueue;
	private int[] array;
	private int start;
	private int end;

	public Sorter(BlockingQueue<Runnable> sortersQueue, int[] array, int start, int end) {
		super();
		this.sortersQueue = sortersQueue;
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		if (start >= end) {
			return;
		}
		int pivot = partition();
		
		try {
			sortersQueue.put(new Sorter(sortersQueue, array, start, pivot - 1));
			sortersQueue.put(new Sorter(sortersQueue, array, pivot + 1, end));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
