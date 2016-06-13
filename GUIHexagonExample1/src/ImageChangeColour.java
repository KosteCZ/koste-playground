import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageChangeColour {
	
	static BufferedImage getImage() {
		return getImage("Hex");	
	}
	
	static BufferedImage getImage(String name) {
		
		BufferedImage image = null;
		
		try { 
			InputStream in = new FileInputStream(new File(name + ".png"));
			
			image = ImageIO.read(in);
			
		} catch (IOException e) { 
			System.err.println("Error: " + e);
		}
		
		return image;
		
	}
	
	/** Creates new copy of original image. (Not only pointer to original.)
	 * @param image Original image.
	 * @return New copy of original image.
	 * */
	static BufferedImage imageDeepCopy(BufferedImage bImg) {
		 ColorModel cm = bImg.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bImg.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	/** Creates new copy of original image partially colored with specified color. 
	 * @param image Original image.
	 * @param color New color for the image parts.
	 * @return New copy of original image partially colored with specified color.
	 * */
	public static BufferedImage colorImage(BufferedImage image, Color color) {

		BufferedImage imageNew = imageDeepCopy(image);
		
   		int width = imageNew.getWidth();
   		int height = imageNew.getHeight();

   		int colorInt = color.getRGB();

 		for (int xx = 0; xx < width; xx++) {
   			for (int yy = 0; yy < height; yy++) {
   				Color originalColor = new Color(imageNew.getRGB(xx, yy), true);
            
/*/                	System.out.println(xx + "|" + yy + " color: " + originalColor.toString() + "alpha: "
                    + originalColor.getAlpha());*/ // Colour values info
            
   				/*if (originalColor.equals(Color.WHITE) && originalColor.getAlpha() == 255) {
                	image.setRGB(xx, yy, Color.BLUE.getRGB());
            	}*/
            
   				//if ( ( (originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue() ) < 500 )  && originalColor.getAlpha() == 255) {
   				if ( ( (originalColor.getRed() > originalColor.getGreen()) && (originalColor.getRed() > originalColor.getBlue() ))  && originalColor.getAlpha() == 255) {
   	                	
   	    			imageNew.setRGB(xx, yy, colorInt);
   	    			
   		    	}
   			}
        }
     		
        return imageNew;
        
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
