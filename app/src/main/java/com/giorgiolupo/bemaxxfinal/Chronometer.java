package com.giorgiolupo.bemaxxfinal;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.widget.TextView;

public class Chronometer {
    int milliseconds, repetitions=1;
    CountDownTimer timer;
    boolean isRunning = false;


    public Chronometer(int milliseconds){
        this.milliseconds = milliseconds;
    }

    public void startTimer(TextView chrono_text , Exercise session, TextView repetitions_text, Context context, BandDBHandler bandDBHandler, String exercise_type){
        if (isRunning) {
            resetTimer();
        }
        isRunning = true;
        timer = new CountDownTimer(milliseconds, 1000) {
            public void onTick(long millisUntilFinished) {
                chrono_text.setText( DateUtils.formatElapsedTime(millisUntilFinished / 1000));


            }

            public void onFinish() {
                switch (exercise_type) {
                    case "displayExercise":
                        isRunning = false;
                        chrono_text.setText("");
                        session.switchExercise(repetitions_text, context, bandDBHandler);
                        session.addRepetition(repetitions_text);
                        break;
                    case "myTrainings":
                        isRunning = false;
                        repetitions++;
                        repetitions_text.setText("" + repetitions);
                        chrono_text.setText("");
                        break;
                    case "timeTraining":
                        session.setRandomExTime(context);
                        startTimer(chrono_text, session, null, context, null, "timeTraining");
                        break;
                    case "timeTrainingTotal":
                        Intent intent = new Intent(context, Statistics.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case "null": break;
                }
            }
        }.start();
    }

    private void resetTimer() {
        //session.switchExercise(imgView);
      //in case wanna click the timer again, sessions will increase  session.addRepetition();
        timer.cancel();
    }




}
