package com.rommansabbir.remindme.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Remind Me";
    private static final int DB_VER = 1;
    private static final String DB_TABLE = "Notes";
    private static final String DB_COLUMN1 = "ID";
    private static final String DB_COLUMN2 = "Title";
    private static final String DB_COLUMN3 = "Description";

    private static final String SQL_QUERY ="CREATE TABLE "+DB_TABLE+" ( "+DB_COLUMN1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DB_COLUMN2+" TEXT, "+DB_COLUMN3+" TEXT )";
    private static final String DROP_QUERY = "DROP TABLE IF EXISTS "+DB_TABLE;
    private static final String GET_DATA = "SELECT * FROM "+ DB_TABLE;
    private static final String GET_ITEM_ID = "SELECT * FROM "+ DB_TABLE;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    Context context;

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_QUERY);
            Toast.makeText(context, "Database Created Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.d("onCreate", "e");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_QUERY);
            onCreate(db);
            Toast.makeText(context, "Database Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.d("onUpgrade", "e");
        }
    }

    public long insertNotes(String Title, String Description){
        SQLiteDatabase sqLiteDatabase = null;

        try {
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB_COLUMN2, Title);
            contentValues.put(DB_COLUMN3, Description);

            long rowID = sqLiteDatabase.insert(DB_TABLE, null, contentValues);
            return rowID;
        }
        catch (Exception e){
            Toast.makeText(context, "Error: "+e, Toast.LENGTH_SHORT).show();
        }
        finally {
            if (sqLiteDatabase!= null){
                sqLiteDatabase.close();
            }
        }
         return 0;
    }

    public long insertNoteswithReminder(String Title, String Description){
        SQLiteDatabase sqLiteDatabase = null;

        try {
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DB_COLUMN2, Title);
            contentValues.put(DB_COLUMN3, Description);

            long rowID = sqLiteDatabase.insert(DB_TABLE, null, contentValues);
            return rowID;
        }
        catch (Exception e){
            Toast.makeText(context, "Error: "+e, Toast.LENGTH_SHORT).show();
        }
        finally {
            if (sqLiteDatabase!= null){
                sqLiteDatabase.close();
            }
        }
         return 0;
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(GET_DATA, null);
        return cursor;
    }

}
