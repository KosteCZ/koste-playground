package gui;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadObjects {
	
	public static final String RESOURCES = "res/";
	public static final String IMAGES = RESOURCES + "img/";
	
	public static Image imgGrass;
	
	public static void loadObjects() {
		
	    try {
	    	imgGrass = ImageIO.read( new File( IMAGES + "grass.png") );
	    } catch (IOException e) {
	    	System.err.println( e );
	    }

		
	}

}
