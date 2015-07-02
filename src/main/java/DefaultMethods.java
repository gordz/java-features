
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
 * - redeclare it as a regular method on the interface
 * - do nothing (which will inherit the default method from the interface being extended)
 * 
 * 
 * Reasons to do this:
 * 	TODO
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
	default void doSomething() {
		System.out.println("do something overidden");
	}
}

class HelloWorldPrinter implements Hello, World { }
