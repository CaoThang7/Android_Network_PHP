package com.example.anroid_networking.Lab1;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
// LoadImageTask kế thừa lớp AsyncTask
public class AsyncTask extends android.os.AsyncTask<String,Void, Bitmap> {
    private Listener mlistener;
    private ProgressDialog progressDialog;
    public AsyncTask(Listener listener, Context context){
       mlistener=listener;
       progressDialog= new ProgressDialog(context);
    }




    //xử lý Load hình từ server trong hàm doInBackground
    @Override
    protected Bitmap doInBackground(String... params) {
        try{
           return BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent());
        }catch (IOException e){
             e.printStackTrace();
        }
        return null;
    }

    //Đóng dialog và set kết quả về cho hàm onImageLoaded ở interface listener
    @Override
    protected void onPostExecute(Bitmap result) {
       super.onPostExecute(result);
       if(progressDialog.isShowing()){
           progressDialog.dismiss();
       }
       if(result != null){
           mlistener.onImageLoaded(result);
       }else {
           mlistener.onError();
       }
    }

    //Nạp chồng hàm onPreExecute và xử lý việc hiển thị dialog
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Downloading image...");
        progressDialog.show();
    }
}
