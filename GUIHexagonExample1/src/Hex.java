import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Hex {

    public static final int HEX_HEIGHT_OVER = 15;
	public static final int HEX_WIDTH = 50;
    public static final int HEX_WIDTH_HALF = HEX_WIDTH / 2;
    public static final int HEX_HEIGHT = 44;

    public static BufferedImage bImgEmpty = ImageChangeColour.getImage(HexType.EMPTY.name().toLowerCase());
    public static BufferedImage bImgGrass = ImageChangeColour.getImage(HexType.GRASS.name().toLowerCase());
    public static BufferedImage bImgPlayer = ImageChangeColour.getImage(HexType.PLAYER.name().toLowerCase());
    
	private int x;
	private int y;
	private HexType hexType;
	private int sheepCount = 0;
	private Player owner = null;
	
	public Hex(int x, int y, HexType hexType) {		
		this.x = x;
		this.y = y;
		this.hexType = hexType;
	}
	
	public int getX() {		
		return x;		
	}	
	
	public int getY() {		
		return y;		
	}

	public HexType getHexType() {		
		return hexType;	
	}

	public void setHexType(HexType hexType) {		
		this.hexType = hexType;	
	}
	
	public boolean conquer(Player player, int sheepCount) {
		
		boolean result = false;
		
		if (HexType.PLAYER != hexType && owner == null) {			
			hexType = HexType.PLAYER;		
			owner = player;			
			this.sheepCount = sheepCount;			
			result = true;			
		}
		
		return result;
		
	}

	public void paint(Graphics2D g) {
		if (HexType.GRASS.equals(hexType)) {
			g.drawImage(bImgGrass, null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
		} else if (HexType.PLAYER.equals(hexType)) {
			g.drawImage(ImageChangeColour.colorImage(bImgPlayer, owner.getColor()), null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
			g.drawString("" + sheepCount, 16 + x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), 37 + y * HEX_HEIGHT);
		} else {
			g.drawImage(bImgEmpty, null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
		}
	}
	
}
