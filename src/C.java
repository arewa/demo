public class C {
	public static void main(String[] args) {
		new B();
	}
	
	static class A {
		A() {
			greeting();
			prints();
		}

		void greeting() {
			System.out.println("instance method from A");
		}

		static void prints() {
			System.out.println("Static method from A");
		}
	}

	static class B extends A {
		B() {
			greeting();
			prints();
		}

		void greeting() {
			System.out.println("instance method from B");
		}

		static void prints() {
			System.out.println("Static method from B");
		}
	}
}
