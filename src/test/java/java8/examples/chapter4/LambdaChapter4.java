package java8.examples.chapter4;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

import com.insightfullogic.java8.examples.chapter1.Artist;

/**
 * Exercises for Java Lambas Chapter 4.
 * @author graham
 *
 */
public class LambdaChapter4 {

	interface Performance {
		public String getName();
		public Stream<Artist> getMusicians();
		
		/**
		 * Get all the artists performing, including individual group members and the groups those artists
		 * are part of.
		 * @return Performing musicians.
		 */
		default Stream<Artist> getAllMusiciansPerforming() {
			return getMusicians().flatMap(artist -> Stream.concat(Stream.of(artist), artist.getMembers()));
		}
	}
	
	static class PerformanceImpl implements Performance {

		private List<Artist> artists = new ArrayList<>();
		
		public PerformanceImpl(List<Artist> artists) {
			this.artists.addAll(artists);
		}
			
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Stream<Artist> getMusicians() {
			return artists.stream();
		}
	}
	
	@Test
	public void ex1() {
		Artist solo1 = new Artist("Daniel Johns", "Australian");
		Artist solo2 = new Artist("Pavarotti", "Italian");
		Artist groupArtist1 = new Artist("Pat Davern", "Australian");
		Artist groupArtist2 = new Artist("Phil Jamieson", "Australian");
		Artist group = new Artist("Grinspoon", asList(groupArtist1, groupArtist2), "Australian");
		Performance performance = new PerformanceImpl(asList(solo1, solo2, group));
		
		List<Artist> allMusiciansPerforming = performance.getAllMusiciansPerforming().collect(toList());
		
		assertThat(allMusiciansPerforming, containsInAnyOrder(solo1, solo2, group, groupArtist1, groupArtist2));
	}
	
	@Test
	public void ex3() {
		class Artists {
			private List<Artist> artists;
			
			public Artists(List<Artist> artists) {
				this.artists = artists;
			}
			
			public Artist getArtist(int index) {
				if (index < 0 || index >= artists.size()) {
					indexException(index);
				}
				return artists.get(index);
			}
			
			private void indexException(int index) {
				throw new IllegalArgumentException(index +
						" doesn't correspond to an Artist");
			}

			public String getArtistName(int index) {
				try {
					Artist artist = getArtist(index);
					return artist.getName();
				} catch (IllegalArgumentException e) {
					return "unknown";
				}
			}
		}
		
		class ArtistsRefactoredOptional {
			private List<Artist> artists;
			
			public ArtistsRefactoredOptional(List<Artist> artists) {
				this.artists = artists;
			}
			
			public Optional<Artist> getArtist(int index) {
				if (index < 0 || index >= artists.size()) {
					indexException(index);
				}
				return artists.get(index);
			}

			public String getArtistName(int index) {
				try {
					Artist artist = getArtist(index);
					return artist.getName();
				} catch (IllegalArgumentException e) {
					return "unknown";
				}
			}
		}
		
		ArtistsRefactoredOptional artistsEmpty = new ArtistsRefactoredOptional(Collections.emptyList());
		assertThat(artistsEmpty.getArtist(-1).isPresent(), equalTo(false));
		assertThat(artistsEmpty.getArtists(0).isPresent(), equalTo(false));
		
	}
}
