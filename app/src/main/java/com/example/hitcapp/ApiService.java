package com.example.hitcapp;

import com.example.hitcapp.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<List<Product>> getAllProducts();
}
