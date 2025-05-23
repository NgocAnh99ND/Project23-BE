package com.example;

public class DanhMuc {
    private String ma_danh_muc;
    private String ten_danh_muc;

    public DanhMuc(String ma_danh_muc, String ten_danh_muc) {
        this.ma_danh_muc = ma_danh_muc;
        this.ten_danh_muc= ten_danh_muc;
    }

    public String getMaDanhMuc() {
        return ma_danh_muc;
    }

    public void setMaDanhMuc(String ma_danh_muc) {
        this.ma_danh_muc = ma_danh_muc;
    }

    public String getTenDanhMuc() {
        return ten_danh_muc;
    }

    public void setTenDanhMuc(String ten_danh_muc) {
        this.ten_danh_muc = ten_danh_muc;
    }


}
