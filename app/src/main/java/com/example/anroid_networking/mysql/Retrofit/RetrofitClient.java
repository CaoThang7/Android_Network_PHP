package com.example.anroid_networking.mysql.Retrofit;

import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(String baseUrl){
        if(retrofit ==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    private static final String baseURL = "http://10.0.2.2/mycay/";
//    private static Retrofit retro;
//
//    public static Retrofit getClient(){
//        if(retro ==null){
//            retro= new Retrofit.Builder()
//                    .baseUrl(baseURL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return retro;
//    }

}
