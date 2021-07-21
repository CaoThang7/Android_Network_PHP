package com.example.anroid_networking.mysql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class ProfileUserActivity extends AppCompatActivity {
    EditText Name,Email,Phone;
    Button update,cPass;
    ProgressDialog progressDialog;
    UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        Name=findViewById(R.id.p_name);
        Email=findViewById(R.id.p_email);
        Phone=findViewById(R.id.p_phone);

        userManager=new UserManager(this);
        userManager.checkLogin();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        update=findViewById(R.id.btnUpdate);
        cPass=findViewById(R.id.btnChangePassword);


//        Intent i=getIntent();
//        String mName=i.getStringExtra("name");
//        final String mEmail=i.getStringExtra("email");
//        String mPhone=i.getStringExtra("phone");

        HashMap<String,String> user=userManager.userDatails();
        String mName=user.get(userManager.NAME);
        final String mEmail=user.get(userManager.EMAIL);
        String mPhone=user.get(userManager.PHONE);

        Name.setText(mName);
        Email.setText(mEmail);
        Phone.setText(mPhone);

        cPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View resetpasswordlayout= LayoutInflater.from(ProfileUserActivity.this).inflate(R.layout.change_password,null);
                EditText Oldpass=resetpasswordlayout.findViewById(R.id.edt_old_password);
                EditText Newpass=resetpasswordlayout.findViewById(R.id.edt_new_password);
                EditText ConformPass=resetpasswordlayout.findViewById(R.id.edt_conform_password);

                AlertDialog.Builder builder=new AlertDialog.Builder(ProfileUserActivity.this);
                builder.setTitle("CHANGE PASSWORD");
                builder.setView(resetpasswordlayout);
                builder.setPositiveButton("CHANGE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String oldpassword=Oldpass.getText().toString().trim();
                        String newpassword=Newpass.getText().toString().trim();
                        String conformpassword=ConformPass.getText().toString().trim();

                        if(oldpassword.isEmpty()|| newpassword.isEmpty() || conformpassword.isEmpty()){
                            massage("some feilds are empty...");
                        }else {
                             progressDialog.show();
                            StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.RESET_PASSWORD_URL,
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
                                    params.put("oldpassword",oldpassword);
                                    params.put("newpassword",newpassword);
                                    params.put("conformpassword",conformpassword);
                                    params.put("email",mEmail);
                                    return params;
                                }
                            };
                            RequestQueue queue= Volley.newRequestQueue(ProfileUserActivity.this);
                            queue.add(stringRequest);
                        }
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=Name.getText().toString().trim();
                String email=Email.getText().toString().trim();
                String phone=Phone.getText().toString().trim();

                if(name.isEmpty() || email.isEmpty()|| phone.isEmpty()){
                    massage("some feilds are empty...");
                }else {
                    progressDialog.setTitle("Update...");
                    progressDialog.show();
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.UPDATE_USER_INFO_URL,
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
                            Map<String,String> updateParams=new HashMap<>();
                            updateParams.put("email",email);
                            updateParams.put("name",name);
                            updateParams.put("phone",phone);
                            updateParams.put("myemail",mEmail);
                            return updateParams;
                        }
                    };
                    RequestQueue queue= Volley.newRequestQueue(ProfileUserActivity.this);
                    queue.add(stringRequest);

                }
            }
        });

    }
    public void massage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_SHORT).show();
    }

    public void logout(View view) {
        userManager.logout();
    }
}