package com.example.anroid_networking.mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anroid_networking.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText Name,Email,Phone,Password;
    Button signup;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name=findViewById(R.id.reg_name);
        Email=findViewById(R.id.reg_email);
        Phone=findViewById(R.id.reg_phone);
        Password=findViewById(R.id.reg_password);
        back=findViewById(R.id.back);
        signup=findViewById(R.id.signup);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this,MySQLActivity.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegistrationProccess();
            }
        });


    }

    private void UserRegistrationProccess(){
        final String name=Name.getText().toString().trim();
        final String email=Email.getText().toString().trim();
        final String phone=Phone.getText().toString().trim();
        final String password=Password.getText().toString().trim();

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){
            massage("Không được bỏ trống!...");
        }else {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            massage(response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    massage(error.getMessage());

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("name",name);
                    params.put("email",email);
                    params.put("phone",phone);
                    params.put("password",password);

                    return params;
                }
            };
            RequestQueue queue= Volley.newRequestQueue(SignUpActivity.this);
            queue.add(stringRequest);
        }
    }

    public void massage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_LONG).show();
    }

}
