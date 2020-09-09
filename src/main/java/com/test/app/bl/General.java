package com.test.app.bl;

import java.io.Console;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


public class General {
    public Scanner input = new Scanner(System.in);
    private static int ID_login_now;
    public General(){}
    public General(int id ){
       ID_login_now = id;
    }
    public static int getID_login_now() {
        return ID_login_now;
    }

    public void setID_login_now(int iD_login_now) {
        ID_login_now = iD_login_now;
    }

    public static String hide_pass() {
        String password = "";
        Console console = System.console();
        char[] pass = console.readPassword("Enter PIN:     ");
        for (Character c : pass) {
            password += c.toString();
        }
        return password;
    }

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static String format_money(long l) {

        long money = l;
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String money_format = currencyVN.format(money);
        return money_format;
    }

}