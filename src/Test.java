
class MyThread1 extends Thread 
{ 
	MyThread1() {} 
	MyThread1(Runnable r) {super(r); } 
    public void run() 
    { 
        System.out.print("Inside Thread ");
    } 
} 
class MyRunnable implements Runnable 
{ 
    public void run() 
    { 
        System.out.print(" Inside Runnable"); 
    } 
} 
class Test 
{  
    public static void main(String[] args) 
    { 
        new MyThread1().start(); 
        new MyThread1(new MyRunnable()).start(); 
    } 
}