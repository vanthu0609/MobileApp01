package com.example.hitcapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hitcapp.*;

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

        listView = findViewById(R.id.listCart);
        tvTotal = findViewById(R.id.tvTotal);
        tvTitle = findViewById(R.id.tvCartTitle);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Lấy danh sách sản phẩm từ CartManager
        cartItems = CartManager.getItems();

        // Khởi tạo adapter
        cartAdapter = new CartAdapter(this, cartItems);
        listView.setAdapter(cartAdapter);

        updateTotal();

        btnCheckout.setOnClickListener(v -> {
            Toast.makeText(this, "Cảm ơn bạn đã mua hàng!", Toast.LENGTH_SHORT).show();
            CartManager.clear();
            cartAdapter.notifyDataSetChanged();
            updateTotal();
        });
    }

    private void updateTotal() {
        int total = CartManager.getTotal();
        tvTotal.setText(String.format("Tổng: %,d₫", total));

        if (tvTitle != null) {
            tvTitle.setText("Giỏ hàng (" + CartManager.getCount() + " sản phẩm)");
        }
    }
}
