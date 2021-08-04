package com.example.anroid_networking.ASMGD1.Utils;

import com.example.anroid_networking.mysql.Retrofit.RetrofitClient;
import com.example.anroid_networking.mysql.Retrofit.mycayApi;

public class Common {
    private static final String BASE_URL = "http://10.0.2.2/mycay/";
    public static mycayApi getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(mycayApi.class);
    }
}
