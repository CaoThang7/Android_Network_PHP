package com.example.anroid_networking.mysql.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.anroid_networking.ASMGD1.Utils.Common;
import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Retrofit.mycayApi;
import com.example.anroid_networking.mysql.model.Banner;

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout=root.findViewById(R.id.slider);

        mcApi= Common.getAPI();

        getBannerImage();
        return root;
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
}