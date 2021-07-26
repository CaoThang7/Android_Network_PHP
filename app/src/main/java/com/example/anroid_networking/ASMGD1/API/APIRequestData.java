package com.example.anroid_networking.ASMGD1.API;

import com.example.anroid_networking.ASMGD1.Model.ResponseModel;
import com.example.anroid_networking.Lab3.FashionList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("shop.php")
    Call<ResponseModel> ardShopData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("name") String name,
            @Field("price") String price,
            @Field("style") String style

    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardCreateData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );


    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("name") String name,
            @Field("price") String price,
            @Field("style") String style
    );



}

