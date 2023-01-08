package com.giorgiolupo.bemaxxfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class BandDBHandler extends DBHandler {
    SQLiteDatabase db = this.getWritableDatabase();
    String type_of_exercise;
   TextView band_text;
   int totalExercises=0;


    public BandDBHandler(Context context, String type_of_exercise, TextView band_text) {
        super(context);
        this.type_of_exercise = type_of_exercise;
        this.band_text = band_text;

    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS '" + type_of_exercise + "'(exerciseNo INTEGER PRIMARY KEY AUTOINCREMENT, bandNo INTEGER)";
        db.execSQL(query);
    }

    private void checkDbIsEmpty() {
        Cursor mcursor = db.rawQuery("SELECT count(*) FROM '" + this.type_of_exercise + "'", null);
        mcursor.moveToFirst();
        if (mcursor.getInt(0) == 0) {
            insertExNoAndBandInDb();
        }
    }

    private void insertExNoAndBandInDb() {
        for (int i = 1; i <= 4; i++) {
            db.execSQL("INSERT INTO '" + this.type_of_exercise + "'(bandNo) VALUES(1)");
        }
    }

    public void updateBandNoInDb() {
        int totalMinus = totalExercises-1;
        db.execSQL("UPDATE '" + this.type_of_exercise + "' SET bandNo=bandNo+1 WHERE exerciseNo='" + totalMinus + "'");
    }

     public void openPopup(Context context) {
        if(totalExercises==3){ //based on progtram
            AlertDialog.Builder alertdi = new AlertDialog.Builder(context);
            alertdi.setMessage("You do not have this product? Check our store!");
            alertdi.setPositiveButton("Check Store", (dialog, which) -> {
                Intent intent = new Intent(context, OnlineShop.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
            alertdi.setNegativeButton("Not now", (dialog, which) -> {});
            alertdi.create().show();
        } else {
            AlertDialog.Builder alertdi = new AlertDialog.Builder(context);
            alertdi.setMessage("Too easy?");
            alertdi.setPositiveButton("Yes", (dialog, which) -> updateBandNoInDb());
            alertdi.setNegativeButton("NO", (dialog, which) -> {});
            alertdi.create().show();
        }

    }


    public void showBand() {
        totalExercises++;
        onCreate(db);
        checkDbIsEmpty();
        Cursor c = db.rawQuery("SELECT bandNo FROM '" + type_of_exercise + "'  WHERE exerciseNo='" + totalExercises + "'", null);
        if (c.moveToFirst()) {
            int bandNo = c.getInt(0);
            band_text.setText("Band Color: " + convertBandNoInColor(bandNo));

        }
    }

    //convert band NO in colors from DB
    public String convertBandNoInColor(int bandNo){
        String color;
        switch (bandNo) {
            case 1:
                color="Yellow";
                break;
            case 2:
                color="Blue";
                break;
            case 3:
                color="Green";
                break;
            case 4:
                color="Grey";
                break;
            default: color="Red";
            break;

        }
        return color;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}