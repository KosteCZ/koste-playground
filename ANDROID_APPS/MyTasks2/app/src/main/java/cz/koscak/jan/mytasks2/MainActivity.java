package cz.koscak.jan.mytasks2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startNewNoteActivity();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        UserAccount.setContext(this);

        loadNotes();
    }

    @Override
    public void onResume() {
        super.onResume();

        loadNotes();
    }

    @Override
    public void onStart() {
        super.onStart();

        loadNotes();
    }

    public void loadNotes() {
        notes = getNotesAndAddCheckBoxes();

        ListView listView = (ListView) findViewById(R.id.tasks_listview);
        final ArrayAdapter<Note> adapter = new InteractiveArrayAdapter(this, notes);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //_listPosition = info.position;      // Get Index of long-clicked item

        super.onCreateContextMenu(menu, view, menuInfo);

        //Log.i("LONG CLICK", "SUCCESS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 7");
        //Toast.makeText(view.getContext(), "Long click noticed. 14X", Toast.LENGTH_SHORT).show();

        MenuInflater inflater = getMenuInflater();
        //menu.setHeaderIcon(R.drawable.icon);
        //menu.setHeaderTitle("Note menu");
        inflater.inflate(R.menu.context_menu_notes_list, menu);
        /*
        menu.setHeaderTitle("Choose Action");   // Context-menu title
        menu.add(0, v.getId(), 0, "Edit");  // Add element "Edit"
        menu.add(0, v.getId(), 1, "Delete");        // Add element "Delete"
        */
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String actionEdit = getResources().getString(R.string.menu_notes_edit);
        String actionDelete = getResources().getString(R.string.menu_notes_delete);
        if(actionEdit .equals(item.getTitle())) { // "Edit" chosen

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int index = info.position;
            View view = info.targetView;
            int id = notes.get(index).getId();

            Log.i("LONG CLICK", "EDIT NOTE !!!");
            //Toast.makeText(MainActivity.this, "EDIT !!!", Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "EDIT !!! Index: " + index + ", view: " + view, Toast.LENGTH_SHORT).show();

            String noteText = notes.get(index).getText();
            //Toast.makeText(MainActivity.this, "EDIT !!! Text: " + noteText, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, EditNoteTextActivity.class);
            intent.putExtra(EditNoteTextActivity.PARAM_ID, id);
            intent.putExtra(EditNoteTextActivity.PARAM_TEXT, noteText);
            startActivity(intent);

        } else if(actionDelete .equals(item.getTitle())) { // "Delete" chosen

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            int index = info.position;
            int id = notes.get(index).getId();

            Log.i("LONG CLICK - DELETE", "Deleting note with id=" + id + "...");

            deleteNote(id);
            Toast.makeText(MainActivity.this, "Note deleted!", Toast.LENGTH_SHORT).show();

            loadNotes();

        } else {
            return false;
        }
        return true;
    }

    public void startNewNoteActivity() {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //String text = "Settings button clicked!\n<No action associated yet.>";
            String text = "Barvy:\n"
                    + "Honza - zelená\n"
                    + "Kačka - růžová\n"
                    + "Petra - červená\n"
                    + "Kuba - modrá\n"
                    + "Kotuč - žlutá\n"
                    + "Test - šedá\n"
                    + "Ostatní - bílá";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_refresh) {
            Toast.makeText(this, "Tasks refreshed.", Toast.LENGTH_SHORT).show();
            loadNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    static String deleteNote(int id) {

        String text = "...nothing happened...";
        try {
            text = new SOAPDeleteNote().execute(String.valueOf(id)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return text;
    }

    static String updateNoteState(int id, int state) {

        String text = "...nothing happened...";
        try {
            text = new SOAPUpdateStateOfNote().execute(String.valueOf(id),String.valueOf(state)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return text;
    }

    private List<Note> getNotesAndAddCheckBoxes() {

        String text = "...nothing happened...";
        try {
            text = new SOAPListNotes().execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        NoteParser noteParser = new NoteParser();

        List<Note> notes = noteParser.parseAnswer(text);

        return notes;

    }

}
