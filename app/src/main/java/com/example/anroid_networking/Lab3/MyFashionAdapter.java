package com.example.anroid_networking.Lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.anroid_networking.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyFashionAdapter extends ArrayAdapter<Fashionn> {
    List<Fashionn> fashionList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyFashionAdapter(Context context, List<Fashionn> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        fashionList = objects;
    }

    @Override
    public Fashionn getItem(int position) {
        return fashionList.get(position);
    }
    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewProducttype;
        private ViewHolder(RelativeLayout rootView,
                           ImageView imageView,
                           TextView textViewName,
                           TextView textViewProducttype) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewProducttype = textViewProducttype;
        }
        public static ViewHolder create(RelativeLayout rootView) { ImageView imageView = (ImageView)
                rootView.findViewById(R.id.imageView);
        TextView textViewName = (TextView)
                rootView.findViewById(R.id.textViewName);
        TextView textViewProducttype = (TextView)
                rootView.findViewById(R.id.textViewProducttype);
            return new ViewHolder(rootView, imageView, textViewName, textViewProducttype);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent,
            false);

                    vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh); } else {
            vh = (ViewHolder) convertView.getTag(); }
        Fashionn item = getItem(position);
        vh.textViewName.setText(item.getName()); vh.textViewProducttype.setText(item.getProducttype());
//        Picasso.with(context).load(item.getProfilePic()).placeholder(R.mipmap.ic_lau ncher).error(R.mipmap.ic_launcher).into(vh.imageView);
//        Picasso.get().load(item.getProfilePic()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);
        return vh.rootView; }
}
