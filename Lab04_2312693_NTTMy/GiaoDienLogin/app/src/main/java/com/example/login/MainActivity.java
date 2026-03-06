package com.example.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private TextView txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtmail);
        edtPassword = findViewById(R.id.edtpass);
        txtLogin = findViewById(R.id.txtlogin);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu người dùng nhập (.trim() để xóa khoảng trắng thừa ở 2 đầu)
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Kiểm tra xem người dùng đã nhập đủ chưa
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ Email và Mật khẩu!", Toast.LENGTH_SHORT).show();
                } else {
                    // Hiển thị Custom Toast nếu nhập đầy đủ
                    showCustomToast(email, password);
                }
            }
        });
    }
    //Hàm tạo và hiển thị Custom Toast
    private void showCustomToast(String email, String password) {
        // Nạp layout custom toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_custom_toast, findViewById(R.id.custom_toast_container));

        // Lấy thẻ TextView trong Custom Toast ra để set nội dung
        TextView tvMessage = layout.findViewById(R.id.tv_toast_message);
        String thongBao = "Bạn đã đăng nhập thành công với email: " + email + " và mật khẩu " + password + ".";
        tvMessage.setText(thongBao);

        // Khởi tạo Toast và gắn Layout vừa tạo vào
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG); // Thời gian hiển thị dài
        toast.setView(layout);
        toast.show();
    }
}