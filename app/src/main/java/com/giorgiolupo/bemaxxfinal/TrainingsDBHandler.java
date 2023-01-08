package com.giorgiolupo.bemaxxfinal;
//class to save personal trainings created by the user

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TrainingsDBHandler extends DBHandler {
    SQLiteDatabase db = this.getWritableDatabase();


    public TrainingsDBHandler(Context context) {
        super(context);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS allTrainings(id INTEGER PRIMARY KEY AUTOINCREMENT, training_name VARCHAR)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createDB() {
        onCreate(db);
    }

    public void insertTrainingInTableAndCreate(String training_name) {
        db.execSQL("INSERT INTO allTrainings(training_name) VALUES('" + training_name + "' )");
        createTableTraining(training_name);
    }

    public void createTableTraining(String training_name) {
        String query = "CREATE TABLE IF NOT EXISTS '" + training_name + "'(id INTEGER PRIMARY KEY AUTOINCREMENT, exercise_pic_position INTEGER)";
        db.execSQL(query);
    }



    //insert clicked recycler view into DB
    public void insertExerciseIntoTable(String training_name, int position) {
        db.execSQL("INSERT INTO '" + training_name + "'(exercise_pic_position) VALUES('" + position + "' )");
    }



    //show buttons for trainings
    public List selectTrainings() {
        List<String> temp = new ArrayList<>();

        //check if empty
        String count = "SELECT count(*) FROM allTrainings";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount!=0) {
            Cursor allRows  = db.rawQuery("SELECT training_name FROM allTrainings", null);
            if (allRows.moveToFirst() ){
                String[] columnNames = allRows.getColumnNames();
                do {
                    for (String name: columnNames) {
                        temp.add(allRows.getString(allRows.getColumnIndex(name)));
                    }

                } while (allRows.moveToNext());
            }

        } else {
            
        }
        return temp;

    }


    //select positions from DB
    public List selectTrainingsExercisesPositions(String training_name) {
        List<Integer> temp = new ArrayList<>();
        Cursor allRows  = db.rawQuery("SELECT exercise_pic_position FROM '" + training_name + "'", null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    temp.add(allRows.getInt(allRows.getColumnIndex(name)));
                }

            } while (allRows.moveToNext());
        }

        return temp;
    }

    //delete trainig
    public void deleteTraining(String selectedItem){
        db.execSQL("DELETE FROM allTrainings WHERE training_name = '" + selectedItem + "'");
    }


}
