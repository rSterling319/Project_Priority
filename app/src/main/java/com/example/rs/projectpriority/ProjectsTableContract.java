package com.example.rs.projectpriority;

import android.provider.BaseColumns;

/**
 * Created by rs on 11/24/17.
 */

public class ProjectsTableContract {
    private ProjectsTableContract(){};

    public static class ProjectsEntry implements BaseColumns{
        public static final String TABLE_NAME = "projects";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PROGRESS = "progress";
        public static final String COLUMN_DEADLINE = "deadline";


    }

    public static final String SQL_CREATE_PROJECTS_TABLE =
            "CREATE TABLE " + ProjectsEntry.TABLE_NAME + "(" +
             ProjectsEntry._ID + " INTEGER PRIMARY KEY, " +
             ProjectsEntry.COLUMN_NAME + " TEXT, " +
             ProjectsEntry.COLUMN_PROGRESS + " INTEGER, " +
             ProjectsEntry.COLUMN_DEADLINE + " TEXT)";

    public static final String SQL_DELETE_PROJECTS_TABLE =
            "DROP TABLE IF EXISTS " + ProjectsEntry.TABLE_NAME;



}
