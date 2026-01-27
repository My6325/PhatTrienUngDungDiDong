package com.example.truyencuoi;

import static android.view.View.inflate;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class m000_frg_splash extends Fragment {

    private boolean isLocked = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_m000_frg_splash, container, false);
        Button btnLock = view.findViewById(R.id.btn_lock);

        btnLock.setOnClickListener(v -> {
            if (!isLocked) {
                // Khóa màn hình ở hướng dọc
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                btnLock.setText("OFF");
                isLocked = true;
            } else {
                // Cho phép xoay theo cảm biến
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                btnLock.setText("ON");
                isLocked = false;
            }
        });
        return view;
    }

    private void initViews() {
        new Handler().postDelayed(this::gotoM001Screen, 2000);
    }

    private void gotoM001Screen() {
        ((MainActivity) getActivity()).gotoM001Screen();

    }
}