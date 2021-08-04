package com.example.anroid_networking.mysql.Retrofit;

import com.example.anroid_networking.ASMGD1.Model.ResponseModel;
import com.example.anroid_networking.mysql.model.Banner;
import com.example.anroid_networking.mysql.model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface mycayApi {
    @GET("getbanner.php")
    Observable<List<Banner>> getBanners();

    @GET("getmenu.php")
    Observable<List<Category>> getMenu();
}
