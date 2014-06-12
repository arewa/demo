package singleton;

public final class TestSingleton {
	
	private static TestSingleton uniqueInstance;
	
	private TestSingleton() {}
	
	public static TestSingleton getInstance() {
		
		if (uniqueInstance == null) {
			uniqueInstance = new TestSingleton(); 
		}
		
		return uniqueInstance;
	}
}
