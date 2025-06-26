package com.example.hitcapp;

public class CartItem { private String name;
private int price;

public CartItem(String name, int price) {
    this.name = name;
    this.price = price;
}

// Getter
public String getName() {
    return name;
}

public int getPrice() {
    return price;
}

// Setter (nếu cần)
public void setName(String name) {
    this.name = name;
}

public void setPrice(int price) {
    this.price = price;
}
}
