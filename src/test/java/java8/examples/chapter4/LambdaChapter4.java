package java8.examples.chapter4;
import java.util.stream.Stream;

import org.junit.Test;

import com.insightfullogic.java8.examples.chapter1.Artist;



public class LambdaChapter4 {

	interface Performance {
		public String getName();
		public Stream<Artist> getMusicians();
		
		default Stream<Artist> getAllMusiciansPerforming() {
			return Stream.empty();
		}
	}
	
	@Test
	public void ex1() {
		Artist solo1 = new Artist("Daniel Johns", "Australian");
		Artist solo2 = new Artist("Pavarotti", "Italian");
		
		Artist group1 = new Artist("Grinspoon", "Australian");
		
	}
}
