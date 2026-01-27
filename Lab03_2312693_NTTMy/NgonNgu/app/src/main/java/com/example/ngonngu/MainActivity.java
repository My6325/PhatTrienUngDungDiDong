package com.example.ngonngu;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tvVi;
    TextView tvEn;
    TextView tvFr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvVi = findViewById(R.id.txtVN);
        tvEn = findViewById(R.id.txtEN);
        tvFr = findViewById(R.id.txtFr);

        tvVi.setOnClickListener(v -> setLanguage("vi"));
        tvEn.setOnClickListener(v -> setLanguage("en"));
        tvFr.setOnClickListener(v -> setLanguage("fr"));
    }
    public void setLanguage(String langCode) {
        // Tạo đối tượng Locale mới để định danh các ngôn ngữ
        Locale locale = new Locale(langCode);
        //Thiết lập Locale mặc định cho máy ảo đang chạy app này
        Locale.setDefault(locale);
        //Đây là "bản hồ sơ" chứa thiết lập của app (ngôn ngữ, font, cỡ chữ)
        Configuration config = new Configuration();
        config.setLocale(locale);//gán ngôn ngữ vào đối tuợng config

        // Áp dụng thay đổi vào tài nguyên (Resources) của app
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        //Load lại hệ thống sẽ tìm đúng thư mục ngôn ngữ
        recreate();
    }
}