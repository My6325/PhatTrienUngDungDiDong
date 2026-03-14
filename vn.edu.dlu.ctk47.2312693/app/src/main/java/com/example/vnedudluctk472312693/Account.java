package com.example.vnedudluctk472312693;

public class Account {
    int acc_id; //nguồn tiền
    String acc_name; //Tiền mặt, ngân hàng
    double balance; //Số dư
    public Account() {
    }
    public Account(String acc_name, double balance) {
        this.acc_name=acc_name;
        this.balance=balance;
    }
    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return acc_name; // Để Spinner hiện tên tài khoản
    }
}
