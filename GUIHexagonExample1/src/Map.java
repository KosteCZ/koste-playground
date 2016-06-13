import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private int height;
	private int width;
	
	private Hex[][] map;
    
	public Map(int height, int width){		
		
		this.height = height;
		this.width = width;
		
		map = new Hex[width+3][height+2];
		
        for (int row = 1; row <= width; row++) {
        	for (int col = 1; col <= (height + (((row % 2) == 0) ? 1 : 0)); col++) {
        		map[col][row] = new Hex(col, row, HexType.EMPTY);
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
		
        for (int row = 1; row <= width; row++) {
        	for (int col = 1; col <= (height + (((row % 2) == 0) ? 1 : 0)); col++) {
        		if(map[col][row] != null) {
        			map[col][row].paint(g);
        		}
			}	
		}
		
	}

	public void generateMap1() {
        for (int row = 5; row <= 9; row++) {
        	for (int col = 4; col <= (9 + (((row % 2) == 0) ? 1 : 0)); col++) {
        		 map[col][row].setHexType(HexType.GRASS);
        	}
        }
	}
	
	public void clearAllSelectionAttributes() {
        for (int row = 1; row <= width; row++) {
        	for (int col = 1; col <= (height + (((row % 2) == 0) ? 1 : 0)); col++) {
        		if(map[col][row] != null) {
        			map[col][row].clearSelectionAttributes();
        		}
			}	
		}		
	}
	
	
	public boolean conquerHex(int col, int row, Player player, int sheepCount) {
		return map[col][row].conquer(player, sheepCount);
	}
	
	public List<Hex> getReachableHexes(Hex hex) {
		
		clearAllSelectionAttributes();
		
		hex.setSelected(true);
		
		List<Hex> hexesPath = new ArrayList<Hex>();
		List<Hex> hexesTarget = new ArrayList<Hex>();
		
		if ( hex == null || hex.getX() < 1 || hex.getX() > width || hex.getY() < 1 || hex.getY() > height ) {
			return hexesTarget;
		}
		
		// LEFT (-1,0)
		int x = hex.getX();
		int y = hex.getY();
//		System.out.println("Start: [" + x + "," + y + "]");
		
		do {
			x--;
			if (map[x][y] != null) { hexesPath.add(map[x][y]); }
		} while ( map[x][y] != null && HexType.GRASS.equals(map[x][y].getHexType()) );
		if (map[x][y] != null) { hexesPath.remove(map[x][y]); }
		x++;
		if (x != hex.getX() || y != hex.getY()) {
			hexesTarget.add(map[x][y]);
			System.out.println("LEFT: [" + x + "," + y + "]");
		}
		
		// RIGHT (+1,0)
		x = hex.getX();
		y = hex.getY();
		
		do {
			x++;
			if (map[x][y] != null) { hexesPath.add(map[x][y]); }
		} while ( map[x][y] != null && HexType.GRASS.equals(map[x][y].getHexType()) );
		if (map[x][y] != null) { hexesPath.remove(map[x][y]); }
		x--;
		if (x != hex.getX() || y != hex.getY()) {
			hexesTarget.add(map[x][y]);
			System.out.println("RIGHT: [" + x + "," + y + "]");
		}
		
		// TOP LEFT (-1,-1) || (0,-1)
		x = hex.getX();
		y = hex.getY();
		
		do {
			y--;
			if ((y % 2) == 1) { x--; }
			if (map[x][y] != null) { hexesPath.add(map[x][y]); }
		} while ( map[x][y] != null && HexType.GRASS.equals(map[x][y].getHexType()) );
		if (map[x][y] != null) { hexesPath.remove(map[x][y]); }
		if ((y % 2) == 1) { x++; }
		y++;
		if (x != hex.getX() || y != hex.getY()) {
			hexesTarget.add(map[x][y]);
			System.out.println("TOP LEFT: [" + x + "," + y + "]");
		}
		
		// TOP RIGHT (0,-1) || (+1,-1)
		x = hex.getX();
		y = hex.getY();
		
		do {
			y--;
			if ((y % 2) == 0) { x++; }
			if (map[x][y] != null) { hexesPath.add(map[x][y]); }
		} while ( map[x][y] != null && HexType.GRASS.equals(map[x][y].getHexType()) );
		if (map[x][y] != null) { hexesPath.remove(map[x][y]); }
		if ((y % 2) == 0) { x--; }
		y++;
		if (x != hex.getX() || y != hex.getY()) {
			hexesTarget.add(map[x][y]);
			System.out.println("TOP RIGHT: [" + x + "," + y + "]");
		}
		
		// BOTTOM LEFT (-1,+1) || (0,+1)
		x = hex.getX();
		y = hex.getY();
		
		do {
			y++;
			if ((y % 2) == 1) { x--; }
			if (map[x][y] != null) { hexesPath.add(map[x][y]); }
		} while ( map[x][y] != null && HexType.GRASS.equals(map[x][y].getHexType()) );
		if (map[x][y] != null) { hexesPath.remove(map[x][y]); }
		if ((y % 2) == 1) { x++; }
		y--;
		if (x != hex.getX() || y != hex.getY()) {
			hexesTarget.add(map[x][y]);
			System.out.println("BOT LEFT: [" + x + "," + y + "]");
		}
		
		// BOTTOM RIGHT (0,+1) || (+1,+1)
		x = hex.getX();
		y = hex.getY();
		
		do {
			y++;
			if ((y % 2) == 0) { x++; }
			if (map[x][y] != null) { hexesPath.add(map[x][y]); }
		} while ( map[x][y] != null && HexType.GRASS.equals(map[x][y].getHexType()) );
		if (map[x][y] != null) { hexesPath.remove(map[x][y]); }
		if ((y % 2) == 0) { x--; }
		y--;
		if (x != hex.getX() || y != hex.getY()) {
			hexesTarget.add(map[x][y]);
			System.out.println("BOT RIGHT: [" + x + "," + y + "]");
		}
		
		System.out.println("Count: " + hexesTarget.size());
		
		hexesPath.removeAll(hexesTarget);
		
		for (Hex hexTarget : hexesTarget) {
			hexTarget.setTarget(true);
		}
		for (Hex hexPath : hexesPath) {
			hexPath.setPath(true);
		}
		
		
		int countPath = 0;
		int countTarget = 0;
		int countSelected = 0;
		
        for (int row = 1; row <= width; row++) {
        	for (int col = 1; col <= (height + (((row % 2) == 0) ? 1 : 0)); col++) {
        		if(map[col][row] != null) {
        			if(map[col][row].isPath()) {
        				countPath++;
        			}
        			if(map[col][row].isTarget()) {
            			countTarget++;
        			}
        			if(map[col][row].isSelected()) {
                		countSelected++;
        			}
        		}
			}	
		}		
        System.err.println("- path:     " + countPath);
        System.err.println("- target:   " + countTarget);
        System.err.println("- selected: " + countSelected);
		
        
		return hexesTarget;
		
	}
	
	// TODO return map position of hex or hex itself // TEST IT !!!
	// Solution from:  http://stackoverflow.com/questions/7705228/hexagonal-grids-how-do-you-find-which-hexagon-a-point-is-in
	public Hex getHexPosition(int x, int y) {

		System.out.println("x: " + x + ", y: " + y);
		
	    // Find the row and column of the box that the point falls in.
	    int row = (int) (y / Hex.HEX_HEIGHT);
	    int column;

	    boolean rowIsOdd = row % 2 == 1;

	    // Is the row an odd number?
	    if (rowIsOdd) { // Yes: Offset x to match the indent of the row
	        column = (int) ((x - Hex.HEX_WIDTH_HALF) / Hex.HEX_WIDTH);
	    } else { // No: Calculate normally
	        column = (int) (x / Hex.HEX_WIDTH);
	    }
	    
		System.out.println("column: " + column + ", row: " + row);
		
		// Work out the position of the point relative to the box it is in
	    double relY = y - (row * Hex.HEX_HEIGHT);
	    double relX;

	    if (rowIsOdd) {
	        relX = (x - (column * Hex.HEX_WIDTH)) - Hex.HEX_WIDTH_HALF;
	    } else {
	        relX = x - (column * Hex.HEX_WIDTH);
	    }
		
		System.out.println("relX: " + relX + ", relY: " + relY);
		
		// Work out if the point is above either of the hexagon's top edges
		//int c = 0;
		int c = Hex.HEX_HEIGHT_OVER; //15; // height of top /\ part (or bottom part \/) of hex ( =  /|\  )
		//double m = 0.5;
		double m = c / Hex.HEX_WIDTH_HALF;
		
	    if (relY < (-m * relX) + c) { // LEFT edge
	        row--;
	        if (!rowIsOdd) {
	        	column--;
	        }
	    }
	    else if (relY < (m * relX) - c) { // RIGHT edge
	    	row--;
	        if (rowIsOdd) {
	        column++;
	        }
	    }
		System.out.println("column: " + column + ", row: " + row);

		if ( column < 1 || column > width || row < 1 || row > height ) {
			return null;
		}
	    
		return map[column][row];
		
	}
	
}
