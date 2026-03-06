package com.example.giaodienmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int[] ID_DRAWABLES = {R.drawable.mess, R.drawable.traveling,
            R.drawable.medical,
            R.drawable.hotel, R.drawable.restaurant,R.drawable.bar,
            R.drawable.store, R.drawable.work, R.drawable.time, R.drawable.education,
            R.drawable.entertainment};
    private static final int[] ID_TEXTS = {R.string.txt_mess, R.string.txt_flight,
            R.string.txt_hospital,
            R.string.txt_hotel, R.string.txt_restaurant, R.string.txt_coctail,
            R.string.txt_store, R.string.txt_work, R.string.txt_time, R.string.txt_education,
            R.string.txt_movie};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.removeAllViews();
        //Tạo ra các Item topic động và add vào LinearLayout
        for (int i = 0; i < ID_DRAWABLES.length; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.item_topic, null);

            ImageView ivTopic = v.findViewById(R.id.iv_topic);
            TextView tvTopic = v.findViewById(R.id.tv_topic);

            ivTopic.setImageResource(ID_DRAWABLES[i]);
            tvTopic.setText(ID_TEXTS[i]);
            //Quy định không gian chiếm của mỗi item view = 1
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );

            // Thêm khoảng cách giữa các dòng cho thoáng
            param.setMargins(0, 10, 0, 10);
            v.setLayoutParams(param);
            lnMain.addView(v);

            final int position = i; // Cần biến final để dùng bên trong sự kiện click
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Lấy tên chủ đề và danh sách từ vựng
                    String topicName = getString(ID_TEXTS[position]);
                    String[] words = getVocabularyList(position);

                    // Khởi tạo Intent để chuyển sang màn hình VocabularyActivity
                    Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                    // Gửi kèm dữ liệu sang màn hình mới
                    intent.putExtra("TOPIC_NAME", topicName);
                    intent.putExtra("WORD_LIST", words);

                    startActivity(intent);
                }
            });
        }
    }
    // Hàm tạo dữ liệu cho các chủ đề
    private String[] getVocabularyList(int index) {
        switch (index) {
            case 0: // Essentials
                return new String[]{"Hello - Xin chào", "Goodbye - Tạm biệt", "Yes - Vâng", "No - Không", "Thank you - Cảm ơn", "Sorry - Xin lỗi"};
            case 1: // While Traveling
                return new String[]{"Ticket - Vé", "Passport - Hộ chiếu", "Luggage - Hành lý", "Flight - Chuyến bay", "Airport - Sân bay"};
            case 2: // Medical
                return new String[]{"Doctor - Bác sĩ", "Hospital - Bệnh viện", "Medicine - Thuốc", "Pain - Đau", "Emergency - Cấp cứu"};
            case 3: // Hotel
                return new String[]{"Room - Phòng", "Key - Chìa khóa", "Reception - Lễ tân", "Check in - Nhận phòng", "Check out - Trả phòng"};
            case 4: // At the Restaurant (Nhà hàng)
                return new String[]{"Menu - Thực đơn", "Order - Gọi món", "Waiter - Bồi bàn", "Bill - Hóa đơn", "Delicious - Ngon miệng"};
            case 5: // At the Bar (Quán Bar/Đồ uống)
                return new String[]{"Beer - Bia", "Wine - Rượu vang", "Cocktail - Cốc-tai", "Cheers - Dô / Cạn ly", "Bartender - Người pha chế"};
            case 6: // At the Store (Cửa hàng / Mua sắm)
                return new String[]{"Price - Giá cả", "Discount - Giảm giá", "Cash - Tiền mặt", "Credit card - Thẻ tín dụng", "Buy - Mua"};
            case 7: // Work (Công việc)
                return new String[]{"Office - Văn phòng", "Boss - Sếp", "Meeting - Cuộc họp", "Salary - Tiền lương", "Colleague - Đồng nghiệp"};
            case 8: // Time (Thời gian)
                return new String[]{"Morning - Buổi sáng", "Afternoon - Buổi chiều", "Night - Buổi tối", "Today - Hôm nay", "Tomorrow - Ngày mai"};
            case 9: // Education (Giáo dục)
                return new String[]{"School - Trường học", "Teacher - Giáo viên", "Student - Học sinh", "Book - Quyển sách", "Homework - Bài tập về nhà"};
            case 10: // Entertainment (Giải trí)
                return new String[]{"Movie - Phim ảnh", "Music - Âm nhạc", "Game - Trò chơi", "Ticket - Vé", "Relax - Thư giãn"};
            default: // Các chủ đề chưa nhập dữ liệu
                return new String[]{"Không có từ vựng cho chủ đề này..."};
        }
    }
}