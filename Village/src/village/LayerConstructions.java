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
public class LayerConstructions extends Layer {

//    private List<Building> listOfBuildings;

    public LayerConstructions(int size, int width, int height) {
        super(size, width, height);
//        listOfBuildings = new ArrayList<Building>();
//        position = new Building[width][height];
    }

/*    public boolean safeAddBuilding(Building building) {
        if (position[building.getX()][building.getY()] != null) {
            listOfBuildings.add(building);
            position[building.getX()][building.getY()] = building;
            return true;
        }
        return false;
    }

    public void addBuilding(Building building) {
        if (position[building.getX()][building.getY()] != null) {
            listOfBuildings.remove(position[building.getX()][building.getY()]);
        }
        listOfBuildings.add(building);
        position[building.getX()][building.getY()] = building;
    }

    public List<Building> getListOfBuildings() {
        return listOfBuildings;
    }
*/
}
