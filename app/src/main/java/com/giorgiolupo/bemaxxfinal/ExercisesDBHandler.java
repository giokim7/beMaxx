package com.giorgiolupo.bemaxxfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;

public class ExercisesDBHandler extends DBHandler {
    SQLiteDatabase db = this.getWritableDatabase();


    public ExercisesDBHandler(Context context) {
        super(context);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void selectSchedule(Button one_3, Button two_3, Button three_3, Button one_4, Button two_4, Button three_4, Button four_4) {
        int timesPerWeek = selectTimesPerWeek();

        //show graphics
        if(timesPerWeek==3){
            one_3.setVisibility(View.VISIBLE);
            two_3.setVisibility(View.VISIBLE);
            three_3.setVisibility(View.VISIBLE);
        } else
        if(timesPerWeek==4){
            one_4.setVisibility(View.VISIBLE);
            two_4.setVisibility(View.VISIBLE);
            three_4.setVisibility(View.VISIBLE);
            four_4.setVisibility(View.VISIBLE);
        }



    }

    //select repetitions
    public int selectRepetitions(){
        Cursor c = db.rawQuery("SELECT repetitions FROM PTrepetitions ORDER BY ID DESC LIMIT 1", null);
        if (c.moveToFirst()) {
            Integer column1 = c.getInt(0);
            return column1;
        }
        return -1;
    }

    //select goal
    private int selectTimesPerWeek(){
        Cursor c = db.rawQuery("SELECT times_per_week FROM PTTimesPerWeek ORDER BY ID DESC LIMIT 1", null);
        if (c.moveToFirst()) {
            Integer column1 = c.getInt(0);
            return column1;
        }
        return -1;
    }








}
