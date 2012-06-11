/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package village;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Honza
 */
public abstract class Layer {

    public final int SIZE;
    private int height = 0;
    private int width = 0;
    protected MapElement[][] position;
    protected ArrayList<MapElement> listOfElements;

    public Layer(int size, int width, int height) {
        this.SIZE = size;
        this.height = height;
        this.width = width;
        listOfElements = new ArrayList<MapElement>();
        position = new MapElement[width][height];
    }

    public MapElement[][] getPosition() {
        return position;
    }

    public boolean safeAddElement(MapElement element) {
        if (position[element.getX()][element.getY()] != null) {
            listOfElements.add(element);
            position[element.getX()][element.getY()] = element;
            return true;
        }
        return false;
    }

    public void addElement(MapElement building) {
        if (position[building.getX()][building.getY()] != null) {
            listOfElements.remove(position[building.getX()][building.getY()]);
        }
        listOfElements.add(building);
        position[building.getX()][building.getY()] = building;
    }

    public List<MapElement> getListOfElements() {
        return listOfElements;
    }

    public static BufferedImage[] splitImage(BufferedImage img, int cols, int rows) {
        int w = img.getWidth() / cols;
        int h = img.getHeight() / rows;
        int num = 0;
        BufferedImage imgs[] = new BufferedImage[w * h];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                imgs[num] = new BufferedImage(w, h, 1); //1 <- img.getType()
                // Tell the graphics to draw only one block of the image
                //imgs[num] = img.getSubimage(x, y, w, h);
                imgs[num] = img.getSubimage(w * x, h * y, w, h);
                //Graphics2D g = imgs[num].createGraphics();
                //g.drawImage(img, 0, 0, w, h, w * x, h * y, w * x + w, h * y + h, null);
                //g.dispose();
                num++;
            }
        }
        return imgs;
    }

    public int hash4NeighbourElements(MapElement element, Class c) {
        int number = 0;
        //Class c = BuildingRoad.class;
        //c.isInstance(position.....);
        if (element.getX() > 0) {
            //if (position[element.getX() - 1][element.getY()] instanceof BuildingRoad) {
            if (c.isInstance(position[element.getX() - 1][element.getY()])) {
                number += 4;
            }
        }
        if (element.getX() < getWidth() - 1) {
//            if (position[element.getX() + 1][element.getY()] instanceof BuildingRoad) {
            if (c.isInstance(position[element.getX() + 1][element.getY()])) {
                number += 1;
            }
        }
        if (element.getY() > 0) {
 //           if (position[element.getX()][element.getY() - 1] instanceof BuildingRoad) {
            if (c.isInstance(position[element.getX()][element.getY() - 1])) {
                number += 2;
            }
        }
        if (element.getY() < getHeight() - 1) {
//            if (position[element.getX()][element.getY() + 1] instanceof BuildingRoad) {
            if (c.isInstance(position[element.getX()][element.getY() + 1])) {
                number += 8;
            }
        }
        //System.out.print(number);
        return number; //15;
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean loadImages(){
        return true;
    }
    
    abstract void paint(Graphics g);

}
