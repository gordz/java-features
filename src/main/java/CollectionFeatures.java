import java.util.Arrays;
import java.util.List;


public class CollectionFeatures {

	
	/**
	 * Consumer#forEach() is a default method providing "internal iteration" over a collection.
	 */
	public void forEach() {
		List<String> strings = Arrays.asList("hello", "world", "tesT");
		strings.forEach(item -> System.out.println(item));
	}
	
}
