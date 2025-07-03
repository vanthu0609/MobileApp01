package com.example.hitcapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CartItem> cartItems;
    private CartActionListener listener;

    public interface CartActionListener {
        void onItemRemoved(int position);
        void onItemCheckedChanged(); // Chỉ cần báo có thay đổi
    }

    public CartAdapter(Context context, ArrayList<CartItem> cartItems, CartActionListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvProductName;
        TextView tvProductPrice;
        TextView tvProductOptions;
        CheckBox cbSelectToPay;
        ImageButton btnRemove;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
            holder = new ViewHolder();
            holder.imgProduct = convertView.findViewById(R.id.imgProduct);
            holder.tvProductName = convertView.findViewById(R.id.tvProductName);
            holder.tvProductPrice = convertView.findViewById(R.id.tvProductPrice);
            holder.tvProductOptions = convertView.findViewById(R.id.tvProductOptions);
            holder.cbSelectToPay = convertView.findViewById(R.id.cbSelectToPay);
            holder.btnRemove = convertView.findViewById(R.id.btnRemove);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartItem item = cartItems.get(position);

        holder.tvProductName.setText(item.getName());
        holder.tvProductPrice.setText(String.format("%,d₫", item.getPrice()));
        holder.tvProductOptions.setText(item.getStorage() + " - " + item.getColor());
        holder.imgProduct.setImageResource(item.getImageResId());

        // Khôi phục trạng thái checkbox
        holder.cbSelectToPay.setOnCheckedChangeListener(null); // Ngăn gọi lại khi setChecked
        holder.cbSelectToPay.setChecked(item.isSelected());

        // Khi người dùng chọn checkbox
        holder.cbSelectToPay.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
            if (listener != null) {
                listener.onItemCheckedChanged(); // Báo lên Activity để cập nhật tổng tiền
            }
        });

        // Xử lý xoá
        holder.btnRemove.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemRemoved(position);
            }
        });

        return convertView;
    }
}
