package com.example.anroid_networking.Lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anroid_networking.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Lab1b22Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn,btnback;
    private TextView tv;
    private ImageView img;
    private ProgressDialog progressDialog;
//    private String url = "https://cdn1.tuoitre.vn/zoom/80_50/2021/6/28/img5278-16248945915451147865614-crop-16248946420781468525756.jpg";
    private String url = "https://i1-vnexpress.vnecdn.net/2021/06/30/giancahxahoi1-1625042953-3532-1625043569.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=40HxelLlS3R7HdzIuKHaog";
    private Bitmap bitmap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1b22);
        img=findViewById(R.id.img);
        tv=findViewById(R.id.tv);
        btn=findViewById(R.id.btn);
        btnback=findViewById(R.id.btnback);

        btn.setOnClickListener(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lab1b22Activity.this,MainChinhActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
//        Xử lý sự kiện click của button. Show dialog, tạo biến runnable, xử lý nền việc
//        tải hình , gửi tin nhắn kết quả trong hàm run. Cuối cùng tạo Thread mới và
//        start Thread.
        progressDialog =ProgressDialog.show(Lab1b22Activity.this,"","Downloading ...");
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
               bitmap=downloadBitmap(url);
               Message msg=messageHandler.obtainMessage();
               Bundle bundle=new Bundle();
               String threadMessage="Image downloaded";
               bundle.putString("message",threadMessage);
               msg.setData(bundle);
               messageHandler.sendMessage(msg);
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();

    }

    //Ham handler xu ly ket qua
    private Handler messageHandler = new Handler(){
        public void handleMessage(Message msg){
           super.handleMessage(msg);
           Bundle bundle=msg.getData();
           String message=bundle.getString("message");
           tv.setText(message);
           img.setImageBitmap(bitmap);
           progressDialog.dismiss();
        }

    };
    //Ham load anh server
    private Bitmap downloadBitmap(String link){
        try {
           URL url =new URL(link);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch (Exception e){
             e.printStackTrace();
        }
        return null;
    }
}