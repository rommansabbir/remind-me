package com.rommansabbir.remindme;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rommansabbir.remindme.Database.DBHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNewNote;
    private DBHelper dbHelper;
    private ListView notesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        onCreatePopulate();
        getNotesfromDB();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddNewNote) {
            try {
                Intent intent = new Intent(HomeActivity.this, NewNoteActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void onCreatePopulate () {
        this.btnNewNote = findViewById(R.id.btnAddNewNote);
        this.btnNewNote.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();

    }

    public void getNotesfromDB(){
        dbHelper = new DBHelper(this);
        this.notesListView = findViewById(R.id.notesListView);

        try{
            ArrayList<String> notesList = new ArrayList<>();
            Cursor cursor = dbHelper.getData();

            if (cursor.getCount() == 0){
                Toast.makeText(this, "Nothing in Database", Toast.LENGTH_SHORT).show();
            }
            else {
                while (cursor.moveToNext()){
                    notesList.add("Title: "+cursor.getString(1));
                    notesList.add("Description: "+cursor.getString(2));
                    final ListAdapter listAdapter = new ArrayAdapter<String>(this,R.layout.noteslistview, notesList);
                    notesListView.setAdapter(listAdapter);

                    notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        }
                    });
                }
            }
        }
        catch (Exception e){
            Log.d("getNotesfromDB", "e");
        }
    }
}
