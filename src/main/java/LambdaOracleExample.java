import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


/**
 * Exmaple based on https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 * @author graham
 *
 */
public class LambdaOracleExample {
	
	/**
	 * Print persons older than <code>age</code>.
	 * @param roster
	 * @param age
	 */
	static void printPersonsOlderThan(List<Person> roster, int age) {
	    for (Person p : roster) {
	        if (p.getAge() >= age) {
	            p.printPerson();
	        }
	    }
	}

	static void processPersonsPreJava8( List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
		for (Person p : roster) {
			if (tester.test(p)) {
				block.accept(p);
			}
		}
	}
	
	/*
	 * 
	 * processPersons(
     roster,
     p -> p.getGender() == Person.Sex.MALE
         && p.getAge() >= 18
         && p.getAge() <= 25,
     p -> p.printPerson()
);
	 */

	/**
	 * Using the stream API.
	 * @param persons
	 * @param action
	 * @param predicate
	 */
	static void processPersons(List<Person> persons, Consumer<Person> action, Predicate<Person> predicate) {
		persons.stream().filter(predicate).forEach(action);
	}
	


}



class Person {

    public enum Sex {
        MALE, FEMALE
    }

    int age;
    String name;
    Date birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return age;
    }

    public void printPerson() {
        System.out.println(name);
    }
}