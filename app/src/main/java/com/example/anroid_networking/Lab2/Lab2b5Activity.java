package com.example.anroid_networking.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anroid_networking.R;

public class Lab2b5Activity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtAdress;
    private Button btnSend;
    private TextView tv_Result;
    String Name, Email, Adress;

    public static final String SEVER_NAME = "http://192.168.0.101/androidnetwork/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2b5);
        edtName = findViewById(R.id.edt1);
        edtEmail = findViewById(R.id.edt2);
        edtAdress = findViewById(R.id.edt3);
        btnSend = findViewById(R.id.btn);
        tv_Result = findViewById(R.id.tv);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = edtName.getText().toString();
                Email = edtEmail.getText().toString();
                Adress = edtAdress.getText().toString();
                Insert_GET insert = new Insert_GET(tv_Result,Name,Email,Adress, Lab2b5Activity.this);
                insert.execute();
            }
        });
    }

    }

