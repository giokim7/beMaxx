package com.giorgiolupo.bemaxxfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class Question1DBHandler extends DBHandler {
    SQLiteDatabase db = this.getWritableDatabase();


    public Question1DBHandler(Context context) {
        super(context);
    }


    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS PTrepetitions(id INTEGER PRIMARY KEY AUTOINCREMENT, repetitions INTEGER)";
        db.execSQL(query);
    }

    //check if DB is empty
    public boolean checkIfEmpty(){
        String count = "SELECT count(*) FROM PTrepetitions";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount!=0) {
           return true; //is not
        } else{
            return false;
        }
    }

    // this method is use to add new course to our sqlite database.
    public void addAnswerToDB(Integer answer) {

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        values.put("repetitions", answer);

        // after adding all values we are passing
        // content values to our table.
        db.insert("PTrepetitions", null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS PTrepetitions");
        onCreate(db);
    }

    public void getResult(TextView text){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT repetitions FROM PTrepetitions", null);
        if (c.moveToFirst()) {
            String column1 = c.getString(0);
            text.setText("" + column1);
        }

    }





}
