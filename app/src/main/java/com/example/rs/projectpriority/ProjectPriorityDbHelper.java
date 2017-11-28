package com.example.rs.projectpriority;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.rs.projectpriority.NotesTableContract.SQL_CREATE_NOTES_TABLE;
import static com.example.rs.projectpriority.NotesTableContract.SQL_DELETE_NOTES_TABLE;
import static com.example.rs.projectpriority.ProjectsTableContract.SQL_CREATE_PROJECTS_TABLE;
import static com.example.rs.projectpriority.ProjectsTableContract.SQL_DELETE_PROJECTS_TABLE;

/**
 * Created by rs on 11/17/17.
 */

public class ProjectPriorityDbHelper extends SQLiteOpenHelper {
    //if database schema is changed, increment database version
    public static final int DATABASE_VERSION = 15;
    public static final String DATABASE_NAME = "ProjectPriority.db";

    public ProjectPriorityDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_NOTES_TABLE);
        db.execSQL(SQL_CREATE_PROJECTS_TABLE);
        //db.execSQL(OTHER_TABLES)etc..
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_NOTES_TABLE);
        db.execSQL(SQL_DELETE_PROJECTS_TABLE);
        //db.execSQL(OTHER_TABLES)etc...
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }
}
