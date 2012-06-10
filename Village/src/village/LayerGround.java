/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package village;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Honza
 */
public class LayerGround extends Layer{

//    private List<Ground> listOfGrounds;

    public LayerGround(int size, int width, int height) {
        super(size, width, height);
//        listOfGrounds = new ArrayList<Ground>();
//        position = new Ground[width][height];
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
