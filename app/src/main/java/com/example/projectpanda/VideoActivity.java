package com.example.projectpanda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView mvideoView;

    MediaController mediaController;
//    private ProgressDialog pd;
//
//    private String streamUrl = "https://www.youtube.com/watch?v=p6Fl4bsgig0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mvideoView = findViewById(R.id.videoView);


        //Video player
        mediaController = new MediaController(this);
        
        mvideoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.pandavideo);

        mediaController.setAnchorView(mvideoView);

        mvideoView.setMediaController(mediaController);

        mvideoView.start();





    }


}
