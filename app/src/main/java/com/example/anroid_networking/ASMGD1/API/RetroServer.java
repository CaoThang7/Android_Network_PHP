package com.example.anroid_networking.ASMGD1.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "http://192.168.0.101/shop/";
    private static Retrofit retro;

    public static Retrofit koneRetrofit(){
        if(retro ==null){
            retro= new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retro;
    }
}
