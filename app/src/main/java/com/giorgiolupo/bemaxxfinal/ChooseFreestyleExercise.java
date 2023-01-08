package com.giorgiolupo.bemaxxfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChooseFreestyleExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_freestyle_exercise);

        //widgets

        //create oop
        Exercise exercise = new Exercise(null, 0, null);

        //recycler
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        MyListAdapter adapter = new MyListAdapter(null, this, "ChooseFreestyleExercise");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
