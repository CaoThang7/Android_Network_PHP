package com.example.anroid_networking.mysql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anroid_networking.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MySQLActivity extends AppCompatActivity {
    EditText Email,Password;
    Button register,login;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_s_q_l);
        Email=findViewById(R.id.log_email);
        Password=findViewById(R.id.log_password);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        register=findViewById(R.id.btnRegister);
        login=findViewById(R.id.btnLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegistrationProccess();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLoginProccess();
            }
        });
    }

    private void UserLoginProccess() {
        String email=Email.getText().toString().trim();
        String password=Password.getText().toString().trim();
        if(email.isEmpty() || password.isEmpty() ){
            massage("Some fields are empty");
        }else {
            progressDialog.dismiss();
            StringRequest request=new StringRequest(Request.Method.POST, Urls.LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String result=jsonObject.getString("status");
                                if(result.equals("Success")){
                                    progressDialog.dismiss();
                                    massage("User Login Success!");
                                }else {
                                    progressDialog.dismiss();
                                    massage("Login error!");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                      progressDialog.dismiss();
                      massage(error.getMessage());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("email",email);
                    params.put("password",password);
                    return params;
                }
            };
            RequestQueue queue= Volley.newRequestQueue(MySQLActivity.this);
            queue.add(request);
        }
    }

    private void UserRegistrationProccess(){
        LayoutInflater inflater=getLayoutInflater();
        View register_layout= inflater.inflate(R.layout.register_layout,null);
       final EditText Name=register_layout.findViewById(R.id.reg_name);
       final EditText Email=register_layout.findViewById(R.id.reg_email);
       final EditText Phone=register_layout.findViewById(R.id.reg_phone);
       final EditText Password=register_layout.findViewById(R.id.reg_password);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(register_layout);
        builder.setTitle("Registration");
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                progressDialog.show();
                final String name=Name.getText().toString().trim();
                final String email=Email.getText().toString().trim();
                final String phone=Phone.getText().toString().trim();
                final String password=Password.getText().toString().trim();

                if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){
                    massage("some feilds are empty...");
                    progressDialog.dismiss();
                }else {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                               massage(response);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
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
                    RequestQueue queue= Volley.newRequestQueue(MySQLActivity.this);
                    queue.add(stringRequest);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();

    }

    public void massage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_LONG).show();
    }



}