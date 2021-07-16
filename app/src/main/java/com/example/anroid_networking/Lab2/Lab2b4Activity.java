package com.example.anroid_networking.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anroid_networking.Lab1.MainChinhActivity;
import com.example.anroid_networking.MainActivity;
import com.example.anroid_networking.R;

public class Lab2b4Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_NAME="http://192.168.0.101/androidnetwork/loginapp.php";
    TextView tvKetqua,tv2;
    EditText edt1,edt2;
    Button btn;
//    Login_POST login;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2b4);
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        btn=findViewById(R.id.btn);
        tvKetqua=findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);
        btn.setOnClickListener(this);

        tv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                user=edt1.getText().toString();
                pass=edt2.getText().toString();
                Login_POST atp=new Login_POST(this,tvKetqua);
                atp.execute(user,pass);
                break;
            case R.id.tv2:
                Intent i=new Intent(Lab2b4Activity.this,Lab2b5Activity.class);
                startActivity(i);

        }

    }
}