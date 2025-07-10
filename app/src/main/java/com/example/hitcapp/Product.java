package com.example.hitcapp;

public class Product {
    private String name;
    private String price;
    private int imageResId;      // ảnh local (R.drawable)
    private String imageUrl;     // ảnh từ URL (API)
    private String description;

    // ✅ Constructor cho ảnh từ drawable
    public Product(String name, String price, int imageResId, String description) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.imageUrl = null;
        this.description = description;
    }

    // ✅ Constructor đơn giản với drawable không có mô tả
    public Product(String name, String price, int imageResId) {
        this(name, price, imageResId, "");
    }

    // ✅ Constructor cho ảnh từ URL (API)
    public Product(String name, String price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageResId = 0;
        this.imageUrl = imageUrl;
        this.description = "";
    }

    // 🟢 Getter
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    // 🔵 Kiểm tra xem có dùng ảnh URL không
    public boolean hasImageUrl() {
        return imageUrl != null && !imageUrl.isEmpty();
    }
}
