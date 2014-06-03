package quicksort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Concurrency3 {
    public static void main(String[] args) {
        final int SIZE = 1000000;
 
        List<Integer> myList = new ArrayList<Integer>(SIZE);
 
        for (int i=0; i<SIZE; i++){
            int value = (int) (Math.random() * 100);
            myList.add(value);
        }
        
        long t = System.currentTimeMillis();
        
        QuickSort<Integer> quickSort = new QuickSort<Integer>(myList);
 
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(quickSort);
        
        t = System.currentTimeMillis() - t;

		//System.out.println(Arrays.toString(arr));
		System.out.println("Array with length " + myList.size() + " sorted on "
				+ t + " ms");
        
        //System.out.println(myList.toString());
    }
}