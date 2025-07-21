package com.example.hitcapp;

<<<<<<< HEAD
import android.content.Context;
=======
>>>>>>> b96b1de3c6ee900596c4f346e14744cd671ec583
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

=======
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
>>>>>>> b96b1de3c6ee900596c4f346e14744cd671ec583
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

<<<<<<< HEAD
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView priceTextView;
        public ImageView productImage;
        public Button btnDetail;
        public Button btnBuyNow;

        public ProductViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.textName);
            priceTextView = view.findViewById(R.id.textPrice);
            productImage = view.findViewById(R.id.imageProduct);
            btnDetail = view.findViewById(R.id.btnDetail);
            btnBuyNow = view.findViewById(R.id.btnBuyNow);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(product.getPrice());

        // 🟢 Load ảnh từ URL nếu có, ngược lại dùng drawable
        if (product.hasImageUrl()) {
            Glide.with(context)
                    .load(product.getImageUrl())
                    .placeholder(R.drawable.placeholder) // tùy chọn ảnh mặc định
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(product.getImageResId());
        }

        // Mở chi tiết
        View.OnClickListener detailClick = v -> openProductDetail(product);
        holder.productImage.setOnClickListener(detailClick);
        holder.btnDetail.setOnClickListener(detailClick);

        // Mua ngay
        holder.btnBuyNow.setOnClickListener(v -> openCheckoutDirectly(product));
    }

    private void openProductDetail(Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("name", product.getName());
        intent.putExtra("price", product.getPrice());
        intent.putExtra("description", product.getDescription());

        if (product.hasImageUrl()) {
            intent.putExtra("imageUrl", product.getImageUrl());
        } else {
            intent.putExtra("imageResId", product.getImageResId());
        }

        context.startActivity(intent);
    }

    private void openCheckoutDirectly(Product product) {
        try {
            // ⚠ Chuyển đổi giá về số nếu là "30.000.000đ" hay "$29.99"
            String cleanPrice = product.getPrice().replaceAll("[^0-9.]", "");
            int priceValue = (int) Double.parseDouble(cleanPrice);
            int quantity = 1;
            int totalAmount = priceValue * quantity;

            Intent intent = new Intent(context, CheckoutActivity.class);
            intent.putExtra("total_amount", String.valueOf(totalAmount));
            intent.putExtra("name", product.getName());
            intent.putExtra("quantity", quantity);
            intent.putExtra("price", product.getPrice());
            intent.putExtra("description", product.getDescription());

            if (product.hasImageUrl()) {
                intent.putExtra("imageUrl", product.getImageUrl());
            } else {
                intent.putExtra("imageResId", product.getImageResId());
            }

            context.startActivity(intent);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
=======
    private final List<ProductModel> productList;

    public ProductAdapter(HomeActivity homeActivity, List<ProductModel> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel product = productList.get(position);

        holder.tvTitle.setText(product.getTitle());
        holder.tvPrice.setText("$" + product.getPrice());
        holder.tvDesc.setText(product.getDescription());

        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.imgProduct);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
            intent.putExtra("name", product.getTitle());
            intent.putExtra("price", "$" + product.getPrice());
            intent.putExtra("description", product.getDescription());
            intent.putExtra("imageUrl", product.getImageUrl());
            holder.itemView.getContext().startActivity(intent);
        });
>>>>>>> b96b1de3c6ee900596c4f346e14744cd671ec583
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
<<<<<<< HEAD
=======

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice, tvDesc;
        ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
>>>>>>> b96b1de3c6ee900596c4f346e14744cd671ec583
}
