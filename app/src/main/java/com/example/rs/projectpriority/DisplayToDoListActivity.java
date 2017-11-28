package com.example.rs.projectpriority;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by rs on 11/27/17.
 */

public class DisplayToDoListActivity extends AppCompatActivity {
    ProjectsTableHelper dbHelper;
    HashMap<String, Integer> toDoList;

    TextView emptyListView;
    ListView toDoListView;

    private String TAG = "DisplayToDoListActivity";

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
    }

    @Override
    protected void onResume(){
        super.onResume();

        dbHelper = new ProjectsTableHelper(this, false);
        toDoList = dbHelper.getToDoList();

        emptyListView = (TextView) findViewById(R.id.emptyToDoList);
        toDoListView = (ListView) findViewById(R.id.toDoList);

        ArrayList<String> values = new ArrayList<String>(toDoList.keySet());
        sort(values);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1, values);

        toDoListView.setAdapter(adapter);

        displayToDoList();


    }

    public void displayToDoList(){
        if (!toDoList.isEmpty()) {

            toDoListView.setVisibility(View.VISIBLE);
            emptyListView.setVisibility(View.GONE);


            toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int itemPosition = position;

                    String itemValue = (String) toDoListView.getItemAtPosition(position);

                    // navigation to project page
                   // Log.e(TAG, "projects.get(itemValue) = "+toDoList.get(itemValue));
                    goToProjectView(toDoList.get(itemValue));
                }
            });
        } else {
            toDoListView.setVisibility(View.GONE);
            emptyListView.setVisibility(View.VISIBLE);
        }
    }

    public void goToProjectView(int id){
        Intent intent = new Intent(this, ProjectActivity.class);
        intent.putExtra("projectID", id);
        startActivity(intent);
    }

}
