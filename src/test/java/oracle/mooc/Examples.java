package oracle.mooc;


import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;

public class Examples {

	
	@Test
	public void intsToHash() {
		List<Integer> ints = asList(5, 10, 20, 30);
		ints.replaceAll(i -> i.hashCode());
	}
}
