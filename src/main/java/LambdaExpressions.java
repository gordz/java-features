import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * Lambdas allow cleaner expression of instances of Single Abstract Method interfaces - interfaces with a single
 * method. Using anonymous classes for this purpose often yields verbose code - lambda's are a shorthand syntax.
 * 
 * Lambdas provide a way representation of code as data.
 * 
 * A functional interface is any interface that contains only one abstract method (excluding static and default methods).
 * 
 * Instead of defining your own functional interfaces, Java provides many implementations that may be of use, e.g Predicate<T>.
 * 
 * Any function that accepts a SAM as a parameter can be passed a lambda expression.
 * 
 * Lambdas can be considered as anonymous methods - methods without a name.
 * 
 * Lambdas can be used anywhere a SAM is used - they can be passed to methods, or assigned.
 * 
 * Lambdas can directly access fields, methods, and local variables of the enclosing scope. These must be effectively final.
 * 
 * Lambda expressions have a target type - the Java compiler uses the target type of the context or situation in which the lambda expression was found
 * Lambdas can be used whereever the compiler can determine a type, and that type is a SAM.
 * 
 * 
 * The forEach() method on collections expresses internal iteration.
 * 
 * You either declare the types of all the parameters for the lambda, or omit all of them
 * 
 * The runtime representation of a lambda is a SAM (typically a class with the @FunctionalInterface annotation).
 * 
 * To emphasise the intention of an interface to be used as a functional one, the new @FunctionalInterface annotation can be applied to prevent your team mates from adding new method declarations into the interface.
 * Code will not compile if a Interface flagged as a FunctionalInterface has more than 1 abstract method.
 * 
 * @author graham
 *
 */
public class LambdaExpressions {

	public void withoutLamdas() {
		EventListener listener = new EventListener() {
			public void handleEvent(Event e) {
				// some code to handle an event here.
				
			}
		};
	}
	
	/**
	List persons = asList(new Person("Joe"), new Person("Jim"), new
			Person("John"));
			persons.forEach(this::doSomething);
	**/
	
	/**
	 * List persons = asList(new Person("Joe"), new Person("Jim"),
new Person("John"));
for(Person person : persons) {
doSomething(person);
}
	 */
	
	public void supplierExample() {
		String result = InstanceCreator.create(() -> "blah blah");
	}
	
	private static class InstanceCreator {
		public static <T> T create(Supplier<T> supplier) {
			return supplier.get();
		}
	}
	
	public void biFunction() {
		final BiFunction<String, String, String> func = (x, y) -> { return x + y; };
		func.apply("hello", " world");
	}

	public void examples() {
		Runnable r = () -> System.out.println("hello world");
		Runnable r1 = () -> {
			System.out.println("hello");
			System.out.println("world");
		};
		
		// Single arg lambdas dont require parenthesis around the argument.
		Consumer<String> c = s -> System.out.println(s);
		Consumer<String> c1 = (s) -> System.out.println(s);
		Consumer<String> c2 = (String s) -> System.out.println(s);
		
		// Assigns a function that adds 2 numbers.
		BinaryOperator<Long> add = (x, y) -> x + y;
		BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
	}
	
	
	public void functionInterface() {
		Function<String,String> concat = (x) -> {
			return x + 10;
		};
		
		Function<String, Function<String,String>> test = (x) -> (y) -> {
			return x + " " + y;
		};
		
		String result = test.apply("hello").apply("world");
		System.out.println(result);
	}
	
	public void assignedLambda() {
		EventListener listener = e -> System.out.println(e.toString());
	}
	
	
	interface EventListener {
		void handleEvent(Event e);
	}
	
	static class Event {
		final String type;
		
		public Event(final String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}
	
	public void exampleComparators() {
		// Lambda.
		Comparator<Integer> cmp = (x, y) -> {
		    return (x < y) ? -1 : ((x > y) ? 1 : 0);
		};

		// Anonymous class.
		Comparator<Integer> cmp1 = new Comparator<Integer>() {
			  @Override
			  public int compare(Integer x, Integer y) {
			    return (x < y) ? -1 : ((x > y) ? 1 : 0);
			  }
		};
	}
	
