package cz.koscak.jan.mytasks2;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jkoscak on 13. 9. 2016.
 */
public class InteractiveArrayAdapter extends ArrayAdapter<Note> {

    private final List<Note> list;
    private final Activity context;

    public InteractiveArrayAdapter(Activity context, List<Note> list) {
        super(context, R.layout.task_row, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.task_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.note_text_view);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.note_checkbox);
            viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            Note element = (Note) viewHolder.checkbox.getTag();
                            if (element.isSelected() != isChecked) {
                                element.setSelected(isChecked);
                                UserAccount userAccount = UserAccount.newInstance();
                                String username = userAccount.getUsername();
                                element.setModifier(username);
                                setCheckBoxBgColor(viewHolder.text, element.getModifier(), isChecked);
                                setCheckBoxBgColor(viewHolder.checkbox, element.getModifier(), isChecked);
                                MainActivity.updateNoteState(element.getId(), isChecked ? 1 : 0);
                            }

                        }

                    });

            viewHolder.text.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.i("LONG CLICK", "SUCCESS !!! (InteractiveArrayAdapter)");
                    //Toast.makeText(view.getContext(), "Long click noticed. 13X", Toast.LENGTH_SHORT).show();

                    parent.showContextMenuForChild(view);

                    return true;
                }
            });

            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getText());
        holder.checkbox.setChecked(list.get(position).isSelected());
        setCheckBoxBgColor(holder.text, list.get(position).getModifier(), list.get(position).isSelected());
        setCheckBoxBgColor(holder.checkbox, list.get(position).getModifier(), list.get(position).isSelected());
        return view;
    }

    private void setCheckBoxBgColor(View view, String modifier, boolean isChecked) {
        String USER_PETRA   = "kostiatko@gmail.com";
        String USER_KUBA    = "xcolsan@gmail.com";
        String USER_KACKA   = "brandejsovakata@gmail.com";
        String USER_KOTUC   = "tomasrkotula@gmail.com";
        String USER_HONZA   = "xkoscak@gmail.com";
        String USER_TEST    = "testAndroidUser";

        //Log.i("setCheckBoxBgColor-v: ", view.toString());
        //Log.i("setCheckBoxBgColor-r: ", resolver);

        view.setBackgroundColor(Color.TRANSPARENT);
        if (isChecked) {
            if (view instanceof TextView) {
                TextView textView = ((TextView) view);
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (USER_HONZA.equals(modifier)) {
                view.setBackgroundColor(Color.parseColor("#CCFFCC"));
            } else if (USER_TEST.equals(modifier)) {
                view.setBackgroundColor(Color.parseColor("#CCCCCC"));
            } else if (USER_PETRA.equals(modifier)) {
                view.setBackgroundColor(Color.parseColor("#FFCCCC"));
            } else if (USER_KUBA.equals(modifier)) {
                view.setBackgroundColor(Color.parseColor("#CCCCFF"));
            } else if (USER_KACKA.equals(modifier)) {
                view.setBackgroundColor(Color.parseColor("#FFCCFF"));
            } else if (USER_KOTUC.equals(modifier)) {
                view.setBackgroundColor(Color.parseColor("#FFFFCC"));
            }
        } else {
            if (view instanceof TextView) {
                TextView textView = ((TextView) view);
                textView.setPaintFlags(textView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            }
        }
    }
}