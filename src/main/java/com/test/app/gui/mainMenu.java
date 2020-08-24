package com.test.app.gui;

import java.util.Scanner;

import com.test.app.dal.Database;

public class mainMenu {
    public static int ID_login_now;
    public static Scanner input = new Scanner(System.in);
    public static Database db = new Database();

    public static void login() {
        String choice;
        String AccountNumber;
        String password;
        int login;
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Chào mừng đến với Ngân Hàng đầu tư và phát triển Bốc Bát Họ Bank (BBH)");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Nhập ID: ");
        AccountNumber = input.nextLine();
        System.out.println("Nhập password");
        password = input.nextLine();

        login = db.login(AccountNumber, password);
        if (login == 1) {
            System.out.println("Đăng nhập thành công!");
            ID_login_now = db.getID(AccountNumber);
            menu_transaction(ID_login_now);

        } else {
            System.out.println("Lỗi!, tài khoản không tồn tại, hoặc sai mật khẩu...");
        }
    }

    public static void menu_transaction(int ID_login_now) {
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
                profile();
                break;
            case "2":
                withdraw();
                break;
            case "3":

                break;
            case "4":

                break;

            default:
                break;
        }

    }

    public static void profile() {
        db.getInfoByID(ID_login_now);

    }

    public static void withdraw() {
        int money_for_withdraw;
        int balance;
        System.out.println("Mời nhập số tiền muốn rút: ");
        money_for_withdraw = input.nextInt();
        balance = db.getBalance(ID_login_now);
        if (balance > 50000 && money_for_withdraw<balance) {
            db.trade(ID_login_now, money_for_withdraw, "W");
        }
        else{
            System.out.println("Đéo có chuyện đấy đâu!");
        }
        System.out.println("Số dư hiện tại:");
        System.out.println(db.getBalance(ID_login_now));

    }

}