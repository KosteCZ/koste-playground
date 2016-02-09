/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package village;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Honza
 */
public class Game {

    private BufferedImage bimg;
    private Map map;
    private Player player;
    private ArrayList<Action> listOfToDoActions;
    private int marioCounter = 0;
    private boolean marioGoesLeft = false;

    public Game() /*throws FileNotFoundException, IOException*/ {
        map = new Map(25, 25);
        player = new Player(map);
        listOfToDoActions = new ArrayList();
    }

    public void loadDefaultMap() {
        map.setDefaultMap2();
//        map.makeUnmodifiableCollections();
    }

    public void loadMapImages() {
        map.loadImages();
//        map.makeUnmodifiableCollections();
    }

    public void paintMapComponents(Graphics g) {
        /*g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 20));*/
//        g.drawRect(10, 10, 1 + WIDTH, 1 + HEIGHT);
        for (MapElement element : map.getListOfElements()) {
            //element.paint(g);
        }
        map.paint(g);
        //for (Building building : map.getListOfBuildings()) {
        //    building.paint(g);
        //}
        //for (Worker worker : map.getListOfWorkers()) {
//            worker.paint(g);
        //}
        /*for (Edge edge : map.getListOfEdges()) {
        edge.paint(g);
        }
        for (Vertex vertex : map.getListOfVertices()) {
        vertex.paint(g);
        }*/
    }

    public ArrayList<Action> getListOfToDoActions() {
        return listOfToDoActions;
    }

    public void addActionToListOfToDoActions(Action action) {
        listOfToDoActions.add(action);
    }

    public void executeActions() {
        for (Action action : listOfToDoActions) {
            switch (action.getType()) {
                case BUILD:
                    if (map.getLayerConstructions().position[action.getElement().getX()][action.getElement().getY()] == null) {
                        if (map.getLayerTerrain().position[action.getElement().getX()][action.getElement().getY()] == null) {
                            action.getElement().loadImageFromFile();
                            //MapElement element = new bRoad(x, y, map.SIZE);
                            map.getLayerConstructions().addElement(action.getElement());
                            //System.out.println("");
                            break;
                        }
                    }
            }
        }
        listOfToDoActions.removeAll(listOfToDoActions);
    }

    public boolean paintMario(Graphics g) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("mario.png"));
        } catch (IOException e) {
            return false;
        }
        //g.drawImage(img, 0, 0, null);
        BufferedImage bs[] = splitImage(img, 6, 4, g);
        for (int i = 0; i < bs.length; i++) {
            //    g.drawImage(bs[i], 50+(i%6)*100, 50+(i/6)*100, null);
        }
        //g.drawImage(bs[(marioCounter % 6) + 6], 110+3*marioCounter, 303, null);
        if (!marioGoesLeft) {
            g.drawImage(bs[(marioCounter % 6) + 6], 110 + 3 * marioCounter, 303, null);
            marioCounter++;
            //marioCounter = marioCounter % 6;
            if (marioCounter > 150) {
                marioGoesLeft = true;
            }
        } else {
            g.drawImage(bs[(marioCounter % 6) + 18], 110 + 3 * marioCounter, 303, null);
            marioCounter--;
            //marioCounter = marioCounter % 6;
            if (marioCounter < 1) {
                marioGoesLeft = false;
            }
        }
        return true;
    }

    //public boolean paintWater(Graphics g) {
    //    BufferedImage img;
    //    try {
    //        img = ImageIO.read(new File("water.png"));
    //    } catch (IOException e) {
    //        return false;
    //    }
    //    //g.drawImage(img, 0, 0, null);
    //    BufferedImage bs[] = splitImage(img, 4, 4, g);
    //    for (int i = 0; i < bs.length; i++) {
    //        g.drawImage(bs[i], 50 + (i % 6) * 100, 50 + (i / 6) * 100, null);
    //    }
    //    //g.drawImage(bs[(marioCounter % 6) + 6], 110+3*marioCounter, 303, null);
    //    return true;
    //}
    //public boolean paintRoads(Graphics g) {
    //    BufferedImage img;
    //    try {
    //        img = ImageIO.read(new File("road1b.png"));
    //    } catch (IOException e) {
    //        return false;
    //    }
    //    //g.drawImage(img, 0, 0, null);
    //    BufferedImage bs[] = splitImage(img, 4, 4, g);
    //    for (int i = 0; i < bs.length; i++) {
    //        g.drawImage(bs[i], 100 + (i % 6) * 100, 50 + (i / 6) * 100, null);
    //    }
    //    //g.drawImage(bs[(marioCounter % 6) + 6], 110+3*marioCounter, 303, null);
    //    return true;
    //}
    public static BufferedImage[] splitImage(BufferedImage img, int cols, int rows, Graphics g) {
        int w = img.getWidth() / cols;
        int h = img.getHeight() / rows;
        int num = 0;
        BufferedImage imgs[] = new BufferedImage[w * h];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                imgs[num] = new BufferedImage(w, h, 1); //1 <- img.getType()
                // Tell the graphics to draw only one block of the image
                //imgs[num] = img.getSubimage(x, y, w, h);
                imgs[num] = img.getSubimage(w * x, h * y, w, h);
                //Graphics2D g = imgs[num].createGraphics();
                //g.drawImage(img, 0, 0, w, h, w * x, h * y, w * x + w, h * y + h, null);
                //g.dispose();
                num++;
            }
        }
        return imgs;
    }

    public void paintBitmap() {
        bimg = new BufferedImage(map.getWidth() * 25, map.getHeight() * 25, BufferedImage.TYPE_INT_RGB);
        paintMapComponents(bimg.createGraphics());
    }

    void paint(Graphics g) {
        g.drawImage(bimg, 0, 0, null);
        paintMario(g);
        executeActions();
        //       paintWater(g);
        //       paintRoads(g);
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
}
