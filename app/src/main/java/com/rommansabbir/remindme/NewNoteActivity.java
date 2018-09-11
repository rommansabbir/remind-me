package com.rommansabbir.remindme;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rommansabbir.remindme.Database.DBHelper;

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnNotNow, btnSetReminder;
    private EditText title, description;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        onCreatePopulate();
    }

    public void onCreatePopulate(){
        this.btnNotNow = findViewById(R.id.btnNotNow);
        this.btnNotNow.setOnClickListener(this);

        this.btnSetReminder = findViewById(R.id.btnSetReminder);
        this.btnSetReminder.setOnClickListener(this);

        title = findViewById(R.id.titleEditText);
        description = findViewById(R.id.descriptionEditText);

        this.dbHelper = new DBHelper(this);
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnNotNow){
            try {
                if (title.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Minimum Requirement: Title", Toast.LENGTH_SHORT).show();
                }
                else {
                    this.dbHelper = new DBHelper(this);
                    SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
                    String titleTemp = title.getText().toString();
                    String descTemp = description.getText().toString();
                    this.dbHelper.insertNotes(titleTemp, descTemp);
                    Intent intent = new Intent(NewNoteActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
            catch (Exception e){
                Toast.makeText(this, "Error: "+e, Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.btnSetReminder){
            try{
                Intent intent = new Intent(NewNoteActivity.this, AddReminderActivity.class);
                startActivity(intent);

            }
            catch (Exception e){
                Log.d("onbtnSetReminder", "");
            }
        }
    }
}
