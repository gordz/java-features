
public class Interfaces {

	/**
	 * Interfaces can be defined within a class. Inner interfaces are static by default.
	 * 
	 * An interface represents a contract that must be adhered to by the producer and consumer.
	 * 
	 * Interfaces can contain abstract, static and default methods. Every is implicitly public in an interface.
	 * Containts can also be defined - these are public static final.
	 * 
	 * Interfaces can contain enums.
	 * 
	 * If I need to add new methods to an interface, but dont want to change consumers who currently implement that interface. my options are to use
	 * - default method
	 * - static method
	 * - create a new interface that extends the old one, containing the new operation
	 * 
	 * Interfaces can contain enums.
	 * 
	 * @author graham
	 *
	 */
	@FunctionalInterface
	public interface MyInterface {
		
		// Constants are public static final by default.
		String constant = "blah";
		
		// Methods are public abstract.
		void work();
		
		// Interfaces n 1.8 + can have static methods.
		static String hello() {
			return "hello";
		}
		
		default String world() {
			return "world";
		}
	}
	
	interface A {
		/**
		 * Default methods can be provided. These are public, and represent the default implementation of a method.
		 */
		default void doSomething() {
			System.out.println("A default method in A.");
		}
	}
	
	interface B {
		default void doSomething() {
			System.out.println("A default method in B.");
		}
		

		static void aStaticMethod() {
			
		}
	}
	
	/**
	 * An interface can extend other interfaces.
	 *
	 */
	interface C extends A, B {
		default void doSomething() {
			System.out.println("A default method in C");
		}
	}
	
	
	/**
	 * Classes can implement / derive from multiple interfaces.
	 */
	class MyClass implements A, B {
		
		/**
		 * If we implement multiple interfaces that have the same default method(s), we must provide overrides.
		 */
		public void doSomething() {
			// Invoke the implementation of doSomething() from interface A.
			A.super.doSomething();
		}
	}
	
	
	interface X {
		enum Cards {
			King,
			Queen,
			Ace
		}
	}
}