package com.test.app.persistance;

public class Account {
     
    private int Account_id;
    private String account_number;
    private String account_name;
    private String phone;
    private Boolean isactive;
    private String password;

    public Account(int account_id, String account_number, String account_name, String phone, Boolean isactive,
            String password) {
        Account_id = account_id;
        this.account_number = account_number;
        this.account_name = account_name;
        this.phone = phone;
        this.isactive = isactive;
        this.password = password;
    }

    public int getAccount_id() {
        return Account_id;
    }

    public void setAccount_id(int account_id) {
        Account_id = account_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String account_number, String password) {
        this.account_number = account_number;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account [Account_id=" + Account_id + ", account_name=" + account_name + ", account_number="
                + account_number + ", isactive=" + isactive + ", phone=" + phone + "]";
    }
    

   

}