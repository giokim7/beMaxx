package com.giorgiolupo.bemaxxfinal;
/*
this class registers the first answer of the user
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Question1 extends AppCompatActivity {
    TextView title;
    Question1DBHandler dbQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);

        //widgets
        title = findViewById(R.id.question_title);

        //create OOP
        dbQuestions = new Question1DBHandler(getApplicationContext());

    }

    //register answers into DB
    public void muscleToningRegisterAnswer(View v){
        dbQuestions.addAnswerToDB(15);
        changePage();
    }

    public void starkGainRegisterAnswer(View v){
        dbQuestions.addAnswerToDB(8);
        changePage();
    }

    public void maxStarkRegisterAnswer(View v){
        dbQuestions.addAnswerToDB(5);
        changePage();
    }

    private void changePage(){
        Intent intent = new Intent(getApplicationContext(), Question2.class);
        startActivity(intent);
    }




}
