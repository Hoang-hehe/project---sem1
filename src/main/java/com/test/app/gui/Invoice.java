package com.test.app.gui;

import com.test.app.bl.General;
import com.test.app.dal.Database;

public class Invoice extends General {
    static Database db = new Database();

    
    public int choice() {
        System.out.println("Bạn có muốn in hoá đơn(y/n):");
        String choice_printf;
        choice_printf = input.nextLine();
        switch (choice_printf) {
            case "y":
                return 1;
            case "n":
                return 0;
            default:
                break;
        }
        return -1;
    }

    public void front(int id_trade) {
        if (choice() == 1) {
            clrscr();
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("+       NGÂN HÀNG TMCP ĐẦU TƯ VÀ VAY VỐN VIỆT NAM (BHB)     +");
            System.out.println("+-----------------------------------------------------------+");
            db.getTrade_date_recent(getID_login_now());
            // db.getTrade_date_recent(1);
            System.out.println("\n      Số tài khoản:        " + getID_login_now());
            // System.out.println("\n      Số dư tài khoản:     " + db.getBalance(getID_login_now()));
            System.out.println("\n      Số dư tài khoản:     " + format_money(db.getBalance(getID_login_now())));
            // System.out.println("Số dư tài khoản: " + db.getBalance(1));
            System.out.println("\n      Phí thực hiện:       1.100 VND");
            System.out.println("\n+-----------------------------------------------------------+");

        }
        else{
            
        }

    }

    public void front_transfer(int id_trade, String account) {
        if (choice() == 1) {
            clrscr();
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("+       NGÂN HÀNG TMCP ĐẦU TƯ VÀ VAY VỐN VIỆT NAM (BHB)     +");
            System.out.println("+-----------------------------------------------------------+");
            db.getTrade_date_recent(getID_login_now());
            System.out.println("\n      Tài khoản thụ hưởng: "+ account);
            System.out.println("\n      Số tài khoản:        " + getID_login_now());
            // System.out.println("\n      Số dư tài khoản:     " + db.getBalance(getID_login_now()));
            System.out.println("\n      Số dư tài khoản:     " + format_money(db.getBalance(getID_login_now())));
            // System.out.println("Số dư tài khoản: " + db.getBalance(1));
            System.out.println("\n      Phí thực hiện:       1.100 VND");
            System.out.println("\n+-----------------------------------------------------------+");

        }
        else{
            System.out.println("quay trở lại");
            
        }

    }

    

}