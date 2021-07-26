package com.example.anroid_networking.ASMGD1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.anroid_networking.ASMGD1.API.APIRequestData;
import com.example.anroid_networking.ASMGD1.API.RetroServer;
import com.example.anroid_networking.ASMGD1.Adapter.AdapterData;
import com.example.anroid_networking.ASMGD1.Model.DataModel;
import com.example.anroid_networking.ASMGD1.Model.ResponseModel;
import com.example.anroid_networking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;

    private List<DataModel> listData =new ArrayList<>();

    private FloatingActionButton fab_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        fab_shop=findViewById(R.id.fab_shop);

        rvData=findViewById(R.id.rv_data);
        lmData=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        rvData.setLayoutManager(lmData);

        reShopData();

        fab_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShopActivity.this,InsertActivity.class);
                startActivity(i);
            }
        });

    }

    public void reShopData(){
        APIRequestData ardData= RetroServer.koneRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData =ardData.ardShopData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode= response.body().getKode();
                String pesan=response.body().getPesan();

//                Toast.makeText(ShopActivity.this,"Kode:"+kode+"| Pesan :"+pesan,Toast.LENGTH_SHORT ).show();

                listData=response.body().getData();

                adData=new AdapterData(ShopActivity.this,listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ShopActivity.this,"Gagal Server:"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}