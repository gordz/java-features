
public class DefaultMethodInheritance {
	
	interface Parent {
		public void message(String body);
		
		public default void welcome() {
			message("Parent: Hi!");
		}
		
		public String getLastMessage();
	}
	
	/**
	 * Overrides the default method.
	 */
	public interface Child extends Parent {
		
		@Override
		public default void welcome() {
			message("Child: Hi!");
		}
	}
	
	static class ChildImpl implements Child {

		@Override
		public void message(String body) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getLastMessage() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	/**
	 * Overrides the default method defined in the ParentImpl interface. Concrete methods from classes
	 * are chosen over default methods.
	 * @author graham
	 *
	 */
	static class OverridingParent extends ParentImpl {
		@Override
		public void welcome() {
			message("Class Parent: Hi!");
		}
	}
	
	/*
	 * Inherits the default method "welcome".
	 * 
	 */
	static class ParentImpl implements Parent {

		@Override
		public void message(String body) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getLastMessage() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
