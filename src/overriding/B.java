package overriding;

public class B extends A {
	
	public int methodA(int x) {
		return 0;
	}
	
	public String methodA() {
		return "";
	}
	
	public int methodA(int x, int y) {
		return 0;
	}

}
