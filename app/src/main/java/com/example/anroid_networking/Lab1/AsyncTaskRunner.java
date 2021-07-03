package com.example.anroid_networking.Lab1;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskRunner extends android.os.AsyncTask<String,String,String>{
    private  String resp;
    ProgressDialog progressDialog;
    TextView tv,tv2;
    EditText edt;
    Context context;
    public  AsyncTaskRunner(Context context,TextView tv,TextView tv2,EditText edt){
        this.context=context;
        this.tv=tv;
        this.edt=edt;
        this.tv2=tv2;
    }
    @Override
    protected String doInBackground(String... params) {
       publishProgress("Sleeping...");
       try{
          int time=Integer.parseInt(params[0])* 1000;
          Thread.sleep(time);
          resp="Slept for" +  " " + params[0]+ "seconds";
       }catch (Exception e){
           e.printStackTrace();
           resp=e.getMessage();
       }
       return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(context,"ProgressDialog","Wait for" + " " + edt.getText().toString() + "s");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        tv2.setText(s);
    }
}
