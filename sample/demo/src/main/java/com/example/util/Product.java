package com.example.util;

public class Product {
    private int productId;
    private String productName;
    private String image;
    private int price;
    private int oldPrice;
    private String color;
    private String description;
    private String ram;
    private String ssd;
    private String gift;
    private double rating;

    // Constructors
    public Product() {}

    public Product(int productId, String productName, String image, int price, int oldPrice,
                   String color, String description, String ram, String ssd, String gift, double rating) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.oldPrice = oldPrice;
        this.color = color;
        this.description = description;
        this.ram = ram;
        this.ssd = ssd;
        this.gift = gift;
        this.rating = rating;
    }

    // Getters and Setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
