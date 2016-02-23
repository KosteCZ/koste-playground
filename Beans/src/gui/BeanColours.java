package gui;

import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class BeanColours {

	List<BeanColourItem> listOfColours = new ArrayList<BeanColourItem>();
	
	public static final String BEAN_COULOUR_ITEM_INACTIVE = "inactive";
	public static final String BEAN_COULOUR_ITEM_EMPTY = "empty";
	//public static final String BEAN_COULOUR_ITEM_WW = "ww";
	
	public BeanColours() {

		listOfColours.add(new BeanColourItem(BEAN_COULOUR_ITEM_INACTIVE, LoadObjects.loadBeanImg(BEAN_COULOUR_ITEM_INACTIVE), true, true));
		listOfColours.add(new BeanColourItem(BEAN_COULOUR_ITEM_EMPTY, LoadObjects.loadBeanImg(BEAN_COULOUR_ITEM_EMPTY), true, true));
		//listOfColours.add(new BeanColourItem(BEAN_COULOUR_ITEM_WW, LoadObjects.loadBeanImg(BEAN_COULOUR_ITEM_WW), true, true));
		
		System.out.println("Number of colours loaded: " + listOfColours.size());
		
		try {
			LoadObjects.loadBeanColours(listOfColours);
		} catch (IOException e) {
			System.err.println("Error during image loading: " + e);
			e.printStackTrace();
		}
		
		System.out.println("Number of colours loaded: " + listOfColours.size());
		
	}
	
	public Image getImg(String beanCoulourItemName) {
		
		for (BeanColourItem beanColourItem : listOfColours) {
			if ( beanColourItem.getImgName().equals(beanCoulourItemName) ) {
				return beanColourItem.getImg();
			}
		}		
		
		return null;
		
	}
	
	public BeanColourItem getColourItem(String beanCoulourItemName) {
		
		for (BeanColourItem beanColourItem : listOfColours) {
			if ( beanColourItem.getImgName().equals(beanCoulourItemName) ) {
				return beanColourItem;
			}
		}		
		
		return null;
		
	}
	
}
