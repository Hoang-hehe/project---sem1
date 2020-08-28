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

    public static void first_login() throws SQLException, InterruptedException {
        clrscr();

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
                clrscr();
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
                System.out.println("Không phát hiện ID trong hệ thống! Mời nhập lại(enter để tiếp tục)");
                input.nextLine();
                clrscr();
                loop = false;
            }

        }
        while (loop2 != true) {
            password = hide_pass();
            loop2 = lg.session_login(AccountNumber, password);
            if (loop2 == false) {
                clrscr();
                System.out.println("Số lần nhập sai: " + lg.getCountLogin());
                if (lg.getCountLogin() > 3) {
                    System.out.println("Bạn đã bị khoá mẹ tài khoản, hack với chúng tôi à?");
                    lg.deactive(AccountNumber);
                    System.exit(0);
                }
            }
            System.out.println("---------------------------------------------------------------------");

        }
        clrscr();

        menu_transaction();

    }

    public static void choice_profile() {
        String choice;

        System.out.println("---------------------------------------------------------------------");
        System.out.println("1. Cập nhật mật khẩu");
        System.out.println("2. Xem lịch sử giao dịch");
        System.out.println("3. Menu chính");
        System.out.println("#Chọn:");
        choice = input.nextLine();
        switch (choice) {
            case "1":
                clrscr();
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
            case "2":
                clrscr();
                System.out.println("Thời gian |Loại giao dịch|Số tiền");
                db.GetHistoryTrade(getID_login_now());
                break;
            case "3":
                break;
            default:
                break;

        }
    }

    

    public static void menu_transaction() throws SQLException {
        while (true) {
            Profile_user pr = new Profile_user();
 
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
                    clrscr();
                    pr.set_type_account(getID_login_now());
                    pr.info();
                    System.out.println("loại account: " + db.getAccountTypeID(getID_login_now()));
                    if (pr.get_type_account(getID_login_now()) == 1) {
                        System.out.println("Cấp độ tài khoản: Vàng");
                        System.out.println(
                                "Ưu đãi: Được giao dịch tối đa 10tr/lần, có thể giao dịch tối đa 100tr/ngày, miễn phí khi giao dịch chuyển tiền");
                    } else if (pr.get_type_account(getID_login_now()) == 2) {
                        System.out.println("Cấp độ tài khoản: Bạc");
                        System.out.println("Cần thêm " + format_money(50000000 - db.getBalance(getID_login_now()))
                                + " để nâng lên cấp độ Vàng");
                        System.out.println(
                                "Uu đãi cấp độ Bạc: Được giao dịch tối đa 5tr/lần, có thể giao dịch tối đa 50tr/ngày");
                    } else if (pr.get_type_account(getID_login_now()) == 3) {
                        System.out.println("Cấp độ tài khoản: Đồng");
                        System.out.println("Cần thêm " + format_money(20000000 - db.getBalance(getID_login_now()))
                                + " để nâng lên cấp độ Bạc và nhận thêm ưu đãi ");
                    }
                    choice_profile();

                    break;
                case "2":
                    clrscr();
                    System.out.println("Mời bạn nhập số tiền muốn rút: ");

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