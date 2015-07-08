import static java.util.stream.Collectors.toSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;


public class LambdaBookExercises {
	
	// Chapter 1, exercise 2.
	@Test
	public void threadLocal() {
		// Thread locals normally assigned to static.
		ThreadLocal<DateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyy"));
	}
	
	// Ch3 ex 1a.
	@Test
	public void sumNumbers() {
		Stream<Integer> numbers = Stream.of(1, 5, 8);
		System.out.println(addUp(numbers));
	}
	
	private int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (a, b) -> a + b);
	}
	
	
	// Ch3 ex 1b
	private List<String> retrieveNameAndPlaceOfOrigin(List<Artist> artists) {
		List<String> result = artists.stream()
				.flatMap(a -> Stream.of(a.getName(), a.getPlaceOfOrigin()))
				.collect(Collectors.toList());
		return result;
	}
	
	// Ch3 ex 1c.
	private List<Album> filterByMaximumThreeTracks(List<Album> albums) {
		return albums.stream()
			.filter(a -> a.getTracks().size() <= 3)
			.collect(Collectors.toList());
	}
	
	
	// Ch3 ex 2.
	private void externalIterationToInternalIteration() {
	
		List<Artist> artists = null;
		
		// External iteration.
		int totalMembers = 0;
		for (Artist artist : artists) {
			Stream<Artist> members = artist.getMembers();
			totalMembers += members.count();
		}
			
		// Use flatmap to get the members (a function that takes an obejct, and returns a stream).
		int totalMembersInternal = (int)artists.stream().flatMap(a -> a.getMembers()).count();
		
		// Suggested method
		int totalMembersCorrectAnswer = artists.stream()
				.map(a -> a.getMembers().count())
				.reduce(0L, Long::sum)
				.intValue();
	}
	
	// Ch3 ex 6
	private long lowerCaseChars(String string) {
		return string.chars().filter(Character::isLowerCase).count();
	}
	
	// Ch3 ex 7 
	private Optional<String> largestNumberOfLowercaseChars(Stream<String> strings) {
		return strings.reduce((acc, next) -> {
			return lowerCaseChars(acc) > lowerCaseChars(next) ? acc : next;
		});
	}
	
	// Ch3 ex 7 - best practice (and suggested answer - using a comparator and max).
	public Optional<String> largestNumberOfLowerChars(Stream<String> strings) {
		return strings.max(Comparator.comparing(this::lowerCaseChars));
	}
	
	static class Album {
		private List<Track> trackList;

		public List<Track> getTracks() {
			return trackList;
		}
	}
	
	static class Artist {
		private final String name;
		private final String placeOfOrigin;
		private List<Artist> members;
		
		public Artist(String name, String placeOfOrigin) {
			super();
			this.name = name;
			this.placeOfOrigin = placeOfOrigin;
		}

		public String getName() {
			return name;
		}
		
		public Stream<Artist> getMembers() {
			return members.stream();
		}

		public String getPlaceOfOrigin() {
			return placeOfOrigin;
		}
		
	}
	
	
	@Test
	public void mapTest() {
		Stream<Integer> stream = Stream.of(1, 2, 4, 8);
		List<Integer> result = stream.map(a -> a * 2).collect(Collectors.toList());
		System.out.println(result);
	}
	
	@Test
	public void mapTestWithReduction() {
		Stream<Integer> stream = Stream.of(1, 2, 4, 8);
		//List<Integer> result = stream.map(a -> a * 2).collect(Collectors.toList());

		Function<Integer,Integer> mapper = a -> a * 2;
		
		List<Integer> results = mapViaReduce(stream, a -> a * 2);
	
		System.out.println("Mapped results: " + results);
	}
	
	
	private <T,R> List<R> mapViaReduce(Stream<T> stream, Function<T,R> mapper) {
		List<R> results = stream.reduce(new ArrayList<>(), 
				(a, b) -> {
					a.add(mapper.apply(b));
					return a;
				}, 
				(a, b) -> {
					a.addAll(b);
					return a;
				}
		);
		return results;
	}
	
	private <T> List<T> filterViaReduce(Stream<T> stream, Predicate<T> predicate) {
		List<T> results = stream.reduce(new ArrayList<>(), 
				(a, b) -> {
					if (predicate.test(b)) {
						a.add(b);
					}
					return a;
				}, 
				(a, b) -> {
					a.addAll(b);
					return a;
				}
		);	
		return results;
 	}
	
	/**
	 * Implementatio of filter using only reduce.
	 * @param stream
	 * @param predicate
	 * @return
	 */
	private <T> List<T> filterWithReduce(Stream<T> stream, Predicate<T> predicate) {
		List<T> result = new ArrayList<>();
		stream.reduce((acc, elem) -> {
			if (predicate.test(elem)) {
				result.add(elem);
			}
			return elem;
		});
		return result;
	}
	
	static class Track {
		private int length;
		private String name;
		
		public Track(String name, int lenght) {
			this.name = name;
			this.length = lenght;
		}

		public int getLength() {
			return length;
		}

		public String getName() {
			return name;
		}
		
		
	}
	
	// Refactor legacy code to use Lambdas.
	/*
	public Set<String> findLongTracks(List<Album> albums) {
		Set<String> trackNames = new HashSet<>();
		for(Album album : albums) {
			for (Track track : album.getTracks()) {
				if (track.getLength() > 60) {
					String name = track.getName();
					trackNames.add(name);
				}
			}
		}
		return trackNames;
	}
	*/
	
	// Intermediate step - for loops 
	public Set<String> findLongTracksForLoopsToForEach(List<Album> albums) {
		Set<String> trackNames = new HashSet<>();
		albums.stream()
		.forEach(album -> {
			album.getTracks()
			.forEach(track -> {
				if (track.getLength() > 60) {
					String name = track.getName();
					trackNames.add(name);
				}
			});
		});
		return trackNames;
	}
	
	
	// Intermedia - still adding to collection.
	/*
	public Set<String> findLongTracks(List<Album> albums) {
		Set<String> trackNames = new HashSet<>();
		albums.stream()
		.flatMap(album -> album.getTracks())
		.filter(track -> track.getLength() > 60)
		.map(track -> track.getName())
		.forEach(name -> trackNames.add(name));
		return trackNames;
	}
	*/
	
	
	public Set<String> findLongTracksLambdas(List<Album> albums) {
		return albums.stream()
			.flatMap(album -> album.getTracks().stream())
			.filter(track -> track.getLength() > 30)
			.map(track -> track.getName())
			.collect(toSet());
	}
}
