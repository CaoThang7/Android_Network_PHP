package com.example.anroid_networking.Lab2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class AsyncTask_POST extends AsyncTask<String, Void, Void> {
    String duongdan=Lab2b4Activity.SERVER_NAME;
    Context context;
    String strUser,strPassword;
    TextView tvResult;
    ProgressDialog pDialog;
    String strResult;

    public AsyncTask_POST(Context context,TextView tvResult){
        this.context=context;
        this.tvResult=tvResult;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url=new URL(duongdan);
            String param="username="+URLEncoder.encode(strUser,"utf-8")+ "&password="+
                    URLEncoder.encode(strPassword,"utf-8");
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            PrintWriter print=new PrintWriter(urlConnection.getOutputStream());
            print.print(param);
            print.close();

            String line="";
            BufferedReader btf=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb=new StringBuffer();
            while ((line=btf.readLine())!= null){
                sb.append(line);
            }

            strResult=sb.toString();
            urlConnection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog=new ProgressDialog(context);
        pDialog.setMessage("Calculating");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(strResult.equalsIgnoreCase("success")){
            Intent i=new Intent(context,Lab2b1Activity.class);
            context.startActivity(i);
            ((Lab2b1Activity)context).finish();
        }else {
            tvResult.setText("Error");
        }

    }
}
