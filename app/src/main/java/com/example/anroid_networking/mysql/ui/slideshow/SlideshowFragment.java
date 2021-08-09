package com.example.anroid_networking.mysql.ui.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.anroid_networking.mysql.Retrofit.mycayApi;
import com.example.anroid_networking.mysql.Utils.Common;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SlideshowFragment extends Fragment {
    RecyclerView recycler_cart;
    Button btn_place_order;

    CompositeDisposable compositeDisposable;

    mycayApi mycayApi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        compositeDisposable=new CompositeDisposable();
        mycayApi=Common.getAPI();

        recycler_cart=(RecyclerView)root.findViewById(R.id.recycler_cart);
        recycler_cart.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_cart.setHasFixedSize(true);

        btn_place_order=(Button)root.findViewById(R.id.btn_place_order);

        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }


        });

        loadCartItems();

        return root;
    }

    private void placeOrder() {
        //create
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Submit Order");
        View submit_order_layout=LayoutInflater.from(getContext()).inflate(R.layout.submit_order_layout,null);
        EditText edt_comment=(EditText)submit_order_layout.findViewById(R.id.edt_comment);
        EditText edt_other_address=(EditText)submit_order_layout.findViewById(R.id.edt_other_address);
        EditText phone=(EditText)submit_order_layout.findViewById(R.id.phone);
        RadioButton rdi_user_address=(RadioButton)submit_order_layout.findViewById(R.id.rdi_user_address);
        RadioButton rdi_other_address=(RadioButton)submit_order_layout.findViewById(R.id.rdi_other_address);

        rdi_user_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    edt_other_address.setEnabled(false);

            }
        });

        rdi_other_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    edt_other_address.setEnabled(true);
            }
        });

        builder.setView(submit_order_layout);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String orderComment=edt_comment.getText().toString();
//                final String orderAddress=edt_other_address.getText().toString();
                final String orderAddress;
                String phone=edt_other_address.getText().toString();

                if(rdi_other_address.isChecked()){
                     orderAddress=edt_other_address.getText().toString();
                }else{
                    orderAddress="";
                }

                compositeDisposable.add(
                        Common.cartRepository.getCartItems()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<List<Cart>>() {
                            @Override
                            public void accept(List<Cart> carts) throws Exception {
                                 if(!TextUtils.isEmpty(orderAddress)){
                                     sendOrderToServer(Common.cartRepository.sumPrice(), carts,
                                             orderComment,orderAddress,phone);
                                 }else {
                                     Toast.makeText(getContext(), "Faill order", Toast.LENGTH_SHORT).show();
                                 }

                            }


                        })
                );
            }
        });
        builder.show();

    }

    private void sendOrderToServer(float sumPrice, List<Cart> carts, String orderComment, String orderAddress,String phone) {
        if(carts.size()>0){
            String orderDetail=new Gson().toJson(carts);
            mycayApi.submitOrder(sumPrice,orderDetail,orderComment,orderAddress,phone)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(getContext(), "order submit", Toast.LENGTH_SHORT).show();
                            //Clear cart
                            Common.cartRepository.emptyCart();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
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