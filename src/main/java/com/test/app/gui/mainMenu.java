package com.test.app.gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.app.bl.General;
import com.test.app.bl.Withdraw;
import com.test.app.bl.Login_user;
import com.test.app.bl.Profile_user;
import com.test.app.bl.Transfer;
import com.test.app.bl.Valid;
import com.test.app.dal.Database;

public class mainMenu extends General {
    public static int ID_login_now; // = account number sau đó gọi hàm getID(accountNumber) để ra được id trong sql
    public static Scanner input = new Scanner(System.in);
    public static Valid valid = new Valid();
    public static Database db = new Database();
    public static Invoice bill = new Invoice();
    public static String AccountNumber = "";
    public static int accountType = 0;
    public static long max_transaction = 0;
    public static long fees = 1100;
    public static long money_in_ATM = 10000000;

    public static void menu_transaction() throws SQLException, InterruptedException {

        while (true) {
            clrscr();
            Profile_user pr = new Profile_user();
            String choice;
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Ngân hàng đầu tư và vay tiền  | BHB                               ");
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%45s%-20s\n", "Vui lòng chọn giao dịch", "");
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%-12s\n", "|1. Rút tiền             /|", "|2. Chuyển khoản        /|");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%-12s\n", "|3. Xem số dư            /|", "|4. In sao kê           /|");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%-12s\n", "|5. Đổi mã PIN           /|", "|6. Thoát               /|");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.println("------------------------------------------------------------------");
            System.out.println("#Chọn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    clrscr();
                    withdraw();

                    break;
                case "2":
                    clrscr();
                    transfer();
                    break;
                case "3":
                    clrscr();

                    pr.info();
                    if (pr.get_type_account(getID_login_now()) == 1) {
                        System.out.println("Cấp độ tài khoản: Vàng");
                        System.out.println("Ưu đãi: Được rút tối đa lên đến 20tr/lần, không giới hạn giao dịch");
                        max_transaction = 20000000;
                    } else if (pr.get_type_account(getID_login_now()) == 2) {
                        System.out.println("Cấp độ tài khoản: Bạc");
                        System.out.println("Cần thêm " + format_money(100000000 - db.getBalance(getID_login_now()))
                                + " để nâng lên cấp độ Vàng");
                        System.out.println(
                                "Uu đãi cấp độ Bạc: Được rút tối đa 10tr/lần, có thể giao dịch tối đa 100tr/ngày");
                        max_transaction = 10000000;
                    } else if (pr.get_type_account(getID_login_now()) == 3) {
                        System.out.println("Cấp độ tài khoản: Đồng");
                        System.out.println("Cần thêm " + format_money(50000000 - db.getBalance(getID_login_now()))
                                + " để nâng lên cấp độ Bạc và nhận thêm ưu đãi ");
                        System.out.println("Hiện tại bạn có thể rút tối đa 5tr/lần và giao dịch tối đa 50tr/ngày");
                        max_transaction = 5000000;
                    }
                    System.out.println("Enter để quay lại menu chính...");
                    input.nextLine();
                    // choice_profile();
                    break;
                case "4":

                    break;
                case "5":
                    clrscr();
                    String new_pass = "";
                    String new_pass2 = "";
                    String old_pass = "";
                    clrscr();
                    System.out.println("Mời nhập mã PIN cũ:");
                    old_pass = hide_pass(); // check mật khẩu cũ
                    if (old_pass.equals(pr.checkPassFromID((getID_login_now())))) {

                        System.out.println("Mời nhập mã PIN mới:");
                        while (true) {
                            new_pass = hide_pass();
                            if (valid.validate(new_pass) == true) {
                                System.out.println("Xác nhập mã PIN mới: ");
                                new_pass2 = hide_pass();
                                if (new_pass2 == new_pass) {
                                    pr.update(new_pass);
                                    System.out.println("Cập nhật mã PIN thành công, enter để trở về menu chính");
                                }

                            } else {
                                System.out.println("Không hợp lệ!");
                                input.nextLine();
                            }
                            break;
                        }
                    } else {
                        System.out.println("Nhập sai mật khẩu cũ, enter để quay lại");
                        input.nextLine();
                    }
                    break;
                case "6":
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
    }

    public static void first_login() throws SQLException, InterruptedException {
        clrscr();

        boolean check1 = false;
        boolean check2 = false;

        String password;
        // int login;
        Login_user lg = new Login_user();
        System.out.println("----------------------------");
        System.out.println("|   Project: ATM System    |");
        System.out.println("|   Class: PF10            |");
        System.out.println("|   Group 2                |");
        System.out.println("----------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Chào mừng đến với Ngân Hàng đầu tư và phát triển Bốc Bát Họ Bank (BBH)");
        // System.out.println("Version ATM : 0001");
        System.out.println("---------------------------------------------------------------------");
        while (check1 != true) {
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
                check1 = true;
            } else {
                System.out.println("Không phát hiện ID trong hệ thống! Mời nhập lại(enter để tiếp tục)");
                input.nextLine();
                clrscr();
                check1 = false;
            }

        }
        while (check2 != true) {
            password = hide_pass();
            check2 = lg.session_login(AccountNumber, password);
            if (check2 == false) {
                clrscr();
                System.out.println("Số lần nhập sai: " + lg.getCountLogin());
                if (lg.getCountLogin() > 3) {
                    System.out.println("Bạn đã bị khoá mẹ tài khoản, hack với chúng tôi à?");
                    lg.deactive(AccountNumber);
                    System.exit(0);
                }
            }
            Profile_user pr = new Profile_user();
            pr.set_type_account(getID_login_now());
            accountType = db.getAccountTypeID(getID_login_now());

            System.out.println("---------------------------------------------------------------------");

        }
        clrscr();

        menu_transaction();

    }

