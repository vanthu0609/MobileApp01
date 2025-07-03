package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    TextView tvName, tvPrice, tvDescription;
    ImageView imgProduct;
    Button btnAddToCart;
    RadioGroup radioGroupStorage, radioGroupColor;

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
        radioGroupStorage = findViewById(R.id.radioGroupStorage);
        radioGroupColor = findViewById(R.id.radioGroupColor);

        // Lấy dữ liệu từ intent
        String name = getIntent().getStringExtra("name");
        String priceStr = getIntent().getStringExtra("price");
        String description = getIntent().getStringExtra("description");
        int imageResId = getIntent().getIntExtra("image", R.drawable.iphone); // fallback

        // Hiển thị dữ liệu
        tvName.setText(name);
        tvPrice.setText(priceStr);
        tvDescription.setText(description);
        imgProduct.setImageResource(imageResId);

        // Xử lý nút "Thêm vào giỏ hàng"
        btnAddToCart.setOnClickListener(v -> {
            // Lấy lựa chọn dung lượng
            int selectedStorageId = radioGroupStorage.getCheckedRadioButtonId();
            RadioButton selectedStorageBtn = findViewById(selectedStorageId);
            String selectedStorage = selectedStorageBtn != null ? selectedStorageBtn.getText().toString() : "";

            // Lấy lựa chọn màu sắc
            int selectedColorId = radioGroupColor.getCheckedRadioButtonId();
            RadioButton selectedColorBtn = findViewById(selectedColorId);
            String selectedColor = selectedColorBtn != null ? selectedColorBtn.getText().toString() : "";

            // Kiểm tra đã chọn đủ chưa
            if (selectedStorage.isEmpty() || selectedColor.isEmpty()) {
                Toast.makeText(ProductDetailActivity.this, "Vui lòng chọn dung lượng và màu sắc", Toast.LENGTH_SHORT).show();
                return;
            }

            int priceInt = 0;
            try {
                // Chuyển "29.990.000₫" → 29990000
                priceInt = Integer.parseInt(priceStr.replaceAll("[^0-9]", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Thêm vào giỏ hàng
            CartItem item = new CartItem(name, priceInt, imageResId, selectedStorage, selectedColor);
            CartManager.addItem(item);

            Toast.makeText(ProductDetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

            // Mở giỏ hàng
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
