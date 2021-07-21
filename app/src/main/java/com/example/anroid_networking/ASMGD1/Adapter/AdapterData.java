package com.example.anroid_networking.ASMGD1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anroid_networking.ASMGD1.Model.DataModel;
import com.example.anroid_networking.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context context;
    private List<DataModel> listData;

    public AdapterData(Context context, List<DataModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder=new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm= listData.get(position);

        holder.tvID.setText(String.valueOf(dm.getId()));
        holder.tvName.setText(dm.getName());
        holder.tvPrice.setText(dm.getPrice());
        holder.tvstyle.setText(dm.getStyle());



    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvID, tvName,tvPrice,tvstyle;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvID=itemView.findViewById(R.id.tv_id);
            tvName=itemView.findViewById(R.id.tv_name);
            tvPrice=itemView.findViewById(R.id.tv_price);
            tvstyle=itemView.findViewById(R.id.tv_style);




        }
    }
}
