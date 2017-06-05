package com.project.huuthiendev.demophongtro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.huuthiendev.demophongtro.Activity.DetailActivity;
import com.project.huuthiendev.demophongtro.Model.PhongTro;
import com.project.huuthiendev.demophongtro.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

/**
 * Created by HUUTHIENDEV on 10/15/2016.
 */

public class PhongTroAdapter extends RecyclerView.Adapter<PhongTroAdapter.PhongTroViewHolder> {
    Context context;
    List<PhongTro> list;
    PhongTro pt;

    public PhongTroAdapter(Context context, List<PhongTro> list){
        this.context = context;
        this.list = list;
    }

    public class PhongTroViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvNgayDang, tvDienTich, tvDiaChi, tvGiaPhong;
        ImageView imgDaiDien, imgHinh;
        CardView cvPhongTro;

        public PhongTroViewHolder(View itemView) {
            super(itemView);
            tvTen = (TextView) itemView.findViewById(R.id.tvTen);
            tvNgayDang = (TextView) itemView.findViewById(R.id.tvNgayDang);
            tvDienTich = (TextView) itemView.findViewById(R.id.tvDienTich);
            tvDiaChi = (TextView) itemView.findViewById(R.id.tvDiaChi);
            tvGiaPhong = (TextView) itemView.findViewById(R.id.tvGiaPhong);
            imgDaiDien = (ImageView) itemView.findViewById(R.id.imgDaiDien);
            imgHinh = (ImageView) itemView.findViewById(R.id.imgHinh);
            cvPhongTro = (CardView) itemView.findViewById(R.id.cvPhongTro);
        }
    }

    @Override
    public PhongTroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_recyler_view_item, parent, false);

        PhongTroViewHolder viewHolder = new PhongTroViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhongTroViewHolder holder, final int position) {
        pt = list.get(position);
        holder.tvTen.setText(pt.idNguoiDung);

        Date date = list.get(position).ngayDang;
        holder.tvNgayDang.setText("Ngày " + date.getDate() + " Tháng " + date.getMonth() + " Năm " + date.getYear());

        holder.tvDiaChi.setText("Địa chỉ: " + pt.diaChi.chitietDiaChi + ", " + pt.diaChi.quanHuyen + ", " + pt.diaChi.tinhThanh);
        holder.tvGiaPhong.setText(pt.giaPhong + " triệu");
        holder.tvDienTich.setText("Diện tích: " + pt.dienTich + " mét vuông");
        Picasso.with(context).load(list.get(position).linkHinh.get(0)).into(holder.imgHinh);

        holder.cvPhongTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("PhongTro", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
