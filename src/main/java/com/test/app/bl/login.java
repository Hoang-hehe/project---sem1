package com.test.app.bl;

import java.util.Scanner;

import com.test.app.dal.Database;

public class login extends General {
    
    int login;
    public void log(String AccountNumber, String password){
        Database db = new Database();
        login = db.login(AccountNumber, password);
        if (login == 1) {
            System.out.println("Đăng nhập thành công!");
            setID_login_now(db.getID(AccountNumber));

        } else {
            System.out.println("Lỗi!, tài khoản không tồn tại, hoặc sai mật khẩu...");
        }
    }
    
}