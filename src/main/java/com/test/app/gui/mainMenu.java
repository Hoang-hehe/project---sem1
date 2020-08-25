package com.test.app.gui;

import java.sql.SQLException;
import java.util.Scanner;

import com.test.app.bl.Withdraw;
import com.test.app.bl.login;
import com.test.app.bl.profile;
import com.test.app.dal.Database;

public class mainMenu {
    public static int ID_login_now;
    public static Scanner input = new Scanner(System.in);
    public static Database db = new Database();

    public static void first_login() throws SQLException {
        String choice;
        String AccountNumber;
        String password;
        int login;
        login lg = new login();

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Chào mừng đến với Ngân Hàng đầu tư và phát triển Bốc Bát Họ Bank (BBH)");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Nhập ID: ");
        AccountNumber = input.nextLine();
        System.out.println("Nhập password");
        password = input.nextLine();
        lg.log(AccountNumber, password);
        menu_transaction();

    }

    public static void menu_transaction() throws SQLException {
        String choice;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("1.Thông tin tài khoản");
        System.out.println("2.Rút tiền");
        System.out.println("3.Chuyển khoản");
        System.out.println("4.Gửi tiền");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("#chọn: ");
        choice = input.nextLine();
        switch (choice) {
            case "1":
                profile pr = new profile();  
                pr.log();
                break;
            case "2":
                Withdraw wd = new Withdraw();
                wd.run();
                break;
            case "3":

                break;
            case "4":

                break;

            default:
                break;
        }

    }

    

    

}