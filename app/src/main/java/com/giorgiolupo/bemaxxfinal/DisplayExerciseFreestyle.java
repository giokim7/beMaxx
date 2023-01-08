package com.giorgiolupo.bemaxxfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DisplayExerciseFreestyle extends AppCompatActivity {
    FreeStyleExercise session;
    List<Integer> allExercises;
    List<Integer> trainingPics = new ArrayList<>();
    List<Integer> temp;
    TextView chrono_text, repetitions_text, band_text;

    ImageView exercise_pic;
    ImageButton timer, checkbox;

    ExercisesDBHandler exercisesDBHandler;
    Chronometer chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_freestyle);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //get intent and widgets
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        exercise_pic = findViewById(R.id.exercise_pic);
        timer = findViewById(R.id.timer);
        chrono_text = findViewById(R.id.chrono_text);
        repetitions_text = findViewById(R.id.repetitions_text);
        checkbox = findViewById(R.id.checkbox);
        band_text = findViewById(R.id.band_text);


        //create OOP
        session = new FreeStyleExercise(null, 0, null, trainingPics, this);
        allExercises = session.getAllExercises();

        //display img
        exercise_pic.setImageResource(allExercises.get(position));

        //create OOP
         chrono = new Chronometer(3000);


        //set text of repetitions to start
        repetitions_text.setText("1");

        //click checkbox
        checkbox.setOnClickListener(v -> {});


    }


    //start timer
    public void startBreak(View v){
        chrono.startTimer(chrono_text, session, repetitions_text,this, null, "myTrainings");
    }


}