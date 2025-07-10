package com.example.hitcapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private Button btnViewCart;
    private RequestQueue mRequestQueue;

    // Gọi API sản phẩm
    private final String url = "https://fakestoreapi.com/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Kiểm tra đăng nhập
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupRecyclerView();
        getData();

        btnViewCart = findViewById(R.id.btnViewCart);
        btnViewCart.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, CartActivity.class));
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        mRequestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    productList.clear();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            String title = obj.getString("title");
                            double price = obj.getDouble("price");
                            String image = obj.getString("image");

                            productList.add(new Product(title, "$" + price, image));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "JSON parse error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e(TAG, "Volley error: " + error.toString());
                    Toast.makeText(this, "API error", Toast.LENGTH_SHORT).show();
                });

        mRequestQueue.add(jsonArrayRequest);
    }
}
