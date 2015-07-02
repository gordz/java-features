import java.util.Arrays;



public class MethodReferences {

	/**
	 * 
	 * Method references are essentially lambda expressions - created by referencing an existing method.
	 * 
	 * Method references can be to 
	 * - static method (Class::method)
	 * - instance method (instanceName::method)
	 * - constructor (Class:new)
	 * - method on an arbitrary instance (Class::method)
	 *
	 *
	 *
	 * Method references can be used anywhere a lambda expression would be used.
	 *
	 */
	public void staticMethodReference() {
		ComparatorFunctions cf = new ComparatorFunctions();
		Person p1 = new Person(20);
		Person p2 = new Person(50);
		Person p3 = new Person(30);
		Person p4 = new Person(5);
		
		Person[] persons = { p1, p2, p3, p4 };
		Arrays.sort(persons, ComparatorFunctions::byAge);
	}
	
	public static class ComparatorFunctions {
		static int byAge(Person left, Person right) {
			if (left.getAge() < right.getAge()) {
				return -1;
			} else if (left.getAge() > right.getAge()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	public void methodReferenceOnThis() {

	}
	
	public String helloWorld() {
		return "hello world";
	}
	
	
	public static class Person implements Comparable<Person> {
		private final int age;
		
		Person(int age) {
			this.age = age;
		}

		public int getAge() {
			return age;
		}

		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
