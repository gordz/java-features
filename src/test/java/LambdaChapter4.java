import java.util.stream.Stream;



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

	static class Artist {
		
	}
}
