package com.example.anroid_networking.Lab3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String ROOT_URL = "http://192.168.0.101/fashions/json_data.json";
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL) .addConverterFactory(GsonConverterFactory.create()) .build();
    }

    /**
     * Get API Service
     *
     * @return API Service */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
