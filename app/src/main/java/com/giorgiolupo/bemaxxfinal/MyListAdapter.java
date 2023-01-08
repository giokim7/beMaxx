package com.giorgiolupo.bemaxxfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    Exercise exercise = new Exercise(null, 0, null);

    List<Integer> allExercises = exercise.getAllExercises();
    List<String> chosenExercises = new ArrayList<>();
    TrainingsDBHandler trainingsDBHandler;
    String training_name, activity_name;
    Context context;

    // RecyclerView recyclerView;
    public MyListAdapter(String training_name, Context context, String activity_name) {
        this.training_name = training_name;
        trainingsDBHandler = new TrainingsDBHandler(context);
        this.activity_name = activity_name;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //set list elements
        holder.textView.setText(exerciseText(position));
        holder.imageView.setImageResource(R.mipmap.abs1);

        if (activity_name.equals("CreateYourTraining")) {
            holder.relativeLayout.setOnClickListener(view -> {
                chosenExercises.add(exerciseText(position));
                openPopupWithChosen();
                trainingsDBHandler.insertExerciseIntoTable(training_name, position);
            });

        } else if (activity_name.equals("ChooseFreestyleExercise")) {
            holder.relativeLayout.setOnClickListener(view -> {
                goToExercisePage(position);
            });
        }


    }

    //check where pic belongs to and write description
    private String exerciseText(int position) {
        if (position <= 13) return "Back" + position;
        if (position > 13 && position <= 26) return "Chest" + position;
        return null;
    }

    //private goToPage and displau exercise
    private void goToExercisePage(int position){
        Intent intent = new Intent(context, DisplayExerciseFreestyle.class);
        intent.putExtra("position", position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //open opoup with chosen exercise
    private void openPopupWithChosen(){
        AlertDialog.Builder alertdi = new AlertDialog.Builder(context);
        alertdi.setMessage("The added exercises are: " + chosenExercises);
        alertdi.create().show();
    }

    @Override
    public int getItemCount() {
        return allExercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}