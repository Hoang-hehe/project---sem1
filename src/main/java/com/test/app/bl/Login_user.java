package com.test.app.bl;

import com.test.app.dal.Database;

public class Login_user extends General {
    Database db = new Database();
    int checkAccountNumber;
    int login;
    private int countLogin = 0;

    public boolean session_login(String AccountNumber, String password) throws InterruptedException {
        login = db.login(AccountNumber, password);
        if (login == 1) {
            System.out.println("Logged in successfully!");
            setID_login_now(db.getID(AccountNumber)); // thao tác với ID khoá chính bằng cách get ra từ accountnumber(id_login_now)
            Thread.sleep(1000);
            return true;

        } else {
            System.out.println("Lỗi sai mật khẩu...");
            countLogin++;
            return false;
        }
    }

    public int first_log(String AccountNumber) {
        return checkAccountNumber = db.checkAccountNumber(AccountNumber);
    }

    public String getName(String AccountNumber) {
        return db.getNameToACTN(AccountNumber);

    }

    public void deactive(String AccountNumber) {
        db.lockAccount(AccountNumber);

    }

    public int checkActive(String AccountNumber) {
        return db.checkActive(AccountNumber);
    }

    public int getCountLogin() {
        return countLogin;
    }

    public int check_password(String accountNumber, String password) {
        if (db.login(accountNumber, password) == 1) {
            return 1;

        } else {
            return 0;
        }
    }

}