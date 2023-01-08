package com.giorgiolupo.bemaxxfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class Question2DBHandler extends Question1DBHandler {

    public Question2DBHandler(Context context) {
        super(context);
    }


    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS PTTimesPerWeek(id INTEGER PRIMARY KEY AUTOINCREMENT, times_per_week INTEGER)";
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addAnswerToDB(Integer answer) {
        SQLiteDatabase db = this.getWritableDatabase();

        onCreate(db);

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        values.put("times_per_week", answer);

        // after adding all values we are passing
        // content values to our table.
        db.insert("PTTimesPerWeek", null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS PTTimesPerWeek");
        onCreate(db);
    }

    public void getResult(TextView text){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT times_per_week FROM PTTimesPerWeek", null);
        if (c.moveToFirst()) {
            String column1 = c.getString(0);
            text.setText("" + column1);
        }

    }





}
