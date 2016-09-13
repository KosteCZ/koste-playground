package cz.koscak.jan.mytasks2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by jkoscak on 13. 9. 2016.
 */
public class MyTaskCheckBox extends CheckBox {

    private Note note;

    public MyTaskCheckBox(Context context) {
        super(context);
    }

    public MyTaskCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTaskCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

}
