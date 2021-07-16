package com.example.anroid_networking.Lab2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;

import com.example.anroid_networking.Lab1.Lab1b22Activity;
import com.example.anroid_networking.Lab1.Lab1b2Activity;
import com.example.anroid_networking.Lab1.MainChinhActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class Insert_GET extends AsyncTask<Void, Void, Void> {
    String huongdan = Lab2b5Activity.SEVER_NAME;
    TextView tvResult;
    String strName, strEmail, strAdress;
    String str;
    ProgressDialog dialog;
    private static int SPLASH_TIME_OUT =3000;
    Context context;
    public Insert_GET(Context context,TextView tvResult, String strName, String strEmai, String strAdress) {
        this.context = context;
        this.tvResult = tvResult;
        this.strName = strName;
        this.strEmail = strEmai;
        this.strAdress = strAdress;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        huongdan += "?name=" + this.strName + "&email=" + this.strEmail + "&adress=" + this.strAdress;
        try {
            URL url = new URL(huongdan);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader btf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = btf.readLine()) != null){
                sb.append(line);
            }
            str = sb.toString();
            urlConnection.disconnect();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Sending...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(dialog.isShowing()){
            dialog.dismiss();
        }

        tvResult.setText(str);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //SplassScreen
                context.startActivity(new Intent(context,Lab2b4Activity.class));
            }
        },SPLASH_TIME_OUT);



    }


}

