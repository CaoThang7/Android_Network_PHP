package com.example.anroid_networking.mysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

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

public class spMyCayActivity extends AppCompatActivity {
    mycayApi mcApi;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RecyclerView lst_mycay;
    TextView txt_banner_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_my_cay);
        txt_banner_name=(TextView)findViewById(R.id.txt_menu_name);
        txt_banner_name.setText(Common.currentCategory.Name);
        lst_mycay=findViewById(R.id.recycler_mycay);
        lst_mycay.setLayoutManager(new GridLayoutManager(this,2));
        lst_mycay.setHasFixedSize(true);

        mcApi= Common.getAPI();

        loadListMyCay(Common.currentCategory.ID);


    }

    private void loadListMyCay(String menuId) {
      compositeDisposable.add(mcApi.getMyCay(menuId)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Consumer<List<mycay>>() {
          @Override
          public void accept(List<mycay> mycays) throws Exception {
              displayMyCayList(mycays);
          }


      }));
    }

    private void displayMyCayList(List<mycay> mycays) {
        MyCayAdapter adapter= new MyCayAdapter(this,mycays);
        lst_mycay.setAdapter(adapter);
    }
}