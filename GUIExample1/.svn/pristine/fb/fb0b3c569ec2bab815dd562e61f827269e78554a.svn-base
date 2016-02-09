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
public class LayerLife extends Layer {

//    private List<Worker> listOfWorkers;
    public LayerLife(int size, int width, int height) {
        super(size, width, height);
//        listOfWorkers = new ArrayList<Worker>();
//        position = new Worker[width][height];
    }

    public void paint(Graphics g) {
        paintWorkers(g);
    }

    public boolean paintWorkers(Graphics g) {
        for (MapElement element : listOfElements) {
//            if (!(element instanceof Worker)) {
            g.drawImage(element.getImg(), element.getX() * element.getSize(), element.getY() * element.getSize(), null);
        }
//        }
        return true;
    }
}
