package com.giorgiolupo.bemaxxfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBHandler db = new DBHandler(getApplicationContext());



    }

    //change page
    public void changePage(View v){

        Intent intent = new Intent(getApplicationContext(), Question1.class);
        startActivity(intent);


    }


}