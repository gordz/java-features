import java.util.Comparator;


/**
 * Java 8 improves upon comparators.
 * @author graham
 *
 */
public class Comparators {

	// Convenience methods to create a comparator. We provide a getter function for the value to compare on.
	// comparing is a function that takes a function and returns a new function.
	public void createComparatorWithKeyExtractor() {
		Comparator<String> comparatorLambda = Comparator.comparing(s -> s.length());
		Comparator<String> comparatorMethodReference = Comparator.comparing(String::length);
		
	}
}
