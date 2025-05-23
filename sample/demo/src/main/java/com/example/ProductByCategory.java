package com.example;

public class ProductByCategory {
    private String ma_san_pham;
    private String ten_san_pham;

    public ProductByCategory(String ma_san_pham, String ten_san_pham){
        this.ma_san_pham = ma_san_pham;
        this.ten_san_pham = ten_san_pham;
    }

    public String getMaDanhMuc() {
        return ma_san_pham;
    }

    public void setMaDanhMuc(String ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

     public String getTenSanPham() {
        return ten_san_pham;
    }

    public void setTenSanPham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }
    
}
