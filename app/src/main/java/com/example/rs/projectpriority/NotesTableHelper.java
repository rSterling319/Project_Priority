package com.example.rs.projectpriority;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rs on 11/17/17.
 */

public class NotesTableHelper extends ProjectPriorityDbHelper {

    ProjectPriorityDbHelper mDbHelper;
    SQLiteDatabase db;
    String[] noteID = {"0"};
    String TAG = "NotesTableHelper";

    public  NotesTableHelper(Context context, boolean isTest){
        super(context, isTest);
        mDbHelper = new ProjectPriorityDbHelper(context, isTest);
        db = mDbHelper.getWritableDatabase();
    }

    public void updateNote(String note){
        ContentValues values = new ContentValues();
        values.put(NotesTableContract.NotesEntry.COLUMN_NAME_NOTE, note);
            if(db.update(NotesTableContract.NotesEntry.TABLE_NAME, values, NotesTableContract.NotesEntry.COLUMN_NAME_DUMMY + "  =?",
                    new String[]{"here"}) == 0){
                newNote(note);
            }
    }


    //save data to db
    public void newNote(String note){
        ContentValues values = new ContentValues();
        values.put(NotesTableContract.NotesEntry.COLUMN_NAME_NOTE, note);
        values.put(NotesTableContract.NotesEntry.COLUMN_NAME_DUMMY, "here");
        try{
            db.insert(NotesTableContract.NotesEntry.TABLE_NAME, null, values);
        }
        catch (SQLException e){
            Log.e(TAG, e + "newNote went wrong");
        }
    }

    public String getNote(){
        String retStm=null;
//        String selectQuery ="SELECT * FROM " + NotesTableContract.NotesEntry.TABLE_NAME+ " WHERE 1=1 OR " + NotesTableContract.NotesEntry.COLUMN_NAME_DUMMY
//                + "  =?";
        String selectQuery = "SELECT * FROM " +NotesTableContract.NotesEntry.TABLE_NAME;
        //Cursor c = db.rawQuery(selectQuery,new String[] {"here"});
        Cursor c = db.rawQuery(selectQuery,null);
        Log.e(TAG, "::getNOTE cursor size: "+c.getCount());
        if(c.getCount() == 0){
            return "";
        }else {
            Log.e(TAG, "getNOTE col Name: " + NotesTableContract.NotesEntry.COLUMN_NAME_NOTE);
            Log.e(TAG, "::getNOTE indx: "+c.getColumnIndex(NotesTableContract.NotesEntry.COLUMN_NAME_NOTE));
            int indx = c.getColumnIndex(NotesTableContract.NotesEntry.COLUMN_NAME_NOTE);
            c.moveToFirst();
            retStm = c.getString(1);
            //noteID = c.getString(c.getColumnIndex(NotesTableContract.NotesEntry._ID));
            Log.e(TAG, "::getNOTE retStm: "+ retStm);
            return retStm;
        }

    }


}
