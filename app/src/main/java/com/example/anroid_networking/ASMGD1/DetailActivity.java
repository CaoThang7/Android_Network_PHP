package com.example.anroid_networking.ASMGD1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anroid_networking.ASMGD1.API.APIRequestData;
import com.example.anroid_networking.ASMGD1.API.RetroServer;
import com.example.anroid_networking.ASMGD1.Model.ResponseModel;
import com.example.anroid_networking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private int xId;
    private String xName,xPrice,xStyle;
    private EditText etName,etPrice,etStyle;
    private Button btn_update;
    private String yName,yPrice,yStyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        etName=findViewById(R.id.et_name);
        etPrice=findViewById(R.id.et_price);
        etStyle=findViewById(R.id.et_style);
        btn_update=findViewById(R.id.btn_update);

        Intent i=getIntent();
        xId=i.getIntExtra("xId",-1);
        xName=i.getStringExtra("xName");
        xPrice=i.getStringExtra("xPrice");
        xStyle=i.getStringExtra("xStyle");

        etName.setText(xName);
        etPrice.setText(xPrice);
        etStyle.setText(xStyle);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yName=etName.getText().toString();
                yPrice=etPrice.getText().toString();
                yStyle=etStyle.getText().toString();
                updateData();
            }
        });

    }
     private void updateData(){
         APIRequestData ardData= RetroServer.koneRetrofit().create(APIRequestData.class);
         Call<ResponseModel> updateData =ardData.ardUpdateData(xId,yName,yPrice,yStyle);

         updateData.enqueue(new Callback<ResponseModel>() {
             @Override
             public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                 int kode=response.body().getKode();
                 String persan=response.body().getPesan();

                 Toast.makeText(DetailActivity.this,"Cập nhật thành công!" ,Toast.LENGTH_SHORT).show();
                 finish();
             }

             @Override
             public void onFailure(Call<ResponseModel> call, Throwable t) {
                 Toast.makeText(DetailActivity.this,"Server bị lỗi"+t.getMessage(),Toast.LENGTH_SHORT).show();
             }
         });
    }

}
