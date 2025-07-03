package com.example.hitcapp;

import java.util.ArrayList;

public class CartManager {
    private static final ArrayList<CartItem> cartItems = new ArrayList<>();

    // Thêm sản phẩm vào giỏ
    public static void addItem(CartItem item) {
        cartItems.add(item);
    }

    // Trả về toàn bộ sản phẩm
    public static ArrayList<CartItem> getItems() {
        return cartItems;
    }

    // Xoá tất cả sản phẩm
    public static void clear() {
        cartItems.clear();
    }

    // Tính tổng tiền
    public static int getTotal() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    // Đếm số sản phẩm
    public static int getCount() {
        return cartItems.size();
    }

    // ✅ Xoá sản phẩm theo vị trí
    public static void removeAt(int index) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
    }

    // ✅ Xoá sản phẩm theo đối tượng (nếu cần)
    public static void removeItem(CartItem item) {
        cartItems.remove(item);
    }
}
