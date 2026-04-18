package com.example.bai1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //biến để quản lý Thread
    private Thread animationThread = null;

    private int currentType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText edtData = findViewById(R.id.edtData);
        Button btnDraw = findViewById(R.id.btnDraw);
        MyChartView myChartView = findViewById(R.id.myChartView);

        btnDraw.setOnClickListener(v -> {
            // Lấy dữ liệu từ EditText và parse thành List<Float> (như đã hướng dẫn trước)
            List<Float> data = parseInput(edtData.getText().toString());
            if (data.isEmpty()) return;
            //Nếu có thread đang chạy, hãy ngắt nó ngay
            if (animationThread != null && animationThread.isAlive()) {
                animationThread.interrupt();
            }
            // Cập nhật dữ liệu ngay lập tức để View sẵn sàng
            currentType = (currentType == 0) ? 1 : 0; // Nếu đang là 0 thì thành 1, và ngược lại
            myChartView.updateChartDataOnly(data, currentType);
            //Tạo thread mới
            animationThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        float p = 0f;
                        while (p <= 1.0f) {
                            // Kiểm tra xem luồng có bị yêu cầu dừng không
                            if (Thread.currentThread().isInterrupted()) return;

                            p += 0.05f; // Tăng bước nhảy lên để mượt hơn
                            if (p > 1.0f) p = 1.0f;

                            myChartView.setAnimationPercent(p);
                            Thread.sleep(25); // Giảm thời gian nghỉ xuống để phản hồi nhanh hơn
                        }
                    } catch (InterruptedException e) {
                        // Khi Thread bị interrupt trong lúc đang sleep, nó sẽ nhảy vào đây
                        return;
                    }
                }
            });
            animationThread.start();
        });

    }
    private List<Float> parseInput(String input) {
        List<Float> data = new ArrayList<>();
        if (input == null || input.isEmpty()) return data;

        // Tách chuỗi bằng dấu phẩy
        String[] parts = input.split(",");
        for (String s : parts) {
            try {
                // Xóa khoảng trắng và chuyển sang kiểu Float
                float val = Float.parseFloat(s.trim());
                data.add(val);
            } catch (NumberFormatException e) {
                // Nếu nhập sai định dạng (ví dụ chữ) thì bỏ qua phần tử đó
                e.printStackTrace();
            }
        }
        return data;
    }
}