package java8.examples.chapter4;
import java.util.stream.Stream;

import com.insightfullogic.java8.examples.chapter1.Artist;



public class LambdaChapter4 {

	interface Performance {
		public String getName();
		public Stream<Artist> getMusicians();
		
		default Stream<Artist> getAllMusiciansPerforming() {
			return Stream.empty();
		}
	}
	
	public void ex1() {
		
	}
}
