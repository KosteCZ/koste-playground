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
public class LayerCursor extends Layer {

    private BufferedImage[] imagesOfSelected;
    private BufferedImage[] imagesOfUnselected;

    public LayerCursor(int size, int width, int height) {
        super(size, width, height);
//        for (int i = 0; i < position.length; i++) {
//            for (int j = 0; j < position[0].length; j++) {
//                //listOfWaterTiles.add(g);
//                position[i][j] = new CursorUnselected(i, j, size);
//            }
//        }
    }

    @Override
    public boolean loadImages() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("cursorselected.png"));
        } catch (IOException e) {
            return false;
        }
        //g.drawImage(img, 0, 0, null);
        imagesOfSelected = splitImage(img, 4, 4);
        for (int i = 0; i < imagesOfSelected.length; i++) {
            //g.drawImage(bs[i], 100 + (i % 6) * 100, 50 + (i / 6) * 100, null);
        }
        try {
            img = ImageIO.read(new File("cursorunselected.png"));
        } catch (IOException e) {
            return false;
        }
        //g.drawImage(img, 0, 0, null);
        imagesOfUnselected = splitImage(img, 4, 4);
        for (int i = 0; i < imagesOfUnselected.length; i++) {
            //g.drawImage(bs[i], 100 + (i % 6) * 100, 50 + (i / 6) * 100, null);
        }
        return true;
    }

    @Override
    void paint(Graphics g) {
        paintSelected(g);
//        paintUnselected(g);
    }

    public boolean paintSelected(Graphics g) {
        for (MapElement element : listOfElements) {
            if (element instanceof CursorSelected) {
                g.drawImage(imagesOfSelected[hash4NeighbourElements(element, CursorSelected.class)], element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }

    public boolean paintUnselected(Graphics g) {
        for (MapElement element : listOfElements) {
            if (element instanceof CursorUnselected) {
                g.drawImage(imagesOfUnselected[hash4NeighbourElements(element, CursorUnselected.class)], element.getX() * element.getSize(), element.getY() * element.getSize(), null);
            }
        }
        return true;
    }
}
