package com.example.anroid_networking.mysql.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.anroid_networking.mysql.Database.DataSource.CartRepository;
import com.example.anroid_networking.mysql.Database.Local.CartDataSource;
import com.example.anroid_networking.mysql.Database.Local.CartDatabase;
import com.example.anroid_networking.mysql.Utils.Common;
import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Adapter.CategoryAdapter;
import com.example.anroid_networking.mysql.Retrofit.mycayApi;
import com.example.anroid_networking.mysql.model.Banner;
import com.example.anroid_networking.mysql.model.Category;
import com.example.anroid_networking.mysql.model.mycay;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends Fragment {
    SliderLayout sliderLayout;
    mycayApi mcApi;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RecyclerView lst_menu;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout=root.findViewById(R.id.slider);
        lst_menu=root.findViewById(R.id.lst_menu);
        lst_menu.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        lst_menu.setHasFixedSize(true);

        mcApi= Common.getAPI();

        //Get Slideshow banner
        getBannerImage();

        //Get menu
        getMenu();

        //Save newest Topping List
        getToppingList();

        //Init Database
        initDB();

        return root;
    }

    private void initDB() {
        Common.cartDatabase= CartDatabase.getInstance(getContext());
        Common.cartRepository= CartRepository.getInstance(CartDataSource.getInstance(Common.cartDatabase.cartDao()));

    }

    private void getToppingList() {
        compositeDisposable.add(mcApi.getMyCay(Common.TOPPING_MENU_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<mycay>>() {
                    @Override
                    public void accept(List<mycay> mycays) throws Exception {
                        Common.toppingList=mycays;
                    }

                }));
    }

    private void getMenu() {
        compositeDisposable.add(mcApi.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        displayMenu(categories);
                    }

                }));
    }

    private void getBannerImage() {
        compositeDisposable.add(mcApi.getBanners()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Banner>>() {
            @Override
            public void accept(List<Banner> banners) throws Exception {
                displayImage(banners);
            }


        }));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private void displayImage(List<Banner> banners) {
        HashMap<String,String> bannerMap=new HashMap<>();
        for(Banner item:banners)
            bannerMap.put(item.getName(),item.getLink());

        for(String name:bannerMap.keySet()){
            TextSliderView textSliderView=new TextSliderView(getContext());
            textSliderView.description(name)
                          .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
    }

    private void displayMenu(List<Category> categories) {
        CategoryAdapter adapter=new CategoryAdapter(getContext(),categories);
        lst_menu.setAdapter(adapter);
    }


}