package com.example.rs.projectpriority;

import android.provider.BaseColumns;

/**
 * Created by rs on 11/17/17.
 */

public class NotesTableContract {

    private NotesTableContract(){};

    public static class NotesEntry implements BaseColumns{
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_NOTE = "note";
        public static final String COLUMN_NAME_DUMMY ="dummy";
    }

    public static final String SQL_CREATE_NOTES_TABLE =
            "CREATE TABLE " + NotesEntry.TABLE_NAME + "(" +
             NotesEntry._ID +  " INTEGER PRIMARY KEY, " +
             NotesEntry.COLUMN_NAME_NOTE + " TEXT, " +
             NotesEntry.COLUMN_NAME_DUMMY + " TEXT)";

    public static final String SQL_DELETE_NOTES_TABLE =
            "DROP TABLE IF EXISTS " + NotesEntry.TABLE_NAME;
}
