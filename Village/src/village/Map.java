/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package village;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Honza
 */
public class Map {

    public final int SIZE = 25;
    private int height = 0;
    private int width = 0;
//    private int[][] position;
//    private List<Building> listOfBuildings;
//    private List<Worker> listOfWorkers;
    private LayerLife layerLife;
    private LayerConstructions layerConstructions;
    private LayerTerrain layerTerrain;
    private LayerGround layerGround;
    private LayerCursor layerCursor;
    private ArrayList<Layer> listOfLayers = new ArrayList<Layer>();

    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        //position = new int[height][height];
        layerGround = new LayerGround(SIZE, width, height);
        layerTerrain = new LayerTerrain(SIZE, width, height);
        layerConstructions = new LayerConstructions(SIZE, width, height);
        layerLife = new LayerLife(SIZE, width, height);
        layerCursor = new LayerCursor(SIZE, width, height);
        listOfLayers.add(layerGround);
        listOfLayers.add(layerTerrain);
        listOfLayers.add(layerConstructions);
        listOfLayers.add(layerLife);
        listOfLayers.add(layerCursor);
    }

    public void setDefaultMap1() {
        //height = 25;
        //width = 25;
        //position = new int[25][25];
        //listOfBuildings = new ArrayList<Building>();
        //listOfWorkers = new ArrayList<Worker>();
        Random r = new Random(12345);
        for (int j = 0; j < layerGround.position[0].length; j++) {
            for (int i = 0; i < layerGround.position.length; i++) {
                Ground g;
                if (r.nextInt(5) != 0) {
                    g = new GroundGrass(i, j, SIZE);
                } else {
                    g = new GroundSand(i, j, SIZE);
                }
                g.loadImageFromFile();
                layerGround.addElement(g);
            }
        }
        for (int j = 0; j < layerTerrain.position[0].length; j++) {
            for (int i = 0; i < layerTerrain.position.length; i++) {
                if (r.nextInt(3) == 0) {
                    TerrainWater g = new TerrainWater(i, j, SIZE);
                    g.loadImageFromFile();
                    layerTerrain.addElement(g);
                }
            }
        }
        /*        for (int i = 0; i < position.length; i++) {
        Building b = new Building(10, i, SIZE);
        b.loadImageFromFile();
        listOfBuildings.add(b);
        }
         */ for (int i = 1; i < (layerConstructions.position.length - 1); i++) {
            Building b = new BuildingRoad(12, i, SIZE);
            b.loadImageFromFile();
            layerConstructions.addElement(b);
        }
        for (int i = 1; i < (layerConstructions.position.length - 1); i++) {
            Building b = new BuildingRoad(i, 14, SIZE);
            b.loadImageFromFile();
            layerConstructions.addElement(b);
        }
        for (int i = 7; i < (layerConstructions.position.length - 7); i++) {
            Building b = new BuildingRoad(i, 8, SIZE);
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

    public void setDefaultMap2() {
        //height = 25;
        //width = 25;
        //position = new int[25][25];
        //listOfBuildings = new ArrayList<Building>();
        //listOfWorkers = new ArrayList<Worker>();
        //Random r = new Random(12345);
        Random r = new Random();
        for (int j = 0; j < layerGround.position[0].length; j++) {
            for (int i = 0; i < layerGround.position.length; i++) {
                Ground g = new GroundGrass(i, j, SIZE);
                if (j <= layerGround.position[0].length / 3) {
                    if (j >= layerGround.position[0].length / 6) {
                        //if (i < (layerGround.position.length - ((j - (layerGround.position[0].length / 6)) * 2))) {
                        //    if (i > (j - ((layerGround.position[0].length / 6))) * 2) {
                        //System.out.println("Sand: " + (j - (layerGround.position[0].length / 6)));
                        if (r.nextInt(5 - ((j - (layerGround.position[0].length / 6)))) != 0) {
                            g = new GroundSand(i, j, SIZE);
                        }
                        //    }
                        //}
                    } else {
                        g = new GroundSand(i, j, SIZE);
                    }
                }
                if (j >= layerGround.position[0].length - (layerGround.position[0].length / 3)) {
                    if (j <= layerGround.position[0].length - (layerGround.position[0].length / 6)) {
                        //if (i < (layerGround.position.length - ((j - (layerGround.position[0].length / 6)) * 2))) {
                        //    if (i > (j - ((layerGround.position[0].length / 6))) * 2) {
                        //System.out.println("Snow: " + (j - (2*(layerGround.position[0].length / 3))));
                        if (r.nextInt(5 - (((j - (2 * (layerGround.position[0].length / 3)))) - 1)) == 0) {
                            g = new GroundSnow(i, j, SIZE);
                        }
                        //    }
                        //}
                    } else {
                        g = new GroundSnow(i, j, SIZE);
                    }
                }
                g.loadImageFromFile();
                layerGround.addElement(g);
            }
        }
        ArrayList<MapElement> listOfWaterTiles = new ArrayList<MapElement>();
        MapElement[][] position = new MapElement[layerTerrain.position.length][layerTerrain.position[0].length];
        for (int j = 0; j < /*layerTerrain.*/ position[0].length; j++) {
            for (int i = 0; i < /*layerTerrain.*/ position.length; i++) {
                if (r.nextInt(30) >= 19) {
                    TerrainWater g = new TerrainWater(i, j, SIZE);
                    g.loadImageFromFile();
                    listOfWaterTiles.add(g);
                    //layerTerrain.addElement(g);
                    position[i][j] = g;
                }
            }
        }
        for (int j = 0; j < position[0].length; j++) {
            for (int i = 0; i < position.length; i++) {
                if (position[i][j] instanceof TerrainWater) {
                    if (countNeighboursOfElement(position, position[i][j]) <= 1) {
                        //TerrainWater g = new TerrainWater(i, j, SIZE);
                        //g.loadImageFromFile();
                        listOfWaterTiles.remove(position[i][j]);
                        position[i][j] = null;
                    }
                }
            }
        }
        for (int j = 0; j < position[0].length; j++) {
            for (int i = 0; i < position.length; i++) {
                if (position[i][j] instanceof TerrainWater) {
                    if (countNeighboursOfElement(position, position[i][j]) == 0) {
                        //TerrainWater g = new TerrainWater(i, j, SIZE);
                        //g.loadImageFromFile();
                        listOfWaterTiles.remove(position[i][j]);
                        position[i][j] = null;
                    }
                }
            }
        }
        for (MapElement terrainWater : listOfWaterTiles) {
            layerTerrain.addElement(terrainWater);
        }
        for (int j = 0; j < layerTerrain.position[0].length; j++) {
            for (int i = 0; i < layerTerrain.position.length; i++) {
                if ((r.nextInt(30) == 0) && (layerTerrain.position[i][j] == null)) {
                    TerrainRock g = new TerrainRock(i, j, SIZE);
                    g.loadImageFromFile();
                    layerTerrain.addElement(g);
                }
            }
        }
        /*        for (int i = 0; i < position.length; i++) {
        Building b = new Building(10, i, SIZE);
        b.loadImageFromFile();
        listOfBuildings.add(b);
        }
         */ for (int i = 1; i < (layerConstructions.position.length - 1); i++) {
            Building b = new BuildingRoad(12, i, SIZE);
            b.loadImageFromFile();
            if (layerTerrain.position[12][i] == null) {
                layerConstructions.addElement(b);
            }
        }
        for (int i = 1; i < (layerConstructions.position.length - 1); i++) {
            Building b = new BuildingRoad(i, 14, SIZE);
            b.loadImageFromFile();
            if (layerTerrain.position[i][14] == null) {
                layerConstructions.addElement(b);
            }
        }
        for (int i = 7; i < (layerConstructions.position.length - 7); i++) {
            Building b = new BuildingRoad(i, 8, SIZE);
            b.loadImageFromFile();
            if (layerTerrain.position[i][8] == null) {
                layerConstructions.addElement(b);
            }
        }
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position[0].length; j++) {
                CursorUnselected c = new CursorUnselected(i, j, SIZE);
                c.loadImageFromFile();
                layerCursor.addElement(c);
            }
        }

        //if (layerTerrain.position[7][12] == null) {
            Building b = new bWarehouse(7, 12, SIZE);
            b.loadImageFromFile();
            layerConstructions.addElement(b);
        //}
        Worker worker = new Worker(8, 14, SIZE);
        worker.loadImageFromFile();
        layerLife.addElement(worker);
        //warehouse
//          b1.paint(null, 10, 10);
    }

    public int countNeighboursOfElement(MapElement[][] position, MapElement element) {
        int number = 0;
        if (element.getX() > 0) {
            if (position[element.getX() - 1][element.getY()] instanceof TerrainWater) {
                number++;
            }
        }
        if (element.getX() < getWidth() - 1) {
            if (position[element.getX() + 1][element.getY()] instanceof TerrainWater) {
                number++;
            }
        }
        if (element.getY() > 0) {
            if (position[element.getX()][element.getY() - 1] instanceof TerrainWater) {
                number++;
            }
        }
        if (element.getY() < getHeight() - 1) {
            if (position[element.getX()][element.getY() + 1] instanceof TerrainWater) {
                number++;
            }
        }
        //System.out.print(number);
        return number; //15;
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

    void paint(Graphics g) {
        for (Layer layer : listOfLayers) {
            layer.paint(g);
        }
        //layerTerrain.paintWater(g);
        //layerConstructions.paintRoads(g);
    }

    void loadImages() {
        for (Layer layer : listOfLayers) {
            layer.loadImages();
        }
        for (MapElement element : getListOfElements()) {
            element.loadImageFromFile();
        }
    }

    public LayerLife getLayerLife() {
        return layerLife;
    }

    public LayerConstructions getLayerConstructions() {
        return layerConstructions;
    }

    public LayerTerrain getLayerTerrain() {
        return layerTerrain;
    }

}
