package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvTotal, tvTitle;
    private Button btnCheckout;

    private ArrayList<CartItem> cartItems;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ view
        listView = findViewById(R.id.listCart);
        tvTotal = findViewById(R.id.tvTotal);
        tvTitle = findViewById(R.id.tvCartTitle);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Lấy dữ liệu từ CartManager
        cartItems = new ArrayList<>(CartManager.getItems()); // tạo bản sao an toàn

        // Khởi tạo adapter
        cartAdapter = new CartAdapter(this, cartItems, new CartAdapter.CartActionListener() {
            @Override
            public void onItemRemoved(int position) {
                if (position >= 0 && position < cartItems.size()) {
                    CartItem removedItem = cartItems.remove(position);
                    CartManager.removeItem(removedItem); // xoá theo object an toàn
                    cartAdapter.notifyDataSetChanged();
                    updateTotal();
                    Toast.makeText(CartActivity.this, "Đã xoá sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemCheckedChanged() {
                updateTotal();
            }
        });

        listView.setAdapter(cartAdapter);
        updateTotal();

        // Xử lý nút thanh toán
        btnCheckout.setOnClickListener(v -> {
            ArrayList<String> paidProductNames = new ArrayList<>();
            ArrayList<CartItem> selectedItems = new ArrayList<>();
            int totalPrice = 0;

            for (CartItem item : cartItems) {
                if (item.isSelected()) {
                    paidProductNames.add(item.getName());
                    totalPrice += item.getPrice();
                    selectedItems.add(item);
                }
            }

            if (paidProductNames.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn sản phẩm để thanh toán!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Xóa các sản phẩm đã chọn
            cartItems.removeAll(selectedItems);
            CartManager.setItems(new ArrayList<>(cartItems)); // cập nhật giỏ hàng

            // Chuyển sang OrderActivity
            Log.d("CartActivity", "Chuyển sang OrderActivity với " + paidProductNames.size() + " sản phẩm");
            Intent intent = new Intent(CartActivity.this, OrderActivity.class);
            intent.putStringArrayListExtra("PAID_PRODUCTS", paidProductNames);
            intent.putExtra("TOTAL_PRICE", totalPrice);
            startActivity(intent);

            cartAdapter.notifyDataSetChanged();
            updateTotal();
        });
    }

    private void updateTotal() {
        int total = 0;
        for (CartItem item : cartItems) {
            if (item.isSelected()) {
                total += item.getPrice();
            }
        }

        tvTotal.setText(String.format("Tổng: %,d₫", total));
        tvTitle.setText("Giỏ hàng (" + cartItems.size() + " sản phẩm)");
    }
}
