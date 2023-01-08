package com.giorgiolupo.bemaxxfinal;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayExercise extends AppCompatActivity {
    String type_of_exercise;
    int repetitions;
    TextView chrono_text, repetitions_text, band_text;

    ImageView exercise_pic;
    ImageButton timer;

    Exercise session;
    ExercisesDBHandler exercisesDBHandler;
    Chronometer chrono;
    BandDBHandler bandDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        //get variables and widget
        type_of_exercise = getIntent().getStringExtra("type_of_exercise");
        exercise_pic = findViewById(R.id.exercise_pic);
        timer = findViewById(R.id.timer);
        chrono_text = findViewById(R.id.chrono_text);
        repetitions_text = findViewById(R.id.repetitions_text);
        band_text = findViewById(R.id.band_text);

        Toast.makeText(getApplicationContext(), type_of_exercise, Toast.LENGTH_SHORT).show();

        //create OOP
        exercisesDBHandler = new ExercisesDBHandler(DisplayExercise.this);
        chrono = new Chronometer(30000);

        //set repetitions based on questionnaire
        repetitions = exercisesDBHandler.selectRepetitions();

        //create session
        session = new Exercise(type_of_exercise, repetitions, exercise_pic);
        //create band DB e OOP
        bandDBHandler = new BandDBHandler(this, type_of_exercise, band_text);
        //display band
        bandDBHandler.showBand();

        //put pic
        session.setImage();

        //set text of repetitions to start
        repetitions_text.setText("1 x " + repetitions);

        //


    }

    //start timer
    public void startBreak(View v){
        chrono.startTimer(chrono_text, session, repetitions_text,DisplayExercise.this, bandDBHandler, "displayExercise");
    }

    //set random exercise
    public void setRandom(View v){
        session.setRandomExercise();
    }





}