package com.giorgiolupo.bemaxxfinal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MyTrainings extends AppCompatActivity {
    TrainingsDBHandler trainingsDBHandler;
    List<String> myTrainings;

    ListView l;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_trainings);

        l = findViewById(R.id.list);

        trainingsDBHandler = new TrainingsDBHandler(this);
        myTrainings = trainingsDBHandler.selectTrainings();
        ArrayAdapter<String> arr;

        arr = new ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                myTrainings);
        l.setAdapter(arr);

        l.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            //create and initialize an intent
            Intent intent = new Intent(this, DisplayExerciseMyTrainings.class);
            intent.putExtra("training_name", selectedItem);
            startActivity(intent);
            });

        //eliminate
        l.setOnItemLongClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            openPopupDelete(selectedItem);
            return true;
        });


    }

    //open popup delecte
    private void openPopupDelete(String selectedItem){
        AlertDialog.Builder alertdi = new AlertDialog.Builder(this);
        alertdi.setMessage("Do you want to delete this training?");
        alertdi.setPositiveButton("Yes", (dialog, which) ->
                trainingsDBHandler.deleteTraining(selectedItem));

        alertdi.setNegativeButton("No", (dialog, which) -> {});
        alertdi.create().show();

    }
}


