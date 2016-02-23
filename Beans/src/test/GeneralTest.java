package test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import gui.BeanColourItem;
import gui.LoadObjects;

public class GeneralTest {
	
	@Test
	public void test() throws IOException {
		
		LoadObjects.loadBeanColours(new ArrayList<BeanColourItem>());
		
	}

}
