package com.giorgiolupo.bemaxxfinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayer extends AppCompatActivity  implements MediaPlayer.OnCompletionListener {
    VideoView vw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

        vw = findViewById(R.id.videoView);

        //Creating MediaController
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(vw);

        vw.setMediaController(new MediaController(this));
        vw.setOnCompletionListener(this);

        // video name should be in lower case alphabet.
       // setVideo(R.raw.bemaxx);
    }

    public void setVideo(int id)
    {
        String uriPath
                = "android.resource://"
                + getPackageName() + "/" + id;
        Uri uri = Uri.parse(uriPath);
        vw.setVideoURI(uri);
        vw.start();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        ///check if DB is empty
        Question1DBHandler question1DBHandler = new Question1DBHandler(this);
        if(question1DBHandler.checkIfEmpty()){
            Intent intent = new Intent(getApplicationContext(), PersonalTrainer.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), Question1.class);
            startActivity(intent);
        }


    }
}
