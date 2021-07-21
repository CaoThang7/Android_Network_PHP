package com.example.anroid_networking.ASMGD1.API;

import com.example.anroid_networking.ASMGD1.Model.ResponseModel;
import com.example.anroid_networking.Lab3.FashionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("shop.php")
    Call<ResponseModel> ardShopData();
}
