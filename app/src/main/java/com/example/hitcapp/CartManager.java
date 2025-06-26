// com.example.hitcapp.CartManager.java
package com.example.hitcapp;

import java.util.ArrayList;

public class CartManager {
    private static final ArrayList<CartItem> cartItems = new ArrayList<>();

    public static void addItem(CartItem item) {
        cartItems.add(item);
    }

    public static ArrayList<CartItem> getItems() {
        return cartItems;
    }

    public static void clear() {
        cartItems.clear();
    }

    public static int getTotal() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    public static int getCount() {
        return cartItems.size();
    }
}
