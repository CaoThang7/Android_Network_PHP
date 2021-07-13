package com.example.anroid_networking.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anroid_networking.R;

public class Lab2b2Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_NAME="http://192.168.0.101/androidnetwork/rectangle_POST.php";
    String strWidth,strLength;
    TextView tvRs;
    EditText edt1,edt2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2b2);
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        btn=findViewById(R.id.btn);
        tvRs=findViewById(R.id.tv);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn:
                strWidth=edt1.getText().toString();
                strLength=edt2.getText().toString();
                BackgroundTask_POST atp=new BackgroundTask_POST(this,strWidth,strLength,tvRs);
                atp.execute();
                break;
        }

    }
}