package com.project.huuthiendev.demophongtro.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.huuthiendev.demophongtro.Adapter.ViewPagerSlideAdapter;
import com.project.huuthiendev.demophongtro.Fragment.FragmentSlide;
import com.project.huuthiendev.demophongtro.Model.PhongTro;
import com.project.huuthiendev.demophongtro.R;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager vpSlide;
    TextView[] tvDots;
    LinearLayout layoutDots;
    List<Fragment> listFragments;
    PhongTro pt;
    Button btnBanDo, btnGoiCT, btnNhanTinCT;
    TextView tvTenChuPhongTro, tvNgayDangCT, tvDiaChiCT, tvDienTichCT, tvGiaPhongCT, tvLienHe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết");
        AnhXa();

        Intent intent = getIntent();
        pt = (PhongTro) intent.getSerializableExtra("PhongTro");
        ArrayList<String> hinh = pt.linkHinh;
        listFragments = new ArrayList<>();

        for (int i = 0; i < hinh.size(); i++) {
            FragmentSlide slide = new FragmentSlide();

            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", hinh.get(i));

            slide.setArguments(bundle);
            listFragments.add(slide);
        }

        tvTenChuPhongTro.setText(pt.idNguoiDung);
        tvNgayDangCT.setText("Ngày " + pt.ngayDang.getDate() + " Tháng " + pt.ngayDang.getMonth() + " Năm " + pt.ngayDang.getYear());
        tvDiaChiCT.setText(pt.diaChi.chitietDiaChi + ", " + pt.diaChi.quanHuyen + ", " + pt.diaChi.tinhThanh);
        tvDienTichCT.setText(pt.dienTich + " mét vuông");
        tvGiaPhongCT.setText(pt.giaPhong + " triệu");
        tvLienHe.setText(pt.sdt);

        btnNhanTinCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","Nhập nội dung bạn muốn hỏi về phòng trọ tại đây!!!");
                intent.setType("text/plain");
                intent.setData(Uri.parse("sms:" + pt.sdt));
                startActivity(intent);
            }
        });

        btnGoiCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + pt.sdt));
                startActivity(intent);
            }
        });

        ViewPagerSlideAdapter viewPagerSlideAdapter = new ViewPagerSlideAdapter(getSupportFragmentManager(), listFragments);
        vpSlide.setAdapter(viewPagerSlideAdapter);
        viewPagerSlideAdapter.notifyDataSetChanged();

        addDotSlide(0);
        vpSlide.setOnPageChangeListener(this);

        btnBanDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_map = new Intent(DetailActivity.this , MapActivity.class);
                intent_map.putExtra("luachon","chitiet");

                Bundle bundle = new Bundle();
                bundle.putDouble("lang", pt.latitude);
                bundle.putDouble("long", pt.longtitue);
                bundle.putDouble("giaphong", pt.giaPhong);
                bundle.putString("diachi", pt.diaChi.chitietDiaChi);

                intent_map.putExtras(bundle);
                startActivity(intent_map);
            }
        });
    }

    private void addDotSlide(int position){
        tvDots = new TextView[listFragments.size()];
        layoutDots.removeAllViews();

        for (int i = 0; i < listFragments.size() ; i++) {
            tvDots[i] = new TextView(this);
            tvDots[i].setText(Html.fromHtml("&#8226;"));
            tvDots[i].setTextSize(40);
            tvDots[i].setTextColor(getIdColor(R.color.colorDotSlider));
            layoutDots.addView(tvDots[i]);
        }

        tvDots[position].setTextColor(getIdColor(R.color.colorWhite));
    }

    private int getIdColor(int idColor) {
        int color = 0;
        if(Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, idColor);
        } else {
            color = getResources().getColor(idColor);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addDotSlide(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AnhXa() {
        vpSlide = (ViewPager) findViewById(R.id.vpSlide);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        btnBanDo = (Button) findViewById(R.id.btnBanDo);
        btnGoiCT = (Button) findViewById(R.id.btnGoiCT);
        btnNhanTinCT = (Button) findViewById(R.id.btnNhanTinCT);
        tvTenChuPhongTro = (TextView) findViewById(R.id.tvNgayDangCT);
        tvNgayDangCT = (TextView) findViewById(R.id.tvNgayDangCT);
        tvDiaChiCT = (TextView) findViewById(R.id.tvDiaChiCT);
        tvDienTichCT = (TextView) findViewById(R.id.tvDienTichCT);
        tvGiaPhongCT = (TextView) findViewById(R.id.tvGiaPhongCT);
        tvLienHe = (TextView) findViewById(R.id.tvLienHe);
    }
}
