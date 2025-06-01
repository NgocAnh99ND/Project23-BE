package com.sample.model.dto;

public class ProductByPriceDescDTO {

    private int product_id;
    private String product_name;
    private String image;
    private int price;
    private int oldPrice;
    private String color;
    private String description;
    private String ram; 
    private String ssd;
    private String gift; 
    private double rating;


    public ProductByPriceDescDTO(int product_id, String product_name, String image, int price, int oldPrice, String color, String description, String ram, String ssd, String gift, double rating) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.image = image;
        this.price = price;
        this.oldPrice = oldPrice;
        this.color = color;
        this.description = description;
        this.ram = ram;
        this.ssd = ssd;
        this. gift = gift;
        this.rating = rating;
    }


    public int getProduct_id() {
        return product_id;
    }


    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }


    public String getProduct_name() {
        return product_name;
    }


    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public int getOldPrice() {
        return oldPrice;
    }


    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getRam() {
        return ram;
    }


    public void setRam(String ram) {
        this.ram = ram;
    }


    public String getSsd() {
        return ssd;
    }


    public void setSsd(String ssd) {
        this.ssd = ssd;
    }


    public String getGift() {
        return gift;
    }


    public void setGift(String gift) {
        this.gift = gift;
    }


    public double getRating() {
        return rating;
    }


    public void setRating(double rating) {
        this.rating = rating;
    }
}

