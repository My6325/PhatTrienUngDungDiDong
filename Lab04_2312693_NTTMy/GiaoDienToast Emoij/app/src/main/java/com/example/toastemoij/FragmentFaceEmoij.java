package com.example.toastemoij;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentFaceEmoij extends Fragment implements View.OnClickListener{
    private static final int[] ids = {R.id.iv_face1, R.id.iv_face2, R.id.iv_face3, R.id.iv_face4,
            R.id.iv_face5, R.id.iv_face6, R.id.iv_face7, R.id.iv_face8, R.id.iv_face9};

    // Lưu ID của các file ảnh (có 9 hình tương ứng với 9 ô)
    private static final int[] DRAWABLE_IDS = {
            R.drawable.angry, R.drawable.sad, R.drawable.cute,
            R.drawable.smile, R.drawable.cry, R.drawable.sick,
            R.drawable.wow, R.drawable.laugh, R.drawable.sleep
    };
    private Context mContext;
    private ImageView[] imageViews = new ImageView[9]; // Mảng chứa 9 ImageView để dễ thao tác
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m001_frg_face_emoij, container, false);
        initViews(rootView);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }
    private void initViews(View v) {
       /* for (int id : ids) {
            v.findViewById(id).setOnClickListener(this);
        }*/
        // Ánh xạ 9 ImageView và cài đặt sự kiện click cho chúng
        for (int i = 0; i < ids.length; i++) {
            imageViews[i] = v.findViewById(ids [i]);
            imageViews[i].setOnClickListener(this);
        }

        // Ánh xạ nút Random và bắt sự kiện khi bấm nút
        Button btnRandom = v.findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomizeIcons(); // Gọi hàm xáo trộn
            }
        });
    }
    // Hàm Random icon
    private void randomizeIcons() {
        // Tạo một List chứa các ID hình ảnh để có thể dùng hàm xáo trộn
        List<Integer> listDrawables = new ArrayList<>();
        for (int id : DRAWABLE_IDS) {
            listDrawables.add(id);
        }

        // Xáo trộn ngẫu nhiên vị trí các hình ảnh trong danh sách
        Collections.shuffle(listDrawables);

        //Chạy vòng lặp gán lại từng ảnh mới vào 9 cái ImageView
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageResource(listDrawables.get(i));
        }
    }
    @Override
    public void onClick(View v) {
        ImageView ivFace = (ImageView) v;
        showToast(ivFace.getDrawable());
    }
    // hiển thị ảnh emoij lên thay vì 1 dòng text thông thường
    private void showToast(Drawable drawable) {
        Toast toast = new Toast(mContext);
        ImageView ivEmoij = new ImageView(mContext);
        ivEmoij.setImageDrawable(drawable);
        //Cài đặt thời gian hiển thị
        toast.setDuration(Toast.LENGTH_SHORT);
        //Set view và hiển thị
        toast.setView(ivEmoij);
        toast.show();
    }
}