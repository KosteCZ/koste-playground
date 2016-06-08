import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

public class TestReflection {

	@Test
	public void test() {
	
		PrivateObject privateObject = new PrivateObject("The Private Value");
		
		System.out.println("TEST 1");
		try {
			
			System.out.println("Methods fo class \"" + PrivateObject.class.getName() + "\":");
			
			//Method[] methods = PrivateObject.class.getMethods(); //ALL
			Method[] methods = PrivateObject.class.getDeclaredMethods();

			for(Method method : methods){
				System.out.println("method: " + method.getName() + "() - (parameters: " + Arrays.toString(method.getParameters()) + ", return type: " + method.getReturnType().getName() + ")");
			}
		
		} catch( SecurityException e ) {
			System.err.println(e);
		}
		
		
		System.out.println();
		System.out.println("TEST 2");
		try {
			
			Class<?> c = Class.forName("PrivateObject");  
	    	
			//Object o= c.newInstance();  
			
			Constructor<?>[] cc = c.getConstructors();
			
			Object o=cc[0].newInstance("Ahoj!");
			
	    	Method m =c.getDeclaredMethod("getPrivateString");  
	    	m.setAccessible(true);  
	    	String text = (String) m.invoke(o);
	    	System.out.println("Result: " + text);
		
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		} catch (ReflectiveOperationException e) {
			System.err.println(e);
			//e.printStackTrace();
		}
		

		System.out.println();
		System.out.println("TEST 3");
		try {
			
			Method privateStringMethod = PrivateObject.class.getDeclaredMethod("getPrivateString");

			privateStringMethod.setAccessible(true);

			String returnValue = (String) privateStringMethod.invoke(privateObject);

			System.out.println("ReturnValue: " + returnValue);
		
		} catch (ReflectiveOperationException e) {
			System.err.println(e);
		}
		
	}
	
}
