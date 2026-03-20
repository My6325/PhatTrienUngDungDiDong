package com.example.xulyanimation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivAnimal;

    // Tạo một mảng chứa ID của 4 file animation
    private final int[] animationResIds = {
            R.anim.alpha,
            R.anim.rotate,
            R.anim.scale,
            R.anim.translate
    };
    // Khởi tạo đối tượng Random
    private final Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews() {
        ivAnimal = findViewById(R.id.iv_animal);
        findViewById(R.id.bt_alpha).setOnClickListener(this);
        findViewById(R.id.bt_rotate).setOnClickListener(this);
        findViewById(R.id.bt_scale).setOnClickListener(this);
        findViewById(R.id.bt_trans).setOnClickListener(this);
        findViewById(R.id.bt_random).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_alpha) {
            // Phương thức .startAnimation giúp thực thi hiệu ứng animation lên ảnh
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
        } else if (v.getId() == R.id.bt_rotate) {
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        } else if (v.getId() == R.id.bt_scale) {
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
        } else if (v.getId() == R.id.bt_trans) {
            ivAnimal.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
        }
        // Xử lý khi bấm nút RANDOM
        else if (v.getId()== R.id.bt_random) {
            playRandomAnimation();
        }
    }
    //Đưa ID hiệu ứng (animResId), đọc file hiê ứng truyền vào và thực hiện
    private void startAnim(int animResId) {
        Animation animation = AnimationUtils.loadAnimation(this, animResId);
        ivAnimal.startAnimation(animation);
    }
    private void playRandomAnimation() {
        //Quay ngẫu nhiên và trả về số (0,3)
        int randomIndex = random.nextInt(animationResIds.length);

        // Lấy ra ID của hiệu ứng tương ứng với số ngẫu nhiên vừa chọn
        int randomAnimId = animationResIds[randomIndex];

        // Chạy hiệu ứng đó
        startAnim(randomAnimId);
    }
}