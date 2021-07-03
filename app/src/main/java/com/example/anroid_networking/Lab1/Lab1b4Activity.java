package com.example.anroid_networking.Lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anroid_networking.R;

public class Lab1b4Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt;
    private Button btn,btnback;
    private TextView tv,tv2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1b4);
        edt=findViewById(R.id.edt);
        btn=findViewById(R.id.btn);
        tv=findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);
        btnback=findViewById(R.id.btnback);

        btn.setOnClickListener(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lab1b4Activity.this,MainChinhActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn:
               AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner(this, tv,tv2,edt);
               String sleepTime=edt.getText().toString();
               asyncTaskRunner.execute(sleepTime);
               break;
       }
    }
}