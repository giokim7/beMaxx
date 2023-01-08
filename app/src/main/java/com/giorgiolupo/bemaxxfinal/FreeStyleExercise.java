package com.giorgiolupo.bemaxxfinal;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import java.util.List;

public class FreeStyleExercise extends Exercise {
    List<Integer> trainingPics;
    int counterForList;
    Context context;
    int repetitions;

    public FreeStyleExercise(String type_of_exercise, int repetitions, ImageView imgView, List<Integer> trainingPics, Context context) {
        super(type_of_exercise, repetitions, imgView);
        this.trainingPics = trainingPics;
        this.context = context;
        this.repetitions = repetitions;
    }
    //change Pic
    public void changeExercise(ImageView imgView){
        changeCounter();
        imgView.setImageResource(trainingPics.get(counterForList));

    }

    //private void change counter
    private void changeCounter(){
        counterForList++;
        if(counterForList==trainingPics.size()){
            goToStatisticsPage();
        }

    }

    //go to statistics
    private void goToStatisticsPage(){
        Intent intent = new Intent(context, Statistics.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }








}
