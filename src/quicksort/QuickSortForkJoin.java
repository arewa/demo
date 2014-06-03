package quicksort;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class QuickSortForkJoin {

	public static void main(String[] args) {

		int[] arr = new int[100000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Random().nextInt(1000);
		}

		long t = System.currentTimeMillis();

		// Arrays.sort(arr, 0, arr.length);
		QuickSorter qs = new QuickSorter(arr, 0, arr.length - 1);
		ForkJoinPool pool = new ForkJoinPool(40);
		pool.invoke(qs);

		t = System.currentTimeMillis() - t;

		//System.out.println(Arrays.toString(arr));
		System.out.println("Array with length " + arr.length + " sorted on "
				+ t + " ms");

	}
}
