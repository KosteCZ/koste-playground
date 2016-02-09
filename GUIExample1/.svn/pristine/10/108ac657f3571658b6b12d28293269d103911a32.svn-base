/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package living.nature;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Honza
 */
public class Game {

    ArrayList<Animal> listOfAnimals = new ArrayList<>();

    public Game() {
    }

    void newGame() {
        for (int i = 0; i < 100; i++) {
            listOfAnimals.add(new Animal(100, 100));
        }
    }

    void play() {
        for (Animal animal : listOfAnimals) {
            Random random = new Random();
            animal.move(random.nextInt(3)-1, random.nextInt(3)-1);
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    void paint(Graphics g) {
        for (Animal animal : listOfAnimals) {
            animal.paint(g);
        }

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
