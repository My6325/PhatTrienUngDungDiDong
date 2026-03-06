package com.example.login;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper helper;
    //public static final String SAVE_PREF="save_pref";
    private void InitialDB(){
        helper=new SQLiteHelper(this);
        helper.openDB();
        helper.createTable();
    }
    public void gotoRegisterScreen() {
        //Sử dụng FragmentManager để bắt đầu thay đổi giao diện
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main, new M001RegisterFragment(helper))
                .addToBackStack(null) // Dòng này giúp khi nhấn nút Back sẽ quay lại Login thay vì thoát app
                .commit();
    }
    public void gotoLoginScreen() {
        //Sử dụng FragmentManager để bắt đầu thay đổi giao diện
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main, new M000LoginFragment(helper))
                .commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        InitialDB();
        gotoLoginScreen();
    }
}