import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Hex {

    public static int HEX_WIDTH = 50;
    public static int HEX_WIDTH_HALF = HEX_WIDTH / 2;
    public static int HEX_HEIGHT = 44;

	private int x;
	private int y;
	
	public Hex(int x, int y) {		
		this.x = x;
		this.y = y;		
	}
	
	public int getX() {		
		return x;		
	}	
	
	public int getY() {		
		return y;		
	}

	public void paint(Graphics2D g, BufferedImage bimg) {
        g.drawImage(bimg, null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
	}	
	
}
