package com.example.rs.projectpriority;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by rs on 11/24/17.
 */

public class ProjectsTableHelper extends ProjectPriorityDbHelper {
    ProjectPriorityDbHelper pDbHelper;
    SQLiteDatabase db;
    String TAG = "ProjectsTableHelper";

    ProjectsTableHelper(Context context){
        super(context);
        pDbHelper = new ProjectPriorityDbHelper(context);
        db = pDbHelper.getWritableDatabase();
    }

    public void updateProject(Project project){
        ContentValues values = new ContentValues();
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_NAME, project.getName());
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_PROGRESS, project.getProgress());
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_DEADLINE, project.getDeadline_asString());
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_TODO, project.getToDoList()? 1 : 0);//1 for true 0 for false


        //FIXME: I think i may be getting an exception here on update table, that's why it creates two??
        try{
            db.update(ProjectsTableContract.ProjectsEntry.TABLE_NAME, values, ProjectsTableContract.ProjectsEntry._ID + " = ?",
                    new String[]{project.getId().toString()});
        }catch (SQLException ex){
            newProject(project);
        }
    }

    public void newProject(Project project){
        ContentValues values = new ContentValues();
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_NAME, project.getName());
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_PROGRESS, project.getProgress());
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_DEADLINE, project.getDeadline_asString());
        values.put(ProjectsTableContract.ProjectsEntry.COLUMN_TODO, project.getToDoList()? 1 : 0);//1 for true 0 for false

        try{
            db.insert(ProjectsTableContract.ProjectsEntry.TABLE_NAME, null, values);
        }catch(SQLException ex){
            Log.e(TAG, ex + " newProject went wrong");

        }


    }

    public Project getProject(Integer id){

        String name;
        int progress;
        String deadline;
        Boolean todolist;

        String selectQuery = "SELECT * FROM " + ProjectsTableContract.ProjectsEntry.TABLE_NAME +
                " WHERE " + ProjectsTableContract.ProjectsEntry._ID + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{id.toString()});

        c.moveToFirst();
        id = c.getInt(c.getColumnIndex(ProjectsTableContract.ProjectsEntry._ID));
        name = c.getString(c.getColumnIndex(ProjectsTableContract.ProjectsEntry.COLUMN_NAME));
        progress = c.getInt(c.getColumnIndex(ProjectsTableContract.ProjectsEntry.COLUMN_PROGRESS));
        deadline = c.getString(c.getColumnIndex(ProjectsTableContract.ProjectsEntry.COLUMN_DEADLINE));
        todolist = (c.getInt(c.getColumnIndex(ProjectsTableContract.ProjectsEntry.COLUMN_TODO))==1)? true : false; //if one return true etc.

        Log.e(TAG,"id = "+id + "\nname = "+name +"\nprogress = "+progress+"\ntodolist= " +todolist);
        Project project = new Project(id, name, progress, deadline, todolist);
        return project;
    }

    public HashMap getAllProjects(){
        String name;
        Integer id;
        HashMap<String, Integer>  projects = new HashMap<String, Integer>();

        String selectQuery = "SELECT " + ProjectsTableContract.ProjectsEntry._ID + ", " +
                ProjectsTableContract.ProjectsEntry.COLUMN_NAME + " FROM " +
                ProjectsTableContract.ProjectsEntry.TABLE_NAME;

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                name = c.getString(c.getColumnIndex(ProjectsTableContract.ProjectsEntry.COLUMN_NAME));
                id = c.getInt(c.getColumnIndex(ProjectsTableContract.ProjectsEntry._ID));
                Log.e(TAG, "Name = " + name + " id = " + id);
                projects.put(name, id);
                c.moveToNext();
            }
        }


        return projects;
    }
}
