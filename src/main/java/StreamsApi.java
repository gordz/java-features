import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


/**
 * 
 * Streams can only be traversed once.
 * 
 * Streams are lazy.
 * 
 * During stream operations, the source collection is not mutated.
 * 
 * Streams can be obtained from collections (by calling .stream()), or from generator functions (e.g IntStream.range(), Stream.iterate() ).
 * 
 * Streams encapsulate the idea of transformations / intermediate functions - transformations applied to the stream
 * to yield a new stream - effectively pipelines of operations on the stream.
 * 
 * Usually, dealing with a stream will involve these steps:

    Obtain a stream from some source.
    Perform one or more intermediate operations, like filter, map, etc.
    Perform one terminal operation (e.g reduce, count, sum, average, forEach, any, first, collect ).

 * 
 * @author graham
 *
 */
public class StreamsApi {
	
	public void filter_AnonymousClass() {
		List<String> results = Arrays.asList("bob", "hello", "testing");
		results.stream().filter(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				return t.length() > 4;
			}
		});
	}
	
	public void filter_WithLambdaExpression() {
		List<String> results = Arrays.asList("bob", "hello", "testing");
		results.stream().filter(s -> s.length() > 4);
		results.stream().filter((String s) -> s.length() > 4);
		
		// Explicit return requires the use of brances in the Lambda expression.
		results.stream().filter(s -> { return s.length() > 4; } );
		
	}
	
	/**
	 * Various ways to create a stream of random integers, using a generator function.
	 */
	public void streamFromRandomInts() {
		Random random = new Random();
		Stream<Integer> stream1 = Stream.generate(() -> random.nextInt());
		
		Stream<Integer> stream2 = Stream.generate(random::nextInt);
		
		IntStream stream3 = random.ints();
	}
	
	/**
	 * Create a stream from a "range" generator function and get its average.
	 */
	public void streamRangeOfNumbers() {
		LongStream stream = LongStream.range(10, 100).filter(x -> x > 50);
		OptionalDouble average = stream.average();
	}
}
