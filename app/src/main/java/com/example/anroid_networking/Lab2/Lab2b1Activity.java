package com.example.anroid_networking.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anroid_networking.R;

public class Lab2b1Activity extends AppCompatActivity implements View.OnClickListener {
//    private EditText edt1,edt2;
//    private Button btn;
//    private TextView tv;
    String link;
    TextView tvKQ;
    EditText edt1,edt2;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2b1);
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        btn=findViewById(R.id.btn);
        tvKQ=findViewById(R.id.tvKQ);

        link="http://192.168.0.101/androidnetwork/student_Get.php";

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        BackgroundTask_GET backgroundTask_get=new BackgroundTask_GET(this,link,edt1.getText().toString(),edt2.getText().toString(),tvKQ);
        backgroundTask_get.execute();

    }
}