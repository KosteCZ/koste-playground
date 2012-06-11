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
public class Worker extends MapElement {

//    private int x = 1;
//    private int y = 1;
//    private BufferedImage img = null;
//    private int size = 25;
//    protected String name = "worker";

    public Worker(int x, int y, int size) {
        super(x, y, size);
//        this.x = x;
//        this.y = y;
//        this.size = size;
    }

/*        public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean loadImageFromFile() {
        try {
            img = ImageIO.read(new File(name+".png"));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    void paint(Graphics g) {
        g.drawImage(img, x*size, y*size, null);
    }
*/
}
