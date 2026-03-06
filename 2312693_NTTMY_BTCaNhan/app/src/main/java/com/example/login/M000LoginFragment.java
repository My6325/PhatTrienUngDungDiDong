package com.example.login;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class M000LoginFragment extends Fragment {
    SQLiteHelper helper;
    public Context mContext;
    public EditText edtEmail,edtPass;
    public TextView tvLogin,tvRegister;
    public M000LoginFragment(SQLiteHelper helper) {
        this.helper = helper;
    }
    //biến mContext sẽ không tự động có giá trị.
    // Nếu gọi Toast.makeText(mContext, ...) mà chưa khởi tạo mContext, ứng dụng sẽ bị văng
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    private void login(String mail,String pass)
    {
        //Kiểm tra có ô nào trống không
        if(mail.isEmpty()||pass.isEmpty()){
            Toast.makeText(mContext,"Empty value",Toast.LENGTH_SHORT).show();//Thông báo 2s
            return;
        }
        if(helper.login(mail,pass))
            Toast.makeText(mContext,"Successfully",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext,"Not Successfully",Toast.LENGTH_SHORT).show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.m000_frg_login, container, false);
        edtEmail=view.findViewById(R.id.edtEmail);
        edtPass=view.findViewById(R.id.edtPass);
        tvLogin=view.findViewById(R.id.tvLogin);
        tvRegister=view.findViewById(R.id.tvRegister);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                login(email, pass);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).gotoRegisterScreen();
                }
            }
        });
        return view;
    }
}