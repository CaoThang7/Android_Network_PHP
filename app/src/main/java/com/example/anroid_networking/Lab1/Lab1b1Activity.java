package com.example.anroid_networking.Lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anroid_networking.R;

import java.io.IOException;
import java.net.URL;

public class Lab1b1Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn,btnback;
    private TextView tv;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1b1);
        btn=findViewById(R.id.btn);
        tv=findViewById(R.id.tv);
        img=findViewById(R.id.img);
        btnback=findViewById(R.id.btnback);

        btn.setOnClickListener(this);

        btnback.setOnClickListener(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lab1b1Activity.this,MainChinhActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
        //Su kien click btn
        final Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap=loadImg("https://cdn1.tuoitre.vn/zoom/80_50/2021/6/28/img5278-16248945915451147865614-crop-16248946420781468525756.jpg");
                img.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Success!");
                        img.setImageBitmap(bitmap);

                    }
                });
            }
        });
        //start
        thread.start();

    }
    //ham load anh tu server
    private Bitmap loadImg(String link){
        URL url;
        Bitmap bitmap= null;
        try{
            url=new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch (IOException e){
             e.printStackTrace();
        }
        return bitmap;
    }
}