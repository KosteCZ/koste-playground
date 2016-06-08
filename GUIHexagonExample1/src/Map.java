import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Map {

    private int height;
	private int width;
	
	private Hex[][] map;
	
    BufferedImage bimg = ImageChangeColour.colorImage();
    
	public Map(int height, int width){		
		
		this.height = height;
		this.width = width;
		
		map = new Hex[width+3][height+2];
		
        for (int row = 1; row <= height; row++) {
        	for (int col = 1; col <= (width + (((row % 2) == 0) ? 1 : 0)); col++) {
        		map[col][row] = new Hex(col, row);
        	}
        }

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void paint(Graphics2D g) {
		
        for (int row = 1; row <= height; row++) {
        	for (int col = 1; col <= (width + (((row % 2) == 0) ? 1 : 0)); col++) {
        		if(map[col][row] != null) {
        			map[col][row].paint(g, bimg);
        		}
			}	
		}
		
	}
}
