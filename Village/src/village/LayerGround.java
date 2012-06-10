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
public class LayerGround extends Layer {

//    private List<Ground> listOfGrounds;
    public LayerGround(int size, int width, int height) {
        super(size, width, height);
//        listOfGrounds = new ArrayList<Ground>();
//        position = new Ground[width][height];
    }

    public void paint(Graphics g) {
        paintGrass(g);
    }

    public boolean paintGrass(Graphics g) {
        for (MapElement element : listOfElements) {
            g.drawImage(element.getImg(), element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            //g.drawImage(bs[hash4NeighbourElements(element)], element.getX() * element.getSize(), element.getY() * element.getSize(), null);
        }
        return true;
    }
    /*    public boolean safeAddBuilding(Ground ground) {
    if (position[ground.getX()][ground.getY()] != null) {
    listOfGrounds.add(ground);
    position[ground.getX()][ground.getY()] = ground;
    return true;
    }
    return false;
    }

    public void addBuilding(Ground ground) {
    if (position[ground.getX()][ground.getY()] != null) {
    listOfGrounds.remove(position[ground.getX()][ground.getY()]);
    }
    listOfGrounds.add(ground);
    position[ground.getX()][ground.getY()] = ground;
    }

    public List<Ground> getListOfBuildings() {
    return listOfGrounds;
    }
     */
}
