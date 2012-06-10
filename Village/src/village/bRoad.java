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
public class bRoad extends Building {

    public bRoad(int x, int y, int size) {
        super(x, y, size);
//        name = "Road";
    }

    @Override
    void paint(Graphics g) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("road1b.png"));
        } catch (IOException e) {
        }
        BufferedImage bs[] = splitImage(img, 4, 4, g);
        //for (int i = 0; i < bs.length; i++) {
        //    g.drawImage(bs[i], 100 + (i % 6) * 100, 50 + (i / 6) * 100, null);
        //}
        g.drawImage(bs[hashPositionToNumber()], getX()*getSize(), getY()*getSize(), null);
        //g.drawImage(getImg(), getX()*getSize(), getY()*getSize(), null);
    }

    private int hashPositionToNumber() {
        int number;
        if(getX() != 0){
            //if(){
            //
            //}
        }
        if(getX() != getWidth()){

        }
        if(getY() != 0){

        }
        if(getY() != getHeight()){

        }
        return 15;
    }

}
