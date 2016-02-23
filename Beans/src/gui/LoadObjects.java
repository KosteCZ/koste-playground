package gui;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class LoadObjects {
	
	public static final String RESOURCES = "res/";
	public static final String IMAGES = RESOURCES + "img/";
	public static final String INNER_COLOR = IMAGES + "in/";
	public static final String OUTTER_COLOR = IMAGES + "out/";
	
	public static final String CONFIG = "cfg/";
	public static final String INPUT_COLOURS_FILE_NAME = CONFIG + "colours.txt";
	
	@Deprecated
	public static void loadObjects() {		
	}

	public static Image loadBeanImg(String beanCoulourItemName) {
	    try {
	    	File file = new File(IMAGES + beanCoulourItemName + ".png");
	    	System.out.println("File path: " + file.getAbsolutePath());
	    	return ImageIO.read(file);
	    } catch (IOException e) {
	    	System.err.println( "loadObjects() - " + e );
	    	return null;
	    }
	}
	
	public static Image loadInnerBeanColourImg(String fileName) {
	    try {
	    	File file = new File(INNER_COLOR + fileName + ".png");
	    	System.out.println("File path: " + file.getAbsolutePath());
	    	return ImageIO.read(file);
	    } catch (IOException e) {
	    	System.err.println( "loadObjects() - " + e );
	    	return null;
	    }
	}
	
	public static Image loadOutterBeanColourImg(String fileName) {
	    try {
	    	File file = new File(OUTTER_COLOR + fileName + ".png");
	    	System.out.println("File path: " + file.getAbsolutePath());
	    	return ImageIO.read(file);
	    } catch (IOException e) {
	    	System.err.println( "loadObjects() - " + e );
	    	return null;
	    }
	}
	
	public static void loadBeanColours(List<BeanColourItem> listOfColours) throws IOException {
		
		String loadedColours = "";
		
		BufferedReader br = new BufferedReader( new FileReader( INPUT_COLOURS_FILE_NAME ) );
		
		try {
			
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		        if ( !line.isEmpty() ) {
		        	
		        	if ( "".equals(line) || line.startsWith("#")) {
		        		System.out.println("XXXX: " + line);
		        	} else {
		        		System.out.println("Line: " + line);
		        		
		        		String[] words = line.split(";");
		        		
		        		System.out.println("Pocet slov radku: " + words.length);
		        		
		        		if ( words.length == 5 ) {
		        			
		        			String name = words[0].trim();
		        			String imgName = words[1].trim();
		        			int price = Integer.valueOf(words[2].trim());
		        			int sell = Integer.valueOf(words[3].trim());
		        			int dominance = Integer.valueOf(words[4].trim());
		        			
		        			BeanColourItem beanColourItem = null;
		        			
		        			if (imgName.startsWith("in")) {
		        			
		        				beanColourItem = new BeanColourItem(name, imgName, loadInnerBeanColourImg(imgName), price, sell, dominance, true, false, true);
		        			
		        			} else {
		        				
		        				beanColourItem = new BeanColourItem(name, imgName, loadOutterBeanColourImg(imgName), price, sell, dominance, false, false, true);
		        			
		        			}
		        			
		        			if ( beanColourItem != null) {
		        				
		        				listOfColours.add(beanColourItem);
		        				
		        			}
		        			
			        		sb.append( name + ", " );
			        		//sb.append( System.lineSeparator() );
		        		
		        		}

		        	}
		        	
		        }
		        
		        line = br.readLine();
		        
		    }
		    
		    loadedColours = sb.toString();
		    
		    loadedColours = loadedColours.substring(0, loadedColours.length()-2);
		    
		} finally {
			
		    br.close();
		    
		}	
		
		System.out.println("Loaded colours [" + loadedColours + "].");
		
	}

}
