package com.example.in0418gq.scrapbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private Button mNote;
    private Button mPicture;

    private static int TAKE_PICTURE = 0;
    private String mImageFilename = "temp";
    private  Uri mImageFileUri;

    @Override
    public void onSaveInstanceState(Bundle outBundle){
        outBundle.putBoolean(PICTURE_TO_DISPLAY,mIsPictureToDisplay);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mIsPictureToDisplay = savedInstanceState.getBoolean(PICTURE_TO_DISPLAY,false);
        }

        mPicture = (Button) findViewById(R.id.take_a_picture_btn);
        mPicture.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               if (pictureIntent.resolveActivity(getPackageManager())!=null){

                   File imageFile = new File(Environment.getExternalStorageDirectory(),mImageFilename);
                   Uri uri = Uri.fromFile(imageFile);
                   pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,mImageFileUri);
                   startActivityForResult(pictureIntent,TAKE_PICTURE);
               } else {
                   Toast.makeText(MainActivity.this,"Your device does not have a camera",
                           Toast.LENGTH_SHORT).show();
               }

           }
        });
    }



}
