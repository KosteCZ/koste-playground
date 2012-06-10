/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package village;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Honza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Village");
        final Game game = new Game();
        frame.add(new MainJPanel(game));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(900, 700));    //(640, 655));
        frame.setVisible(true);

        // TODO code application logic here

        //game.play();
        
        game.loadDefaultMap();
        game.loadMapImages();

        game.paintBitmap();


        new Thread() {

            @Override
            public synchronized void run() {
                while (true) {
                    frame.repaint();
                    try {
                        sleep(40);
                        //sleep(2000);
                    } catch (InterruptedException ex) {
                        System.err.println(ex);
                    }
                }
            }
        }.start();
    }

}
