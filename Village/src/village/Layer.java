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

    public static BufferedImage[] splitImage(BufferedImage img, int cols, int rows, Graphics g) {
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    abstract void paint(Graphics g);

}
