package com.test.app.gui;

import com.test.app.bl.General;
import com.test.app.dal.Database;

public class Invoice extends General {

    public static void before(int id_trade) {
        Database db = new Database();
        System.out.println("--------------------------------------");
        System.out.println("NGÂN HÀNG TMCP ĐẦU TƯ VÀ VAY VỐN VIỆT NAM (BHB)");
        System.out.println("--------------------------------------");
        db.getTrade_date_recent(getID_login_now());
        // db.getTrade_date_recent(1);
        System.out.println("Số dư tài khoản: " + db.getBalance(getID_login_now()));
        // System.out.println("Số dư tài khoản: " + db.getBalance(1));
        System.out.println("Phí: 1,000 VND | VAT: 100VND");
        System.out.println("Phí in hoá đơn: 500 VND");
        System.out.println("VAT in hoá đơn: 50 VND");

    }

}