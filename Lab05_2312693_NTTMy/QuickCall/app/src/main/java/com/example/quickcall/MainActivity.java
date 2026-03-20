package com.example.quickcall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initView();
    }
    //tắt hiệu ứng cũ khhi chạy hiệu ứng mới
    private void clearAllAnimations() {
        findViewById(R.id.fr_mom).clearAnimation();
        findViewById(R.id.fr_dad).clearAnimation();
        findViewById(R.id.fr_crush).clearAnimation();
        findViewById(R.id.fr_best_friend).clearAnimation();
        findViewById(R.id.iv_dialer).clearAnimation();
    }
    private void initView() {
        findViewById(R.id.fr_mom).setOnClickListener(this);
        findViewById(R.id.fr_dad).setOnClickListener(this);
        findViewById(R.id.fr_crush).setOnClickListener(this);
        findViewById(R.id.fr_best_friend).setOnClickListener(this);
        findViewById(R.id.iv_dialer).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        clearAllAnimations();
        int viewId = v.getId(); // Lấy ID của nút vừa bị bấm

        if (viewId == R.id.fr_mom) {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
        }
        else if (viewId == R.id.fr_dad) {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        }
        else if (viewId == R.id.fr_crush) {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
        }
        else if (viewId == R.id.fr_best_friend) {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
        }
        else if (viewId == R.id.iv_dialer) {
            // Hiệu ứng nút bàn phím
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
            gotoDialPad();
            return; // Nút Dialer xong việc thì dừng hàm
        }
        // Dùng instanceof kiểm tra xem có ấn đúng Frame đó hay không
        if (v instanceof FrameLayout) {
            processCall((String) v.getTag());
            return;
        }
        //Nếu không phải FrameLayout chứa số (tag) thì nó chuyển sang màn hình màn phím
        gotoDialPad();
    }
    //Mở màn hình quay số của phone
    private void gotoDialPad() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"));
        try {
            startActivity(intent);
        } catch (Exception e) {
            // Nếu máy ảo không có app gọi điện, nó sẽ báo lỗi nhẹ nhàng thay vì văng app
            Toast.makeText(this, "Máy ảo này không có chức năng gọi điện!", Toast.LENGTH_SHORT).show();
        }
    }
    //Kiểm tra người dùng đã cáp quyền hay chưa
    private void processCall(String phone) {
        //Kiểm tra xem đã cấp quyền CALL_PHONE chưa
        if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //Nếu chưa cấp (if đúng) thì nó sẽ hỏi và hiển thị thông báo
            //Toast vì lúc này chưa gọi (để bạn cấp quyền)
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
            Toast.makeText(this, "Hãy thực hiện lại sau khi cấp quyền!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Đã cấp quyền -> Mở ứng dụng phone để thực hiện cuộc gọi
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + phone));
        startActivity(intent);
    }
}