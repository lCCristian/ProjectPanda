package com.example.projectpanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        final ImageView mred_pandaIV = findViewById(R.id.red_pandaIV);
        Button mshareImageBtn = findViewById(R.id.shareImgBtn);


        //share image on button click
        mshareImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get image as bitmap from ImageView
               // Drawable myDrawable = mred_pandaIV.getDrawable();
                //Bitmap bitmap = ((BitmapDrawable) myDrawable).getBitmap();

                //sharing intent

                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ShareActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                    return;
                }

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_panda);
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/LatestShare.jpg";
                OutputStream out = null;
                File file = new File(path);
                try {
                    out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                path = file.getPath();
                Uri bmpUri = Uri.parse("file://" + path);
                Intent shareIntent = new Intent();
                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                shareIntent.setType("image/jpg");
                startActivity(Intent.createChooser(shareIntent, "Share with"));
            }
        });
    }
}
