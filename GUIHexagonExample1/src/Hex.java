import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Hex {

    public static final int HEX_HEIGHT_OVER = 15;
	public static final int HEX_WIDTH = 50;
    public static final int HEX_WIDTH_HALF = HEX_WIDTH / 2;
    public static final int HEX_HEIGHT = 44;

    public static BufferedImage bImgPlayer = ImageChangeColour.getImage(HexType.PLAYER.name().toLowerCase());
    public static BufferedImage bImgPlayerSelected = ImageChangeColour.getImage(HexType.PLAYER.name().toLowerCase() + "_selected");
    public static BufferedImage bImgEmpty = ImageChangeColour.getImage(HexType.EMPTY.name().toLowerCase());
    public static BufferedImage bImgGrass = ImageChangeColour.getImage(HexType.GRASS.name().toLowerCase());
    public static BufferedImage bImgGrassPath = ImageChangeColour.getImage(HexType.GRASS.name().toLowerCase() + "_path");
    public static BufferedImage bImgGrassTarget = ImageChangeColour.getImage(HexType.GRASS.name().toLowerCase() + "_target");
    
	private int x;
	private int y;
	private HexType hexType;
	private int sheepCount = 0;
	private Player owner = null;
	private boolean selected = false;
	private boolean target = false;
	private boolean path = false;
	
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
	
	// Attributes for user GUI target selection
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isTarget() {
		return target;
	}

	public void setTarget(boolean target) {
		this.target = target;
	}

	public boolean isPath() {
		return path;
	}

	public void setPath(boolean path) {
		this.path = path;
	}
	
	public void clearSelectionAttributes() {
		this.selected = false;
		this.target = false;
		this.path = false;
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
			if (isPath()) {
				g.drawImage(bImgGrassPath, null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
			} else if (isTarget()) {
				g.drawImage(bImgGrassTarget, null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
			} else {
				g.drawImage(bImgGrass, null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
			}
		} else if (HexType.PLAYER.equals(hexType)) {
			if (isSelected()) {
				g.drawImage(owner.getImageSelected(), null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
				g.drawString("" + sheepCount, 16 + x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), 37 + y * HEX_HEIGHT);
			} else {
				g.drawImage(owner.getImage(), null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
				g.drawString("" + sheepCount, 16 + x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), 37 + y * HEX_HEIGHT);
			}
		} else {
			g.drawImage(bImgEmpty, null, x * HEX_WIDTH + ((((y + 1) % 2) == 1) ? 0 : HEX_WIDTH_HALF), y * HEX_HEIGHT);
		}
	}
	
}
