package com.project.huuthiendev.demophongtro.Model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HUUTHIENDEV on 10/13/2016.
 */

public class PhongTro implements Serializable {
    public String idNguoiDung;
    public Double latitude;
    public Double longtitue;
    public DiaChi diaChi;
    public Integer dienTich;
    public Double giaPhong;
    public String sdt;
    public String moTa;
    public Date ngayDang;
    public ArrayList<String> linkHinh;

    public PhongTro() {}

    public PhongTro(String idNguoiDung, Double latitude, Double longtitue, DiaChi diaChi, Integer dienTich, Double giaPhong, String sdt, String moTa, Date ngayDang, ArrayList<String> linkHinh) {
        this.idNguoiDung = idNguoiDung;
        this.latitude = latitude;
        this.longtitue = longtitue;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.giaPhong = giaPhong;
        this.sdt = sdt;
        this.moTa = moTa;
        this.ngayDang = ngayDang;
        this.linkHinh = linkHinh;
    }
}
