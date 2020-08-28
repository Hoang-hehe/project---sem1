package com.test.app.bl;

import com.test.app.dal.Database;

public class Withdraw extends General {
    Database db = new Database();

    public int run(int money_for_withdraw) {
        int balance;
        balance = db.getBalance(getID_login_now());
        if (balance > 50000 && money_for_withdraw < balance && money_for_withdraw > 0) {
            db.trade(getID_login_now(), money_for_withdraw, "W");
            return 1;
        } else {
            return 0;
        }
    }
}