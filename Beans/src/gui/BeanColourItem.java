package gui;

import java.awt.Image;

public class BeanColourItem {
	
	private final String name;
	private final String imgName;
	private final Image img;
	private final int price;
	private final int sell;
	private final int dominance;
	private final boolean inner;
	private final boolean empty;
	private final boolean active;
	
	public BeanColourItem(String name, Image img) {
		this(name, img, true, false);
	}
	
	public BeanColourItem(String name, String imgName, Image img, int price, int sell, int dominance, boolean inner, boolean empty, boolean active) {
		this.name = name;
		this.imgName = imgName;
		this.img = img;
		this.empty = empty;
		this.active = active;
		this.price = price;
		this.sell = sell;
		this.dominance = dominance;
		this.inner = inner;
	}
	
	public BeanColourItem(String name, Image img, boolean empty, boolean active) {
		this.name = name;
		this.img = img;
		this.empty = empty;
		this.active = active;
		
		this.imgName = name;
		this.price = 1;
		this.sell = 1;
		this.dominance = 1;
		this.inner = true;
	}
	
	public String getName() {
		return name;
	}

	public String getImgName() {
		return imgName;
	}
	
	public Image getImg() {
		return img;
	}

	public int getDominance() {
		return dominance;
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public boolean isActive() {
		return active;
	}

}
