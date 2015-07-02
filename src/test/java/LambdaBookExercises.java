import static java.util.stream.Collectors.toSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	
	static class Album {
		private List<Track> trackList;

		public List<Track> getTracks() {
			return trackList;
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
