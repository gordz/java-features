import java.util.Optional;

import org.junit.Test;


public class OptionalExample {

	@Test
	public void usage() {
		Optional<String> string = Optional.of("hello");
	
		// Guard to check if optional value is present.
		if (string.isPresent()) {
			System.out.println("Value is: " + string.get());
		}
		
		// Eager method to provide an alternate method if optional is empty/
		System.out.println(string.orElse("value if null"));
		
		// Lazy method of providing an alternate value, e.g if providing the alternate value is computationally expensive.
		System.out.println(string.orElseGet(() -> "alt value"));
	}
	
}
