package com.example.anroid_networking.Lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.anroid_networking.R;

public class Lab1b2Activity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT =3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1b2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //SplassScreen
                Intent intent=new Intent(Lab1b2Activity.this,Lab1b22Activity.class);
                startActivity(intent);
            }
        },SPLASH_TIME_OUT);
    }
}