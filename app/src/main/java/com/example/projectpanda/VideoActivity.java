package com.example.projectpanda;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

   ImageView image_arrows;
   Button button_press;
   ObjectAnimator objectAnimator;

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

        image_arrows = findViewById(R.id.image_arrows);
        button_press = findViewById(R.id.button_press);

        objectAnimator = ObjectAnimator.ofFloat(image_arrows,"x",500);

        button_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.setDuration(5000);
                objectAnimator.start();
            }
        });



//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                }
//            });

    }


}
