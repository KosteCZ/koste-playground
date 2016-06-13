import java.awt.Color;
import java.awt.image.BufferedImage;

public class Player {

	private String name;
	private Color color;
	private BufferedImage image;
	private BufferedImage imageSelected;
	
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

}
