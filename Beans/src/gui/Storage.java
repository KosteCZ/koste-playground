package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gui.Bean;

public class Storage {

	private int columns = 0;
	private int rows = 0;
	private int posX = 0; // Pixel position/shift of storage from left
	private int posY = 0; // Pixel position/shift of storage from top
	private String[][] position;
	private Bean[][] beans;
	private String name;
	
	public final int FIELD_SIZE = 40; // Pixel size of one field
	
	public Storage(String name, int posX, int posY, int columns, int rows) {
		
		this(columns, rows);
		
		this.posX = posX;
		this.posY = posY;
		
		this.name = name;
		
	}
	
	public Storage(int columns, int rows) {
		
		this.columns = columns;
		this.rows = rows;
		
		position = new String[columns][rows];
		beans = new Bean[columns][rows];
		
	}
	
	public void initStorageStrategy1() {	
		//LoadObjects.loadObjects();	
		for (int x = 0; x < beans.length; x++) {
			for (int y = 0; y < beans[x].length; y++) {
				if ( y == 0 && x < 8 ) {
					if ( x < 4 ) {
						//beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), "eb");
						beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), "in_green", "out_red");
					} else {
						//beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), BeanColours.BEAN_COULOUR_ITEM_WW);
						beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), "in_white", "in_yellow");
					}
				} else if ( y <= 1 ) {
					beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), BeanColours.BEAN_COULOUR_ITEM_EMPTY);
				} else {
					beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), BeanColours.BEAN_COULOUR_ITEM_INACTIVE);
				}
			}
		}	
	}
	
	public void initFieldStrategy1() {	
		//LoadObjects.loadObjects();		
		for (int x = 0; x < beans.length; x++) {
			for (int y = 0; y < beans[x].length; y++) {
				if ( x <= 1 ) {
					beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), BeanColours.BEAN_COULOUR_ITEM_EMPTY);
				} else {
					beans[x][y] = new Bean(posX + (x * FIELD_SIZE), posY + (y * FIELD_SIZE), BeanColours.BEAN_COULOUR_ITEM_INACTIVE);
				}
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
		
		boolean isInStorage = true;
		
		if (x < 0 ) {
			System.out.println("X<0");
			isInStorage = false;
		}
		
		if (x > getWidth() ) {
			System.out.println("X>MAX");
			isInStorage = false;
		}
		
		int result = -1;
		if (isInStorage) { 
			result = Integer.valueOf( x / FIELD_SIZE );
		}
		
		return result;

	}
	
	// Returns column number for specific y position (=height)
	public int getColumnForHeight(int height) {
		int y = (height - posY);
		//System.out.println("height: " + height);
		//System.out.println("posY: " + posY);
		
		boolean isInStorage = true;
		
		if (y < 0) { 
			System.out.println("Y<0");
			isInStorage = false;
		}
		
		if (y > getHeight()) { 
			System.out.println("Y>MAX");
			isInStorage = false;
		}
		
		int result = -1;
		if (isInStorage) { 
			result = Integer.valueOf( y / FIELD_SIZE );
		}
		
		return result;

	}
	
	public MapPositionItem getMapPosition(int width, int height) {
		
		MapPositionItem storagePositionItem = null;
				
		int x = getRowForWidth(width);
		
		int y = getColumnForHeight(height);
		
		if (x >= 0 && y >= 0) {
		
			storagePositionItem = new MapPositionItem(x, y);
				
		}
		
		return storagePositionItem;
		
	}
	
	public void paintComponent(Graphics g) {
		
		for (int i = 0; i < beans.length; i++) {
			for (int j = 0; j < beans[i].length; j++) {
				if ( beans[i][j] != null && beans[i][j].getInnerImage() != null && beans[i][j].getOutterImage() != null ) {
					g.drawImage( beans[i][j].getInnerImage(), beans[i][j].getX(), beans[i][j].getY(), null );
					g.drawImage( beans[i][j].getOutterImage(), beans[i][j].getX(), beans[i][j].getY(), null );
				}
			}
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		g.drawString(name, posX, posY - 5);	
		for (int x = 0; x < beans.length; x++) {
			if ( beans[0][0] != null ) {
				g.drawLine(posX + (x * FIELD_SIZE), posY, posX + (x * FIELD_SIZE), posY + (beans[0].length * FIELD_SIZE) - 1);
				g.drawLine(posX + (((x+1) * FIELD_SIZE)-1), posY, posX + (((x+1) * FIELD_SIZE)-1), posY + (beans[0].length * FIELD_SIZE) - 1);
			}
		}
		for (int y = 0; y < beans[0].length; y++) {
			if ( beans[0][0] != null ) {
				g.drawLine(posX, posY + (y * FIELD_SIZE), posX + (beans.length * FIELD_SIZE) - 1, (((posY+1) + y * FIELD_SIZE)-1));
				g.drawLine(posX, posY + (((y+1) * FIELD_SIZE)-1), posX + (beans.length * FIELD_SIZE) - 1, posY + (((y+1) * FIELD_SIZE)-1));
			}
		}
		
	}

	public Bean selectNewSelectedBean(MapPositionItem mapPositionItem, Bean selectedBean) {
		
		System.out.println("Getting bean for: " + selectedBean.getX() + "," + selectedBean.getY() + ": " +
			beans[mapPositionItem.getX()][mapPositionItem.getY()] + ".");
		
		System.out.println("Colour: " + beans[mapPositionItem.getX()][mapPositionItem.getY()].getColourName());
		
		String innerColour = beans[mapPositionItem.getX()][mapPositionItem.getY()].getInColor();
		String outterColour = beans[mapPositionItem.getX()][mapPositionItem.getY()].getOutColor();
		
		Bean bean = new Bean(selectedBean.getX(), selectedBean.getY(), innerColour, outterColour);
		
		return bean;
		
	}
	
}
