package com.giorgiolupo.bemaxxfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimeTrainingExercise extends AppCompatActivity {
    Integer training_time;
    Chronometer chrono_total, chrono_exercise;
    TextView time_left_total, time_left_exercise;
    String type_of_exercise;
    Exercise session;
    ImageView exercise_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_training_exercise);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        //get intent and widgets
        Intent intent = getIntent();
        training_time = intent.getIntExtra("training_time", 0);
        type_of_exercise = intent.getStringExtra("type_of_exercise");

        time_left_total = findViewById(R.id.time_left_total);
        time_left_exercise = findViewById(R.id.time_left_exercise);
        exercise_pic = findViewById(R.id.exercise_pic);

        //create chrono total time
        session = new Exercise(type_of_exercise, 0, exercise_pic);

        chrono_total = new Chronometer(training_time*60000);
        chrono_total.startTimer(time_left_total, session, null,this, null, "timeTrainingTotal");
        //create chrono per exercise
        chrono_exercise = new Chronometer(31000);
        chrono_exercise.startTimer(time_left_exercise, session, null,this, null, "timeTraining");

        //create exercise
        session.setRandomExTime(this);


    }

    //
    public void switchExercise(View v){
        session.setRandomExTime(this);
    }

}