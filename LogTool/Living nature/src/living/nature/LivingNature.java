/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package living.nature;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Honza
 */
public class LivingNature {

    private static GameFrame gameFrame;
    private static Game game;
    static Graphics g;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gameFrame = new GameFrame();
        gameFrame.setTitle("Living nature");
        gameFrame.setBounds(0, 0, 1000, 700);
        gameFrame.setVisible(true);
        game = new Game();
        game.newGame();
        game.play();

        g = gameFrame.getGraphics();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        gameFrame.repaint();
                        game.play();
                        game.paint(g);
                        System.out.println("Hello, world!");
                        Thread.sleep(100);
                    }
                } catch (InterruptedException iex) {
                    System.err.println(iex);
                }
            }
        };
        r1.run();
    }
}
