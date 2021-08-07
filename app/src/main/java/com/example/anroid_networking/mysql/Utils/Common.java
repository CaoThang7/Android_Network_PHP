package com.example.anroid_networking.mysql.Utils;

import com.example.anroid_networking.mysql.Database.DataSource.CartRepository;
import com.example.anroid_networking.mysql.Database.Local.CartDatabase;
import com.example.anroid_networking.mysql.Retrofit.RetrofitClient;
import com.example.anroid_networking.mysql.Retrofit.mycayApi;
import com.example.anroid_networking.mysql.model.Category;
import com.example.anroid_networking.mysql.model.mycay;

import java.util.ArrayList;
import java.util.List;

public class Common {
    private static final String BASE_URL = "http://10.0.2.2/mycay/";


    public static Category currentCategory= null;

    public static List<mycay> toppingList =new ArrayList<>();

    public static final String TOPPING_MENU_ID="5";

    public static double toppingPrice=0.0;
    public static List<String> toppingAdded=new ArrayList<>();

    //Hold field
    public static int sizeOfCup=-1; //-1: no chose(error), 0:M, 1:L
    public static int capdo=-1;
    public static int loai=-1;

    //Database
    public static CartDatabase cartDatabase;
    public static CartRepository cartRepository;




    public static mycayApi getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(mycayApi.class);
    }
}
