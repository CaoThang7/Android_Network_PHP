package com.example.anroid_networking.Lab2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackgroundTask_GET extends AsyncTask<String,Void,String> {
    private Context context;
    private String link,name,score;
    private TextView tvKQ;
    private String kq="";
    public BackgroundTask_GET(Context context, String link, String name, String score, TextView tvKQ){
        this.context=context;
        this.link=link;
        this.name=name;
        this.score=score;
        this.tvKQ=tvKQ;
    }

    @Override
    protected String doInBackground(String... strings) {
        link +="?name="+name+"&score="+score;
        try {
            URL url =new URL(link);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            BufferedReader btf=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line ="";
            StringBuffer sb=new StringBuffer();
            while ((line=btf.readLine()) != null){
                sb.append(line);
            }
            kq=sb.toString();
            urlConnection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        tvKQ.setText(kq);
    }
}
