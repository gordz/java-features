//import java.util.Arrays;
//import java.util.List;
//
//
//public class StrategyWithLambdas {
//
//	public void test() {
//		List<Strategy> strategies = Arrays.asList(
//				new LazyStrategy(),
//				new NowStrategy()
//		);
//		
//		// Enhanced for loop.
//		for (Strategy strategy : strategies) {
//			strategy.compute();
//		}
//		
//		// forEach with Lambda.
//		strategies.forEach(elem -> elem.compute());
//		
//		// forEach with method reference.
//		strategies.forEach(Strategy::compute);
//	}
//}
//
//interface Strategy {
//	void compute();
//}
//
//class LazyStrategy implements Strategy {
//
//	@Override
//	public void compute() {
//		System.out.println("Doing it lazilly.");
//		
//	}
//}
//
//class NowStrategy implements Strategy {
//
//	@Override
//	public void compute() {
//		System.out.println("Doing it now.");
//	}
//}