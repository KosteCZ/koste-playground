/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package village;

/**
 *
 * @author Honza
 */
class Ground extends MapElement {

    //protected String name = getClass().getSimpleName(); //Ground.class.getSimpleName();// Class.name "ground";

    public Ground() {
        super();
    }

    public Ground(int x, int y) {
        super(x, y);
    }

    public Ground(int x, int y, int size) {
        super(x, y, size);
    }

}
