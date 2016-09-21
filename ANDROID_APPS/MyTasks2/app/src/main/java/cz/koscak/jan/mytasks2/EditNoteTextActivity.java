package cz.koscak.jan.mytasks2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by jkoscak on 12. 9. 2016.
 */
public class EditNoteTextActivity extends AppCompatActivity {

    public static final String PARAM_ID = "param_id";
    public static final String PARAM_TEXT = "param_text";
    private int id = -1;
    private String text = null;

    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_text);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt(PARAM_ID);
            text = extras.getString(PARAM_TEXT);
        }

        if (text != null) {
            EditText editNoteTextEditText = (EditText) findViewById(R.id.edit_note_text);
            editNoteTextEditText.setText(text);
            editNoteTextEditText.setSelection(editNoteTextEditText.getText().length());//move cursor at the end of the text
        }
    }

    public void saveEditedNoteText(View view) {

        EditText newNoteEditText = (EditText) findViewById(R.id.edit_note_text);
        String editNoteTextEditText = newNoteEditText.getText().toString();

        Log.i("UpdateNoteTextActivity", "UpdatedNoteText: " + editNoteTextEditText);

        String text = "...nothing happened...";
        try {
            text = new SOAPUpdateTextOfNote().execute(String.valueOf(id), editNoteTextEditText).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // TOAST / for debug purposes
        //Toast.makeText(getApplicationContext(), "TEXT: " + text, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Note updated.", Toast.LENGTH_LONG).show();

        finish();
    }

    public void cancelEditedNoteText(View view) {
        finish();
    }

}
