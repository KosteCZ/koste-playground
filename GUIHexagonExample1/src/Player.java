import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player> {

	private String name;
	private Color color;
	private BufferedImage image;
	private BufferedImage imageSelected;
	private boolean alive;
	private int largestAreaHexCount;
	private int finalPosition;
	
	private List<Hex> liveHexes = new ArrayList<Hex>();
	private List<Hex> deadHexes = new ArrayList<Hex>();
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
//		this.image = Hex.bImgPlayer;
		this.image = ImageChangeColour.colorImage(Hex.bImgPlayer, color);
		this.imageSelected = ImageChangeColour.colorImage(Hex.bImgPlayerSelected, color);
		this.alive = true;
		this.largestAreaHexCount = 0;
		this.finalPosition = 0;
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

	public List<Hex> getLiveHexes() {
		return liveHexes;
	}

	public boolean liveHexesRemove( Hex hex ) {
		return liveHexes.remove( hex );
	}

	public void liveHexesRemoveAll( List<Hex> killHexes ) {
		liveHexes.removeAll( killHexes );
	}
	
	public boolean liveHexesAdd( Hex hex ) {
		return liveHexes.add( hex );
	}

	public int getLiveHexesCount() {
		return liveHexes.size();
	}
	
	public List<Hex> getDeadHexes() {
		return deadHexes;
	}

	public boolean deadHexesRemove( Hex hex ) {
		return deadHexes.remove( hex );
	}

	public boolean deadHexesAdd( Hex hex ) {
		return deadHexes.add( hex );
	}

	public int getDeadHexesCount() {
		return deadHexes.size();
	}

	public int getLargestAreaHexCount() {
		return largestAreaHexCount;
	}
	
	public void setLargestAreaHexCount( int largestAreaHexCount ) {
		this.largestAreaHexCount = largestAreaHexCount;
	}
	
	public int getFinalPosition() {
		return finalPosition;
	}

	public void setFinalPosition( int finalPosition ) {
		this.finalPosition = finalPosition;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void kill() {
		alive = false;		
	}
	
	public int getScore() {
		return countScore( this );
	}
	
	private int countScore( Player player ) {
		int score = ( player.getDeadHexesCount() * 100 ) + player.getLargestAreaHexCount();
		return score;
	}
	
	public int compareTo( Player comparePlayer ) {
		
		int compareScore = countScore( comparePlayer ); 
		
		//descending order
		return compareScore - countScore( this );
		
	}

}
