package com.giorgiolupo.bemaxxfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TimeTraining extends AppCompatActivity {
    EditText time_training_edit;
    Integer time_training;
    String type_of_exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_training);

        //get widgets and variables
        time_training_edit = findViewById(R.id.time_training_edit);

        Intent intent = getIntent();
        type_of_exercise = intent.getStringExtra("type_of_exercise");


    }

    //go to start
    public void goToTraining(View v) {
        if (time_training_edit.getText().toString().length() != 0)
            time_training = Integer.parseInt(time_training_edit.getText().toString());
        //intent
        Intent intent = new Intent(getApplicationContext(), TimeTrainingExercise.class);
        intent.putExtra("training_time", time_training);
        intent.putExtra("type_of_exercise", type_of_exercise);
        startActivity(intent);
    }


}