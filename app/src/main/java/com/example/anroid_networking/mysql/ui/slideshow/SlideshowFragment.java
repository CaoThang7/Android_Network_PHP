package com.example.anroid_networking.mysql.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Adapter.CartAdapter;
import com.example.anroid_networking.mysql.Database.ModelDB.Cart;
import com.example.anroid_networking.mysql.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class SlideshowFragment extends Fragment {
    RecyclerView recycler_cart;
    Button btn_place_order;

    CompositeDisposable compositeDisposable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        compositeDisposable=new CompositeDisposable();

        recycler_cart=(RecyclerView)root.findViewById(R.id.recycler_cart);
        recycler_cart.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_cart.setHasFixedSize(true);

        btn_place_order=(Button)root.findViewById(R.id.btn_place_order);

        loadCartItems();

        return root;
    }

    private void loadCartItems() {
        compositeDisposable.add(
                Common.cartRepository.getCartItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Cart>>() {
                    @Override
                    public void accept(List<Cart> carts) throws Exception {
                        displayCartItem(carts);
                    }


                })
        );
    }
    private void displayCartItem(List<Cart> carts) {
        CartAdapter cartAdapter=new CartAdapter(getContext(),carts);
        recycler_cart.setAdapter(cartAdapter);
    }

    //ctlr + O


    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}