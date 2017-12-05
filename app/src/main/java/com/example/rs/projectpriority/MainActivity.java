package com.example.rs.projectpriority;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*go to display projects activity*/
    public void goToProjectsView(View view){
        Intent intent = new Intent(this, DisplayProjectsActivity.class);
        startActivity(intent);
    }

    //go to notes
    public void goToNotesView(View view){
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }

    //go to to do list list
    public void goToToDoListView(View view) {
        Intent intent = new Intent(this,DisplayToDoListActivity.class);
        startActivity(intent);
    }



    //FIXME MENU stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toDoList_menu:
                Intent intent = new Intent(this,DisplayToDoListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
