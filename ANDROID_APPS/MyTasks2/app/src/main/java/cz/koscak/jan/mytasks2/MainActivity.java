package cz.koscak.jan.mytasks2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        getNotesAndAddCheckBoxes();

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

    private void getNotesAndAddCheckBoxes() {

        String text = "...nothing happened...";
        try {
            text = new SOAPCommunication().execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        NoteParser noteParser = new NoteParser();

        List<Note> notes = noteParser.parseAnswer(text);

        /*CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox1.setText(text);

        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        String checkBox2Text = "";
        for(int i = 0; i < notes.size(); i++) {
            checkBox2Text = checkBox2Text + notes.get(i) + "\n\n";
        }
        checkBox2.setText(checkBox2Text);*/

        final LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_linear_layout);

        List<MyTaskCheckBox> checkBoxes = new ArrayList<MyTaskCheckBox>();

        for(int i = 0; i < notes.size(); i++) {

            final MyTaskCheckBox checkBox = new MyTaskCheckBox(this);
            checkBox.setText(/*"!!! " + i + " " + */notes.get(i).getText());
            checkBox.setPadding(2, 2, 2, 2);
            if (1 <= notes.get(i).getState()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            setCheckBoxBgColor(checkBox, notes.get(i).getCreator());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if ( isChecked ) {
                        checkBox.setBackgroundColor(Color.parseColor("#E3FFE3"));
//                        setCheckBoxBgColor((MyTaskCheckBox) buttonView, ((MyTaskCheckBox) buttonView).getNote().getCreator());
                    } else {
                        buttonView.setBackgroundColor(Color.TRANSPARENT);
                    }

                }
            });
            checkBoxes.add(checkBox);

            mainLayout.addView(checkBox,
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT) );

            if (i < (notes.size() - 1)) {
                View v = new View(this);
                v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5));
                v.setBackgroundColor(Color.parseColor("#E3E3E3"));
                mainLayout.addView(v);
            }

        }
    }

    private void setCheckBoxBgColor(CheckBox checkBox, String resolver) {
        String USER_PETRA = "";
        String USER_HONZA = "xkoscak@gmail.com";
        String USER_TEST = "";

        if (USER_HONZA.equals(resolver)) {
            checkBox.setBackgroundColor(Color.parseColor("#E3FFE3"));
        }
    }
}
