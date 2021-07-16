package com.example.anroid_networking.Lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.anroid_networking.R;

public class Lab3b1MainActivity extends AppCompatActivity {
    private ListView lvFashion;
    GetFashion getFashion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3b1_main);
        lvFashion=findViewById(R.id.listFashion);

        getFashion=new GetFashion(this, lvFashion);
        getFashion.execute();
    }
}