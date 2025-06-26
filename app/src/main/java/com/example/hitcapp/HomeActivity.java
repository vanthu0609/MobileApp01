package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Nút "Xem tất cả sản phẩm"
        Button btnViewAll = findViewById(R.id.buttonViewAll);
        btnViewAll.setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng 'Xem tất cả sản phẩm' đang phát triển", Toast.LENGTH_SHORT).show();
        });

        // SearchView
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(HomeActivity.this, "Đang tìm: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Sản phẩm 1 - click vào khung sản phẩm
        LinearLayout product1 = findViewById(R.id.product1);
        product1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("name", "iPhone 16 Pro Max");
            intent.putExtra("price", "29.990.000₫");
            intent.putExtra("description", "iPhone 16 Pro Max mới nhất với chip A18 Bionic, camera cải tiến...");
            intent.putExtra("image", R.drawable.iphone);
            startActivity(intent);
        });

        // Sản phẩm 2
        LinearLayout product2 = findViewById(R.id.product2);
        product2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("name", "Samsung Galaxy S25 Ultra");
            intent.putExtra("price", "25.490.000₫");
            intent.putExtra("description", "Samsung Galaxy S25 Ultra với màn hình 6.9 inch, camera 200MP.");
            intent.putExtra("image", R.drawable.samsung);
            startActivity(intent);
        });

        // Sản phẩm 3
        LinearLayout product3 = findViewById(R.id.product3);
        product3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("name", "Redmi Note 14");
            intent.putExtra("price", "7.990.000₫");
            intent.putExtra("description", "Redmi Note 14 pin khủng 6000mAh, Snapdragon 8 Gen 2.");
            intent.putExtra("image", R.drawable.redmi);
            startActivity(intent);
        });

        // ===== Xử lý nút "Xem chi tiết sản phẩm" cho từng sản phẩm =====

        Button btnBuyNow1 = findViewById(R.id.btnBuyNow1);
        btnBuyNow1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("name", "iPhone 16 Pro Max");
            intent.putExtra("price", "29.990.000₫");
            intent.putExtra("description", "iPhone 16 Pro Max mới nhất với chip A18 Bionic, camera cải tiến...");
            intent.putExtra("image", R.drawable.iphone);
            startActivity(intent);
        });

        Button btnBuyNow2 = findViewById(R.id.btnBuyNow2);
        btnBuyNow2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("name", "Samsung Galaxy S25 Ultra");
            intent.putExtra("price", "25.490.000₫");
            intent.putExtra("description", "Samsung Galaxy S25 Ultra với màn hình 6.9 inch, camera 200MP.");
            intent.putExtra("image", R.drawable.samsung);
            startActivity(intent);
        });

        Button btnBuyNow3 = findViewById(R.id.btnBuyNow3);
        btnBuyNow3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("name", "Redmi Note 14");
            intent.putExtra("price", "7.990.000₫");
            intent.putExtra("description", "Redmi Note 14 pin khủng 6000mAh, Snapdragon 8 Gen 2.");
            intent.putExtra("image", R.drawable.redmi);
            startActivity(intent);
        });
        ImageView imgCart = findViewById(R.id.imgCart);
        imgCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

    }
}
