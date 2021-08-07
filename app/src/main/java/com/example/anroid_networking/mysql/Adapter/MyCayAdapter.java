package com.example.anroid_networking.mysql.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Database.ModelDB.Cart;
import com.example.anroid_networking.mysql.Interface.ItemClickListener;
import com.example.anroid_networking.mysql.Utils.Common;
import com.example.anroid_networking.mysql.model.Category;
import com.example.anroid_networking.mysql.model.mycay;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCayAdapter extends RecyclerView.Adapter<MyCayViewHolder> {
    Context context;
    List<mycay> mycayList;

    public MyCayAdapter(Context context, List<mycay> mycayList) {
        this.context = context;
        this.mycayList = mycayList;
    }

    @NonNull
    @Override
    public MyCayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.mycay_item_layout,null);
        return new MyCayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCayViewHolder holder, int position) {

//        holder.txt_price.setText(new StringBuilder("$").append(mycayList.get(position).Price).toString());
        holder.txt_price.setText(mycayList.get(position).Price);
        holder.txt_mycay_name.setText(mycayList.get(position).Name);
        Picasso.with(context)
                .load(mycayList.get(position).Link)
                .into(holder.img_product);

        //holder cart
        holder.img_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddToCartDialog(position);
            }


        });


        holder.setItemClickListener(new ItemClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
          }
        });
    }

    private void showAddToCartDialog(int position) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.add_to_cart_layout,null);

        //View
        ImageView img_product_dialog=(ImageView)itemView.findViewById(R.id.img_cart_product);
        ElegantNumberButton txt_count=(ElegantNumberButton)itemView.findViewById(R.id.txt_count);
        TextView txt_product_dialog=(TextView)itemView.findViewById(R.id.txt_cart_product_name);
        EditText edt_comment=(EditText)itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_sizeM=(RadioButton)itemView.findViewById(R.id.rdi_sizeM);
        RadioButton rdi_sizeL=(RadioButton)itemView.findViewById(R.id.rdi_sizeL);

        rdi_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.sizeOfCup=0;
                }
            }
        });
        rdi_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.sizeOfCup=1;
                }
            }
        });

        RadioButton rdi_capdo_10=(RadioButton)itemView.findViewById(R.id.rdi_capdo_10);
        RadioButton rdi_capdo_20=(RadioButton)itemView.findViewById(R.id.rdi_capdo_20);
        RadioButton rdi_capdo_30=(RadioButton)itemView.findViewById(R.id.rdi_capdo_30);
        RadioButton rdi_capdo_40=(RadioButton)itemView.findViewById(R.id.rdi_capdo_40);
        RadioButton rdi_capdo_50=(RadioButton)itemView.findViewById(R.id.rdi_capdo_50);
        RadioButton rdi_capdo_60=(RadioButton)itemView.findViewById(R.id.rdi_capdo_60);
        RadioButton rdi_capdo_70=(RadioButton)itemView.findViewById(R.id.rdi_capdo_70);

        rdi_capdo_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.capdo=1;
                }
            }
        });
        rdi_capdo_20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.capdo=2;
                }
            }
        });
        rdi_capdo_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.capdo=3;
                }
            }
        });
        rdi_capdo_40.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.capdo=4;
                }
            }
        });
        rdi_capdo_50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.capdo=5;
                }
            }
        });
        rdi_capdo_60.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.capdo=6;
                }
            }
        });
        rdi_capdo_70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.capdo=7;
                }
            }
        });


        RadioButton rdi_topokki=(RadioButton)itemView.findViewById(R.id.rdi_topokki);
        RadioButton rdi_dorayaki=(RadioButton)itemView.findViewById(R.id.rdi_dorayaki);

        rdi_topokki.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.loai=30;
                }
            }
        });
        rdi_dorayaki.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.loai=50;
                }
            }
        });


        RecyclerView recycler_topping=(RecyclerView)itemView.findViewById(R.id.recycler_topping);
        recycler_topping.setLayoutManager(new LinearLayoutManager(context));
        recycler_topping.setHasFixedSize(true);

        MultiChoiceAdapter adapter=new MultiChoiceAdapter(context, Common.toppingList);
        recycler_topping.setAdapter(adapter);


        //set data
        Picasso.with(context)
                .load(mycayList.get(position).Link)
                .into(img_product_dialog);

        txt_product_dialog.setText(mycayList.get(position).Name);

        builder.setView(itemView);
        builder.setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Common.sizeOfCup == -1){
                    Toast.makeText(context, "Please choose size of cup", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Common.capdo == -1){
                    Toast.makeText(context, "Please choose cap do", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Common.loai == -1){
                    Toast.makeText(context, "Please choose mon an kem", Toast.LENGTH_SHORT).show();
                    return;
                }

                showConfirmDialog(position,txt_count.getNumber());
                dialog.dismiss();
            }
        });


        builder.show();


    }

    private void showConfirmDialog(int position, String number) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.confirm_add_to_cart,null);

        //view
        //View
        ImageView img_product_dialog=(ImageView)itemView.findViewById(R.id.img_product);
        TextView txt_product_dialog=(TextView)itemView.findViewById(R.id.txt_cart_product_name);
        TextView txt_product_price=(TextView)itemView.findViewById(R.id.txt_cart_product_price);
        TextView txt_capdo=(TextView)itemView.findViewById(R.id.txt_capdo);
        TextView txt_loai=(TextView)itemView.findViewById(R.id.txt_loai);
        TextView txt_topping_extra=(TextView)itemView.findViewById(R.id.txt_topping_extra);

        //set data
        Picasso.with(context).load(mycayList.get(position).Link).into(img_product_dialog);
        txt_product_dialog.setText(new StringBuilder(mycayList.get(position).Name)
                .append(" x")
                .append(number)
                .append(Common.sizeOfCup ==0 ?"Size M": "Size L").toString());
        txt_loai.setText(new StringBuilder("Mon an them:").append(Common.loai).toString());
        txt_capdo.setText(new StringBuilder("Cap do:").append(Common.capdo).toString());

        double price=(Double.parseDouble(mycayList.get(position).Price)*Double.parseDouble(number))+Common.toppingPrice;

        if(Common.sizeOfCup ==1){ //size 1
            price+=3.0;
        }

        txt_product_price.setText(new StringBuilder().append(price));

        StringBuilder topping_final_comment = new StringBuilder("");
        for (String line:Common.toppingAdded)
            topping_final_comment.append(line).append("\n");
        txt_topping_extra.setText(topping_final_comment);

        double finalPrice = price;
        builder.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                try {
                    //add to SQLIte
                    //Create new Cart item
                    Cart cartItem = new Cart();
                    cartItem.name = txt_product_dialog.getText().toString();
                    cartItem.amount = Integer.parseInt(number);
                    cartItem.capdo = Common.capdo;
                    cartItem.loai = Common.loai;
                    cartItem.price = finalPrice;
                    cartItem.toppingExtras = txt_topping_extra.getText().toString();
                    cartItem.link= mycayList.get(position).Link;
                    //add to DB
                    Common.cartRepository.insertToCart(cartItem);

                    Log.d("BUg", new Gson().toJson(cartItem));

                    Toast.makeText(context, "Save item to cart success", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(context,ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(itemView);
        builder.show();


    }

    @Override
    public int getItemCount() {
        return mycayList.size();
    }
}
