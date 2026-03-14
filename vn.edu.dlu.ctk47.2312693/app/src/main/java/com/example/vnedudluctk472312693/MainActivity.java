package com.example.vnedudluctk472312693;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvTienMat, tvNganHang, tvDate;
    EditText edtAmount;
    Spinner spinnerAccount, spinnerCategory;
    Button btnSave;
    RecyclerView recyclerViewHistory;
    TransactionDAO transactionDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvTienMat = findViewById(R.id.tvTienMat);
        tvNganHang = findViewById(R.id.tvNganHang);
        tvDate = findViewById(R.id.tvDate);
        edtAmount = findViewById(R.id.edtAmount);
        spinnerAccount = findViewById(R.id.spinnerAccount);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnSave);

        //Khởi tạo Database
        transactionDAO = new TransactionDAO(this);
        transactionDAO.open();

        loadSpinners();
        loadBalances();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Lấy dữ liệu từ giao diện
                String amountStr = edtAmount.getText().toString();
                String dateStr = tvDate.getText().toString();

                // Kiểm tra xem người dùng đã nhập số tiền chưa
                if (amountStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số tiền!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountStr);

                // Lấy đối tượng Tài khoản và Danh mục đang được chọn trên Spinner
                Account selectedAccount = (Account) spinnerAccount.getSelectedItem();
                Category selectedCategory = (Category) spinnerCategory.getSelectedItem();

                // 2. Tạo đối tượng giao dịch và lưu vào Database
                TransactionRecord newTrans = new TransactionRecord(amount, dateStr, selectedCategory.getCat_ID(), selectedAccount.getAcc_id());
                long result = transactionDAO.insertTransaction(newTrans);

                // 3. Nếu lưu thành công thì xử lý cập nhật
                if (result != -1) {
                    // --- CẬP NHẬT SỐ DƯ TÀI KHOẢN ---
                    double currentBalance = selectedAccount.getBalance();

                    if (selectedCategory.getType() == 1) {
                        // Nếu type = 1 (Thu) -> Cộng tiền
                        selectedAccount.setBalance(currentBalance + amount);
                    } else {
                        // Nếu type = 0 (Chi) -> Trừ tiền
                        selectedAccount.setBalance(currentBalance - amount);
                    }

                    // Lưu số dư mới vào Database
                    transactionDAO.updateAccount(selectedAccount);
                    loadBalances(); // Cập nhật lại 2 ô hiển thị tiền mặt/ngân hàng

                    // Xóa trắng ô nhập tiền để nhập lần sau
                    edtAmount.setText("");

                    Toast.makeText(MainActivity.this, "Đã lưu giao dịch!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi: Không thể lưu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loadSpinners() {
        // Lấy danh sách từ database
        ArrayList<Account> accountList = transactionDAO.getAllAccounts();
        ArrayList<Category> categoryList = transactionDAO.getAllCategories();

        // Tạo Adapter cho Account Spinner
        ArrayAdapter<Account> accountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, accountList);
        spinnerAccount.setAdapter(accountAdapter);

        // Tạo Adapter cho Category Spinner
        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        spinnerCategory.setAdapter(categoryAdapter);
    }

    // Hàm hiển thị số dư
    private void loadBalances() {
        ArrayList<Account> accounts = transactionDAO.getAllAccounts();
        DecimalFormat formatter = new DecimalFormat("#,###");
        for (Account acc : accounts) {
            String soTien = formatter.format(acc.getBalance());
            if (acc.getAcc_name().equals("Tiền mặt")) {
                tvTienMat.setText(soTien + "đ");
            } else if (acc.getAcc_name().equals("Ngân hàng")) {
                tvNganHang.setText(soTien + "đ");
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        transactionDAO.close(); // Đóng DB khi thoát app
    }
}