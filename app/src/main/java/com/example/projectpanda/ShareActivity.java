package com.example.projectpanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
                Drawable myDrawable = mred_pandaIV.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)myDrawable).getBitmap();

                //sharing intent


                try {
                    File file = new File(ShareActivity.this.getExternalCacheDir(),"red_panda.jpg");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG,80,fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true,false);
                    //sharing intent
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(),getPackageName()+".fileprovider",file));
                    intent.setType("red_panda.jpg");
                    startActivity(Intent.createChooser(intent,"Share Image Via"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ShareActivity.this,"File not found",Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
