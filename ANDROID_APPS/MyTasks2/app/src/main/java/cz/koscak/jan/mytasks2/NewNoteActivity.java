package cz.koscak.jan.mytasks2;

import android.content.Intent;
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
public class NewNoteActivity extends AppCompatActivity {

    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
    }

    public void saveNewNote(View view) {

        //setContentView(R.layout.new_note);
        EditText newNoteEditText = (EditText) findViewById(R.id.new_note_text);
        String newNoteText = newNoteEditText.getText().toString();

        Log.i("NewNoteActivity", "NewNoteText: " + newNoteText);

        String text = "...nothing happened...";
        try {
            text = new SOAPNewNote().execute(newNoteText).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // TOAST / for debug purposes
        //Toast.makeText(getApplicationContext(), "TEXT: " + text, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Note created.", Toast.LENGTH_LONG).show();

        finish();
    }

    public void cancelNewNote(View view) {
        finish();
    }

}
