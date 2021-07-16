package com.example.anroid_networking.Lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anroid_networking.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Lab3b2Activity extends AppCompatActivity {
    // json object response url
    private String urlJsonObj = "http://192.168.0.101/fashions/person_object.json";
    // json array response url
    private String urlJsonArry = "http://192.168.0.101/fashions/person_array.json";

    private static String TAG = Lab3b2Activity.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;

    // progress dialog
//    private ProgressDialog pDialog;
    private TextView txtResponse;
    // temprorary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3b2);
        btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);

//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjectRequest();
            }
        });

        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonArrayRequest();
            }
        });

    }
    private void makeJsonArrayRequest() {
//        showDialog();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(urlJsonArry, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.d(TAG, jsonArray.toString());
                try {
// Parsing json array response
// loop through each json object
                    jsonResponse = "";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject person = (JSONObject) jsonArray.get(i);
                        String name = person.getString("name");
                        String producttype = person.getString("producttype");
                        JSONObject mystyle = person.getJSONObject("mystyle");
                        String modern = mystyle.getString("modern");
                        String vintage = mystyle.getString("vintage");
                        jsonResponse += "Name: " + name + "\n\n";
                        jsonResponse += "Producttype: " + producttype + "\n\n";
                        jsonResponse += "Modern: " + modern + "\n\n";
                        jsonResponse += "Vintage: " + vintage + "\n\n";
                    }
                    txtResponse.setText(jsonResponse); } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
//                hideDialog();
            }
        }, new Response.ErrorListener() { @Override
        public void onErrorResponse(VolleyError volleyError) {
            VolleyLog.d(TAG,"Error: "+ volleyError.getMessage());
            Toast.makeText(getApplicationContext(),volleyError.getMessage(),Toast.LENGTH_LONG).show();
//            hideDialog();
        }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrayRequest);
    }

    private void makeJsonObjectRequest() {
//        showDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, urlJsonObj,
                null, new Response.Listener<JSONObject>() { @Override
        public void onResponse(JSONObject jsonObject) { Log.d(TAG, jsonObject.toString());
            try {
// Parsing json object response
// response will be a json object
//                String name = jsonObject.getString("name");
//                String email = jsonObject.getString("email");
//                JSONObject phone = jsonObject.getJSONObject("phone");
//                String home = phone.getString("home");
//                String mobile = phone.getString("mobile");

                String name = jsonObject.getString("name");
                String producttype = jsonObject.getString("producttype");
                JSONObject mystyle = jsonObject.getJSONObject("mystyle");
                String modern = mystyle.getString("modern");
                String vintage = mystyle.getString("vintage");
                jsonResponse = "";
                jsonResponse += "Name: " + name + "\n\n";
                jsonResponse += "Producttype: " + producttype + "\n\n";
                jsonResponse += "Modern: " + modern + "\n\n";
                jsonResponse += "Vintage: " + vintage + "\n\n";
                txtResponse.setText(jsonResponse);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error: " +
                        e.getMessage(), Toast.LENGTH_LONG).show(); }
//            hideDialog();
        }
        }, new Response.ErrorListener() { @Override
        public void onErrorResponse(VolleyError volleyError) {
            VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
            Toast.makeText(getApplicationContext(), "Error: " +
                volleyError.getMessage(), Toast.LENGTH_LONG).show();
//            hideDialog();
        }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq); }



}