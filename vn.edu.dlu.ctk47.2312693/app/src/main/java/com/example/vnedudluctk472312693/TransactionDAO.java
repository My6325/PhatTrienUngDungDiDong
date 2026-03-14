package com.example.vnedudluctk472312693;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TransactionDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    // Hàm khởi tạo
    public TransactionDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    // Mở kết nối
    public void open() {
        //Không dùng getReadableDatabase() vì chỉ cho phép đọc
        db = dbHelper.getWritableDatabase();
    }

    // Đóng kết nối
    public void close() {
        dbHelper.close();
    }

    //Lấy danh sách nguồn tiền
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Account", null);
        //Kiểm tra xem có dữ liệu trong bảng hay không
        if (cursor.moveToFirst()) {
            do {
                Account acc = new Account();
                acc.setAcc_id(cursor.getInt(0));
                acc.setAcc_name(cursor.getString(1));
                acc.setBalance(cursor.getDouble(2));
                list.add(acc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    //Lấy danh sách danh mục
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Category", null);

        if (cursor.moveToFirst()) {
            do {
                Category cat = new Category();
                cat.setCat_id(cursor.getInt(0));  // cat_id
                cat.setName(cursor.getString(1)); // name
                cat.setType(cursor.getInt(2));    // type (0: Chi, 1: Thu)
                list.add(cat);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    //Lấy danh sách danh mục
    public ArrayList<TransactionRecord> getAllTransactions() {
        ArrayList<TransactionRecord> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TransactionRecord", null);

        if (cursor.moveToFirst()) {
            do {
                TransactionRecord trans = new TransactionRecord();
                trans.setTrans_id(cursor.getInt(0)); // trans_id
                trans.setAmount(cursor.getDouble(1)); // amount
                trans.setDate(cursor.getString(2));   // date
                trans.setCat_id(cursor.getInt(3));   // cat_id
                trans.setAcc_id(cursor.getInt(4));   // acc_id
                list.add(trans);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public long insertTransaction(TransactionRecord trans) {
        ContentValues values = new ContentValues();
        values.put("amount", trans.getAmount());
        values.put("date", trans.getDate());
        values.put("cat_id", trans.getCat_id());
        values.put("acc_id", trans.getAcc_id());

        // Trả về ID của dòng vừa thêm, nếu lỗi trả về -1
        return db.insert("TransactionRecord", null, values);
    }
    public int updateTransaction(TransactionRecord trans) {
        ContentValues values = new ContentValues();
        values.put("amount", trans.getAmount());
        values.put("date", trans.getDate());
        values.put("cat_id", trans.getCat_id());
        values.put("acc_id", trans.getAcc_id());

        //số dòng khi bị thay đổi (1: Sửa thành công 1 dòng)
        return db.update("TransactionRecord", values, "trans_id=?",
                new String[]{String.valueOf(trans.getTrans_id())});
    }

    public int deleteTransaction(int transId) {
        // số dòng bị xóa
        return db.delete("TransactionRecord", "trans_id=?",
                new String[]{String.valueOf(transId)});
    }
    public int updateAccount(Account acc) {
        ContentValues values = new ContentValues();
        values.put("balance", acc.getBalance());

        // Trả về số dòng bị ảnh hưởng
        return db.update("Account", values, "acc_id=?",
                new String[]{String.valueOf(acc.getAcc_id())});
    }
}
