package com.test.app.bl;

import com.test.app.dal.Database;

public class Withdraw extends General {

    public void run() {
        Database db = new Database();
        int money_for_withdraw;
        int balance;
        System.out.println("Mời nhập số tiền muốn rút: ");
        money_for_withdraw = input.nextInt();
        balance = db.getBalance(getID_login_now());
        if (balance > 50000 && money_for_withdraw < balance && money_for_withdraw > 0) {
            db.trade(getID_login_now(), money_for_withdraw, "W");
        } else {
            System.out.println("Đéo có chuyện đấy đâu!");
        }
        System.out.println("Số dư hiện tại:");
        System.out.println(db.getBalance(getID_login_now()));

    }
}