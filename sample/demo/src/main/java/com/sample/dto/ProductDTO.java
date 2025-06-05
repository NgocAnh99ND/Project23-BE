package com.sample.dto;

public class ProductDTO {
    private Integer productId;
    private String productName;
    private String image;
    private Integer price;
    private Integer oldPrice;
    private String color;
    private String description;
    private String ram;
    private String ssd;
    private String gift;
    private Double rating;

    // Constructors
    public ProductDTO() {
    }

    public ProductDTO(Integer productId, String productName, String image, Integer price, Integer oldPrice,
            String color, String description, String ram, String ssd, String gift, Double rating) {
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
