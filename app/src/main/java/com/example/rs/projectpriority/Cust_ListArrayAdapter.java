package com.example.rs.projectpriority;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rs on 12/24/17.
 */

public class Cust_ListArrayAdapter extends ArrayAdapter<String> {

    ProjectsTableHelper dbHelper;
    HashMap<String,Integer> projectsHash;

    public Cust_ListArrayAdapter(Context context, ArrayList<String> projects) {
        super(context, 0, projects);
        dbHelper = new ProjectsTableHelper(context, false);
        projectsHash = dbHelper.getAllProjects();
    }


        @Override
        public View getView (int position, View convertView, ViewGroup parent){

        // Get the data item for this position
            String projectStr = getItem(position);
            Project project = dbHelper.getProject(projectsHash.get(projectStr));

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_bars, parent, false);
            }
            // Lookup view for data population
            TextView projectName = (TextView) convertView.findViewById(R.id.ProjName);
            ProgressBar progress = (ProgressBar) convertView.findViewById(R.id.ProjProgress);
            // Populate the data into the template view using the data object
            projectName.setText(project.getName());
            progress.setProgress(project.getProgress());
            // Return the completed view to render on screen
            return convertView;
        }
    }