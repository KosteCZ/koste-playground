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
public class Map {

    public final int SIZE = 25;
    private int height = 0;
    private int width = 0;
    private int[][] position;
//    private List<Building> listOfBuildings;
//    private List<Worker> listOfWorkers;
    private LayerConstructions layerConstructions;
    private LayerLife layerLife;
    private LayerGround layerGround;
    private ArrayList<Layer> listOfLayers = new ArrayList<Layer>();

    public Map() {
        position = new int[height][height];
        layerGround = new LayerGround(SIZE, width, height);
        layerConstructions = new LayerConstructions(SIZE, width, height);
        layerLife = new LayerLife(SIZE, width, height);
        listOfLayers.add(layerGround);
        listOfLayers.add(layerConstructions);
        listOfLayers.add(layerLife);
    }

    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        position = new int[height][height];
        layerGround = new LayerGround(SIZE, width, height);
        layerConstructions = new LayerConstructions(SIZE, width, height);
        layerLife = new LayerLife(SIZE, width, height);
        listOfLayers.add(layerGround);
        listOfLayers.add(layerConstructions);
        listOfLayers.add(layerLife);
    }

    public void setDefaultMap1() {
        //height = 25;
        //width = 25;
        //position = new int[25][25];
        //listOfBuildings = new ArrayList<Building>();
        //listOfWorkers = new ArrayList<Worker>();
        for (int j = 0; j < position[0].length; j++) {
            for (int i = 0; i < position.length; i++) {
                Ground g = new GroundGrass(i, j, SIZE);
                g.loadImageFromFile();
                layerGround.addElement(g);
            }
        }
/*        for (int i = 0; i < position.length; i++) {
            Building b = new Building(10, i, SIZE);
            b.loadImageFromFile();
            listOfBuildings.add(b);
        }
*/        for (int i = 1; i < (position.length - 1); i++) {
            Building b = new bRoad(12, i, SIZE);
            b.loadImageFromFile();
            layerConstructions.addElement(b);
        }
        for (int i = 1; i < (position.length - 1); i++) {
            Building b = new bRoad(i, 14, SIZE);
            b.loadImageFromFile();
            layerConstructions.addElement(b);
        }
        for (int i = 7; i < (position.length - 7); i++) {
            Building b = new bRoad(i, 8, SIZE);
            b.loadImageFromFile();
            layerConstructions.addElement(b);
        }
        Building b = new bWarehouse(7, 12, SIZE);
        b.loadImageFromFile();
        layerConstructions.addElement(b);
        Worker worker = new Worker(8, 14, SIZE);
        worker.loadImageFromFile();
        layerLife.addElement(worker);
        //warehouse
//          b1.paint(null, 10, 10);
    }

    public List<MapElement> getListOfBuildings() {
        return layerConstructions.getListOfElements();
    }

    public List<MapElement> getListOfWorkers() {
        return layerLife.getListOfElements();
    }

    public List<MapElement> getListOfGrounds() {
        return layerGround.getListOfElements();
    }

/*  public List<Building> getListOfBuildings() {
        return (List<Building>) layerConstructions.getListOfElements();
    }

    public List<Worker> getListOfWorkers() {
        return (List<Worker>) (Worker) layerLife.getListOfElements();
    }*/

    public List<MapElement> getListOfElements() {
        ArrayList<MapElement> listOfElements = new ArrayList<MapElement>();
        for (Layer layer : listOfLayers) {
            listOfElements.addAll(layer.getListOfElements());
        }
        return listOfElements;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
