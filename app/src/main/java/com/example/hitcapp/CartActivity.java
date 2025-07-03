package com.example.hitcapp;

import android.os.Bundle;
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
        cartItems = CartManager.getItems();

        // Khởi tạo adapter
        cartAdapter = new CartAdapter(this, cartItems, new CartAdapter.CartActionListener() {
            @Override
            public void onItemRemoved(int position) {
                CartManager.removeAt(position);
                cartItems.remove(position);
                cartAdapter.notifyDataSetChanged();
                updateTotal();
                Toast.makeText(CartActivity.this, "Đã xoá sản phẩm", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemCheckedChanged() {
                updateTotal();
            }
        });

        listView.setAdapter(cartAdapter);
        updateTotal();

        // Xử lý nút thanh toán các sản phẩm được chọn
        btnCheckout.setOnClickListener(v -> {
            ArrayList<String> paidProductNames = new ArrayList<>();

            for (int i = cartItems.size() - 1; i >= 0; i--) {
                CartItem item = cartItems.get(i);
                if (item.isSelected()) {
                    paidProductNames.add(item.getName());
                    CartManager.removeAt(i);
                    cartItems.remove(i);
                }
            }

            if (paidProductNames.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn sản phẩm để thanh toán!", Toast.LENGTH_SHORT).show();
            } else {
                StringBuilder message = new StringBuilder("Đã thanh toán: ");
                for (int i = 0; i < paidProductNames.size(); i++) {
                    message.append(paidProductNames.get(i));
                    if (i < paidProductNames.size() - 1) {
                        message.append(", ");
                    }
                }

                Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show();
            }

            cartAdapter.notifyDataSetChanged();
            updateTotal();
        });
    }

    // Tính tổng giá tiền từ các sản phẩm đã chọn
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
