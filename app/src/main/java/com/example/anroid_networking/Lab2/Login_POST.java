package com.example.anroid_networking.Lab2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.anroid_networking.Lab1.Lab1b22Activity;
import com.example.anroid_networking.Lab1.Lab1b2Activity;
import com.example.anroid_networking.Lab1.MainChinhActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.MalformedInputException;
public class Login_POST extends AsyncTask<String,Void,Void> {
    String duongdan = Lab2b4Activity.SERVER_NAME;
    TextView tvKetqua;
    String strKetqua;
    ProgressDialog pDialog;
    Context context;
    private static int SPLASH_TIME_OUT =7000;

    public Login_POST(Context context,TextView tvKetqua) {
        this.context = context;
        this.tvKetqua = tvKetqua;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(String... strings) {
        try {
            URL url = new URL(duongdan);
            String param = "username="+ URLEncoder.encode(strings[0].toString(),"utf-8")+  "&password=" + URLEncoder.encode(strings[1].toString(),"utf-8");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            PrintWriter print = new PrintWriter(urlConnection.getOutputStream());
            print.print(param);
            print.close();

            String line = "";
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            while ((line = bfr.readLine()) != null) {
                sb.append(line);
            }
            strKetqua = sb.toString();
            urlConnection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(strKetqua.equalsIgnoreCase("fail")) {
//            Intent intent = new Intent(context, Lab2b2Activity.class);
//            context.startActivity(intent);
//            ((Lab2b2Activity)context).finish();
            tvKetqua.setText(strKetqua);
        }
        else {
            tvKetqua.setText(strKetqua);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //SplassScreen
                    context.startActivity(new Intent(context, MainChinhActivity.class));
                }
            },SPLASH_TIME_OUT);


        }
    }
}

