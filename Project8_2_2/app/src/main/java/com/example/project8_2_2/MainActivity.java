package com.example.project8_2_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    myPictureView myPicture;
    int curNum;
    File[] imageFiles;
    String imageFname;
    TextView tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        myPicture = (myPictureView) findViewById(R.id.myPictureView1);
        tvNumber = (TextView) findViewById(R.id.tvNumber);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.getName().startsWith(".");
            }
        });

        imageFname = imageFiles[0].toString();
        myPicture.imgPath = imageFname;
        tvNumber.setText("1" + "/" + imageFiles.length);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum <= 0) {
                    curNum = imageFiles.length - 1;
                }
                else {
                    curNum--;
                }
                imageFname = imageFiles[curNum].toString();
                myPicture.imgPath = imageFname;
                myPicture.invalidate();
                tvNumber.setText((curNum + 1) + "/" + imageFiles.length);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum >= imageFiles.length - 1) {
                    curNum = 0;
                }
                else {
                    curNum++;
                }
                imageFname = imageFiles[curNum].toString();
                myPicture.imgPath = imageFname;
                myPicture.invalidate();
                tvNumber.setText((curNum + 1) + "/" + imageFiles.length);
            }
        });
    }
}