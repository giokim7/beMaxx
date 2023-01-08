package com.giorgiolupo.bemaxxfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DisplayExerciseMyTrainings extends AppCompatActivity {
    TrainingsDBHandler trainingsDBHandler;
    FreeStyleExercise session;
    List<Integer> allExercises;
    List<Integer> trainingPics = new ArrayList<>();
    List<Integer> temp;
    TextView chrono_text, repetitions_text, band_text;
    BandDBHandler bandDBHandler;

    ImageView exercise_pic;
    ImageButton timer, checkbox;

    ExercisesDBHandler exercisesDBHandler;
    Chronometer chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_freestyle);

        //get intent and widgets
        Intent intent = getIntent();
        String training_name = intent.getStringExtra("training_name");

        exercise_pic = findViewById(R.id.exercise_pic);
        timer = findViewById(R.id.timer);
        chrono_text = findViewById(R.id.chrono_text);
        repetitions_text = findViewById(R.id.repetitions_text);
        checkbox = findViewById(R.id.checkbox);
        band_text = findViewById(R.id.band_text);


        //create OOP
        session = new FreeStyleExercise(null, 0, null, trainingPics, this);
        trainingsDBHandler = new TrainingsDBHandler(this);
        allExercises = session.getAllExercises();
        temp = trainingsDBHandler.selectTrainingsExercisesPositions(training_name);
        bandDBHandler = new BandDBHandler(this, training_name, band_text);

        //create list
        createTrainingPicsList();

        //display img
        exercise_pic.setImageResource(trainingPics.get(0));

        //create OOP
        exercisesDBHandler = new ExercisesDBHandler(this);
        chrono = new Chronometer(30000);


        //set text of repetitions to start
        repetitions_text.setText("1");

        //click checkbox
        checkbox.setOnClickListener(v -> {session.changeExercise(exercise_pic);});


    }

    //create list with R.mipmap.i
    private void createTrainingPicsList(){
        for(int position:temp){
            trainingPics.add(allExercises.get(position));
        }
    }
    //start timer
    public void startBreak(View v){
        chrono.startTimer(chrono_text, session, repetitions_text,this, bandDBHandler, "myTrainings");
    }


}