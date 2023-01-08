package com.giorgiolupo.bemaxxfinal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Statistics extends AppCompatActivity {
    TextView statistics_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        //widgets
        statistics_text = findViewById(R.id.statistics_text);

        //create oop
        StatisticsDBHandler statisticsDBHandler = new StatisticsDBHandler(this);
        statisticsDBHandler.updateCounter();

        statistics_text.setText("completed trainings: " + statisticsDBHandler.selectCounter());
        openPopupStore();


    }

    private void openPopupStore() {
        AlertDialog.Builder alertdi = new AlertDialog.Builder(this);
        alertdi.setMessage("Check our store for new products!");
        alertdi.setPositiveButton("Check Store", (dialog, which) -> {
            Intent intent = new Intent(this, OnlineShop.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        });
            alertdi.setNegativeButton("Not now", (dialog, which) -> {});
            alertdi.create().show();
    }
}
