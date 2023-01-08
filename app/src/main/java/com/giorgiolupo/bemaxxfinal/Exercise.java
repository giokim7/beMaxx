package com.giorgiolupo.bemaxxfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Exercise {
    List<Integer> backPics = Arrays.asList(R.mipmap.back2, R.mipmap.back1, R.mipmap.back3, R.mipmap.back4, R.mipmap.back5, R.mipmap.back7, R.mipmap.back8, R.mipmap.back9, R.mipmap.back10, R.mipmap.back11, R.mipmap.back12, R.mipmap.back4, R.mipmap.back9);
    List<Integer> chestPics = Arrays.asList(R.mipmap.chest1, R.mipmap.chest2, R.mipmap.chest4, R.mipmap.chest5, R.mipmap.chest6, R.mipmap.chest7, R.mipmap.chest8, R.mipmap.chest3, R.mipmap.chest3, R.mipmap.chest10, R.mipmap.chest11, R.mipmap.chest7, R.mipmap.chest3);
    List<Integer> armPics = Arrays.asList(R.mipmap.arms1, R.mipmap.arms2, R.mipmap.arms3, R.mipmap.arms4, R.mipmap.arms5, R.mipmap.arms6, R.mipmap.arms7, R.mipmap.arms8, R.mipmap.arms9, R.mipmap.arms10, R.mipmap.arms11, R.mipmap.arms7, R.mipmap.arms3);
    List<Integer> absPics = Arrays.asList(R.mipmap.abs1, R.mipmap.abs2, R.mipmap.abs3, R.mipmap.abs4, R.mipmap.abs5, R.mipmap.abs6, R.mipmap.abs7, R.mipmap.abs8, R.mipmap.abs9, R.mipmap.abs10, R.mipmap.abs11, R.mipmap.abs13, R.mipmap.abs7, R.mipmap.abs5);
    List<Integer> shouldersPics = Arrays.asList(R.mipmap.shoulder1, R.mipmap.shoulder2, R.mipmap.shoulder3, R.mipmap.shoulder4, R.mipmap.shoulder5, R.mipmap.shoulder6, R.mipmap.shoulder3, R.mipmap.shoulder7, R.mipmap.shoulder8, R.mipmap.shoulder9, R.mipmap.shoulder10, R.mipmap.shoulder11, R.mipmap.shoulder12, R.mipmap.shoulder8);
    List<Integer> legsPics = Arrays.asList(R.mipmap.leg1, R.mipmap.leg2, R.mipmap.leg3, R.mipmap.leg4, R.mipmap.leg5, R.mipmap.leg6, R.mipmap.leg7, R.mipmap.leg8, R.mipmap.leg9, R.mipmap.leg10, R.mipmap.leg6, R.mipmap.leg3, R.mipmap.leg9, R.mipmap.leg3);
    List<Integer> upperBodyPics = Arrays.asList(R.mipmap.back2, R.mipmap.arms1, R.mipmap.arms2, R.mipmap.back1, R.mipmap.shoulder1, R.mipmap.arms5, R.mipmap.chest4, R.mipmap.chest5, R.mipmap.arms9, R.mipmap.arms10, R.mipmap.arms11, R.mipmap.chest6, R.mipmap.arms6, R.mipmap.shoulder2, R.mipmap.back3, R.mipmap.back4, R.mipmap.back5);
    List<Integer> lowerBodyPics = Arrays.asList(R.mipmap.leg4,R.mipmap.abs4,R.mipmap.leg8,R.mipmap.abs6,R.mipmap.leg2,R.mipmap.abs11, R.mipmap.leg9,R.mipmap.abs7, R.mipmap.leg3, R.mipmap.abs3, R.mipmap.leg10, R.mipmap.abs3, R.mipmap.leg6, R.mipmap.abs13);
    List<Integer> bemaxxExercises = Arrays.asList(R.mipmap.specialex1, R.mipmap.specialex2, R.mipmap.specialex3, R.mipmap.specialex4, R.mipmap.specialex5);

    Random rand = new Random();
    String type_of_exercise;
    ImageView imgView;
    int repetitions, currentMonth, series = 1, totalExercises = 1, number;

    //construct
    public Exercise(String type_of_exercise, int repetitions, ImageView imgView) {
        this.type_of_exercise = type_of_exercise;
        this.repetitions = repetitions;
        this.imgView = imgView;
    }

    //get lsit
    public List getAllExercises() {
        List<Integer> allExercises = new ArrayList<>();
        allExercises.addAll(backPics);
        allExercises.addAll(chestPics);
        allExercises.addAll(shouldersPics);
        allExercises.addAll(armPics);
        allExercises.addAll(legsPics);
        allExercises.addAll(absPics);
        return allExercises;
    }


    //get current month
    private void getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        currentMonth = c.get(Calendar.MONTH);
    }

    //set img
    public void setImage() {
        getCurrentMonth();

        switch (type_of_exercise) {
            case "Back+Chest":
                imgView.setImageResource(backPics.get(currentMonth));
                break;
            case "Shoulders+Arms":
                imgView.setImageResource(shouldersPics.get(currentMonth));
                break;
            case "Legs+Abs":
                imgView.setImageResource(legsPics.get(currentMonth));
                break;
            case "UpperBody":
                imgView.setImageResource(upperBodyPics.get(currentMonth));
                break;
            case "LowerBody":
                imgView.setImageResource(lowerBodyPics.get(currentMonth));
                break;
        }

    }

    public void addRepetition(TextView repetitions_text) {
        series++;
        if (number < 15) repetitions_text.setText(series + " X " + repetitions);
    }

    public void switchExercise(TextView repetitions_text, Context context, BandDBHandler bandDBHandler) {
        number++;
        if (number >= 15) {
            finishSession(repetitions_text, context, bandDBHandler);
        } else if (series >= 3) {
            bandDBHandler.openPopup(context);
            totalExercises++;
            bandDBHandler.showBand();
            series = 0;

            switch (type_of_exercise) {
                case "Back+Chest":
                    if (totalExercises < 3) {
                        imgView.setImageResource(backPics.get(currentMonth + 1));
                    } else if (totalExercises == 3) {
                        imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    } else if (totalExercises == 4) {
                        imgView.setImageResource(chestPics.get(currentMonth));
                    } else if (totalExercises > 4) {
                        imgView.setImageResource(chestPics.get(currentMonth + 1));
                    }
                    break;
                case "Shoulders+Arms":
                    if (totalExercises < 3) {
                        imgView.setImageResource(shouldersPics.get(currentMonth + 1));
                    } else if (totalExercises == 3) {
                        imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    } else if (totalExercises == 4) {
                        imgView.setImageResource(armPics.get(currentMonth));
                    } else if (totalExercises > 4) {
                        imgView.setImageResource(armPics.get(currentMonth + 1));
                    }
                    break;
                case "Legs+Abs":
                    if (totalExercises < 3) {
                        imgView.setImageResource(legsPics.get(currentMonth + 1));
                    } else if (totalExercises == 3) {
                        imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    } else if (totalExercises == 4) {
                        imgView.setImageResource(absPics.get(currentMonth));
                    } else if (totalExercises > 4) {
                        imgView.setImageResource(absPics.get(currentMonth + 1));
                    }
                    break;
                case "UpperBody":
                    if (totalExercises < 3) {
                        imgView.setImageResource(upperBodyPics.get(currentMonth + 1));
                    } else if (totalExercises == 3) {
                        imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    } else if (totalExercises == 4) {
                        imgView.setImageResource(upperBodyPics.get(currentMonth));
                    } else if (totalExercises > 4) {
                        imgView.setImageResource(upperBodyPics.get(currentMonth + 1));
                    }
                    break;
                case "LowerBody":
                    if (totalExercises < 3) {
                        imgView.setImageResource(lowerBodyPics.get(currentMonth + 1));
                    } else if (totalExercises == 3) {
                        imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    } else if (totalExercises == 4) {
                        imgView.setImageResource(lowerBodyPics.get(currentMonth));
                    } else if (totalExercises > 4) {
                        imgView.setImageResource(lowerBodyPics.get(currentMonth + 1));
                    }
                    break;
            }


        }


    }


    private void finishSession(TextView repetitions_text, Context context, BandDBHandler bandDBHandler) {
        bandDBHandler.openPopup(context);
        repetitions_text.setText("FINISHED");
        imgView.setVisibility(View.INVISIBLE);
        repetitions_text.setOnClickListener(v -> {
            Intent intent = new Intent(context, Statistics.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    public void setRandomExercise() {

        switch (type_of_exercise) {
            case "Back+Chest":
                if (totalExercises < 3) {
                    imgView.setImageResource(backPics.get(rand.nextInt(backPics.size())));
                } else if (totalExercises == 3) {
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                } else {
                    imgView.setImageResource(chestPics.get(rand.nextInt(backPics.size())));
                }
                break;
            case "Shoulders+Arms":
                if (totalExercises < 3) {
                    imgView.setImageResource(shouldersPics.get(rand.nextInt(shouldersPics.size())));
                } else if (totalExercises == 3) {
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                } else {
                    imgView.setImageResource(armPics.get(rand.nextInt(armPics.size())));
                }
                break;
            case "Legs+Abs":
                if (totalExercises < 3) {
                    imgView.setImageResource(legsPics.get(rand.nextInt(legsPics.size())));
                } else if (totalExercises == 3) {
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                } else {
                    imgView.setImageResource(absPics.get(rand.nextInt(absPics.size())));
                }
                break;
            case "UpperBody":
                if (totalExercises < 3) {
                    imgView.setImageResource(upperBodyPics.get(rand.nextInt(upperBodyPics.size())));
                } else if (totalExercises == 3) {
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                } else
                    imgView.setImageResource(upperBodyPics.get(rand.nextInt(upperBodyPics.size())));
                break;
            case "LowerBody":
                if (totalExercises < 3) {
                    imgView.setImageResource(lowerBodyPics.get(rand.nextInt(lowerBodyPics.size())));
                } else if (totalExercises == 3) {
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                } else {
                    imgView.setImageResource(lowerBodyPics.get(rand.nextInt(lowerBodyPics.size())));
                }
                break;


        }


    }

    //set random time based
    public void setRandomExTime(Context context){
        int n = rand.nextInt(4);
        
        switch (type_of_exercise) {
            case "Back+Chest":
                if(n==1)imgView.setImageResource(backPics.get(rand.nextInt(backPics.size())));
                if(n==2)imgView.setImageResource(chestPics.get(rand.nextInt(chestPics.size())));
                if(n==3){
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    openPopupStore(context);
                }
                break;
            case "Shoulders+Arms":
                if(n==1)imgView.setImageResource(shouldersPics.get(rand.nextInt(shouldersPics.size())));
                if(n==2)imgView.setImageResource(armPics.get(rand.nextInt(armPics.size())));
                if(n==3){
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    openPopupStore(context);
                }
                break;
            case "Legs+Abs":
                if(n==1)imgView.setImageResource(legsPics.get(rand.nextInt(legsPics.size())));
                if(n==2)imgView.setImageResource(absPics.get(rand.nextInt(absPics.size())));
                if(n==3){
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    openPopupStore(context);
                }
                break;
            case "UpperBody":
                if((n==1) || (n==2))imgView.setImageResource(upperBodyPics.get(rand.nextInt(upperBodyPics.size())));
                if(n==3){
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    openPopupStore(context);
                }
                break;
            case "LowerBody":
                if((n==1) || (n==2))imgView.setImageResource(lowerBodyPics.get(rand.nextInt(lowerBodyPics.size())));
                if(n==3){
                    imgView.setImageResource(bemaxxExercises.get(rand.nextInt(bemaxxExercises.size())));
                    openPopupStore(context);
                }
                break;
        }

    }

    public void openPopupStore(Context context){
        AlertDialog.Builder alertdi = new AlertDialog.Builder(context);
        alertdi.setMessage("You do not have this product? Check our store!");
        alertdi.setPositiveButton("Check Store", (dialog, which) -> {
            Intent intent = new Intent(context, OnlineShop.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        alertdi.setNegativeButton("Not now", (dialog, which) -> {});
        alertdi.create().show();
    }


}
