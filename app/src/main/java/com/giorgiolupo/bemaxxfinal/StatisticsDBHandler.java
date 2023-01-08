package com.giorgiolupo.bemaxxfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StatisticsDBHandler extends DBHandler {
    SQLiteDatabase db = this.getWritableDatabase();


    public StatisticsDBHandler(Context context) {
        super(context);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS statistics(id INTEGER PRIMARY KEY AUTOINCREMENT, counter INTEGER)";
        db.execSQL(query);

        //check if empty
        String count = "SELECT count(*) FROM statistics";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount==0) db.execSQL("INSERT INTO statistics(counter) VALUES(0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public void updateCounter(){
        onCreate(db);
        db.execSQL("UPDATE statistics SET counter=counter+1");
    }

    public int selectCounter(){
        Cursor c = db.rawQuery("SELECT counter FROM statistics ORDER by id DESC LIMIT 1", null);
        if (c.moveToFirst()) {
            int column1 = c.getInt(0);
            return column1;
        }
        return -1;
    }



}
