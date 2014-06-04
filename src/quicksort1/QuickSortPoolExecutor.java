package quicksort1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuickSortPoolExecutor {
	
	transient int b = 3;
	
	public static void main(String[] args) {

		int[] arr = new int[10000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Random().nextInt(1000);
		}
		
		int poolSize = 40;
		long t = System.currentTimeMillis();

		BlockingQueue<Runnable> sortersQueue = new ArrayBlockingQueue<Runnable>(1024);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(4, poolSize, 20, TimeUnit.SECONDS, sortersQueue, new ThreadPoolExecutor.CallerRunsPolicy());
		
		executor.execute(new Sorter(sortersQueue, arr, 0, arr.length - 1));
		
		while (executor.getActiveCount() > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();

		t = System.currentTimeMillis() - t;

		//System.out.println(Arrays.toString(arr));
		System.out.println("Array with length " + arr.length + " sorted on "
				+ t + " ms");

	}
}
