import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private Color color;
	private BufferedImage image;
	private BufferedImage imageSelected;
	
	private List<Hex> liveHexes = new ArrayList<Hex>();
	private List<Hex> deadHexes = new ArrayList<Hex>();
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
//		this.image = Hex.bImgPlayer;
		this.image = ImageChangeColour.colorImage(Hex.bImgPlayer, color);
		this.imageSelected = ImageChangeColour.colorImage(Hex.bImgPlayerSelected, color);
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public BufferedImage getImage() {
		return image;
	}

	public BufferedImage getImageSelected() {
		return imageSelected;
	}

	/*public List<Hex> getLiveHexes() {
		return liveHexes;
	}*/

	public boolean liveHexesRemove(Hex hex) {
		return liveHexes.remove(hex);
	}

	public boolean liveHexesAdd(Hex hex) {
		return liveHexes.add(hex);
	}

	public int liveHexesCount() {
		return liveHexes.size();
	}
	/*public List<Hex> getDeadHexes() {
		return deadHexes;
	}*/

	public boolean deadHexesRemove(Hex hex) {
		return deadHexes.remove(hex);
	}

	public boolean deadHexesAdd(Hex hex) {
		return deadHexes.add(hex);
	}

	public int deadHexesCount() {
		return deadHexes.size();
	}
	
}
