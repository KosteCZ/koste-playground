/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package village;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public abstract class MapElement {

    private int height = 1;
    private int width = 1;
    private int x = 1;
    private int y = 1;
    private BufferedImage img = null;
    private int size = 25;
    //protected String name = "empty";
    protected String name = getClass().getSimpleName().toLowerCase();

    public MapElement() {
    }

    public MapElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MapElement(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

//    public BufferedImage getImg() {
//        return img;
//    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public BufferedImage getImg() {
        return img;
    }

    public boolean loadImageFromFile() {
        try {
            System.out.println("Name: " + name);
            img = ImageIO.read(new File(name + ".png"));
        } catch (IOException e) {
            return false;
        }
        return true;
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

    void paint(Graphics g) {
        g.drawImage(img, x * size, y * size, null);
    }
//    void paint(Graphics g, int x, int y) {
//        g.drawImage(img, x, y, null);
//    }
}
