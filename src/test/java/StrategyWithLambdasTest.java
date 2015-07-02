import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;


public class StrategyWithLambdasTest {

	@Test
	public void test() {
		List<Strategy> strategies = Arrays.asList(
				new LazyStrategy(),
				new NowStrategy()
		);
		
		// Enhanced for loop.
		for (Strategy strategy : strategies) {
			strategy.compute();
		}
		
		// forEach with explicit Consumer
		strategies.forEach(new Consumer<Strategy>() {
			@Override
			public void accept(Strategy t) {
				t.compute();
			}
		});
		
		// forEach with Lambda.
		strategies.forEach(elem -> elem.compute());
		
		// forEach with method reference.
		strategies.forEach(Strategy::compute);
	}
}

interface Strategy {
	void compute();
}

class LazyStrategy implements Strategy {

	@Override
	public void compute() {
		System.out.println("Doing it lazilly.");
		
	}
}

class NowStrategy implements Strategy {

	@Override
	public void compute() {
		System.out.println("Doing it now.");
	}
}