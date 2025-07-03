package com.example.hitcapp;

public class CartItem {
    private String name;
    private int price;
    private int imageResId; // ảnh từ drawable
    private String storage;
    private String color;
    private boolean selected; // ✅ Trạng thái được chọn

    // Constructor đầy đủ
    public CartItem(String name, int price, int imageResId, String storage, String color) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.storage = storage;
        this.color = color;
        this.selected = false; // Mặc định chưa được chọn
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getStorage() {
        return storage;
    }

    public String getColor() {
        return color;
    }

    public boolean isSelected() {
        return selected;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
