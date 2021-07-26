package com.example.anroid_networking.ASMGD1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anroid_networking.ASMGD1.API.APIRequestData;
import com.example.anroid_networking.ASMGD1.API.RetroServer;
import com.example.anroid_networking.ASMGD1.Model.ResponseModel;
import com.example.anroid_networking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    private EditText etName,etPrice,etStyle;
    private Button btn_insert;
    private String name,price,style;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etName=findViewById(R.id.et_name);
        etPrice=findViewById(R.id.et_price);
        etStyle=findViewById(R.id.et_style);
        btn_insert=findViewById(R.id.btn_insert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              name=etName.getText().toString();
              price=etPrice.getText().toString();
              style=etStyle.getText().toString();

              if(name.trim().equals("")){
                  etName.setError("Khong dc bo trong");
              }else  if(price.trim().equals("")){
                  etPrice.setError("Khong dc bo trong");
              }else  if( style.trim().equals("")){
                  etStyle.setError("Khong dc bo trong");
              }else {
                createData();
              }


            }
        });
    }



    private void createData(){
        APIRequestData ardData= RetroServer.koneRetrofit().create(APIRequestData.class);
        Call<ResponseModel> saveData =ardData.ardCreateData(name,price,style);

        saveData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode=response.body().getKode();
                String persan=response.body().getPesan();

                Toast.makeText(InsertActivity.this,"Thêm thành công!" ,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(InsertActivity.this,"Server bị lỗi:"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}