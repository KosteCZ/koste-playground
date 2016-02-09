/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package village;

/**
 *
 * @author Honza
 */
public class Action {

    private MapElement element;
    private ActionType type;

    public Action(MapElement element, ActionType type) {
        this.element = element;
        this.type = type;
    }

    public MapElement getElement() {
        return element;
    }

    public ActionType getType() {
        return type;
    }

}
