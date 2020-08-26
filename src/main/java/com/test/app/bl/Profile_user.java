package com.test.app.bl;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import com.test.app.dal.Database;

public class Profile_user extends General {
    Database db = new Database();

    public void info() throws SQLException {

        db.getInfoByID(getID_login_now());

        Integer money = db.getBalance(getID_login_now());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String money_format = currencyVN.format(money);
        System.out.println("Số dư trong tài khoản: " + money_format);

    }

    public void update(String password) {
        db.ChangePassword(getID_login_now(), password);

    }

    public String checkPassFromID(int ID)
    {
        return db.checkPassfromID(getID_login_now());
    }
    
}