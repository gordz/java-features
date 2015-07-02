
/**
 * Example of an Interface using generics. T is referred to as a type parameter. The Sortable interface is
 * a parameterized type.
 * 
 * @author graham
 *
 */
public class InterfaceGenerics {
	
	interface Sortable<T> {
		 
		   T get(int idx); 
		   void set(T t, int idx); 
		 
		   default void sort()  {
		      // here is an implementation of sort using get() and set()
		      // maybe I need a size() as well, but that’s not the point
		      return; 
		   }
	}
}
