package gui;
import gui.terrain.Grass;
import gui.terrain.Terrain;

public class GameMap {

	private int columns = 0;
	private int rows = 0;
	private int posX = 0;
	private int posY = 0;
	private String[][] position;
	private Terrain[][] background;
	
	public final int FIELD_SIZE = 25; // Pixel size of one field
	
	public GameMap(int posX, int posY, int columns, int rows) {
		
		this(columns, rows);
		
		this.posX = posX;
		this.posY = posY;
		
	}
	
	public GameMap(int columns, int rows) {
		
		this.columns = columns;
		this.rows = rows;
		
		position = new String[columns][rows];
		background = new Terrain[columns][rows];
		
	}
	
	public void initMapStrategy1() {
		
		LoadObjects.loadObjects();
		
		for (int x = 0; x < background.length; x++) {
			for (int y = 0; y < background[x].length; y++) {
				Grass grass = new Grass();
				background[x][y] = grass;
			}
		}
		
	}
	
	
	// Object on selected position
	public String getPosition(int column, int row) {
		return position[column][row];
	}
	
	// Map columns count
	public int getColumns() {
		return columns;
	}
	
	// Map rows count
	public int getRows() {
		return rows;
	}	
	
	// Map width in pixels
	public int getWidth() {	
		return FIELD_SIZE * columns;	
	}
	
	// Map Height in pixels
	public int getHeight() {	
		return FIELD_SIZE * rows;	
	}
	
	// Returns row number for specific x position (=width)
	public int getRowForWidth(int width) {
		int x = (width - posX);
		//System.out.println("width: " + width);
		//System.out.println("posX: " + posX);
		
		boolean isInMap = true;
		
		if (x < 0 ) {
			System.out.println("X<0");
			isInMap = false;
		}
		
		if (x > getWidth() ) {
			System.out.println("X>MAX");
			isInMap = false;
		}
		
		int result = -1;
		if (isInMap) { 
			result = Integer.valueOf( x / FIELD_SIZE );
		}
		
		return result;

	}
	
	// Returns column number for specific y position (=height)
	public int getColumnForHeight(int height) {
		int y = (height - posY);
		//System.out.println("height: " + height);
		//System.out.println("posY: " + posY);
		
		boolean isInMap = true;
		
		if (y < 0) { 
			System.out.println("Y<0");
			isInMap = false;
		}
		
		if (y > getHeight()) { 
			System.out.println("Y>MAX");
			isInMap = false;
		}
		
		int result = -1;
		if (isInMap) { 
			result = Integer.valueOf( y / FIELD_SIZE );
		}
		
		return result;

	}
	
	public MapPositionItem getMapPosition(int width, int height) {
		
		MapPositionItem mapPositionItem = null;
				
		int x = getRowForWidth(width);
		
		int y = getColumnForHeight(height);
		
		if (x >= 0 && y >= 0) {
		
			mapPositionItem = new MapPositionItem(x, y);
				
		}
		
		return mapPositionItem;
		
	}
	
}
