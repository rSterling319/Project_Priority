package com.example.rs.projectpriority;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Collections.sort;

/**
 * Created by rs on 11/15/17.
 */

public class DisplayProjectsActivity extends AppCompatActivity {

    ProjectsTableHelper dbHelper;
    HashMap<String,Integer> projects;

    private String TAG = "DisplayProjectsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_projects);

        dbHelper = new ProjectsTableHelper(this);
        projects = dbHelper.getAllProjects();

        //Array list of keys from hashmap for the array list adapter
        ArrayList<String> values = new ArrayList<String>(projects.keySet());
        sort(values);

//       Dummy values
//        for (int i = 0; i < 20; i++)
//            values.add("Project " + i);
        final TextView textView = (TextView) findViewById(R.id.emptyList);
        final ListView listView = (ListView) findViewById(R.id.projectsList);
        if (!projects.isEmpty()) {

            listView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    android.R.id.text1, values);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int itemPosition = position;

                    String itemValue = (String) listView.getItemAtPosition(position);

                    // navigation to project page
                    Log.e(TAG, "projects.get(itemValue) = "+projects.get(itemValue));
                    goToProjectView(projects.get(itemValue));
                    //show alert
//                    Toast.makeText(getApplicationContext(), "Position:" + itemPosition + "ITEMVALUE: " + itemValue, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            listView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void goToProjectView(View view){
        Intent intent = new Intent(this,ProjectActivity.class);
        startActivity(intent);
    }

    public void goToProjectView(int id) {
        Intent intent = new Intent(this, ProjectActivity.class);
        intent.putExtra("projectID", id);
        startActivity(intent);
    }
//  FIXME: Add logic on resume to check if projects array list is still empty, other wise hid emptyList text view.

    }
