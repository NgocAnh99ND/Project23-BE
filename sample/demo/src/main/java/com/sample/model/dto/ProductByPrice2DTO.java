package com.sample.model.dto;

public class ProductByPrice2DTO {
    private String ten_san_pham;
    private String ma_san_pham;

    public ProductByPrice2DTO( String ten_san_pham, String ma_san_pham ) {
        this.ten_san_pham = ten_san_pham;
        this.ma_san_pham = ma_san_pham;
    }
    public String getTen_san_pham() {
        return ten_san_pham;
    }
    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }
    public String getMa_san_pham() {
        return ma_san_pham;
    }
    public void setMa_san_pham(String ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }
}
