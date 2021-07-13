package com.example.anroid_networking.Lab2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;


import com.example.anroid_networking.Lab1.Listener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask_POST extends AsyncTask<Void, Void, Void> {
    String duongdan=Lab2b2Activity.SERVER_NAME;
    Context context;
    String strWidth,strLength;
    TextView tvRs;
    ProgressDialog pDialog;
    String strResult;
    public BackgroundTask_POST(Context context, String strWidth, String strLength, TextView tvRs){
        this.context=context;
        this.strWidth=strWidth;
        this.strLength=strLength;
        this.tvRs=tvRs;

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
    protected Void doInBackground(Void... params) {
        try {
            URL url=new URL(duongdan);
            String param="chieurong="+URLEncoder.encode(strWidth,"utf-8")+ "&chieudai="+
                    URLEncoder.encode(strLength,"utf-8");
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            PrintWriter print=new PrintWriter(urlConnection.getOutputStream());
            print.print(param);
            print.close();

            String line="";
            BufferedReader bfr= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb=new StringBuffer();
            while ((line=bfr.readLine())!=null){
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
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
        Log.i("abc",strResult);
        tvRs.setText(strResult);
    }
}
