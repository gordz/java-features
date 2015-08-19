import org.junit.Test;

public class TestingOverriding {

	
	@Test
	public void doWork_ShouldThrowException_WhenThingHappens() {
			
	}
	
	@Test
	public void doStuff_ShouldReturnPositiveInteger_WhenXIsNegative() {
		
		
	}
	
	static class Parent {
		protected void doWork() {
			System.out.println("hello");
		}
	}
	
	static class Child extends Parent {
		public void doWork() {
			System.out.println("world");
		}
	}
}
