package com.giorgiolupo.bemaxxfinal;
/*
this class registers the first answer of the user
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Question2 extends AppCompatActivity {
    Question2DBHandler dbQuestions;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question2);

        test = findViewById(R.id.test);
        //create OOP
        dbQuestions = new Question2DBHandler(getApplicationContext());

    }

    //register answers into DB
    public void fourTimesRegisterAnswer(View v){
        dbQuestions.addAnswerToDB(4);
        changePage();
    }

    public void threeTimesRegisterAnswer(View v){
        dbQuestions.addAnswerToDB(3);
        changePage();
    }

    //chamge page
    private void changePage(){
        Intent intent = new Intent(getApplicationContext(), PersonalTrainer.class);
        startActivity(intent);
    }






}
