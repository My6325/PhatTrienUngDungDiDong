package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kotlinx.coroutines.flow.SharedFlowKt;

public class M001RegisterFragment extends Fragment {
    SQLiteHelper helper;
    public Context mContext;

    public EditText edtEmail,edtPass1,edtPass2;
    public View ic_back;
    public TextView tvRegister;

    public M001RegisterFragment(SQLiteHelper helper) {
        this.helper = helper;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.m001_frg_register, container, false);
        edtEmail=view.findViewById(R.id.edt_email);
        edtPass1=view.findViewById(R.id.edt_pass1);
        edtPass2=view.findViewById(R.id.edt_pass2);
        tvRegister=view.findViewById(R.id.tv_register);
        ic_back=view.findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String p1 = edtPass1.getText().toString().trim();
                String p2 = edtPass2.getText().toString().trim();
                register(email, p1, p2);
            }
        });
        return view;
    }
    //biến mContext sẽ không tự động có giá trị.
    // Nếu gọi Toast.makeText(mContext, ...) mà chưa khởi tạo mContext, ứng dụng sẽ bị văng
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    private void register(String mail, String pass, String repass) {
        if (mail.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(mContext, "Empty value", Toast.LENGTH_SHORT).show();
            return;
        }
        // Kiểm tra mật khẩu
        if (!pass.equals(repass)) {
            Toast.makeText(mContext, "Password is not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng Account và lưu vào Database
        Account account = new Account(mail, pass);
        helper.insert(account);

        Toast.makeText(mContext, "Successfully!", Toast.LENGTH_SHORT).show();
        //SharedPreferences: Kiểm tra nhanh cho dữ liệu đơn giản
        /*SharedPreferences pref=mContext.getSharedPreferences(MainActivity.SAVE_PREF,Context.MODE_PRIVATE);
        String savePass=pref.getString(mail,null);*/
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}