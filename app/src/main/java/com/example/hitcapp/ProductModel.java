package com.example.hitcapp;

public class ProductModel {
    private final String title;
    private final String price;
    private final String description;
    private final String imageUrl;

    public ProductModel(String title, String price, String description, String imageUrl) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
