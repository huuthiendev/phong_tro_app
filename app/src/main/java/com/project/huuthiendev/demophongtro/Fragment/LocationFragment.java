package com.project.huuthiendev.demophongtro.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.huuthiendev.demophongtro.Activity.MapActivity;
import com.project.huuthiendev.demophongtro.R;

/**
 * Created by HUUTHIENDEV on 11/2/2016.
 */

public class LocationFragment extends Fragment {
    private TextView tvGiaPhong, tvDienTich, tvBanKinh;
    private SeekBar sbGiaPhong, sbDienTich, sbBanKinh;
    private Button btnTimPhongTro;
    private Double banKinh = 0D, giaPhong = 0D;
    private int dienTich = 0;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_location, container, false);
        tvGiaPhong = (TextView) v.findViewById(R.id.tvGiaPhong);
        tvDienTich = (TextView) v.findViewById(R.id.tvDienTich);
        tvBanKinh = (TextView) v.findViewById(R.id.tvBanKinh);
        sbGiaPhong = (SeekBar) v.findViewById(R.id.sbGiaPhong);
        sbDienTich = (SeekBar) v.findViewById(R.id.sbDienTich);
        sbBanKinh = (SeekBar) v.findViewById(R.id.sbBanKinh);
        btnTimPhongTro = (Button) v.findViewById(R.id.btnTimPhongTro);

        sbBanKinh.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 1:
                        tvBanKinh.setText("Bán kính: 500 m");
                        banKinh = 0.5D;
                        break;
                    case 2:
                        tvBanKinh.setText("Bán kính: 1 km");
                        banKinh = 1D;
                        break;
                    case 3:
                        tvBanKinh.setText("Bán kính: 2 km");
                        banKinh = 2D;
                        break;
                    case 4:
                        tvBanKinh.setText("Bán kính: 4 km");
                        banKinh = 4D;
                        break;
                    case 5:
                        tvBanKinh.setText("Bán kính: 8 km");
                        banKinh = 8D;
                        break;
                    case 6:
                        tvBanKinh.setText("Bán kính: 15 km");
                        banKinh = 15D;
                        break;
                    case 7:
                        tvBanKinh.setText("Bán kính: 20 km");
                        banKinh = 20D;
                        break;
                    case 8:
                        tvBanKinh.setText("Bán kính: 30 km");
                        banKinh = 30D;
                        break;
                    default:
                        tvBanKinh.setText("Bán kính: tất cả");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbGiaPhong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 1:
                        tvGiaPhong.setText("Giá phòng: trên 500 nghìn");
                        giaPhong = 0.5D;
                        break;
                    case 2:
                        tvGiaPhong.setText("Giá phòng: trên 1 triệu");
                        giaPhong = 1D;
                        break;
                    case 3:
                        tvGiaPhong.setText("Giá phòng: trên 1.5 triệu");
                        giaPhong = 1.5D;
                        break;
                    case 4:
                        tvGiaPhong.setText("Giá phòng: trên 2 triệu");
                        giaPhong = 2D;
                        break;
                    case 5:
                        tvGiaPhong.setText("Giá phòng: trên 2.5 triệu");
                        giaPhong = 2.5D;
                        break;
                    case 6:
                        tvGiaPhong.setText("Giá phòng: trên 3 triệu");
                        giaPhong = 3D;
                        break;
                    case 7:
                        tvGiaPhong.setText("Giá phòng: trên 3.5 triệu");
                        giaPhong = 3.5D;
                        break;
                    case 8:
                        tvGiaPhong.setText("Giá phòng: trên 4 triệu");
                        giaPhong = 4D;
                        break;
                    case 9:
                        tvGiaPhong.setText("Giá phòng: trên 4.5 triệu");
                        giaPhong = 4.5D;
                        break;
                    case 10:
                        tvGiaPhong.setText("Giá phòng: trên 5 triệu");
                        giaPhong = 5D;
                        break;
                    default:
                        tvGiaPhong.setText("Giá phòng: tất cả");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbDienTich.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 1:
                        tvDienTich.setText("Diện tích: trên 5 mét vuông");
                        dienTich = 5;
                        break;
                    case 2:
                        tvDienTich.setText("Diện tích: trên 10 mét vuông");
                        dienTich = 10;
                        break;
                    case 3:
                        tvDienTich.setText("Diện tích: trên 15 mét vuông");
                        dienTich = 15;
                        break;
                    case 4:
                        tvDienTich.setText("Diện tích: trên 20 mét vuông");
                        dienTich = 20;
                        break;
                    case 5:
                        tvDienTich.setText("Diện tích: trên 25 mét vuông");
                        dienTich = 25;
                        break;
                    case 6:
                        tvDienTich.setText("Diện tích: trên 30 mét vuông");
                        dienTich = 30;
                        break;
                    case 7:
                        tvDienTich.setText("Diện tích: trên 35 mét vuông");
                        dienTich = 35;
                        break;
                    case 8:
                        tvDienTich.setText("Diện tích: trên 40 mét vuông");
                        dienTich = 40;
                        break;
                    case 9:
                        tvDienTich.setText("Diện tích: trên 45 mét vuông");
                        dienTich = 45;
                        break;
                    case 10:
                        tvDienTich.setText("Diện tích: trên 50 mét vuông");
                        dienTich = 50;
                        break;
                    default:
                        tvDienTich.setText("Diện tích: tất cả");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnTimPhongTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("luachon", "xungquanh");

                Bundle bundle = new Bundle();
                bundle.putDouble("bankinh", banKinh);
                bundle.putDouble("giaphong", giaPhong);
                bundle.putInt("dientich", dienTich);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return v;
    }
}
