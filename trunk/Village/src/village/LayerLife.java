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
public class LayerLife extends Layer {

//    private List<Worker> listOfWorkers;

    public LayerLife(int size, int width, int height) {
        super(size, width, height);
//        listOfWorkers = new ArrayList<Worker>();
//        position = new Worker[width][height];
    }

/*    public boolean safeAddBuilding(Worker worker) {
        if (position[worker.getX()][worker.getY()] == null) {
            listOfWorkers.add(worker);
            position[worker.getX()][worker.getY()] = worker;
            return true;
        }
        return false;
    }

    public void addWorker(Worker worker) {
        if (position[worker.getX()][worker.getY()] != null) {
            listOfWorkers.remove(position[worker.getX()][worker.getY()]);
        }
        listOfWorkers.add(worker);
        position[worker.getX()][worker.getY()] = worker;
    }

    public List<Worker> getListOfWorkers() {
        return listOfWorkers;
    }
*/
}
