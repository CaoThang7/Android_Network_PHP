package com.example.anroid_networking.mysql.ui.gallery;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Adapter.MyCayAdapter;
import com.example.anroid_networking.mysql.Retrofit.mycayApi;
import com.example.anroid_networking.mysql.Utils.Common;
import com.example.anroid_networking.mysql.model.mycay;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class GalleryFragment extends Fragment {

    mycayApi mcApi;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RecyclerView lst_mycay;
    TextView txt_banner_name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

//        txt_banner_name=(TextView)root.findViewById(R.id.txt_menu_name);
//        txt_banner_name.setText(Common.currentCategory.Name);
//        lst_mycay=root.findViewById(R.id.recycler_mycay);
//        lst_mycay.setLayoutManager(new GridLayoutManager(getContext(),2));
//        lst_mycay.setHasFixedSize(true);
//
//        mcApi= Common.getAPI();
//
//        loadListMyCay(Common.currentCategory.ID);
        return root;
    }
//    private void loadListMyCay(String menuId) {
//        compositeDisposable.add(mcApi.getMyCay(menuId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<mycay>>() {
//                    @Override
//                    public void accept(List<mycay> mycays) throws Exception {
//                        displayMyCayList(mycays);
//                    }
//
//
//                }));
//    }
//
//    private void displayMyCayList(List<mycay> mycays) {
//        MyCayAdapter adapter= new MyCayAdapter(getContext(),mycays);
//        lst_mycay.setAdapter(adapter);
//    }

}