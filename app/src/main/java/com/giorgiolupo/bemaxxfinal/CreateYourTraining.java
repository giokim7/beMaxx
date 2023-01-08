package com.giorgiolupo.bemaxxfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CreateYourTraining extends AppCompatActivity {
    TrainingsDBHandler trainingsDBHandler;
    EditText training_name_edit;
    String training_name;
    ImageView image;
    Button createBtn;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_your_training);

        //widgets
        training_name_edit = findViewById(R.id.training_name_edit);
        image = findViewById(R.id.image);
        createBtn = findViewById(R.id.create_btn);
        test = findViewById(R.id.test);

        //create oop
        trainingsDBHandler = new TrainingsDBHandler(getApplicationContext());
        trainingsDBHandler.createDB();

        //btn
        createBtn.setOnClickListener(v -> {
            training_name = training_name_edit.getText().toString();
            trainingsDBHandler.insertTrainingInTableAndCreate(training_name);
            //recycler
            RecyclerView recyclerView = findViewById(R.id.recyclerView);

            MyListAdapter adapter = new MyListAdapter(training_name, this, "CreateYourTraining");
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        });



    }



}