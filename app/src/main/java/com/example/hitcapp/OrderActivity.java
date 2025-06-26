package com.example.hitcapp;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    TextView tvProductName, tvProductPrice;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Ánh xạ view theo đúng ID trong XML
        tvProductName = findViewById(R.id.textViewOrderProductName);
        tvProductPrice = findViewById(R.id.textViewOrderPrice);
        btnConfirm = findViewById(R.id.buttonConfirmOrder);

        // Lấy dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");

        // Gán dữ liệu vào TextView
        tvProductName.setText(name != null && !name.isEmpty() ? name : "Sản phẩm không xác định");
        tvProductPrice.setText(price != null && !price.isEmpty() ? price : "0₫");

        // Sự kiện nút xác nhận
        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(OrderActivity.this, "Cảm ơn bạn đã đặt hàng!", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại màn hình trước
        });
    }
}
