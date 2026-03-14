package com.example.vnedudluctk472312693;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Lưu tt môi trường ứng dụng
    Context context;
    private static String DB_NAME ="QLThuChiCaNhan.db";
    public DatabaseHelper(@Nullable Context context){
        super(context,DB_NAME,null,1);
        this.context = context;
    }
    //Hàm onCreate sẽ được tự động gọi khi ứng dụng chạy lần đầu
    @Override
    public void onCreate(SQLiteDatabase db) {
        // bảng Category
        String categoryTable = "CREATE TABLE Category (" +
                "cat_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "type INTEGER NOT NULL)";
        db.execSQL(categoryTable);

        //bảng Account
        String accountTable = "CREATE TABLE Account (" +
                "acc_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "acc_name TEXT NOT NULL, " +
                "balance REAL NOT NULL)";
        db.execSQL(accountTable);

        //bảng Transaction
        String transactionTable = "CREATE TABLE TransactionRecord (" +
                "trans_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "amount REAL NOT NULL, " +
                "date TEXT NOT NULL, " +
                "cat_id INTEGER, " +
                "acc_id INTEGER, " +
                "FOREIGN KEY(cat_id) REFERENCES Category(cat_id), " +
                "FOREIGN KEY(acc_id) REFERENCES Account(acc_id))";
        db.execSQL(transactionTable);

        // danh mục mặc định
        db.execSQL("INSERT INTO Category (name, type) VALUES ('Lương', 1)"); // Thu
        db.execSQL("INSERT INTO Category (name, type) VALUES ('Thưởng', 1)"); // Thu
        db.execSQL("INSERT INTO Category (name, type) VALUES ('Ăn uống', 0)"); // Chi
        db.execSQL("INSERT INTO Category (name, type) VALUES ('Tiền nhà', 0)"); // Chi
        db.execSQL("INSERT INTO Category (name, type) VALUES ('Di chuyển', 0)"); // Chi

        // nguồn tiền mặc định
        db.execSQL("INSERT INTO Account (acc_name, balance) VALUES ('Tiền mặt', 5000000)");
        db.execSQL("INSERT INTO Account (acc_name, balance) VALUES ('Ngân hàng', 10000000)");
    }
    //Được gọi sau khi tăng version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TransactionRecord");
        db.execSQL("DROP TABLE IF EXISTS Account");
        db.execSQL("DROP TABLE IF EXISTS Category");
        onCreate(db);
    }
}
