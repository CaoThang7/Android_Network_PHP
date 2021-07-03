package com.example.anroid_networking.Lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anroid_networking.R;

public class Lab1b3Activity extends AppCompatActivity implements View.OnClickListener,Listener {
    private Button btn,btnback;
    private TextView tv;
    private ImageView img;
//    public static  final  String IMAGE_URL= "https://cdn1.tuoitre.vn/zoom/80_50/2021/6/28/img5278-16248945915451147865614-crop-16248946420781468525756.jpg";
    public static  final  String IMAGE_URL= "https://i1-vnexpress.vnecdn.net/2021/06/30/205658775141534431548998457758-5999-4615-1625043426.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=yt78EkBQxUG08sb-MKC8cQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1b3);
        btn=findViewById(R.id.btn);
        tv=findViewById(R.id.tv);
        img=findViewById(R.id.img);
        btnback=findViewById(R.id.btnback);

        btn.setOnClickListener(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lab1b3Activity.this,MainChinhActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View arg0) {
       switch (arg0.getId()){
           case R.id.btn:
               new AsyncTask(this,this).execute(IMAGE_URL);
               break;
       }

    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        img.setImageBitmap(bitmap);
        tv.setText("Image Success!");
    }

    @Override
    public void onError() {
         tv.setText("Error download image");
    }
}