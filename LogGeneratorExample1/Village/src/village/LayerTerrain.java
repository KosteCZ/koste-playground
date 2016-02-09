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

/**
 *
 * @author Honza
 */
public class LayerTerrain extends Layer {

    private BufferedImage[] imagesOfRoad;

    public LayerTerrain(int size, int width, int height) {
        super(size, width, height);
    }

    @Override
    public boolean loadImages() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("water.png"));
        } catch (IOException e) {
            return false;
        }
        //g.drawImage(img, 0, 0, null);
        imagesOfRoad = splitImage(img, 4, 4);
        for (int i = 0; i < imagesOfRoad.length; i++) {
            //g.drawImage(bs[i], 100 + (i % 6) * 100, 50 + (i / 6) * 100, null);
        }
        return true;
    }

    public void paint(Graphics g) {
        paintWater(g);
        paintRocks(g);
    }

    public boolean paintRocks(Graphics g) {
        for (MapElement element : listOfElements) {
            if (!(element instanceof TerrainWater)) {
                g.drawImage(element.getImg(), element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }

    public boolean paintWater(Graphics g) {
        for (MapElement element : listOfElements) {
            if(element instanceof TerrainWater){
            g.drawImage(imagesOfRoad[hash4NeighbourElements(element, TerrainWater.class)], element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }

}
