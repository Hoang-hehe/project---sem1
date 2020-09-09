package com.test.app.bl;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import com.test.app.dal.Database;

public class Profile_user extends General {
    Database db = new Database();

    
    public void info() throws SQLException {

        db.getInfoByID(getID_login_now());

        long money =  db.getBalance(getID_login_now());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String money_format = currencyVN.format(money);
        System.out.println("Balance: " + money_format);

    }

    public void update(String password) {
        db.ChangePassword(getID_login_now(), password);

    }

    public String checkPassFromID(int ID)
    {
        return db.checkPassfromID(getID_login_now());
    }

    public void History_trade(){
        db.GetHistoryTrade(getID_login_now());
    }

    public int get_type_account(int id){
        return db.getAccountTypeID(id);
    }
    public void set_type_account(int id){
        TypeAccount tp = new TypeAccount();
        tp.set_type_account(getID_login_now());
    }

    
}