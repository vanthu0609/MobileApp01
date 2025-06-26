package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {

    TextView tvName, tvPrice, tvDescription;
    ImageView imgProduct;
    Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Ánh xạ view
        tvName = findViewById(R.id.textViewProductName);
        tvPrice = findViewById(R.id.textViewProductPrice);
        tvDescription = findViewById(R.id.textViewProductDescription);
        imgProduct = findViewById(R.id.imageViewProduct);
        btnAddToCart = findViewById(R.id.buttonAddToCart);

        // Lấy dữ liệu từ intent
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String description = getIntent().getStringExtra("description");
        int imageResId = getIntent().getIntExtra("image", R.drawable.iphone);

        // Hiển thị dữ liệu
        tvName.setText(name);
        tvPrice.setText(price);
        tvDescription.setText(description);
        imgProduct.setImageResource(imageResId);

        // Sau khi nhấn "Thêm vào giỏ hàng"
        btnAddToCart.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, com.example.hitcapp.CartActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("price", price);
            startActivity(intent);
        });
        btnAddToCart.setOnClickListener(v -> {
            int priceInt = 0;
            try {
                // Chuyển "29.990.000₫" → 29990000
                priceInt = Integer.parseInt(price.replaceAll("[^0-9]", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }

            CartItem item = new CartItem(name, priceInt);
            CartManager.addItem(item);

            Toast.makeText(ProductDetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

            // Mở CartActivity
            Intent intent = new Intent(ProductDetailActivity.this, com.example.hitcapp.CartActivity.class);
            startActivity(intent);
        });

    }
}
