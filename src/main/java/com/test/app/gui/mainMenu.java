package com.test.app.gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.app.bl.General;
import com.test.app.bl.Withdraw;
import com.test.app.bl.Login_user;
import com.test.app.bl.Profile_user;
import com.test.app.bl.Valid;
import com.test.app.dal.Database;

public class mainMenu extends General {
    public static int ID_login_now;
    public static Scanner input = new Scanner(System.in);
    public static Valid valid = new Valid();
    public static Database db = new Database();

    public static void first_login() throws SQLException {
        boolean loop = false;
        boolean loop2 = false;
        String AccountNumber = "";
        String password;
        // int login;
        Login_user lg = new Login_user();

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Chào mừng đến với Ngân Hàng đầu tư và phát triển Bốc Bát Họ Bank (BBH)");
        System.out.println("---------------------------------------------------------------------");
        while (loop != true) {
            System.out.println("Nhập ID: ");
            AccountNumber = input.nextLine();
            if (lg.first_log(AccountNumber) == 1) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Chào mừng bạn trở lại: " + lg.getName(AccountNumber));
                if (lg.checkActive(AccountNumber) == 0) {
                    System.out.println("Rất tiếc tài khoản của bạn đã bị khoá ");
                    System.out.println("Liên hệ admin để có thể tiếp tục sử dụng tài khoản");
                    System.exit(0);

                }
                System.out.println("---------------------------------------------------------------------");
                loop = true;
            } else {
                System.out.println("Không phát hiện ID trong hệ thống! Mời nhập lại");
                loop = false;
            }

        }
        while (loop2 != true) {
            password = hide_pass();
            loop2 = lg.session_login(AccountNumber, password);
            if (loop2 == false) {
                System.out.println("Số lần nhập sai: " + lg.getCountLogin());
                if (lg.getCountLogin() > 3) {
                    System.out.println("Bạn đã bị khoá mẹ tài khoản, hack với chúng tôi à?");
                    lg.deactive(AccountNumber);
                    System.exit(0);
                }
            }
            System.out.println("---------------------------------------------------------------------");

        }

        menu_transaction();

    }

    public static void updateInfo() {
        String choice;
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Cập nhật mật khẩu(y/n)");
        choice = input.nextLine();
        switch (choice) {
            case "y":
                String new_pass = "";
                String old_pass = "";
                Profile_user pr = new Profile_user();
                System.out.println("Mời nhập mật khẩu cũ:");
                old_pass = input.nextLine(); // check mật khẩu cũ
                if (old_pass.equals(pr.checkPassFromID((getID_login_now())))) {

                    System.out.println("Mời nhập mật khẩu mới:");
                    while (true) {
                        new_pass = input.nextLine();
                        if (valid.validate(new_pass) == true) {
                            pr.update(new_pass);
                            break;
                        } else {
                            System.out.println("Không hợp lệ, nhập lại:");
                        }
                    }
                } else {
                    System.out.println("Nhập sai mật khẩu cũ...");
                    break;
                }

                break;
            case "n":
                break;
            default:
                break;
        }
    }

    public static void menu_transaction() throws SQLException {
        while (true) {

            String choice;
            System.out.println("---------------------------------------------------------------------");
            System.out.println("1.Thông tin tài khoản");
            System.out.println("2.Rút tiền");
            System.out.println("3.Chuyển khoản");
            System.out.println("4.Gửi tiền");
            System.out.println("5.Đăng xuất");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("#chọn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    Profile_user pr = new Profile_user();
                    pr.info();
                    updateInfo();
                    break;
                case "2":
                    Withdraw wd = new Withdraw();
                    wd.run();
                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":
                    System.exit(0);
                    break;

                default:
                    break;
            }

        }
    }

}