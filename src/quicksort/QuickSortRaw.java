package quicksort;
import java.util.Arrays;
import java.util.Random;

public class QuickSortRaw {

	public static void main(String[] args) {
		
		int[] arr = new int[100000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Random().nextInt(1000);
		}
	    
	    long t = System.currentTimeMillis();
		
	    Arrays.sort(arr, 0, arr.length);
	    //quicksort(arr, 0, arr.length - 1);
		
	    t = System.currentTimeMillis() - t;

		System.out.println("Array with length " + arr.length + " sorted on " + t + " ms");

	}

	static int partition(int[] array, int start, int end) {
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

	static void quicksort(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int pivot = partition(array, start, end);
		quicksort(array, start, pivot - 1);
		quicksort(array, pivot + 1, end);
	}
}