	// Predicate example
	public void examplePredicates() {
		Predicate<Integer> atLeast5 = x -> x > 5;
		BinaryOperator<Long> addLongs = (x, y) -> x + y;
	}
	
	public void streamToList() {
		Stream<String> strings = Stream.of("a", "b","c", "d").filter(s -> s.equals("a"));
		List<String> result = strings.collect(Collectors.toList());		
	}
	
	public void streamOfSomething() {
		Stream<Integer> intStream = Stream.of(1, 2, 3, 4);
		IntStream ints = IntStream.of(1, 2, 3, 4);
		Stream<Integer> ints2 = Arrays.stream(new Integer[] {1, 2, 3, 4});
	}
	
	public void filter() {
		List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
				.filter(value -> Character.isDigit(value.charAt(0)))
				.collect(Collectors.toList());
	}
	
	/**
	 * flatMap() takes a function that takes each element of the stream, and returns a stream. The resulting mapped
	 * streams are then flattened into a single stream.
	 * 
	 * Here we have a stream of a List of numbers - we want to flatten the stream.
	 * 
	 */
	public void flatMap() {
		List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
				.flatMap(numbers -> numbers.stream())
				.collect(Collectors.toList());
	}
	
	// Example 3.14 from Lambdas.
	public void minUsingExternalIteration() {
		// Using external iteration.
		List<Track> tracks = asList(new Track("Bakai", 524),
				new Track("Violets for Your Furs", 378),
				new Track("Time Was", 451));
		
		Track shortestTrack = tracks.get(0);
		for (Track track : tracks) {
			if (track.getLength() < shortestTrack.getLength()) {
				shortestTrack = track;
			}
		}
	}
	
	// Reduction produces a single value from the collection by folding over the collection.
	public void minUsingReduction() {
		// Using reduction.
		List<Track> tracks = asList(new Track("Bakai", 524),
				new Track("Violets for Your Furs", 378),
				new Track("Time Was", 451));
		
		Track shortestTrack = tracks.stream().reduce((a, b) -> a.getLength() < b.getLength() ? a : b).get();
	}
	
	public void minUsingComparator() {
		// Using external iteration.
		List<Track> tracks = asList(new Track("Bakai", 524),
				new Track("Violets for Your Furs", 378),
				new Track("Time Was", 451));
		
		// Create a comparator by passing in a key extractor function.
		Track shortestTrack = tracks.stream().min(Comparator.comparing(t -> t.getLength())).get();
	}

	public void min() {
		List<Track> tracks = asList(new Track("Bakai", 524),
			new Track("Violets for Your Furs", 378),
			new Track("Time Was", 451));
				
			Track shortestTrack = tracks.stream()
				.min(Comparator.comparing(track -> track.getLength()))
				.get();
	}
	
	// Using reduction to fold over a stream. An optional is returned, incase the stream is empty.
	public void summationWithReduce() {
		List<Track> tracks = asList(new Track("Bakai", 524),
				new Track("Violets for Your Furs", 378),
				new Track("Time Was", 451));
		
		// First map to get the lengths, then reduce.
		int totalLength = tracks.stream().map(t -> t.getLength()).reduce((t1, t2) -> t1 + t2).get();
	}
	
	public void imperitiveSummationReduction() {
		int acc = 0;
		for (Integer element : asList(1, 2, 3)) {
			acc = acc + element;
		}
		//assertEquals(6, acc);
	}
	
	// If you provide an initial value, the initial value will be returned if the stream is empty.
	public void summationWithReduceAndInitialValue() {
		List<Track> tracks = asList(new Track("Bakai", 524),
				new Track("Violets for Your Furs", 378),
				new Track("Time Was", 451));
		
		// First map to get the lengths, then reduce.
		int totalLength = tracks.stream().map(t -> t.getLength()).reduce(0, (t1, t2) -> t1 + t2);
	}
	
	public void sumUsingReduction() {
		int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
	}
	
	
	static class Track {
		private final String name;
		private final int length;
		
		public Track(String name, int length) {
			this.name = name;
			this.length = length;
		}

		public String getName() {
			return name;
		}

		public int getLength() {
			return length;
		}
	}
}
		