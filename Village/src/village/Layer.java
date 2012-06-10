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

}
