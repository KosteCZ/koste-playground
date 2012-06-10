/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package village;

import java.awt.Graphics;

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
        g.drawImage(getImg(), getX()*getSize(), getY()*getSize(), null);
    }

}
