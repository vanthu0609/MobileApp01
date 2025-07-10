package com.example.hitcapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private EditText edtName, edtPhone, edtAddress, edtNote;
    private RadioGroup radioGroupPayment;
    private TextView tvOrderTotal, tvOrderInfo;
    private Button btnPlaceOrder;
    private LinearLayout layoutBankLogos;

    private ArrayList<String> paidProducts;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Ánh xạ view
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtNote = findViewById(R.id.edtNote);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        tvOrderTotal = findViewById(R.id.tvOrderTotal);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        tvOrderInfo = findViewById(R.id.tvOrderInfo);
        layoutBankLogos = findViewById(R.id.layoutBankLogos);

        // Nhận dữ liệu từ CartActivity
        paidProducts = getIntent().getStringArrayListExtra("PAID_PRODUCTS");
        totalPrice = getIntent().getIntExtra("TOTAL_PRICE", 0);

        // Hiển thị sản phẩm
        StringBuilder info = new StringBuilder("Sản phẩm đã chọn:\n");
        for (String name : paidProducts) {
            info.append("- ").append(name).append("\n");
        }
        tvOrderInfo.setText(info.toString());
        tvOrderTotal.setText(String.format("Tổng tiền: %,d₫", totalPrice));

        // Xử lý thay đổi hình thức thanh toán
        radioGroupPayment.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbBanking) {
                layoutBankLogos.setVisibility(View.VISIBLE);
                showBankTransferPopup(); // Hiện popup hướng dẫn
            } else {
                layoutBankLogos.setVisibility(View.GONE);
            }
        });

        // Xử lý đặt hàng
        btnPlaceOrder.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String note = edtNote.getText().toString().trim();

            int selectedId = radioGroupPayment.getCheckedRadioButtonId();
            String paymentMethod = selectedId == R.id.rbCOD ? "COD" :
                    selectedId == R.id.rbBanking ? "Chuyển khoản" : "";

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || paymentMethod.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Hiện Dialog cảm ơn
            new AlertDialog.Builder(this)
                    .setTitle("Cảm ơn bạn đã đặt hàng!")
                    .setMessage("Đơn hàng sẽ được xử lý trong thời gian sớm nhất.\nChúng tôi sẽ liên hệ qua SĐT: " + phone)
                    .setPositiveButton("OK", (dialog, which) -> finish())
                    .setCancelable(false)
                    .show();
        });
    }

    private void showBankTransferPopup() {
        String message = "📌 Hướng dẫn chuyển khoản:\n\n"
                + "Ngân hàng: MB Bank\n"
                + "Chủ tài khoản: NGUYEN VAN A\n"
                + "Số tài khoản: 123456789\n"
                + "Nội dung: [Tên + SĐT]\n\n"
                + "📍 Lưu ý: Chúng tôi sẽ xác nhận khi nhận được chuyển khoản.";

        new AlertDialog.Builder(this)
                .setTitle("Hướng dẫn chuyển khoản")
                .setMessage(message)
                .setPositiveButton("Đã hiểu", null)
                .show();
    }
}
