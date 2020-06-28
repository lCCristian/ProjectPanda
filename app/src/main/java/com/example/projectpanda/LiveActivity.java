package com.example.projectpanda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class LiveActivity extends YouTubeBaseActivity {

    private static final String TAG = "LiveActivity";

    YouTubePlayerView mYoutubePlayerView;
    Button youtubeButton;
    YouTubePlayer.OnInitializedListener mOnInitializerListener;


    String videoURL = "https://www.youtube.com/watch?v=p6Fl4bsgig0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        Log.d(TAG, "onCreate: Starting...");

      youtubeButton = findViewById(R.id.youtubeButton);
      mYoutubePlayerView = findViewById(R.id.youtubePlay);

      mOnInitializerListener = new YouTubePlayer.OnInitializedListener() {
          @Override
          public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

              Log.d(TAG, "onClick: Done initializing");
              List<String> videoList = new ArrayList<>();
              videoList.add("p6Fl4bsgig0");
              videoList.add("Gm3bQVANtVo");
              youTubePlayer.loadVideos(videoList);
          }

          @Override
          public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
              Log.d(TAG, "onClick: Failed to initialize");
          }
      };

    youtubeButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: Initializing YouTube Player");
            mYoutubePlayerView.initialize(YouTubeConfig.getApiKey(),mOnInitializerListener);
        }
    });

    }

}