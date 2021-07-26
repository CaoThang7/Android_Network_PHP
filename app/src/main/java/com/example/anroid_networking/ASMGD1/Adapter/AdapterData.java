package com.example.anroid_networking.ASMGD1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anroid_networking.ASMGD1.API.APIRequestData;
import com.example.anroid_networking.ASMGD1.API.RetroServer;
import com.example.anroid_networking.ASMGD1.DetailActivity;
import com.example.anroid_networking.ASMGD1.Model.DataModel;
import com.example.anroid_networking.ASMGD1.Model.ResponseModel;
import com.example.anroid_networking.ASMGD1.ShopActivity;
import com.example.anroid_networking.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context context;
    private List<DataModel> listDataShop;
    private List<DataModel> listData;
    private int id;

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
        ImageView img_delete;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvID=itemView.findViewById(R.id.tv_id);
            tvName=itemView.findViewById(R.id.tv_name);
            tvPrice=itemView.findViewById(R.id.tv_price);
            tvstyle=itemView.findViewById(R.id.tv_style);
            img_delete=itemView.findViewById(R.id.img_delete);

            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogPesan=new AlertDialog.Builder(context);
                    dialogPesan.setMessage("Bạn có chắc chắn xoá");
                    dialogPesan.setCancelable(true);
                    id=Integer.parseInt(tvID.getText().toString());
                    dialogPesan.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData();
                            dialog.dismiss();
                            ((ShopActivity)context).reShopData();
                        }
                    });

                    dialogPesan.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            getData();
//                            dialog.dismiss();
                        }
                    });

                    dialogPesan.show();
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan=new AlertDialog.Builder(context);
                    dialogPesan.setMessage("Xem chi tiết sản phẩm");
                    dialogPesan.setCancelable(true);
                    id=Integer.parseInt(tvID.getText().toString());
                    dialogPesan.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getData();
                        }
                    });
                    dialogPesan.show();
                    return false;
                }
            });

//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    AlertDialog.Builder dialogPesan=new AlertDialog.Builder(context);
//                    dialogPesan.setMessage("Bạn có chắc chắn xoá");
//                    dialogPesan.setCancelable(true);
//                    id=Integer.parseInt(tvID.getText().toString());
//                    dialogPesan.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            deleteData();
//                            dialog.dismiss();
//                            ((ShopActivity)context).reShopData();
//                        }
//                    });
//
//                    dialogPesan.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//
//                    dialogPesan.show();
//                    return false;
//                }
//            });

        }

        private void deleteData(){
            APIRequestData ardData= RetroServer.koneRetrofit().create(APIRequestData.class);
            Call<ResponseModel> deleteData =ardData.ardCreateData(id);

            deleteData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                     int kode= response.body().getKode();
                     String persan=response.body().getPesan();

                    Toast.makeText(context,"Xoá thành công!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(context,"Server bị lỗi:"+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData= RetroServer.koneRetrofit().create(APIRequestData.class);
            Call<ResponseModel> GetData =ardData.ardGetData(id);

            GetData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode= response.body().getKode();
                    String persan=response.body().getPesan();

                    listDataShop=response.body().getData();

                    int varIdShop= listDataShop.get(0).getId();
                    String varNameShop=listDataShop.get(0).getName();
                    String varPriceShop=listDataShop.get(0).getPrice();
                    String varStyleShop=listDataShop.get(0).getStyle();

                    Intent i= new Intent(context, DetailActivity.class);
                    i.putExtra("xId",varIdShop);
                    i.putExtra("xName",varNameShop);
                    i.putExtra("xPrice",varPriceShop);
                    i.putExtra("xStyle",varStyleShop);
                    context.startActivity(i);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(context,"Server bị lỗi:"+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
