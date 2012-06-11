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

    private BufferedImage[] imagesOfBuildings;

    //    private List<Building> listOfBuildings;
    public LayerConstructions(int size, int width, int height) {
        super(size, width, height);
//        listOfBuildings = new ArrayList<Building>();
//        position = new Building[width][height];
    }

    @Override
    public boolean loadImages() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("road2c.png"));
        } catch (IOException e) {
            return false;
        }
        //g.drawImage(img, 0, 0, null);
        imagesOfBuildings = splitImage(img, 4, 4);
        for (int i = 0; i < imagesOfBuildings.length; i++) {
            //g.drawImage(bs[i], 100 + (i % 6) * 100, 50 + (i / 6) * 100, null);
        }
        return true;
    }

    public void paint(Graphics g) {
        paintRoads(g);
        paintBuildings(g);
    }

    public boolean paintBuildings(Graphics g) {
        for (MapElement element : listOfElements) {
            if (!(element instanceof BuildingRoad)) {
                g.drawImage(element.getImg(), element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }

    public boolean paintRoads(Graphics g) {
        for (MapElement element : listOfElements) {
            if (element instanceof BuildingRoad) {
//                g.drawImage(imagesOfBuildings[hash4NeighbourElements(element)], element.getX() * element.getSize(), element.getY() * element.getSize(), null);
                g.drawImage(imagesOfBuildings[hash4NeighbourElements(element, BuildingRoad.class)], element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }

}
