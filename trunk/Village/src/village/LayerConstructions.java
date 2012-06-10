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
import javax.lang.model.element.Element;

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

    public void paint(Graphics g) {
        paintRoads(g);
        paintBuildings(g);
    }

    public boolean paintBuildings(Graphics g) {
        for (MapElement element : listOfElements) {
            if (!(element instanceof bRoad)) {
                g.drawImage(element.getImg(), element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }

    public boolean paintRoads(Graphics g) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("road2c.png"));
        } catch (IOException e) {
            return false;
        }
        //g.drawImage(img, 0, 0, null);
        BufferedImage bs[] = splitImage(img, 4, 4, g);
        for (int i = 0; i < bs.length; i++) {
            //g.drawImage(bs[i], 100 + (i % 6) * 100, 50 + (i / 6) * 100, null);
        }
        //g.drawImage(bs[(marioCounter % 6) + 6], 110+3*marioCounter, 303, null);
        for (MapElement element : listOfElements) {
            if (element instanceof bRoad) {
                g.drawImage(bs[hash4NeighbourElements(element)], element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }

    public int hash4NeighbourElements(MapElement element) {
        int number = 0;
        if (element.getX() > 0) {
            if (position[element.getX() - 1][element.getY()] instanceof bRoad) {
                number += 4;
            }
        }
        if (element.getX() < getWidth() - 1) {
            if (position[element.getX() + 1][element.getY()] instanceof bRoad) {
                number += 1;
            }
        }
        if (element.getY() > 0) {
            if (position[element.getX()][element.getY() - 1] instanceof bRoad) {
                number += 2;
            }
        }
        if (element.getY() < getHeight() - 1) {
            if (position[element.getX()][element.getY() + 1] instanceof bRoad) {
                number += 8;
            }
        }
        //System.out.print(number);
        return number; //15;
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
