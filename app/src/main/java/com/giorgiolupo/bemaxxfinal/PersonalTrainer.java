package com.giorgiolupo.bemaxxfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalTrainer extends AppCompatActivity {
    ExercisesDBHandler exercisesDBHandler;
    TextView text;
    Button day1_3, day2_3, day3_3, day1_4, day2_4, day3_4, day4_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pt);

        //widget
        text = findViewById(R.id.testPT);
        day1_3 = findViewById(R.id.day1_3);
        day2_3 = findViewById(R.id.day2_3);
        day3_3 = findViewById(R.id.day3_3);
        day1_4 = findViewById(R.id.day1_4);
        day2_4 = findViewById(R.id.day2_4);
        day3_4 = findViewById(R.id.day3_4);
        day4_4 = findViewById(R.id.day4_4);


        //db oop
        exercisesDBHandler = new ExercisesDBHandler(getApplicationContext());

        //select schedule
        exercisesDBHandler.selectSchedule(day1_3, day2_3, day3_3, day1_4, day2_4, day3_4, day4_4);


    }

    //go to training
    public void goToTraining(View v) {
        Button b = (Button) v;
        String buttonText = b.getText().toString();

        if (buttonText.equals("Traning 1: Chest - Back")) {
            openPopup("Back+Chest");

        } else if (buttonText.equals("Traning 2: Shoulders - Arms")) {
            openPopup("Shoulders+Arms");

        } else if (buttonText.equals("Traning 3: Legs - Abs")) {
              openPopup("Legs+Abs");

        } else if (buttonText.equals("Training: Upper Body")) {
            openPopup("UpperBody");

        } else if (buttonText.equals("Training: Lower Body")) {
            openPopup("LowerBody");

        } else if (buttonText.equals("Training: Upper Body")) {
            openPopup("UpperBody");

        } else if (buttonText.equals("Training: Lower Body")) {
            openPopup("LowerBody");

        }


    }

    private void openPopup(String value) {
        AlertDialog.Builder alertdi = new AlertDialog.Builder(this);
        alertdi.setMessage("Do you want classic training or time-based");
        alertdi.setPositiveButton("Classic", (dialog, which) -> {
            Intent intent = new Intent(getApplicationContext(), DisplayExercise.class);
            intent.putExtra("type_of_exercise", value);
            startActivity(intent);
        });
        alertdi.setNegativeButton("Time-based", (dialog, which) -> {
            Intent intent = new Intent(getApplicationContext(), TimeTraining.class);
            intent.putExtra("type_of_exercise", value);
            startActivity(intent);
        });
        alertdi.create().show();
    }
}







