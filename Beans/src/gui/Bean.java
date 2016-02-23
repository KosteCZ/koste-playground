package gui;

import java.awt.Image;

public class Bean {
	
	Image innerColourImg;
	Image outterColourImg;
	static BeanColours beanColours = new BeanColours();
	int x, y;
	String colourName;
	String inColour1;
	String inColour2;
	String outColour1;
	String outColour2;
	
	public static final String PREFIX_IN = "in_";
	public static final String PREFIX_OUT = "out_";
	
	public Bean(int x, int y, String colourName) {
		this(x, y, colourName, colourName);
		/*if (BeanColours.BEAN_COULOUR_ITEM_EMPTY.equals(colourName)) {
			this(x, y, colourName, colourName);
		} else {
			this(x, y, PREFIX_IN + colourName, PREFIX_OUT + colourName);
		}*/
	}

	public Bean(int x, int y, String inColour, String outColour) {
		this(x, y, inColour, inColour, outColour, outColour);
	}

	public Bean(int x, int y, String inColour1, String inColour2, String outColour1, String outColour2) {
		this.x = x;
		this.y = y;
		innerColourImg = beanColours.getImg(inColour1);
		outterColourImg = beanColours.getImg(outColour1);
		this.inColour1 = inColour1;
		this.inColour2 = inColour2;
		this.outColour1 = outColour1;
		this.outColour2 = outColour2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getInColor() {
		int colour1dominance = beanColours.getColourItem(inColour1).getDominance();
		int colour2dominance = beanColours.getColourItem(inColour2).getDominance();
		if (colour1dominance >= colour2dominance) {
			return inColour1;
		} else {
			return inColour2;
		}
	}
	
	public String getOutColor() {
		int colour1dominance = beanColours.getColourItem(outColour1).getDominance();
		int colour2dominance = beanColours.getColourItem(outColour2).getDominance();
		if (colour1dominance >= colour2dominance) {
			return outColour1;
		} else {
			return outColour2;
		}
	}
		
	public Image getInnerImage() {
		return innerColourImg;
	}

	public Image getOutterImage() {
		return outterColourImg;
	}

	public String getColourName() {
		return colourName;
	}
	
	@Override
	public String toString() {
		return "Bean [x=" + x + ", y=" + y + "]";
	}

}
