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
    public static long money_in_ATM = 100000000;

    public static void menu_transaction() throws SQLException, InterruptedException {

        while (true) {
            clrscr();
            Profile_user pr = new Profile_user();
            String choice;
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("Investment banking and borrowing money | BHB                               ");
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%45s%-20s\n", "Please select transaction", "");
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%-12s\n", "|1. Withdraw cash       /|", "|2. Transfer           /|");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%-12s\n", "|3. Check balance       /|", "|4. Deposit cash       /|");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.printf("%-40s%-12s\n", "|5. Change PIN          /|", "|6. Exit               /|");
            System.out.printf("%-40s%s\n", " -------------------------", "-------------------------");
            System.out.println("------------------------------------------------------------------");
            System.out.println("#Choice: ");
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
                        System.out.println("Account level: GOLD");
                        System.out.println("Offer: Can withdraw up to 20 million VND / time, no limit transactions");
                        max_transaction = 20000000;
                    } else if (pr.get_type_account(getID_login_now()) == 2) {
                        System.out.println("Account level: SILVER");
                        System.out.println("It takes an extra  " + format_money(100000000 - db.getBalance(getID_login_now()))
                                + " to level up GOLD");
                        System.out.println(
                                "Silver level incentives: Can withdraw up to 10 million / time, can withdraw to 100 million / day");
                        max_transaction = 10000000;
                    } else if (pr.get_type_account(getID_login_now()) == 3) {
                        System.out.println("Account level: BRONZE");
                        System.out.println("It takes an extra " + format_money(50000000 - db.getBalance(getID_login_now()))
                                + " to level up SILVER ");
                        System.out.println("Currently you can withdraw up to 5 million VND / time and withdraw up to 50 million VND / day");
                        max_transaction = 5000000;
                    }
                    System.out.println("Enter to return main menu...");
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
                    old_pass = hide_pass(); // check mật khẩu cũ
                    if (old_pass.equals(pr.checkPassFromID((getID_login_now())))) {
                        clrscr();
                        System.out.println("Enter new PIN");
                        while (true) {
                            new_pass = hide_pass();
                            if (valid.validate(new_pass) == true) {
                                clrscr();
                                System.out.println("Confirm password: ");
                                new_pass2 = hide_pass();
                                if (new_pass2 == new_pass) {
                                    pr.update(new_pass);
                                    System.out.println("PIN successfully updated, enter to return to the main menu");
                                }
                                else{
                                    System.out.println("Invalid, return to the main menu...");
                                    Thread.sleep(1000);
                                }

                            } else {
                                System.out.println("Invalid!");
                                System.out.println("exit to main menu...");
                                Thread.sleep(1000);
                            }
                            break;
                        }
                    } else {
                        System.out.println("Enter wrong old password, enter to go back...");
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
        System.out.println("Welcome to investment banking and borrowing money: BAT HO BANK (BHB)");
        // System.out.println("Version ATM : 0001");
        System.out.println("---------------------------------------------------------------------");
        while (check1 != true) {
            System.out.println("Import ID: ");
            AccountNumber = input.nextLine();
            if (lg.first_log(AccountNumber) == 1) {
                clrscr();
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Welcome back: " + lg.getName(AccountNumber));

                if (lg.checkActive(AccountNumber) == 0) {
                    System.out.println("Sorry your account has been locked ");
                    System.out.println("Contact the admin to continue using the account");
                    System.exit(0);

                }
                System.out.println("---------------------------------------------------------------------");
                check1 = true;
            } else {
                System.out.println("ID not detected in the system! Please re-enter (enter to continue)");
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
                System.out.println("Number of wrong entries: " + lg.getCountLogin());
                if (lg.getCountLogin() > 3) {
                    System.out.println("You have been deactivated");
                    System.out.println("End of login session...");
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
            System.out.println("4. Other ( Multiples of 500.000 VND )");
            System.out.println("5. Main menu");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("#Choice: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                clrscr();
                    money_want_withdraw = 500000;
                    if (money_want_withdraw > money_in_ATM) {
                        System.out.println("So sorry! The ATM is temporarily unable to respond to withdrawal request! ");
                        menu_transaction();
                    } else {
                        if (wd.run(money_want_withdraw) == 1) {
                            System.out.println("Successful withdrawal!");
                            Thread.sleep(1000);
                            bill.front(getID_login_now());
                            // db.trade(getID_login_now(), fees, "T");
                            // db.trade(10, fees, "G");
                            money_in_ATM -= money_want_withdraw;
                            System.out.println("Enter to go back...");
                            input.nextLine();
                            break;
                        } else {
                            System.out.println("The account does not have sufficient free balance");
                            Thread.sleep(1000);
                        }
                        break;
                    }

                case "2":clrscr();
                    money_want_withdraw = 1000000;
                    if (money_want_withdraw > money_in_ATM) {
                        System.out.println("So sorry! The ATM is temporarily unable to respond to withdrawal request! ");
                        menu_transaction();

                    } else {
                        if (wd.run(money_want_withdraw) == 1) {
                            System.out.println("Successful withdrawal!");
                            Thread.sleep(1000);
                            bill.front(getID_login_now());
                            // db.trade(getID_login_now(), fees, "T");
                            // db.trade(10, fees, "G");
                            money_in_ATM -= money_want_withdraw;
                            System.out.println("Enter to go back...");
                            input.nextLine();
                            break;
                        } else {
                            System.out.println("The account does not have sufficient free balance");
                            Thread.sleep(1000);
                        }
                        break;
                    }

                case "3":clrscr();
                money_want_withdraw = 2000000;
                if (money_want_withdraw > money_in_ATM) {
                    System.out.println("So sorry! The ATM is temporarily unable to respond to withdrawal request! ");
                    menu_transaction();

                } else {
                    if (wd.run(money_want_withdraw) == 1) {
                        System.out.println("Successful withdrawal!");
                        Thread.sleep(1000);
                        bill.front(getID_login_now());
                        // db.trade(getID_login_now(), fees, "T");
                        // db.trade(10, fees, "G");
                        money_in_ATM -= money_want_withdraw;
                        System.out.println("Enter to go back...");
                        input.nextLine();
                        break;
                    } else {
                        System.out.println("The account does not have sufficient free balance");
                        Thread.sleep(1000);
                    }
                    break;
                }

                case "4":clrscr();
                    try {
                        money_want_withdraw = 0;
                        System.out.println("Please enter the amount you want to withdraw (multiple of 500.000 VND).");
                        money_want_withdraw = Integer.parseInt(input.nextLine());
                        if (money_want_withdraw > money_in_ATM) {
                            System.out.println("So sorry! The ATM is temporarily unable to respond to withdrawal request! ");
                            input.nextLine();
                            menu_transaction();
                        } else {
                            if (money_want_withdraw > max_transaction) {
                                System.out.println(
                                        "You can only withdraw up to " + format_money(max_transaction) + " per withdrawal");
                                input.nextLine();

                            } else {
                                if (money_want_withdraw % 100000 == 0 && wd.run(money_want_withdraw) == 1) {
                                    System.out.println("Withdraw successfully");
                                    bill.front(getID_login_now());
                                    // db.trade(getID_login_now(), fees, "T");
                                    // db.trade(10, fees, "G");
                                    money_in_ATM -= money_want_withdraw;
                                    System.out.println("Enter to go back...");
                                    input.nextLine();
                                    break;
                                } else {
                                    System.out.println("Error! Withdrawal amount is not available");
                                    Thread.sleep(1000);
                                }

                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Enter is not valid!");
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
                System.out.println("Enter the ID you want to transfer");
                transfer_toID = input.nextLine();
                if (trs.check_accountNumber(transfer_toID) == 1 && transfer_toID.equals(AccountNumber) == false) {
                    while (true) {
                        System.out.println("Enter amount: ");
                        money = Long.parseLong(input.nextLine());
                        if (money > 0 && money < db.getBalance(getID_login_now()) - 50000) {
                            System.out.println("Enter your PIN to confirm:");
                            password = hide_pass();
                            if (lg.check_password(AccountNumber, password) == 1) {

                                // if (condition) {

                                // } else {

                                // }

                                if (trs.run(transfer_toID, money) == 1) {
                                    System.out.println("Transfer successfully");
                                    bill.front_transfer(getID_login_now(), transfer_toID);
                                    input.nextLine();
                                    break;

                                }

                            } else {
                                System.out.println("Enter the wrong password, end the session");
                                input.nextLine();
                                break label1;
                            }

                        } else {
                            System.out.println("This amount cannot be traded");
                            break;

                        }
                    }
                    break;

                } else {
                    clrscr();
                    System.out.println("Transfer cannot be made with this ID");
                    System.out.println("1. Re-enter ID");
                    System.out.println("2. Return to the main menu");
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
            System.out.println("Enter is not valid!");
            System.out.println("Enter to return to the main menu");
            input.nextLine();
        }
    }

}