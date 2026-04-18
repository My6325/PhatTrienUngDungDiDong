package com.example.customcontrols;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnToast, btnDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnToast = findViewById(R.id.btnToast);
        btnDialog = findViewById(R.id.btnDialog);

        btnToast.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Gọi file thiết kế layout_custom_toast.xml mà bạn đã tạo
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.layout_custom_toast, null);

                // Tạo đối tượng Toast và gắn giao diện custom vào
                Toast customToast = new Toast(MainActivity.this);
                customToast.setDuration(Toast.LENGTH_SHORT);
                //đẩy Toast ra chính giữa màn hình
                customToast.setGravity(Gravity.CENTER, 0, 0);

                customToast.setView(layout);
                customToast.show();

                // Trả về true để báo hệ thống rằng sự kiện nhấn giữ đã được xử lý trọn vẹn
                return true;
            }
        });


        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.layout_custom_dialog);

                Button btnDongY = dialog.findViewById(R.id.btnDongY);
                Button btnThoat = dialog.findViewById(R.id.btnThoat);

                EditText edtTaiKhoan = dialog.findViewById(R.id.edtTaiKhoan);
                EditText edtMatKhau = dialog.findViewById(R.id.edtMatKhau);

                btnThoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Lấy dữ liệu người dùng nhập vào
                        String username = edtTaiKhoan.getText().toString().trim();
                        String password = edtMatKhau.getText().toString().trim();

                        // Kiểm tra điều kiện
                        if (username.equals("admin") && password.equals("123456")) {
                            // Nếu ĐÚNG: Hiện thông báo thành công và đóng Dialog
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            // Nếu SAI: Hiện thông báo lỗi và KHÔNG đóng Dialog
                            Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
    }
}