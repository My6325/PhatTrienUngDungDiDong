package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    Context context;
    private static String DB_NAME ="MyAcount.db";
    SQLiteDatabase myDB;
    public SQLiteHelper(@Nullable Context context){
        //Gọi hàm khởi tạo với tên file và phiên bản database (1)
        super(context,DB_NAME,null,1);
        this.context = context;
    }
    public void openDB() throws SQLException {
        if (myDB == null)
            myDB = getWritableDatabase();
    }
    public void closeDB(){
        if(myDB != null)
            myDB.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //Tạo bảng
    public void createTable(){
        String query = "create table if not exists Account (email text PRIMARY KEY, password text)";
        myDB.execSQL(query);
    }
    //Lưu tài khoản khi người dùng đăng kí
    public void insert(Account acc){
        //ContentValues: chứa dữ liệu theo cặp (tên cột-giá trị)
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",acc.getEmail());
        contentValues.put("password",acc.getPass());
        myDB.insert("Account",null,contentValues);
    }
    public boolean login(String email, String pass) {
        String query = "SELECT * FROM Account WHERE email ='"+email+"' and password='" + pass+"'";
        Cursor cursor = myDB.rawQuery(query,null);
        return (cursor.getCount() > 0) ? true : false;
    }
}
