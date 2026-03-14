package com.example.vnedudluctk472312693;

public class TransactionRecord {
    int trans_id; //giao dịch
    double amount; // số tiền
    String date;
    int cat_id;//danh mục
    int acc_id;//nguồn tiền
    public TransactionRecord(){
    }
    public TransactionRecord(double amount, String date, int cat_id, int acc_id) {
        this.amount = amount;
        this.date = date;
        this.cat_id = cat_id;
        this.acc_id = acc_id;
    }
    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
