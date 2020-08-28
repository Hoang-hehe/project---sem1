package com.test.app.bl;

import com.test.app.dal.Database;

public class TypeAccount extends General {
    Database db = new Database();
    int balance = db.getBalance(getID_login_now());

    public void set_type_account(int id) {
        if (balance >= 50000000) {
            db.setAccountType(id, 1);
        } else if (balance >= 2000000 && balance < 50000000) {
            db.setAccountType(id, 2);
        } else if (balance < 20000000) {
            db.setAccountType(id, 3);
        }
    }

}