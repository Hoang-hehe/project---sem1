package com.test.app.bl;

import java.util.Scanner;

import com.test.app.dal.Database;

public class General {
   
    static Scanner input = new Scanner(System.in);
    private static int ID_login_now;

    public int getID_login_now() {
        return ID_login_now;
    }

    public void setID_login_now(int iD_login_now) {
        ID_login_now = iD_login_now;
    }
}