    public static void choice_profile() throws SQLException, InterruptedException {
        String choice;
        while (true) {

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
                                System.out.println(choice);
                            } else {
                                System.out.println("Không hợp lệ!");
                                input.nextLine();
                            }
                            break;
                        }
                    } else {
                        System.out.println("Nhập sai mật khẩu cũ...");
                        input.nextLine();
                    }
                    clrscr();
                    break;
                case "2":
                    System.out.println("Thời gian |Loại giao dịch|Số tiền");
                    db.GetHistoryTrade(getID_login_now());
                    input.nextLine();
                    break;
                case "3":
                    menu_transaction();
                    break;
                default:
                    break;

            }

        }
    }

    public static void withdraw() throws SQLException, InterruptedException {
        String choice = "";
        // int balance = db.getBalance(getID_login_now());
        long money_want_withdraw = 0;
        Withdraw wd = new Withdraw();
        while (true) {
            clrscr();
            long balance = db.getBalance(getID_login_now());
            // System.out.println("Số dư: " + format_money(balance));
            System.out.println("---------------------------------------------------------------------");
            System.out.println("1. 500.000đ");
            System.out.println("2. 1.000.000đ");
            System.out.println("3. 2.000.000đ");
            System.out.println("4. Số khác ( Bội số của 10.000 )");
            System.out.println("5. Main menu");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("#chọn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":

                    money_want_withdraw = 500000;
                    if (money_want_withdraw > money_in_ATM) {
                        System.out.println("Rất xin lỗi! ATM tạm thời không thể đáp ứng yêu cầu rút tiền! ");
                        menu_transaction();
                    } else {
                        if (wd.run(money_want_withdraw + 1100) == 1) {
                            System.out.println("Rút thành công!");
                            Thread.sleep(1000);
                            bill.front(getID_login_now());
                            // db.trade(getID_login_now(), fees, "T");
                            // db.trade(10, fees, "G");
                            money_in_ATM -= money_want_withdraw;
                            System.out.println("Nhấn phím bất kì để quay trở lại");
                            input.nextLine();
                            break;
                        } else {
                            System.out.println("Tài khoản không đủ số dư khả dụng");
                            Thread.sleep(1000);
                        }
                        break;
                    }

                case "2":
                    money_want_withdraw = 1000000;
                    if (money_want_withdraw > money_in_ATM) {
                        System.out.println("Rất xin lỗi! ATM tạm thời không thể đáp ứng yêu cầu rút tiền! ");
                        menu_transaction();

                    } else {
                        if (wd.run(money_want_withdraw + 1100) == 1) {
                            System.out.println("Rút thành công!");
                            Thread.sleep(1000);
                            bill.front(getID_login_now());
                            // db.trade(getID_login_now(), fees, "T");
                            // db.trade(10, fees, "G");
                            money_in_ATM -= money_want_withdraw;
                            System.out.println("Nhấn phím bất kì để quay trở lại");
                            input.nextLine();
                            break;
                        } else {
                            System.out.println("Lỗi! số tiền rút không khả dụng");
                            Thread.sleep(1000);
                        }
                        break;
                    }

                case "3":
                    money_want_withdraw = 2000000;
                    if (money_want_withdraw > money_in_ATM) {
                        System.out.println("Rất xin lỗi! ATM tạm thời không thể đáp ứng yêu cầu rút tiền! ");
                        menu_transaction();
                    } else {
                        if (wd.run(money_want_withdraw + 1100) == 1) {
                            System.out.println("Rút thành công!");
                            Thread.sleep(1000);
                            bill.front(getID_login_now());
                            // db.trade(getID_login_now(), fees, "T");
                            // db.trade(10, fees, "G");
                            money_in_ATM -= money_want_withdraw;
                            System.out.println("Nhấn phím bất kì để quay trở lại");
                            input.nextLine();
                            break;

                        }
                        break;
                    }

                case "4":
                    try {
                        money_want_withdraw = 0;
                        System.out.println("Mời nhập số tiền muốn rút (bội số của 500.000)");
                        money_want_withdraw = Integer.parseInt(input.nextLine());
                        if (money_want_withdraw > money_in_ATM) {
                            System.out.println("Rất xin lỗi! ATM tạm thời không thể đáp ứng yêu cầu rút tiền! ");
                            input.nextLine();
                            menu_transaction();
                        } else {
                            if (money_want_withdraw > max_transaction) {
                                System.out.println(
                                        "Bạn chỉ có thể rút tối đa " + format_money(max_transaction) + " một lần rút");
                                input.nextLine();

                            } else {
                                if (money_want_withdraw % 100000 == 0 && wd.run(money_want_withdraw + 1100) == 1) {
                                    System.out.println("Rút thành công");
                                    bill.front(getID_login_now());
                                    // db.trade(getID_login_now(), fees, "T");
                                    // db.trade(10, fees, "G");
                                    money_in_ATM -= money_want_withdraw;
                                    System.out.println("Nhấn phím bất kì để quay trở lại");
                                    input.nextLine();
                                    break;
                                } else {
                                    System.out.println("Lỗi! số tiền rút không khả dụng");
                                    Thread.sleep(1000);
                                }

                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Nhập không hợp lệ!");
                        input.nextLine();
                    }

                case "5":
                    menu_transaction();
                    break;

                default:
                    break;
            }
        }

    }

    public static void transfer() throws SQLException, InterruptedException {
        String transfer_toID = "";
        long money;
        String password;
        String choice;
        Login_user lg = new Login_user();
        Transfer trs = new Transfer();
        try {
            label1: while (true) {
                clrscr();
                System.out.println("Nhập ID muốn chuyển khoản");
                transfer_toID = input.nextLine();
                if (trs.check_accountNumber(transfer_toID) == 1 && transfer_toID.equals(AccountNumber) == false) {
                    while (true) {
                        System.out.println("Nhập số tiền: ");
                        money = Long.parseLong(input.nextLine());
                        if (money > 0 && money < db.getBalance(getID_login_now()) - 50000) {
                            System.out.println("Nhập mật khẩu xác nhận:");
                            password = hide_pass();
                            if (lg.check_password(AccountNumber, password) == 1) {

                                // if (condition) {

                                // } else {

                                // }

                                if (trs.run(transfer_toID, money) == 1) {
                                    System.out.println("Chuyển khoản thành công");
                                    bill.front_transfer(getID_login_now(), transfer_toID);
                                    input.nextLine();
                                    break;

                                }

                            } else {
                                System.out.println("Nhập sai mật khẩu, kết thúc phiên");
                                input.nextLine();
                                break label1;
                            }

                        } else {
                            System.out.println("Không thể giao dịch số tiền này");
                            break;

                        }
                    }
                    break;

                } else {
                    clrscr();
                    System.out.println("Không thể thực hiện chuyển tiền với tài khoản này");
                    System.out.println("1. Nhập lại ID");
                    System.out.println("2. Trở về menu chinhs");
                    System.out.println("#chọn: ");
                    choice = input.nextLine();
                    switch (choice) {
                        case "1":
                            break;
                        case "2":
                            menu_transaction();
                            break;

                        default:
                            break;
                    }
                }
            }

        } catch (java.lang.NumberFormatException e) {
            System.out.println("Nhập không hợp lệ!");
            System.out.println("Enter để trở về menu chính");
            input.nextLine();
        }
    }

}