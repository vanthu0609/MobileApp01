package com.example.hitcapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FakeStoreApi {
    @GET("products")
    Call<List<Product>> getProducts();
}
