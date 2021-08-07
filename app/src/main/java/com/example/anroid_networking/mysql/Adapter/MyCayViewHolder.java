package com.example.anroid_networking.mysql.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Interface.ItemClickListener;

public class MyCayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img_product;
    TextView txt_mycay_name,txt_price;

    ItemClickListener itemClickListener;

    ImageView img_add_to_cart;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MyCayViewHolder(@NonNull View itemView) {
        super(itemView);
        img_product=(ImageView)itemView.findViewById(R.id.image_product);
        txt_mycay_name=(TextView)itemView.findViewById(R.id.txt_mycay_name);
        txt_price=(TextView)itemView.findViewById(R.id.txt_price);
        img_add_to_cart=(ImageView)itemView.findViewById(R.id.img_add_cart);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }
}
