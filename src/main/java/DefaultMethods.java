
/**
 * 
 * Default methods enable you to add new functionality to the interfaces of 
 * your libraries and ensure binary compatibility with code written for older versions of those interfaces.
 * 
 * The default keyword allows you to add methods to interfaces, and can enable a form of multiple inheritance.
 * 
 * Allow an interface in addition to declaring methods actually supply their implementations.
 * 
 * They allow us to add methods to an interface, without breaking classes that implement that interface-
 *  to enable interfaces to evolve without introducing incompatibility with existing implementations.
 * Similar to Traits, which allow you to add functionality to a type.
 * 
 * Default methods also know as defender methods.
 * 
 * If you extend an interface with a default method, you can either
 * - redefine it
 * - redeclare it as a regular method on the interface, to be implemented by child classes/child implementations/
 * - do nothing (which will inherit the default method from the interface being extended)
 * 
 * If the default method is overidden in a class, then that version will be used.
 * 
 * Class (implementation) overrides are chosen over default methods.
 * 
 * If you implement (or extend) multiple interfaces that have the same default method, you must either override the method, 
 * or choose to invoke one of the parent default methods using the super keyword.
 * 
 * https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
 * 
 * @author graham
 *
 */
public class DefaultMethods {
	
	public static void main(String[] args) {
		HelloWorldPrinter printer = new HelloWorldPrinter();
		System.out.println(printer.hello() + " " + printer.world());
	}
}

interface Hello {
	
	default String hello() {
		return "hello";
	}
}

interface World {
	default String world() {
		return "world";
	}
}

interface WithDefault {
	default void doSomething() { 
		System.out.println("do something");
	}
}

interface RedeclaredDefault extends WithDefault {
	void doSomething();
}

interface OverrideDefault extends WithDefault {
	@Override
	default void doSomething() {
		System.out.println("do something overidden");
	}
}

// Default method collission - 2 interfaces with the same default method will cause a compile error - we need to specify
// which version to call using the super keyword, override the method, or declare as a regular method to be implemnted by 
// any classes that implement the interface.
interface Child {
	default void talk() {
		System.out.println("Child talk()");
	}
}

interface Parent {
	default void talk() {
		System.out.println("Parent talk");
	}
}

// Refering to a specific implementation of the default method usign the Super keyword.
interface Sibilng extends Child, Parent {
	default void talk() {
		Child.super.talk();
	}
}

class HelloWorldPrinter implements Hello, World { }
