package com.example.anroid_networking.mysql.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Database.ModelDB.Cart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context context;
    List<Cart> cartList;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.cart_item_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Picasso.with(context)
                .load(cartList.get(position).link)
                .into(holder.img_product);

        holder.txt_product_price.setText(new StringBuilder().append(cartList.get(position).price));
        holder.txt_product_name.setText(cartList.get(position).name);
        holder.txt_product_capdo.setText(new StringBuilder("Cap do:")
        .append(cartList.get(position).capdo)
        .append("\n")
        .append("Mon an them:").append(cartList.get(position).loai));



    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView img_product;
        TextView txt_product_name,txt_product_capdo,txt_product_price;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product=(ImageView)itemView.findViewById(R.id.img_product);
            txt_product_name=(TextView)itemView.findViewById(R.id.txt_cart_product_name);
            txt_product_capdo=(TextView)itemView.findViewById(R.id.txt_cart_product_capdo);
            txt_product_price=(TextView)itemView.findViewById(R.id.txt_cart_product_price);
        }
    }

}
