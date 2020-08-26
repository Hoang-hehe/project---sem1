package com.test.app.bl;

import java.io.Console;
import java.util.Scanner;

import com.test.app.dal.Database;

public class General {
    static Scanner input = new Scanner(System.in);
    private static int ID_login_now;

    public static int getID_login_now() {
        return ID_login_now;
    }

    public void setID_login_now(int iD_login_now) {
        ID_login_now = iD_login_now;
    }
    public static String hide_pass() {
        String password = "";
        Console console = System.console();
        char[] pass = console.readPassword("Nhập password:     ");
        for (Character c : pass){
            password += c.toString();
        }
        return password;
    }

    
}