import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageChangeColour {
	
	static BufferedImage colorImage() {
		
		BufferedImage image = null;
		
		try { 
			InputStream in = new FileInputStream(new File("Hex.png"));
			
			image = ImageIO.read(in);
			
		} 
		catch (IOException e) { 
			System.err.println("Error: " + e);
		}
		return colorImage(image);
		
	}
	
	private static BufferedImage colorImage(BufferedImage image) {

       		int width = image.getWidth();
       		int height = image.getHeight();

       		int color = Color.BLACK.getRGB();

     		for (int xx = 0; xx < width; xx++) {
       			for (int yy = 0; yy < height; yy++) {
       				Color originalColor = new Color(image.getRGB(xx, yy), true);
                
/*/                	System.out.println(xx + "|" + yy + " color: " + originalColor.toString() + "alpha: "
                        + originalColor.getAlpha());*/ // Colour values info
                
       				/*if (originalColor.equals(Color.WHITE) && originalColor.getAlpha() == 255) {
                    	image.setRGB(xx, yy, Color.BLUE.getRGB());
                	}*/
                
       				if ( ( (originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue() ) < 500 )  && originalColor.getAlpha() == 255) {
                	
       	    			image.setRGB(xx, yy, color);
       	    			
       		    	}
       			}
            }
     		
        return image;
        
    }

	/*private static BufferedImage colorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                Color originalColor = new Color(image.getRGB(xx, yy), true);
                
                if (originalColor.equals(Color.WHITE) && originalColor.getAlpha() == 255) {
                    image.setRGB(xx, yy, Color.BLUE.getRGB());
                }
                
                if ( ( (originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue() ) < 500 )  && originalColor.getAlpha() == 255) {            
                	
                	image.setRGB(xx, yy, Color.BLUE.getRGB());

                }
            }
        }
        return image;
    }*/
}
