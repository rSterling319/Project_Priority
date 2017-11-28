package com.example.rs.projectpriority;

import android.arch.lifecycle.ViewModelProviders;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by rs on 11/16/17.
 */

public class NotesActivity extends AppCompatActivity {

    EditText editText;
    NotesTableHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
    }

    @Override
    protected void onResume(){
        super.onResume();

        dbHelper = new NotesTableHelper(this, false);

        editText = (EditText) findViewById(R.id.notes);

        editText.setText(dbHelper.getNote());
    }


    @Override
    protected void onPause() {
        super.onPause();
        dbHelper.updateNote(editText.getText().toString());
        dbHelper.close();
    }

}
