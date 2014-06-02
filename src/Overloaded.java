public class Overloaded {
  public void method(Object o) {
    System.out.println("Object");
  }
  public void method(java.io.FileNotFoundException f) {
    System.out.println("FileNotFoundException");
  }
  public void method(java.io.IOException i) {
    System.out.println("IOException");
  }
  public static void main(String args[]) {
	  Overloaded test = new Overloaded();
    test.method(null);
  }
}
