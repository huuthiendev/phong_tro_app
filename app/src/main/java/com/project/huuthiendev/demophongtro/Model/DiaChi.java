package com.project.huuthiendev.demophongtro.Model;

import java.io.Serializable;

/**
 * Created by HUUTHIENDEV on 10/13/2016.
 */

public class DiaChi implements Serializable {
    public String tinhThanh;
    public String quanHuyen;
    public String chitietDiaChi;

    DiaChi(){}

    public DiaChi(String tinhThanh, String quanHuyen, String chitietDiaChi) {
        this.tinhThanh = tinhThanh;
        this.quanHuyen = quanHuyen;
        this.chitietDiaChi = chitietDiaChi;
    }
}
