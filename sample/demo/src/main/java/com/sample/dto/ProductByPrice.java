package com.sample.dto;

public class ProductByPrice {
    private String id;
    private String ma_phieu_nhap_kho;
    private String ma_san_pham;
    private Integer gia_nhap;
    private Integer so_luong;
    private String ten_san_pham;

    public ProductByPrice(String id, String ma_phieu_nhap_kho, String ma_san_pham, Integer gia_nhap, Integer so_luong) {
        this.id = id;
        this.ma_phieu_nhap_kho = ma_phieu_nhap_kho;
        this.ma_san_pham = ma_san_pham;
        this.gia_nhap = gia_nhap;
        this.so_luong = so_luong;
    }

    public ProductByPrice(String ten_san_pham, String ma_san_pham) {
        this.ten_san_pham = ten_san_pham;
        this.ma_san_pham = ma_san_pham;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa_phieu_nhap_kho() {
        return ma_phieu_nhap_kho;
    }

    public void setMa_phieu_nhap_kho(String ma_phieu_nhap_kho) {
        this.ma_phieu_nhap_kho = ma_phieu_nhap_kho;
    }

    public String getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(String ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

    public Integer getGia_nhap() {
        return gia_nhap;
    }

    public void setGia_nhap(Integer gia_nhap) {
        this.gia_nhap = gia_nhap;
    }

    public Integer getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(Integer so_luong) {
        this.so_luong = so_luong;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }
    
}
