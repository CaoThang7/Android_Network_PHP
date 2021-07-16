package com.example.anroid_networking.Lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anroid_networking.R;

import java.util.ArrayList;

public class FashionAdapter extends BaseAdapter {
    Context context;
    ArrayList<Fashion> fashionList;

    public FashionAdapter(Context context, ArrayList<Fashion> fashionList) {
        this.context = context;
        this.fashionList = fashionList;
    }
    @Override
    public int getCount() {
        return fashionList.size();
    }

    @Override
    public Object getItem(int i) {
        return fashionList.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        TextView tvName, tvProducttype, tvPrice, tvStyle, tvMyStyle;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        FashionAdapter.ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new FashionAdapter.ViewHolder();
            view = inflater.inflate(R.layout.list_item, null);
            viewHolder.tvName = (TextView) view.findViewById(R.id.name);
            viewHolder.tvProducttype = (TextView) view.findViewById(R.id.producttype);
            viewHolder.tvPrice = (TextView) view.findViewById(R.id.price);
            viewHolder.tvStyle = (TextView) view.findViewById(R.id.style);
            viewHolder.tvMyStyle = (TextView) view.findViewById(R.id.mystyle);
            view.setTag(viewHolder);
        }else {
            viewHolder = (FashionAdapter.ViewHolder)view.getTag();
        }
        Fashion fashion = fashionList.get(i);
        viewHolder.tvName.setText(fashion.getName());
        viewHolder.tvProducttype.setText(fashion.getProducttype());
        viewHolder.tvPrice.setText(fashion.getPrice());
        viewHolder.tvStyle.setText(fashion.getStyle());
        viewHolder.tvMyStyle.setText(fashion.getModern());
        return view;

    }
}
